package com.jmag.projet.domain.exceptions;

public class PlanerTreeServerException extends PlanerTreeException {

    public static final String TEST_ERROR_Server_MESSAGE = "Hello server exception";
    public static final String DEFAULT_ERROR = "Le service est indisponible pour l'instant, veuillez réessayer ultérieurement.";

    public PlanerTreeServerException(final String message) {
        super(message);
    }
}
