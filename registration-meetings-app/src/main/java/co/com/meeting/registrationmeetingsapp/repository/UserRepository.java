package co.com.meeting.registrationmeetingsapp.repository;

import co.com.meeting.registrationmeetingsapp.model.entity.Account;
import co.com.meeting.registrationmeetingsapp.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface UserRepository<T extends User> extends JpaRepository<T, Long> {
	
	//La consulta en si misma sobra, debido a propiedad de SpringData.
	@Query("SELECT u FROM User u WHERE u.identification = :identification")
	Optional<T> findByIdentification(@Param("identification") String identification);

	@Modifying
	@Transactional
	@Query("UPDATE User u set u.identification = :identification, u.name = :name, " +
			"u.lastName = :last_name, u.age = :age, u.birthDate = :birthDate "
			+ "WHERE u.identification = :identification")
	void update(@Param("identification") String identification, @Param("name") String name,
			@Param("last_name") String lastName, @Param("age") String age, @Param("birthDate") String birthDate);
	
	@Query("SELECT u.type FROM User u WHERE u.identification = :identification")
	Optional<String> findUserType(@Param("identification") String identification);

	@Query("SELECT u.account FROM User u WHERE u.id = :id")
	Optional<Account> findAccountById(@Param("id") Long id);
}
