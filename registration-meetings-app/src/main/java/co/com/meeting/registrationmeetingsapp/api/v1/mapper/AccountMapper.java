package co.com.meeting.registrationmeetingsapp.api.v1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountMapper {

    AccountMapper accountMapper = Mappers.getMapper(AccountMapper.class);



}
