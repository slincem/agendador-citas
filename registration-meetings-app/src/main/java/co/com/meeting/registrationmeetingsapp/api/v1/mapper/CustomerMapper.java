package co.com.meeting.registrationmeetingsapp.api.v1.mapper;

import co.com.meeting.registrationmeetingsapp.api.v1.model.dto.in.CustomerInDTO;
import co.com.meeting.registrationmeetingsapp.api.v1.model.dto.in.CustomerUpdateInDTO;
import co.com.meeting.registrationmeetingsapp.api.v1.model.dto.out.CustomerOutDTO;
import co.com.meeting.registrationmeetingsapp.model.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    Customer customerRegistryInDTOToCustomer(CustomerInDTO customerInDTO);
    CustomerInDTO customerToCustomerInDTO(Customer customer);
    CustomerOutDTO customerToCustomerOutDTO(Customer customer);
    Customer customerUpdateInDTOToCustomer(CustomerUpdateInDTO customerUpdateInDTO);
}
