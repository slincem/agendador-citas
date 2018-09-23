package co.com.meeting.registrationmeetingsapp.model.entity;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import co.com.meeting.registrationmeetingsapp.utils.factory.constants.UserType;

@Entity
@Table(name = "customer")
@DiscriminatorValue(value = UserType.CUSTOMER_TYPE)
public class Customer extends User implements Serializable{
	
	private static final long serialVersionUID = 1917051479398602353L;
	
}
