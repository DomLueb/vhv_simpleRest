package de.lmis.vhv.simplerest.api;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Value
@Builder
public class ErrorDto {
    String message;
    String errorCode;
}
