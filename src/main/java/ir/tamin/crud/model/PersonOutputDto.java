package ir.tamin.crud.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;

@Getter
@Setter
public class PersonOutputDto {

    @Id
    private Long id;
    private String lastName;
}
