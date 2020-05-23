package com.nelkinda.systemassert.examples;

@SuppressWarnings({"java:S106", "PMD.SystemPrintln"})
public enum Echo {
    ;

    public static void main(final String... args) {
        System.out.println(String.join(" ", args));
    }
}
