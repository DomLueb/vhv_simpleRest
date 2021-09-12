package de.lmis.vhv.simplerest.api;

import de.lmis.vhv.simplerest.business.PersonService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/persons")
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public List<PersonDTO> getAllPersons() {
        var persons = this.personService.getAllPersons();
        return persons.stream().map(p -> PersonDTO.builder()
                .fn(p.getFirstName()).ln(p.getLastName()).build())
                .collect(Collectors.toList());
    }

    @GetMapping("/{lastName}")
    public PersonDTO getPerson(@PathVariable String lastName) {
        var person=this.personService.getPersonByLastName(lastName);
        return PersonDTO.builder()
                .fn(person.getFirstName())
                .ln(person.getLastName())
                .build();
    }
}
