package com.nelkinda.systemassert;

import java.security.Permission;

class ExitInterceptingSecurityManager extends SecurityManager {
    @Override
    public void checkExit(final int status) {
        throw new ExitException(status);
    }

    @Override
    public void checkPermission(final Permission perm) {
        // No Security
    }
}
