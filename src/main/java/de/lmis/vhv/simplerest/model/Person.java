package de.lmis.vhv.simplerest.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Data
@Builder
@AllArgsConstructor
public class Person {
    private String firstName;
    private String lastName;
    private String password;
}
