package co.com.meeting.registrationmeetingsapp.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import co.com.meeting.registrationmeetingsapp.utils.factory.constants.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "customer")
@DiscriminatorValue(value = UserType.CUSTOMER_TYPE)
public class Customer extends User implements Serializable{
	
	private static final long serialVersionUID = 1917051479398602353L;

	public Customer(Long id,
			 String identification,
			 String name,
			 String lastName,
			 String age,
			 Date birthDate,
			 String type, Account account) {

		super(id, identification, name, lastName, age, birthDate, type, account);
	}
}
