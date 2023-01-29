package com.jmag.projet.domain.exceptions;

import lombok.Getter;

import java.util.Set;

public class PlanerTreeBadRequestException extends PlanerTreeException {
    @Getter
    private Set<String> details;
    public static final String TEST_ERROR_BadRequest_MESSAGE = "Hello Bad request exception";

    public PlanerTreeBadRequestException(final String message) {
        super(message);
    }

    public PlanerTreeBadRequestException(final String message, Set<String> details) {
        super(message);
        this.details = details;
    }
}
