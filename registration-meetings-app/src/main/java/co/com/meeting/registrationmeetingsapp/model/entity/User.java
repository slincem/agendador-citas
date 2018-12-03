package co.com.meeting.registrationmeetingsapp.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type")
public abstract class User implements Serializable {

    private static final long serialVersionUID = -3373109788208364741L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_user", updatable = false, nullable = false)
    private Long id;

    @Column(unique = true)
    private String identification;

    private String name;

    @Column(name = "last_name")
    private String lastName;

    private String age;

    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @Column(name = "user_type", updatable = false, insertable = false)
    private String type;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Account account;

}
