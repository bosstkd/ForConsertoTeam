package com.jmag.projet.domain.exceptions;

import lombok.Getter;

import java.util.Set;

public class PlanerTreeNotFoundException extends PlanerTreeException {

    @Getter
    private Set<String> details;
    public static final String TEST_ERROR_NotFound_MESSAGE = "Hello Not Found exception";

    public PlanerTreeNotFoundException(final String message) {
        super(message);
    }

    public PlanerTreeNotFoundException(final String message, Set<String> details) {
        super(message);
        this.details = details;
    }
}
