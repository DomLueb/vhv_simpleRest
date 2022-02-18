package de.lmis.vhv.simplerest.api;

import de.lmis.vhv.simplerest.model.Person;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfiguration.class)
public interface PersonMapper {

    @Mapping(target = "ln", source = "lastName")
    @Mapping(target = "fn", source = "firstName")
    PersonDTO map(Person person);

    @Mapping(target = "password", ignore = true)
    @Mapping(target = "lastName", source = "ln")
    @Mapping(target = "firstName", source = "fn")
    Person map(PersonDTO personDTO);
}
