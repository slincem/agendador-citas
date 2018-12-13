package co.com.meeting.registrationmeetingsapp.api.v1.model.dto.out;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
public class CustomerListOutDTO implements Serializable {
    private List<CustomerOutDTO> customers;
}
