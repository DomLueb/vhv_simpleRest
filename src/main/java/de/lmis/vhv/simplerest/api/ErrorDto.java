package de.lmis.vhv.simplerest.api;

import lombok.Data;

@Data
public class ErrorDto {
    private String message;
    private String errorCode;
}
