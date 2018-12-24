package co.com.meeting.registrationmeetingsapp.model.entity;

import co.com.meeting.registrationmeetingsapp.utils.factory.constants.UserType;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

@Entity
@DiscriminatorValue(value = UserType.EMPLOYEE_TYPE)
public class Employee extends User implements Serializable {

	private static final long serialVersionUID = 2921096494409854214L;
	
	@Enumerated(EnumType.STRING)
	private Cargo cargo;
}
