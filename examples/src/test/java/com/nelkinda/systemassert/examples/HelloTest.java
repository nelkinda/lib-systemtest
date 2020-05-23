package com.nelkinda.systemassert.examples;

import org.junit.jupiter.api.Test;

import static com.nelkinda.systemassert.SystemAssertions.assertProc;

class HelloTest {
    @Test
    void printsHelloWorld() {
        assertProc(Hello::main)
                .hadStdout("Hello, world!\n")
                .hadStderr("")
                .withSuccess();
    }
}
