package mapper;

import com.firstspring.firstspring.model.Person;
import dto.PersonCreateRequest;
import dto.PersonResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;


@Mapper(uses = AddressMapper.class)
public interface PersonMapper {


    Person modelFromCreateRequest(PersonCreateRequest personCreateDto);

    PersonResponse responseFromModel(Person person);
}


