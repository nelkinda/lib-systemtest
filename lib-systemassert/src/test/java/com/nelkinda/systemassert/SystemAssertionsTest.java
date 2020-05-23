package com.nelkinda.systemassert;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.opentest4j.AssertionFailedError;

import java.io.PrintStream;

import static com.nelkinda.systemassert.SystemAssertions.assertProc;
import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings({"PMD.JUnitTestContainsTooManyAsserts", "java:S106", "java:S3415"})
class SystemAssertionsTest {
    private static final PrintStream ORIGINAL_STDOUT = System.out;
    private static final PrintStream ORIGINAL_STDERR = System.err;
    private static final SecurityManager ORIGINAL_SECURITY_MANAGER = System.getSecurityManager();
    private static final SecurityManager NO_SECURITY_MANAGER = new NoSecurityManager();

    private static void assertFails(final Executable executable) {
        assertThrows(AssertionFailedError.class, executable);
    }

    @BeforeEach
    void installTestSecurityManager() {
        System.setSecurityManager(NO_SECURITY_MANAGER);
    }

    @SuppressWarnings("java:S3415") // SonarLint guessed this wrong
    @AfterEach
    void assertRestoration() {
        try {
            assertAll(
                    () -> assertEquals(ORIGINAL_STDOUT, System.out),
                    () -> assertEquals(ORIGINAL_STDERR, System.err),
                    () -> assertEquals(NO_SECURITY_MANAGER, System.getSecurityManager())
            );
        } finally {
            System.setOut(ORIGINAL_STDOUT);
            System.setErr(ORIGINAL_STDERR);
            System.setSecurityManager(ORIGINAL_SECURITY_MANAGER);
        }
    }

    @Test
    void callsFunction() {
        final boolean[] called = new boolean[1];
        assertProc(() -> called[0] = true).withSuccess();
        assertTrue(called[0]);
    }

    @Test
    void hasStdoutStringEmpty() {
        assertProc(TestPrograms::noOperation).hadStdout("");
    }

    @Test
    void hasStdoutStringFail() {
        final SystemAsserter asserter = assertProc(TestPrograms::noOperation);
        assertFails(() -> asserter.hadStdout("x"));
    }

    @Test
    void hasStdoutStringCompare() {
        final SystemAsserter asserter = assertProc(TestPrograms::helloWorldOnStdout);
        asserter.hadStdout(String.format("Hello, world!%n"));
    }

    @Test
    void hasStderrStringEmpty() {
        assertProc(TestPrograms::noOperation).hadStderr("");
    }

    @Test
    void hasStderrStringFail() {
        final SystemAsserter asserter = assertProc(TestPrograms::noOperation);
        assertFails(() -> asserter.hadStderr("x"));
    }

    @Test
    void hasStderrStringCompare() {
        final SystemAsserter asserter = assertProc(TestPrograms::helloWorldOnStderr);
        asserter.hadStderr(String.format("Hello, world!%n"));
    }

    @Test
    void restoresStdout() {
        assertProc(TestPrograms::noOperation);
        assertEquals(ORIGINAL_STDOUT, System.out);
    }

    @Test
    void restoresStdoutIfExceptionIsThrown() {
        assertProc(TestPrograms::throwsTestException);
        assertEquals(ORIGINAL_STDOUT, System.out);
    }

    @Test
    void restoresStderr() {
        assertProc(TestPrograms::noOperation);
        assertEquals(ORIGINAL_STDERR, System.err);
    }

    @Test
    void restoresStderrIfExceptionIsThrown() {
        assertProc(TestPrograms::throwsTestException);
        assertEquals(ORIGINAL_STDERR, System.err);
    }

    @Test
    void restoresSecurityManager() {
        assertProc(TestPrograms::noOperation);
        assertEquals(NO_SECURITY_MANAGER, System.getSecurityManager());
    }

    @Test
    void providesException() {
        final SystemAsserter asserter = assertProc(TestPrograms::throwsTestException);
        asserter.threw(TestException.class);
        assertFails(asserter::withSuccess);
    }

    @Test
    void providesExceptionBadCase() {
        final SystemAsserter asserter = assertProc(TestPrograms::noOperation);
        assertFails(() -> asserter.threw(TestException.class));
    }

    @Test
    void interceptsExit() {
        final SystemAsserter asserter = assertProc(TestPrograms::exitZero);
        asserter.withSuccess();
        assertFails(() -> asserter.withExitStatus(1));
    }

    @Test
    void exitValueStored() {
        final SystemAsserter asserter = assertProc(TestPrograms::exitOne);
        asserter.withExitStatus(1);
        final AssertionFailedError assertionFailedError =
                assertThrows(AssertionFailedError.class, () -> asserter.withExitStatus(0));
        assertEquals("Unexpected exit status ==> expected: <0> but was: <1>", assertionFailedError.getMessage());
    }

    @Test
    void exitNotCalled() {
        final SystemAsserter asserter = assertProc(TestPrograms::noOperation);
        final AssertionFailedError assertionFailedError =
                assertThrows(AssertionFailedError.class, () -> asserter.withExitStatus(0));
        assertEquals("System.exit() wasn't called.", assertionFailedError.getMessage());
    }

    @Test
    void methodsCanBeChained() {
        assertProc(TestPrograms::exitZero)
                .hadStdout("")
                .hadStderr("")
                .withSuccess()
                .withExitStatus(0)
                .threw(ExitException.class)
                .withSuccess();
    }
}
