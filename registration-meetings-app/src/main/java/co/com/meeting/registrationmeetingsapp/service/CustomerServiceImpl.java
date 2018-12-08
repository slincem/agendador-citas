package co.com.meeting.registrationmeetingsapp.service;

import co.com.meeting.registrationmeetingsapp.api.v1.mapper.CustomerMapper;
import co.com.meeting.registrationmeetingsapp.api.v1.model.dto.in.CustomerRegistryInDTO;
import co.com.meeting.registrationmeetingsapp.api.v1.model.dto.out.CustomerInformationOutDTO;
import co.com.meeting.registrationmeetingsapp.exception.BusinessException;
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

		if (isCustomerAlreadyRegistered(customer)) {
			throw new BusinessException(messageSource.buildMessage(ErrorMessage.USER_IS_ALREADY_REGISTERED));
		} else {
			saveCustomerInBD(customer);
		}
	}

	private boolean isCustomerAlreadyRegistered(Customer customer) {
		boolean customerIsAlreadyRegisteredInBD = false;
		try {
			findCustomer(customer.getIdentification());
			customerIsAlreadyRegisteredInBD = true;
		} catch (BusinessException e) {
			// Es correcto, el usuario aún no existe en el sistema
			log.info("Usuario no existe, es posible registralo");
		}

		return customerIsAlreadyRegisteredInBD;
	}

	private User saveCustomerInBD(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public List<CustomerInformationOutDTO> findAllCustomers() {

		List<Customer> allCustomersInBD = customerRepository.findAll();

		if (allCustomersInBD.isEmpty()) {
			throw new BusinessException(messageSource.buildMessage(ErrorMessage.NO_REGISTERED_USERS_FOUND));
		}
		return allCustomersInBD.parallelStream()
				.map(customerMapper::customerToCustomerInformationOutDTO)
				.collect(Collectors.toList());
	}

	@Override
	public CustomerInformationOutDTO findCustomer(String identification) {

		Optional<Customer> customerFoundInBD = customerRepository.findByIdentification(identification);
		if (customerFoundInBD.isPresent()) {
			return customerMapper.customerToCustomerInformationOutDTO(customerFoundInBD.get());
		} else {
			throw new BusinessException(messageSource.buildMessage(ErrorMessage.USER_NOT_FOUND));
		}
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
