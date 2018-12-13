package co.com.meeting.registrationmeetingsapp.service;

import co.com.meeting.registrationmeetingsapp.api.v1.model.dto.in.CustomerInDTO;
import co.com.meeting.registrationmeetingsapp.api.v1.model.dto.in.CustomerUpdateInDTO;
import co.com.meeting.registrationmeetingsapp.api.v1.model.dto.out.CustomerOutDTO;

import java.util.List;

public interface CustomerService {
	
	CustomerOutDTO registerCustomer(CustomerInDTO customerInDTO);
	
	List<CustomerOutDTO> findAllCustomers();
	
	CustomerOutDTO findCustomer(Long id);
	
	CustomerOutDTO update(Long id, CustomerUpdateInDTO customerUpdateInDTO);

	void delete(Long id);
	
	String findUserType(String identification);
	
}
