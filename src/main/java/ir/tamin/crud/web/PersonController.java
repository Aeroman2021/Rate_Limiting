package ir.tamin.crud.web;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import ir.tamin.crud.model.Person;
import ir.tamin.crud.model.PersonDto;
import ir.tamin.crud.model.PersonOutputDto;
import ir.tamin.crud.model.PersonRequestDto;
import ir.tamin.crud.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/person")

public class PersonController {

   @Autowired
   private PersonService personService;

   @PostMapping
   public ResponseEntity<Person> save(@RequestBody PersonDto personDto){
       Person result = personService.create(personDto);
       return new ResponseEntity<>(result,new HttpHeaders(), HttpStatus.OK);
   }

   @PutMapping
   public ResponseEntity<PersonDto> update(@RequestBody PersonDto personDto){
       PersonDto result = personService.update(personDto);
       return new ResponseEntity<>(result,new HttpHeaders(),HttpStatus.OK);
   }

    @GetMapping("/get_person_by_id/{id}")
    public ResponseEntity<PersonDto> findById(@PathVariable Long id){
        PersonDto result = personService.findPersonById(id);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @Operation(summary = "Get all Person", description = "fetch all persons")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok, successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Person.class)))) ,
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not found")})
    @GetMapping("/get_all_persons/{page}/{size}")
    public ResponseEntity<List<PersonOutputDto>> findAll(@PathVariable int page, @PathVariable int size){
        List<PersonOutputDto> result = personService.findAll(page, size);
        return new ResponseEntity<>(result,new HttpHeaders(),HttpStatus.OK);
    }

}
