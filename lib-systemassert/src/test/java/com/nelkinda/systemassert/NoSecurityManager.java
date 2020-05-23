package com.nelkinda.systemassert;

import java.security.Permission;

import static org.junit.jupiter.api.Assertions.fail;

class NoSecurityManager extends SecurityManager {
    @Override
    public void checkPermission(final Permission perm) {
        // No security
    }

    @Override
    public void checkExit(final int status) {
        fail(() -> String.format("Unexpected call to exit: %d", status));
    }
}
