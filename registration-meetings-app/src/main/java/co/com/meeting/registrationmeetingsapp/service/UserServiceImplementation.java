package co.com.meeting.registrationmeetingsapp.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import co.com.meeting.registrationmeetingsapp.exception.BusinessException;
import co.com.meeting.registrationmeetingsapp.model.entity.User;
import co.com.meeting.registrationmeetingsapp.repository.UserRepository;

@Service
public class UserServiceImplementation implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	protected Properties errorMessagesProperties;

	@Value("${error_messages_path}")
	protected String errorMessagesPath;

	@PostConstruct
	public void getErrorMessages() {
		try (FileInputStream sqlPropertiesFile = new FileInputStream((ResourceUtils.getFile(errorMessagesPath)));
				InputStreamReader readerPropertiesFile = new InputStreamReader(sqlPropertiesFile, "UTF-8");) {
			errorMessagesProperties.load(readerPropertiesFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected String getErrorMessage(String errorMessageName) {
		return errorMessagesProperties.getProperty(errorMessageName);
	}

	@Override
	public void registerUser(User user) {
		if (isUserAlreadyRegistered(user)) {
			throw new BusinessException(getErrorMessage("userAlreadyRegistered"));
		} else {
			saveUserInBD(user);
		}
	}
	
	private boolean isUserAlreadyRegistered(User user) {
		boolean userIsAlreadyRegisteredInBD = false;
		try {
			findUser(user.getIdentification());
			userIsAlreadyRegisteredInBD = true;
		} catch (BusinessException e) {
			// Es correcto, el usuario aún no existe en el sistema
		}

		return userIsAlreadyRegisteredInBD;
	}

	private User saveUserInBD(User user) {
		return userRepository.save(user);
	}

	@Override
	public List<User> findAllUsers() {
		List<User> allUsersInBD = userRepository.findAll();

		if (allUsersInBD.isEmpty()) {
			throw new BusinessException(getErrorMessage("zeroUsersInBD"));
		}
		return userRepository.findAll();
	}

	@Override
	public User findUser(String identification) {

		Optional<User> userFoundInBD = userRepository.findByIdentification(identification);
		if (userFoundInBD.isPresent()) {
			return userFoundInBD.get();
		} else {
			throw new BusinessException(getErrorMessage("userNotFound"));
		}
	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public String findUserType(String identification) {

		// Optional<List<User>> allUsersFoundInBD = userRepository.findAll();
		return null;
	}

}
