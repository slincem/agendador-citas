package co.com.meeting.registrationmeetingsapp.api.v1.model.dto.in;

import co.com.meeting.registrationmeetingsapp.model.entity.Account;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class CustomerInDTO implements Serializable {

	private static final long serialVersionUID = 6038552632966804327L;
	private String identification;
	private String name;
	private String lastName;
	private String age;
	private Date birthDate;
	private Account account;

}