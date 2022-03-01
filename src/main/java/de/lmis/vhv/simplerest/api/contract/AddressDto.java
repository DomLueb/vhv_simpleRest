package de.lmis.vhv.simplerest.api.contract;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
@Builder
public class AddressDto {
    String street;
    String number;
    String zipCode;
    @NotNull
    String city;
}
