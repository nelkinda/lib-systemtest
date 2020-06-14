package com.nelkinda.systemassert;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ExitInterceptingSecurityManagerTest {
    @SuppressWarnings("PMD.JUnitTestContainsTooManyAsserts")
    @Test
    void checkExitThrowsExitException() {
        final ExitInterceptingSecurityManager securityManager = new ExitInterceptingSecurityManager();
        final ExitException exitException = assertThrows(ExitException.class, () -> securityManager.checkExit(42));
        assertEquals(42, exitException.getStatus());
    }
}
