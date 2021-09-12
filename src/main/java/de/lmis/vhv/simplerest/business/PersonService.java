package de.lmis.vhv.simplerest.business;

import de.lmis.vhv.simplerest.model.Person;
import de.lmis.vhv.simplerest.persistence.PersonRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    private final PersonRepo personRepo;

    public PersonService(PersonRepo personRepo) {
        this.personRepo = personRepo;
    }

    public List<Person> getAllPersons() {
        return this.personRepo.getAllPersons();
    }

    public Person getPersonByLastName(final String lastName) {
        return this.personRepo.getPersonByLastName(lastName).orElseThrow(PersonNotFoundException::new);
    }

}
