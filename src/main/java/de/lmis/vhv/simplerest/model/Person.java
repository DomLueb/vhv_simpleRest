package de.lmis.vhv.simplerest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Person {
    private String firstName;
    private String lastName;
}
