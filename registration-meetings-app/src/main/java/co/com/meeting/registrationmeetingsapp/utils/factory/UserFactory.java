package co.com.meeting.registrationmeetingsapp.utils.factory;

import co.com.meeting.registrationmeetingsapp.model.entity.Customer;
import co.com.meeting.registrationmeetingsapp.model.entity.Employee;
import co.com.meeting.registrationmeetingsapp.model.entity.User;
import co.com.meeting.registrationmeetingsapp.utils.factory.constants.UserType;

public class UserFactory {

	public User getUser(String userType) throws Exception {

		switch (userType) {

		case UserType.CUSTOMER_TYPE:
			return new Customer();

		case UserType.EMPLOYEE_TYPE:
			return new Employee();

		default:
			throw new Exception();
		}
	}

}
