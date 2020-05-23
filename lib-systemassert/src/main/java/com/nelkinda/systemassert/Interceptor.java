package com.nelkinda.systemassert;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

@SuppressWarnings({"java:S106", "java:S1170"})
class Interceptor implements AutoCloseable {
    private final PrintStream originalStdout = System.out;
    private final PrintStream originalStderr = System.err;
    private final SecurityManager originalSM = System.getSecurityManager();
    final ByteArrayOutputStream stdout = new ByteArrayOutputStream();
    final ByteArrayOutputStream stderr = new ByteArrayOutputStream();

    Interceptor() {
        System.setOut(new PrintStream(stdout));
        System.setErr(new PrintStream(stderr));
        System.setSecurityManager(new ExitInterceptingSecurityManager());
    }

    @Override
    public void close() {
        System.setSecurityManager(originalSM);
        System.setOut(originalStdout);
        System.setErr(originalStderr);
    }
}
