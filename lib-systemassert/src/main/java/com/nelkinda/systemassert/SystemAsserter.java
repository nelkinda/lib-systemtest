package com.nelkinda.systemassert;

import org.opentest4j.AssertionFailedError;

import static org.junit.jupiter.api.Assertions.*;

public class SystemAsserter {
    private final byte[] stdout;
    private final byte[] stderr;
    private final Throwable throwable;

    @SuppressWarnings("PMD.ArrayIsStoredDirectly")
    SystemAsserter(
            final byte[] stdout,
            final byte[] stderr,
            final Throwable throwable
    ) {
        this.stdout = stdout;
        this.stderr = stderr;
        this.throwable = throwable;
    }

    /**
     * Verifies that it has generated the expected output on STDOUT ({@code System.out}).
     * @param expectedStdout Output expected on STDOUT.
     * @return this SystemAsserter for method chaining.
     */
    public SystemAsserter hadStdout(final String expectedStdout) {
        assertEquals(expectedStdout, new String(stdout));
        return this;
    }

    /**
     * Verifies that it the result has generated the expected output on STDERR ({@code System.err}).
     * @param expectedStderr Output expected on STDERR.
     * @return this SystemAsserter for method chaining.
     */
    public SystemAsserter hadStderr(final String expectedStderr) {
        assertEquals(expectedStderr, new String(stderr));
        return this;
    }

    /**
     * Verifies that it has thrown an exception of the specified type.
     * @param exceptionClass Expected exception class
     * @return this SystemAsserter for method chaining.
     */
    public SystemAsserter threw(final Class<? extends Throwable> exceptionClass) {
        assertTrue(exceptionClass.isInstance(throwable));
        return this;
    }

    /**
     * The execution was successful (no exception, {@code System.exit()} was not called or called with 0).
     * @return this SystemAsserter for method chaining.
     */
    public SystemAsserter withSuccess() {
        if (throwable != null) {
            withExitStatus(0);
        }
        return this;
    }

    /**
     * The execution threw no exception and {@code System.exit()} was called with the expected status code.
     * @param expected Expected status code
     * @return this SystemAsserter for method chaining.
     */
    @SuppressWarnings({"java:S1181", "PMD.AvoidCatchingThrowable"})
    public SystemAsserter withExitStatus(final int expected) {
        if (throwable == null) {
            throw new AssertionFailedError("System.exit() wasn't called.");
        }
        try {
            throw throwable;
        } catch (final ExitException exitException) {
            assertEquals(expected, exitException.getStatus(), "Unexpected exit status");
        } catch (final Throwable anyOtherException) {
            throw new AssertionFailedError("Unexpected exception", anyOtherException);
        }
        return this;
    }
}
