package co.com.meeting.registrationmeetingsapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.com.meeting.registrationmeetingsapp.model.dto.in.UserRegistryInDTO;
import co.com.meeting.registrationmeetingsapp.model.dto.out.UserInformationOutDTO;
import co.com.meeting.registrationmeetingsapp.model.entity.Account;
import co.com.meeting.registrationmeetingsapp.model.entity.User;
import co.com.meeting.registrationmeetingsapp.service.UserService;
import co.com.meeting.registrationmeetingsapp.utils.factory.UserFactory;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@CrossOrigin(value = "*")
	@PostMapping(value = "/registerUser")
	public ResponseEntity<Void> registerUser(@RequestBody UserRegistryInDTO userDto) throws Exception {
		User user = createUserDependingOnType(userDto);
		fillUserFieldsFromUserDto(user, userDto);
		Account account = createAccountFromUserDto(userDto);
		associateAccountToUser(user, account);

		userService.registerUser(user);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@CrossOrigin(value = "*")
	@GetMapping(value = "/searchUser/{identification}")
	public ResponseEntity<UserInformationOutDTO> findUserByIdentification(
			@PathVariable("identification") String userIdentification) {
		User userFoundByIdentification = userService.findUser(userIdentification);

		UserInformationOutDTO userInformationOutDto = convertUserToUserInformationOutDto(userFoundByIdentification);

		return new ResponseEntity<>(userInformationOutDto, HttpStatus.OK);
	}

	private UserInformationOutDTO convertUserToUserInformationOutDto(User user) {
		UserInformationOutDTO userInformationOutDto = new UserInformationOutDTO();

		userInformationOutDto.setName(user.getName());
		userInformationOutDto.setLastName(user.getLastName());
		userInformationOutDto.setAge(user.getAge());
		userInformationOutDto.setIdentification(user.getIdentification());
		userInformationOutDto.setBirthdate(user.getBirthdate());
		userInformationOutDto.setType(user.getType());
		return userInformationOutDto;
	}

	private User createUserDependingOnType(UserRegistryInDTO userDto) throws Exception {
		UserFactory userFactory = new UserFactory();
		return userFactory.getUser(userDto.getUserType());
	}

	private void fillUserFieldsFromUserDto(User user, UserRegistryInDTO userDto) {
		user.setName(userDto.getName());
		user.setIdentification(userDto.getIdentification());
		user.setLastName(userDto.getLastName());
		user.setAge(userDto.getAge());
		user.setBirthdate(userDto.getBirthdate());
	}

	private Account createAccountFromUserDto(UserRegistryInDTO userDto) {
		Account account = new Account();
		account.setUsername(userDto.getUsername());
		account.setPassword(userDto.getPassword());
		return account;
	}

	private void associateAccountToUser(User user, Account account) {
		user.setAccount(account);
	}
}
