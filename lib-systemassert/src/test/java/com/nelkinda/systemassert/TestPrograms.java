package com.nelkinda.systemassert;

@SuppressWarnings({"PMD.DoNotCallSystemExit", "PMD.SystemPrintln"})
enum TestPrograms {
    ;

    static void noOperation() {
        // Intentionally empty
    }

    static void helloWorldOnStdout() {
        System.out.println("Hello, world!");
    }

    static void helloWorldOnStderr() {
        System.err.println("Hello, world!");
    }

    static void throwsTestException() throws TestException {
        throw new TestException();
    }

    static void exitZero() {
        System.exit(0);
    }

    static void exitOne() {
        System.exit(1);
    }
}
