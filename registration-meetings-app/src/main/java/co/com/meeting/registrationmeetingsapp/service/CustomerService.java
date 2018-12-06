package co.com.meeting.registrationmeetingsapp.service;

import co.com.meeting.registrationmeetingsapp.api.v1.model.dto.in.CustomerRegistryInDTO;
import co.com.meeting.registrationmeetingsapp.api.v1.model.dto.out.CustomerInformationOutDTO;
import co.com.meeting.registrationmeetingsapp.model.entity.Customer;

import java.util.List;

public interface CustomerService {
	
	public void registerCustomer(CustomerRegistryInDTO customerRegistryInDTO);
	
	public List<CustomerInformationOutDTO> findAllCustomers();
	
	public CustomerInformationOutDTO findCustomer(String identification);
	
	public void update(Customer customer);
	
	public String findUserType(String identification);
	
}
