package co.com.meeting.registrationmeetingsapp.service;

import co.com.meeting.registrationmeetingsapp.api.v1.mapper.CustomerMapper;
import co.com.meeting.registrationmeetingsapp.api.v1.model.dto.in.CustomerRegistryInDTO;
import co.com.meeting.registrationmeetingsapp.api.v1.model.dto.out.CustomerInformationOutDTO;
import co.com.meeting.registrationmeetingsapp.exception.BusinessException;
import co.com.meeting.registrationmeetingsapp.exception.ResourceNotFoundException;
import co.com.meeting.registrationmeetingsapp.exception.messages.ErrorMessage;
import co.com.meeting.registrationmeetingsapp.model.entity.Customer;
import co.com.meeting.registrationmeetingsapp.model.entity.User;
import co.com.meeting.registrationmeetingsapp.repository.CustomerRepository;
import co.com.meeting.registrationmeetingsapp.utils.MessageSourceUtil;
import lombok.extern.slf4j.Slf4j;
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
	public void registerCustomer(CustomerRegistryInDTO customerRegistryInDTO) {

		Customer customer = customerMapper.customerRegistryInDTOToCustomer(customerRegistryInDTO);

		validateIfUserIsAlreadyRegistered(customer);
		saveCustomerInBD(customer);
	}

	private void validateIfUserIsAlreadyRegistered(Customer customer) {
		try {
			findCustomer(customer.getIdentification());
		} catch (ResourceNotFoundException e) {
			log.info("Usuario con identificación {} no existe, es posible registrarlo", customer.getIdentification());
			return;
		}

		log.error("El usuario con identificación {}, ya se encuentra registrado", customer.getIdentification());
		throw new BusinessException(messageSource.buildMessage(ErrorMessage.USER_IS_ALREADY_REGISTERED));
	}

	private User saveCustomerInBD(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public List<CustomerInformationOutDTO> findAllCustomers() {

		List<Customer> allCustomersInBD = customerRepository.findAll();

		return allCustomersInBD.parallelStream()
				.map(customerMapper::customerToCustomerInformationOutDTO)
				.collect(Collectors.toList());
	}

	@Override
	public CustomerInformationOutDTO findCustomer(String identification) {

		Optional<Customer> customerFoundInBD = customerRepository.findByIdentification(identification);

		return customerFoundInBD
				.map(customerMapper::customerToCustomerInformationOutDTO)
				.orElseThrow( () -> {
					log.error("El usuario con identificacion {} no se encuentra registrado", identification);
					return new ResourceNotFoundException(messageSource.buildMessage(ErrorMessage.USER_NOT_FOUND));
				});
	}

	@Override
	public void update(Customer customer) {
		// TODO Auto-generated method stub

	}

	@Override
	public String findUserType(String identification) {

		return null;
	}

}
