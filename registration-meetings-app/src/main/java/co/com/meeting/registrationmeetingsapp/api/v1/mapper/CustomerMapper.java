package co.com.meeting.registrationmeetingsapp.api.v1.mapper;

import co.com.meeting.registrationmeetingsapp.api.v1.model.dto.in.CustomerRegistryInDTO;
import co.com.meeting.registrationmeetingsapp.api.v1.model.dto.out.CustomerInformationOutDTO;
import co.com.meeting.registrationmeetingsapp.model.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    Customer customerRegistryInDTOToCustomer(CustomerRegistryInDTO customerRegistryInDTO);
    CustomerRegistryInDTO customerToCustomerRegistryInDTO(Customer customer);
    CustomerInformationOutDTO customerToCustomerInformationOutDTO(Customer customer);
}
