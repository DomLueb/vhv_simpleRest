package de.lmis.vhv.simplerest.business;

import de.lmis.vhv.simplerest.model.Person;
import de.lmis.vhv.simplerest.persistence.PersonRepo;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.Jwt;
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

    public void printCurrentUser() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var user = (Jwt) authentication.getPrincipal();
        System.out.println("Username: "+user.getClaimAsString("name"));
    }

    @Async
    public void longRunningTask() {
        System.out.println("Start longing task....");
        this.printCurrentUser();
        try {
            Thread.sleep(5*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("...finished");
    }
}
