package ir.tamin.crud.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;

@Getter
@Setter
public class PersonDto {

    @Id
    private Long id;

    private String firstName;

    private String lastName;

    private Integer age;

}
