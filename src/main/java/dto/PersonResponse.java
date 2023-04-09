package dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.firstspring.firstspring.model.Address;
import com.firstspring.firstspring.model.Photo;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import validation.ValidEgn;

import java.util.Set;
import java.util.UUID;

@Data
@Builder
public class PersonResponse {
    private UUID id;
    private String name;
    private int age;
    private AddressDto address;
    private String egnNumber;

}
