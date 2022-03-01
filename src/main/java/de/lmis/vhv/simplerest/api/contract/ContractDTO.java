package de.lmis.vhv.simplerest.api.contract;

import lombok.Builder;
import lombok.Value;

import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Value
@Builder
public class ContractDTO {
    @NotEmpty
    @UpperCase
    String id;

    @NotNull
    Category category;

    String description;

    @Valid
    AddressDto address;

    @Max(300)
    @Min(100)
    long value;

    @AssertTrue(message = "Falsche Länge für ID oder category")
    boolean isValidContract() {
        return id != null && category != null && category.name().length() > id.length();
    }
}
