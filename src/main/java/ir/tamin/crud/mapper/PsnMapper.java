package ir.tamin.crud.mapper;

import ir.tamin.crud.model.Person;
import ir.tamin.crud.model.PersonDto;
import ir.tamin.crud.model.PersonOutputDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PsnMapper {

   PsnMapper Instance = Mappers.getMapper(PsnMapper.class);

   PersonDto modelToDto(Person person);

   PersonOutputDto modelToOutputDto(Person person);

   List<PersonDto> modelToDtos(List<Person> personList);

   @InheritInverseConfiguration
   Person DtoToModel(PersonDto person);

}
