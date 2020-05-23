package com.nelkinda.systemassert.examples;

import org.junit.jupiter.api.Test;

import static com.nelkinda.systemassert.SystemAssertions.assertProc;

class EchoTest {
    @Test
    void noArguments() {
        assertProc(Echo::main)
                .hadStdout("\n")
                .hadStderr("")
                .withSuccess();
    }

    @Test
    void singleArgument() {
        assertProc(() -> Echo.main("Hello"))
                .hadStdout("Hello\n")
                .hadStderr("")
                .withSuccess();
    }

    @Test
    void twoArguments() {
        assertProc(() -> Echo.main("Hello,", "world!"))
                .hadStdout("Hello, world!\n")
                .hadStderr("")
                .withSuccess();
    }
}
