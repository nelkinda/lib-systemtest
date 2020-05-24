# lib-systemtest

A library to test JVM functions and programs behavior on `System.out`, `System.err`, and `System.exit()`.

[![GitHub CI/CD](https://github.com/nelkinda/lib-systemtest/workflows/CI/CD/badge.svg)](https://github.com/nelkinda/lib-systemtest/actions)

## Target Audience
* Developers of command line tools on the JVM.
* Teachers and Students that work on JVM languages and want to verify assignments that operate on System.out.
* Trainers of Test-Driven Development.

## Dependencies/Requirements
* Java 8 JRE
* The library depends on the JUnit5 API but should work quite well also with TestNG or Cucumber.

It has been developed in Java, but should also work quite nicely in other JVM-languages like Kotlin, Groovy, Scala, and Clojure.

## Example

```java
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
```

## Reports
The reports are available locally, after running a build using `make build pitest`

### `lib-systemassert`
* [Checkstyle Report Main](lib-systemassert/build/reports/checkstyle/main.html)
* [Checkstyle Report Test](lib-systemassert/build/reports/checkstyle/test.html)
* [Test Report](lib-systemassert/build/reports/tests/test/index.html)
* [Jacoco Test Coverage Report](lib-systemassert/build/reports/jacoco/test/html/index.html)
* [PMD Report Main](lib-systemassert/build/reports/pmd/main.html)
* [PMD Report Test](lib-systemassert/build/reports/pmd/test.html)
* [SonarLint Report Main](lib-systemassert/build/reports/sonarlint/sonarlintMain/report.html)
* [SonarLint Report Test](lib-systemassert/build/reports/sonarlint/sonarlintTest/report.html)
* [PiTest Mutation Test Report](lib-systemassert/build/reports/pitest/index.html)

### `examples`
* [Checkstyle Report Main](examples/build/reports/checkstyle/main.html)
* [Checkstyle Report Test](examples/build/reports/checkstyle/test.html)
* [Test Report](examples/build/reports/tests/test/index.html)
* [Jacoco Test Coverage Report](examples/build/reports/jacoco/test/html/index.html)
* [PMD Report Main](examples/build/reports/pmd/main.html)
* [PMD Report Test](examples/build/reports/pmd/test.html)
* [SonarLint Report Main](examples/build/reports/sonarlint/sonarlintMain/report.html)
* [SonarLint Report Test](examples/build/reports/sonarlint/sonarlintTest/report.html)
* [PiTest Mutation Test Report](examples/build/reports/pitest/index.html)
