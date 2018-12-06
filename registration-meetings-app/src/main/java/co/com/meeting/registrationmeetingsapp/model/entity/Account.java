package co.com.meeting.registrationmeetingsapp.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
@Table(name = "account")
public class Account implements Serializable {

	private static final long serialVersionUID = 5035524697427522180L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String username;
	private String password;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_user", nullable = false)
	private User user;

}
