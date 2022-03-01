package de.lmis.vhv.simplerest.api.person;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PersonDTO {
    String fn;
    String ln;
}
