package co.com.meeting.registrationmeetingsapp.service;

import co.com.meeting.registrationmeetingsapp.api.v1.mapper.CustomerMapper;
import co.com.meeting.registrationmeetingsapp.api.v1.model.dto.in.CustomerRegistryInDTO;
import co.com.meeting.registrationmeetingsapp.api.v1.model.dto.out.CustomerInformationOutDTO;
import co.com.meeting.registrationmeetingsapp.exception.BusinessException;
import co.com.meeting.registrationmeetingsapp.model.entity.Customer;
import co.com.meeting.registrationmeetingsapp.model.entity.User;
import co.com.meeting.registrationmeetingsapp.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

	private final CustomerRepository customerRepository;
	private final CustomerMapper customerMapper;

	private Properties errorMessagesProperties;

	@Value("${error_messages_path}")
	protected String errorMessagesPath;

	public CustomerServiceImpl(CustomerRepository userRepository, CustomerMapper customerMapper) {
		this.customerRepository = userRepository;
		this.customerMapper = customerMapper;
	}

	@PostConstruct
	public void getErrorMessages() {
		try (FileInputStream sqlPropertiesFile = new FileInputStream((ResourceUtils.getFile(errorMessagesPath)));
			 InputStreamReader readerPropertiesFile = new InputStreamReader(sqlPropertiesFile, StandardCharsets.UTF_8)) {

			errorMessagesProperties = new Properties();
			errorMessagesProperties.load(readerPropertiesFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected String getErrorMessage(String errorMessageName) {
		return errorMessagesProperties.getProperty(errorMessageName);
	}

	@Override
	public void registerCustomer(CustomerRegistryInDTO customerRegistryInDTO) {

		Customer customer = customerMapper.customerRegistryInDTOToCustomer(customerRegistryInDTO);

		if (isCustomerAlreadyRegistered(customer)) {
			throw new BusinessException(getErrorMessage("userAlreadyRegistered"));
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
		List<Customer> allUsersInBD = customerRepository.findAll();

		if (allUsersInBD.isEmpty()) {
			throw new BusinessException(getErrorMessage("zeroUsersInBD"));
		}
		return allUsersInBD.parallelStream()
				.map(customer -> customerMapper.customerToCustomerInformationOutDTO(customer))
				.collect(Collectors.toList());
	}

	@Override
	public CustomerInformationOutDTO findCustomer(String identification) {

		Optional<Customer> customerFoundInBD = customerRepository.findByIdentification(identification);
		if (customerFoundInBD.isPresent()) {
			return customerMapper.customerToCustomerInformationOutDTO(customerFoundInBD.get());
		} else {
			throw new BusinessException(getErrorMessage("userNotFound"));
		}
	}

	@Override
	public void update(Customer customer) {
		// TODO Auto-generated method stub

	}

	@Override
	public String findUserType(String identification) {

		// Optional<List<User>> allUsersFoundInBD = userRepository.findAll();
		return null;
	}

}
