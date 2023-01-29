package com.jmag.projet.domain.exceptions;

public abstract class PlanerTreeException extends RuntimeException{

    public PlanerTreeException(final String message) {
        super(message);
    }

    public PlanerTreeException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public PlanerTreeException(final Throwable cause) {
        super(cause);
    }
}
