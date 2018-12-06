package co.com.meeting.registrationmeetingsapp.api.v1.model.dto.out;

import lombok.Data;

import java.util.Date;

@Data
public class CustomerInformationOutDTO {
	
	private String identification;
	private String name;
	private String lastName;
	private String age;
	private Date birthDate;
	private String type;
}
