package co.com.meeting.registrationmeetingsapp.api.v1.mapper;

import co.com.meeting.registrationmeetingsapp.CustomerTestDataBuilder;
import co.com.meeting.registrationmeetingsapp.api.v1.model.dto.out.UserInformationOutDTO;
import co.com.meeting.registrationmeetingsapp.model.entity.User;
import org.junit.Assert;
import org.junit.Test;

public class UserMapperTest {

    UserMapper userMapper = UserMapper.INSTANCE;

    @Test
    public void CustomerUserToUserInformationOutDTO() {

        // arrange
        User user = new CustomerTestDataBuilder().build();

        // act
        UserInformationOutDTO userInformationOutDTO = userMapper.INSTANCE.userToUserInformationOutDTO(user);

        // assert
        Assert.assertEquals(user.getBirthDate(), userInformationOutDTO.getBirthDate());

    }
}
