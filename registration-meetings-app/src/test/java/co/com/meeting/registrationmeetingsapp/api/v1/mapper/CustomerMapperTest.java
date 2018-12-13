package co.com.meeting.registrationmeetingsapp.api.v1.mapper;

import co.com.meeting.registrationmeetingsapp.CustomerTestDataBuilder;
import co.com.meeting.registrationmeetingsapp.api.v1.model.dto.in.CustomerInDTO;
import co.com.meeting.registrationmeetingsapp.model.entity.Customer;
import org.junit.Assert;
import org.junit.Test;

public class CustomerMapperTest {

    CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    @Test
    public void CustomerUserToUserInformationOutDTO() {

        // arrange
        Customer customer = new CustomerTestDataBuilder().build();

        // act
        CustomerInDTO customerInformationOutDTO = customerMapper.INSTANCE.customerToCustomerInDTO(customer);

        // assert
        Assert.assertEquals(customer.getName(), customerInformationOutDTO.getName());
        Assert.assertEquals(customer.getLastName(), customerInformationOutDTO.getLastName());
        Assert.assertEquals(customer.getIdentification(), customerInformationOutDTO.getIdentification());
        Assert.assertEquals(customer.getAge(), customerInformationOutDTO.getAge());
        Assert.assertEquals(customer.getBirthDate(), customerInformationOutDTO.getBirthDate());

    }
}
