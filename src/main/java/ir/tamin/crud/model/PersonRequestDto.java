package ir.tamin.crud.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
public class PersonRequestDto {

    private String firstName;
    private String lastName;
    private Integer age;

    public PersonRequestDto(String firstName, String lastName, Integer age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public PersonRequestDto() {
    }


}
