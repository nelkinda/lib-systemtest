package com.nelkinda.systemassert;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ExitInterceptingSecurityManagerTest {
    @Test
    void checkExitThrowsExitException() {
        final ExitInterceptingSecurityManager securityManager = new ExitInterceptingSecurityManager();
        assertThrows(ExitException.class, () -> securityManager.checkExit(0));
    }
}
