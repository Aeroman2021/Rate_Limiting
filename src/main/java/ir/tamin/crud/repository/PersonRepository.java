package ir.tamin.crud.repository;

import ir.tamin.crud.model.Person;
import ir.tamin.crud.model.PersonDto;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PersonRepository extends JpaRepository<Person,Long> {

}
