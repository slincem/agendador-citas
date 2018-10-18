package co.com.meeting.registrationmeetingsapp.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.com.meeting.registrationmeetingsapp.model.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	//La consulta en si misma sobra, debido a propiedad de SpringData.
	@Query("SELECT u FROM User u WHERE u.identification = :identification")
	public Optional<User> findByIdentification(@Param("identification") String identification);

	@Modifying
	@Transactional
	@Query("UPDATE User u set u.identification = :identification, u.name = :name, u.lastName = :last_name, u.age = :age, u.birthdate = :birthdate "
			+ "WHERE u.identification = :identification")
	public void update(@Param("identification") String identification, @Param("name") String name,
			@Param("last_name") String lastName, @Param("age") String age, @Param("birthdate") String birthdate);
	
	@Query("SELECT u.type FROM User u WHERE u.identification = :identification")
	public Optional<String> findUserType(@Param("identification") String identification);
}
