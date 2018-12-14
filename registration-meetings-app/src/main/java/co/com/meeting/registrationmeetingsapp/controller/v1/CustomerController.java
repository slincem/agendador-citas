package co.com.meeting.registrationmeetingsapp.controller.v1;

import co.com.meeting.registrationmeetingsapp.api.v1.model.dto.in.CustomerInDTO;
import co.com.meeting.registrationmeetingsapp.api.v1.model.dto.in.CustomerUpdateInDTO;
import co.com.meeting.registrationmeetingsapp.api.v1.model.dto.out.CustomerListOutDTO;
import co.com.meeting.registrationmeetingsapp.api.v1.model.dto.out.CustomerOutDTO;
import co.com.meeting.registrationmeetingsapp.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("v1/customers")
public class CustomerController {

	private final CustomerService customerService;

	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}

	@CrossOrigin(value = "*")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CustomerOutDTO registerCustomer(@RequestBody CustomerInDTO customerInDTO) {

		return customerService.registerCustomer(customerInDTO);
	}

	@CrossOrigin(value = "*")
	@GetMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<CustomerOutDTO> findCustomerById(
			@PathVariable Long id) {
		CustomerOutDTO customerFoundByIdentification = customerService.findCustomer(id);

		return new ResponseEntity<>(customerFoundByIdentification, HttpStatus.OK);
	}

	@CrossOrigin(value = "*")
	@GetMapping(value = "/identification/{identification}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<CustomerOutDTO> findCustomerByIdentification(
			@PathVariable String identification) {
		CustomerOutDTO customerFoundByIdentification = customerService.findCustomer(identification);

		return new ResponseEntity<>(customerFoundByIdentification, HttpStatus.OK);
	}

	@CrossOrigin(value = "*")
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public CustomerListOutDTO findAllCustomers() {

		List<CustomerOutDTO> allCustomersFound = customerService.findAllCustomers();

		return new CustomerListOutDTO(allCustomersFound);
	}

	@CrossOrigin(value = "*")
	@PutMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public CustomerOutDTO updateCustomer(@PathVariable Long id, @RequestBody CustomerUpdateInDTO customerUpdateInDTO) {
		return customerService.update(id, customerUpdateInDTO);
	}

	@CrossOrigin(value = "*")
	@DeleteMapping(value = "/{id}")
	public void deleteCustomerByIdentification(@PathVariable Long id) {
		customerService.delete(id);
	}

}
