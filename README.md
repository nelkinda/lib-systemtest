# lib-systemtest

A library to test JVM functions and programs behavior on `System.out`, `System.err`, and `System.exit()`.

[![GitHub CI/CD](https://github.com/nelkinda/lib-systemtest/workflows/CI/CD/badge.svg)](https://github.com/nelkinda/lib-systemtest/actions)

## Example

```java
import org.junit.jupiter.api.Test;
import static com.nelkinda.systemassert.SystemAssertions.assertProc;

class HelloTest {
    @Test
    void printsNewline() {
        assertProc(() -> Hello.main())
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
