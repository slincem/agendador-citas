package co.com.meeting.registrationmeetingsapp.service;

import co.com.meeting.registrationmeetingsapp.api.v1.mapper.CustomerMapper;
import co.com.meeting.registrationmeetingsapp.api.v1.model.dto.in.CustomerInDTO;
import co.com.meeting.registrationmeetingsapp.api.v1.model.dto.in.CustomerUpdateInDTO;
import co.com.meeting.registrationmeetingsapp.api.v1.model.dto.out.CustomerOutDTO;
import co.com.meeting.registrationmeetingsapp.exception.BusinessException;
import co.com.meeting.registrationmeetingsapp.exception.ResourceNotFoundException;
import co.com.meeting.registrationmeetingsapp.exception.messages.ErrorMessage;
import co.com.meeting.registrationmeetingsapp.model.entity.Account;
import co.com.meeting.registrationmeetingsapp.model.entity.Customer;
import co.com.meeting.registrationmeetingsapp.repository.CustomerRepository;
import co.com.meeting.registrationmeetingsapp.utils.MessageSourceUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

	private final CustomerRepository customerRepository;
	private final CustomerMapper customerMapper;
	private final MessageSourceUtil messageSource;

	public CustomerServiceImpl(CustomerRepository userRepository, CustomerMapper customerMapper, MessageSourceUtil messageSource) {
		this.customerRepository = userRepository;
		this.customerMapper = customerMapper;
		this.messageSource = messageSource;
	}

	@Override
	public CustomerOutDTO registerCustomer(CustomerInDTO customerInDTO) {
		Customer customer = customerMapper.customerRegistryInDTOToCustomer(customerInDTO);
		if(isUserIsAlreadyRegistered(customer.getIdentification())) {
			log.error("El usuario con identificación {}, ya se encuentra registrado", customer.getIdentification());
			throw new BusinessException(messageSource.buildMessage(ErrorMessage.USER_IS_ALREADY_REGISTERED));
		}
		return saveAndReturnCustomerOutDTO(customer);
	}

	private CustomerOutDTO saveAndReturnCustomerOutDTO(Customer customer) {
		customer = saveCustomerInBD(customer);
		return customerMapper.customerToCustomerOutDTO(customer);
	}

	private boolean isUserIsAlreadyRegistered(Long id) {
		return findCustomerById(id).isPresent();
	}

	private boolean isUserIsAlreadyRegistered(String identification) {
		return findCustomerByIdentification(identification).isPresent();
	}

	private Customer saveCustomerInBD(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public List<CustomerOutDTO> findAllCustomers() {

		List<Customer> allCustomersInBD = customerRepository.findAll();

		return allCustomersInBD.parallelStream()
				.map(customerMapper::customerToCustomerOutDTO)
				.collect(Collectors.toList());
	}

	@Override
	public CustomerOutDTO findCustomer(Long id) {

		Optional<Customer> customerFoundInBD = findCustomerById(id);

		return convertCustomerToCustomerOutDTO(customerFoundInBD)
				.orElseThrow( () -> {
					log.error("El usuario con id {} no se encuentra registrado", id);
					return new ResourceNotFoundException(messageSource.buildMessage(ErrorMessage.USER_NOT_FOUND));
				});
	}

	private Optional<Customer> findCustomerById(Long id) {
		return customerRepository.findById(id);
	}

	@Override
	public CustomerOutDTO findCustomer(String identification) {

		Optional<Customer> customerFoundInBD = findCustomerByIdentification(identification);

		return convertCustomerToCustomerOutDTO(customerFoundInBD)
				.orElseThrow( () -> {
					log.error("El usuario con identificacion {} no se encuentra registrado", identification);
					return new ResourceNotFoundException(messageSource.buildMessage(ErrorMessage.USER_NOT_FOUND));
				});
	}

	private Optional<Customer> findCustomerByIdentification(String identification) {
		return customerRepository.findByIdentification(identification);
	}

	private Optional<CustomerOutDTO> convertCustomerToCustomerOutDTO(Optional<Customer> customerFoundInBD) {
		return customerFoundInBD
				.map(customerMapper::customerToCustomerOutDTO);
	}

	@Override
	public CustomerOutDTO update(Long id, CustomerUpdateInDTO customerUpdateInDTO) {

		if(!isUserIsAlreadyRegistered(id)) {
			throw new ResourceNotFoundException(messageSource.buildMessage(ErrorMessage.USER_NOT_FOUND));
		}

		Customer customer = customerMapper.customerUpdateInDTOToCustomer(customerUpdateInDTO);
		Account customerAccount = findUserAccount(id);
		customer.setId(id);
		customer.setAccount(customerAccount);
		return saveAndReturnCustomerOutDTO(customer);
	}

	private Account findUserAccount(Long id) {
		return customerRepository.findAccountById(id).orElse(new Account());
	}

	@Override
	public void delete(Long id) {
		try {
			customerRepository.deleteById(id);
		} catch (EmptyResultDataAccessException ex) {
			log.debug("Se intento eliminar al usuario con {}, el cual no existe en el sistema", id);
		}
	}

	@Override
	public String findUserType(String identification) {

		return null;
	}

}
