package de.lmis.vhv.simplerest.persistence;

import de.lmis.vhv.simplerest.model.Person;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PersonRepo {
    private List<Person> allPersons= List.of(
            Person.builder().firstName("Dominik").lastName("Lübbers").build(),
            Person.builder().firstName("Marco").lastName("Barenkamp").build(),
            Person.builder().firstName("Olaf").lastName("Scholz").build());

    public List<Person> getAllPersons() {
        return allPersons;
    }

    public Optional<Person> getPersonByLastName(final String lastName) {
        return this.allPersons.stream().filter(p -> p.getLastName().equalsIgnoreCase(lastName)).findFirst();
    }
}
