package de.lmis.vhv.simplerest.business;

public class PersonNotFoundException extends RuntimeException {
    public PersonNotFoundException() {
        super("Person could no be found");
    }
}
