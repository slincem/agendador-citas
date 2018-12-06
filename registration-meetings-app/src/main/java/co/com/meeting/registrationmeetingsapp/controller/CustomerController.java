package co.com.meeting.registrationmeetingsapp.controller;

import co.com.meeting.registrationmeetingsapp.api.v1.model.dto.in.CustomerRegistryInDTO;
import co.com.meeting.registrationmeetingsapp.api.v1.model.dto.out.CustomerInformationOutDTO;
import co.com.meeting.registrationmeetingsapp.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

	private final CustomerService customerService;

	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}

	@CrossOrigin(value = "*")
	@PostMapping(value = "/registerCustomer")
	public ResponseEntity<Void> registerCustomer(@RequestBody CustomerRegistryInDTO customerRegistryInDTO) {
		customerService.registerCustomer(customerRegistryInDTO);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@CrossOrigin(value = "*")
	@GetMapping(value = "/searchCustomer/{identification}")
	public ResponseEntity<CustomerInformationOutDTO> findCustomerByIdentification(
			@PathVariable("identification") String customerIdentification) {
		CustomerInformationOutDTO customerFoundByIdentification = customerService.findCustomer(customerIdentification);

		return new ResponseEntity<>(customerFoundByIdentification, HttpStatus.OK);
	}

	@CrossOrigin(value = "*")
	@GetMapping(value = "/searchCustomers")
	public ResponseEntity<List<CustomerInformationOutDTO>> findAllCustomers() {

		List<CustomerInformationOutDTO> allCustomersFound = customerService.findAllCustomers();

		return new ResponseEntity<>(allCustomersFound, HttpStatus.OK);
	}

	
	@CrossOrigin(value = "*")
	@PutMapping(value = "/deleteCustomer")
	public ResponseEntity<Void> deleteCustomerByIdentification(@RequestBody String customerIdentification) {

		return null;
	}

}
