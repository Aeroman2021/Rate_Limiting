package ir.tamin.crud.convertor;

import ir.tamin.crud.model.Person;
import ir.tamin.crud.model.PersonDto;
import ir.tamin.crud.model.PersonRequestDto;
import org.springframework.stereotype.Component;

@Component
public class PersonConvertor {

    public PersonDto entityToDto(Person person){
        PersonDto personDto = new PersonDto();

        if(person.getId() != null){
            personDto.setId(person.getId());
        }

        if(person.getFirstName() != null)
            personDto.setFirstName(person.getFirstName());

        if(person.getLastName() != null)
            personDto.setLastName(person.getLastName());

        if(person.getAge() != null)
            personDto.setAge(person.getAge());

        return personDto;
    }


    public Person dtoToEntity( PersonDto personDto){
        Person person = new Person();

        if(personDto.getId() != null){
            person.setId(personDto.getId());
        }

        if(personDto.getFirstName() != null){
            person.setFirstName(personDto.getFirstName());
        }

        if(personDto.getLastName() != null){
            person.setLastName(personDto.getLastName());
        }

        if(personDto.getAge() != null){
            person.setAge(personDto.getAge());
        }

        return person;
    }




}
