package com.nelkinda.systemassert;

class ExitException extends SecurityException {
    private static final long serialVersionUID = 1L;
    private final int status;

    ExitException(final int status) {
        this.status = status;
    }

    int getStatus() {
        return status;
    }
}
