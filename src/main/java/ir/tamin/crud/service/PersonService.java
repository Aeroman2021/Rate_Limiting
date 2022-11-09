package ir.tamin.crud.service;


import ir.tamin.crud.convertor.PersonConvertor;
import ir.tamin.crud.exception.EmptyListException;
import ir.tamin.crud.exception.PersonNotFoundException;
import ir.tamin.crud.mapper.PsnMapper;
import ir.tamin.crud.model.Person;
import ir.tamin.crud.model.PersonDto;
import ir.tamin.crud.model.PersonOutputDto;
import ir.tamin.crud.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service

public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonConvertor personConvertor;



    public Person create(PersonDto personDto){
        Person person = PsnMapper.Instance.DtoToModel(personDto);
        return personRepository.save(person);
    }

    public PersonDto update(PersonDto personDto) {

        Optional<Person> foundPerson = personRepository.findById(personDto.getId());

        if(!foundPerson.isPresent()){
            throw new EntityNotFoundException("Entity does not Exist.");
        }

        Person person = new Person();

        person.setId(personDto.getId());

        if(personDto.getFirstName() != null){
            person.setFirstName(personDto.getFirstName() );
        }

        if(personDto.getLastName() != null){
            person.setLastName(personDto.getLastName() );
        }

        if(personDto.getAge()  != null){
            person.setAge(personDto.getAge() );
        }

        return personConvertor.entityToDto(personRepository.saveAndFlush(person));

    }

    public PersonDto findPersonById(Long id){
        Optional<Person> foundPerson = personRepository.findById(id);
        if(foundPerson.isPresent()){
            return personConvertor.entityToDto(foundPerson.get());
        }else
            throw new PersonNotFoundException(id);
    }

    public List<PersonOutputDto> findAll(int page,int size){
        List<PersonOutputDto> resultList = new ArrayList<>();

        List<Person> content = personRepository.findAll();
        for (Person person : content) {
            PersonOutputDto personOutputDto = PsnMapper.Instance.modelToOutputDto(person);
            resultList.add(personOutputDto);
        }
        return resultList;
    }


    public void delete(Long id){
        personRepository.deleteById(id);
    }
}
