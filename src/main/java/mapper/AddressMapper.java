package mapper;

import ch.qos.logback.core.model.ComponentModel;
import com.firstspring.firstspring.model.Address;
import dto.AddressDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AddressMapper {



    AddressDto modelRoDto(Address address);

    Address dtoModel(AddressDto addressDto);



}
