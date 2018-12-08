package co.com.meeting.registrationmeetingsapp.service;

import co.com.meeting.registrationmeetingsapp.api.v1.model.dto.in.CustomerRegistryInDTO;
import co.com.meeting.registrationmeetingsapp.api.v1.model.dto.out.CustomerInformationOutDTO;
import co.com.meeting.registrationmeetingsapp.model.entity.Customer;

import java.util.List;

public interface CustomerService {
	
	void registerCustomer(CustomerRegistryInDTO customerRegistryInDTO);
	
	List<CustomerInformationOutDTO> findAllCustomers();
	
	CustomerInformationOutDTO findCustomer(String identification);
	
	void update(Customer customer);
	
	String findUserType(String identification);
	
}
