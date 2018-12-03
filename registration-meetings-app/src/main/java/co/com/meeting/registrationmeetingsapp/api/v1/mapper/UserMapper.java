package co.com.meeting.registrationmeetingsapp.api.v1.mapper;

import co.com.meeting.registrationmeetingsapp.api.v1.model.dto.out.UserInformationOutDTO;
import co.com.meeting.registrationmeetingsapp.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserInformationOutDTO userToUserInformationOutDTO(User user);
}
