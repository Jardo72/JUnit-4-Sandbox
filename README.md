# JUnit 4 Sandbox

## Introduction

JUnit 4 Sandbox is a small educational project allowing to:
- gain deeper understanding of how JUnit 4 works, for instance how the life-cycle of a test fixture looks like
- learn some best practices so that your test cases will not lack run-time and/or design-time qualities

For instance, if a test suite contains a lot of code duplication, it is lacking one of the design-time qualities. If a test case fails, and the test result does not provide sufficient information about the expected and actual outcome of the tested functionality, it is missing one of the run-time qualities (the consequence of such a deficiency is difficult or even impossible analysis of failed test cases).


## Source Code Organization, Building and Running the Test Suite
The Java source code is organized as a simple Maven project. Majority of the source code resides within the `src/test/java` directory structure which is the usual location of test code within a Maven project. However, there are also few classes in the `src/main/java` directory structure. These serve as dummy SUTs (System Under Test).

In order to compile and run the test suite, just navigate to the root directory of the project and execute the following command:

```
mvn clean test
```

You can also import the project to your favourite IDE like Eclipse and execute the test suite there. In fact, some of the test cases intentionally write to `stdout` to illustrate aspects like order of invocation of some methods. If you run the test suite from an IDE rather than via Maven, the output produced by the test suite will not be mixed with Maven output, so you might regard this approach with favour.

Please be aware of the fact that when you run the test suite, some of the test cases will fail. This is intentional, for instance in order to illustrate how a test cases ensures that in the case of failure, the test result provides details of what was expected and how the actual outcome of the tested functionality differs from the expectation.


## Illustrated JUnit Aspects
Each of the test fixtures provided by this project contains a solid portion of JavaDoc documentation describing the aspects and functionalities of JUnit illustrated by the test fixture, including the benefits and liabilities of various approaches. Therefore, the following sections of this document provide just a brief overview of the contents of this project rather than detailed descriptions.

### Life-Cycle of Test Fixture Including Setup/Tear-Down
The `jch.education.junit4sandbox.setupteardown` package contains just a single test fixture. It illustrates the instantiation policy JUnit applies to test fixtures as well as various setup and tear-down methods.

### Assertions
The `jch.education.junit4sandbox.assertions` package contains several test fixtures illustrating various verification approaches, including original JUnit asserts and advanced asserts based on Hamcrest Matcher (including asserts with logical expressions combining several conditions).

### Verification of Exceptions
The `jch.education.junit4sandbox.exceptions` package illustrates testing of methods that are expected to throw an exception.

### Parametrization of Test Cases
The `jch.education.junit4sandbox.parametrization` package contains several test fixtures that illustrate JUnit support for parametrized tests.
