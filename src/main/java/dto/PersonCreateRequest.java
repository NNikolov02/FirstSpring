package dto;

import com.firstspring.firstspring.model.Address;
import org.hibernate.validator.constraints.Range;

import lombok.Builder;
import lombok.Data;
import validation.ValidEgn;

@Data
@Builder
public class PersonCreateRequest {

    private String name;
    @Range(min = 0, max = 200, message = "i like ages from 0 to 200")
    private int age;

    private Address address;

    @ValidEgn(message = "Persons EGN should have exactly 10 chars")
    private String egnNumber;

}