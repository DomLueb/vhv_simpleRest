package de.lmis.vhv.simplerest.api;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PersonDTO {
    String fn;
    String ln;
}
