package co.com.meeting.registrationmeetingsapp.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "account")
public class Account implements Serializable {

	private static final long serialVersionUID = 5035524697427522180L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String username;
	private String password;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_user", nullable = false)
	private User user;

}
