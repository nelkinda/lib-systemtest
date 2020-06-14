package com.nelkinda.systemassert;

import org.junit.jupiter.api.function.Executable;

public enum SystemAssertions {
    ;

    /**
     * Perform assertions on the process level.
     *
     * @param runnable Runnable that is to be asserted.
     * @return A SystemAsserter that captured the execution of the {@code runnable}.
     */
    @SuppressWarnings({"PMD.CloseResource", "PMD.AvoidCatchingThrowable", "java:S106", "java:S2093"})
    public static SystemAsserter assertProc(final Executable runnable) {
        try (Interceptor interceptor = new Interceptor()) {
            Throwable throwable = null;
            try {
                runnable.execute();
            } catch (final Throwable caughtException) {
                throwable = caughtException;
            }
            return new SystemAsserter(
                    interceptor.stdout.toByteArray(),
                    interceptor.stderr.toByteArray(),
                    throwable
            );
        }
    }
}
