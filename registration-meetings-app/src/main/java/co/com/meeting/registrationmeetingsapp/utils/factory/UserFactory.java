package co.com.meeting.registrationmeetingsapp.utils.factory;

import co.com.meeting.registrationmeetingsapp.api.v1.mapper.CustomerMapper;
import co.com.meeting.registrationmeetingsapp.api.v1.mapper.EmployeeMapper;
import co.com.meeting.registrationmeetingsapp.exception.BusinessException;
import co.com.meeting.registrationmeetingsapp.model.entity.Customer;
import co.com.meeting.registrationmeetingsapp.model.entity.Employee;
import co.com.meeting.registrationmeetingsapp.model.entity.User;
import co.com.meeting.registrationmeetingsapp.utils.factory.constants.UserType;
import org.springframework.stereotype.Component;

@Component
public class UserFactory {

	private final CustomerMapper customerMapper;
	private final EmployeeMapper employeeMapper;

	public UserFactory(CustomerMapper customerMapper, EmployeeMapper employeeMapper) {
		this.customerMapper = customerMapper;
		this.employeeMapper = employeeMapper;
	}

	public User getUser(String userType)  {

		switch (userType) {

			case UserType.CUSTOMER_TYPE:
				return new Customer();

			case UserType.EMPLOYEE_TYPE:
				return new Employee();

			default:
				throw new BusinessException("No existe un empleado de tipo " + userType);
		}
	}

}
