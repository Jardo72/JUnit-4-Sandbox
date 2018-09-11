/*
 * Copyright 2017 Jaroslav Chmurny
 *
 * This file is part of JUnit 4 Sandbox.
 *
 * JUnit 4 Sandbox is free software developed for educational purposes.
 * It is licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jch.education.junit4sandbox.exceptions;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.Assert.fail;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * This dummy test fixture illustrating various options how to verify that the tested
 * method throws an exception. The JavaDoc descriptions of the methods discuss the
 * benefits and liabilities of particular approaches as well as further details.
 */
public class ExperimentalTestFixture {

    /**
     * It is crucial to set this to none, so that test methods not using this instance member
     * at all have no expectations. Otherwise, test cases not expecting exceptions could be
     * failing.
     *
     * @see #passingTestCaseThatNeitherExpectsNorLeadsToAnyException()
     */
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    /**
     * This test case is here just to illustrate the purpose of ExpectedException.none().
     * This class involves a member of the type ExpectedException, but this method does not
     * expect any exception. In order to make this test method green, the expectedException
     * must be (by default) set to ExpectedException.none(). Those test method(s) that expect
     * an exception must explicitly overwrite the expectations according to their needs.
     */
    @Test
    public void passingTestCaseThatNeitherExpectsNorLeadsToAnyException() {
    }

    /**
     * This test method is doomed to fail. It invokes a method that will always throw
     * an exception, but the exception is by no means declared as expected. In practical
     * terms, JUnit will treat the exception as unexpected and thus make this test case
     * red.
     */
    @Test
    public void failingTestCaseNotExpectingAnyException() throws Exception {
        TroubleMaker.makeSeriousProblem();
    }

    /**
     * This is the most trivial exception verification approach. One of the benefits of
     * this approach is that you can verify various characteristics of the exception thrown
     * by the SUT (including its type, message or specific properties introduced by
     * a custom exception type). The drawback is that you have to write a solid portion of
     * boilerplate code in form of try-catch block with assertion forcing the test case to
     * fail in cases when the expected exception is not thrown. This drawback is the reason
     * why the other approaches demonstrated by the test methods below are to be used instead
     * of this one.
     */
    @Test
    public void passingTestCaseWithBoilerplateExceptionHandlingCode() {
        try {
            TroubleMaker.makeSeriousProblem();
            fail(CustomException.class.getName() + " expected but not thrown");
        } catch (final CustomException e) {
            // if you need to verify properties of the exception (regardless of
            // whether standard ones like the exception message, or specific), this
            // is the right place to do so
        }
    }

    /**
     * This test case is green as the Test annotation tells JUnit that an exception
     * of the specified type is expected. However, this approach does not allow to
     * verify any properties of the exception like the exception message. Be aware
     * of the fact that this test case will be green even if an exception of a subclass
     * of the given type will be thrown. For instance, if the expected exception is
     * java.io.IOException, but an instance of java.io.FileNotFoundException will be
     * thrown at run-time, the test case will be green anyway. In addition, this test
     * case will be green even if the expected exception will be thrown before the
     * invocation of the tested method (i.e. during the setup if there is any). In
     * such a case, the green test case will make an illusion that the tested method
     * works properly. However, we cannot make such a conclusion as it does not get
     * invoked.
     */
    @Test(expected=CustomException.class)
    public void passingTestCaseWithLimitedExceptionVerification() throws Exception {
        TroubleMaker.makeSeriousProblem();
    }

    /**
     * This test case is green as the expectedException tells JUnit that an exception
     * of the specified type with a message containing the given string is expected. This
     * approach is suitable for verification of standard characteristics of the exception
     * like its type and message. For custom exception types with specific properties whose
     * values are to be verified as well, this approach is insufficient.
     */
    @Test
    public void passingTestCaseWithAdvancedExceptionVerificationWithoutAnyMatcher() throws Exception {
        // it is crucial to setup the expectations before the actual invocation of the SUT
        this.expectedException.expect(CustomException.class);
        this.expectedException.expectMessage("there are much more threatening problems");

        TroubleMaker.makeSeriousProblem();
    }

    /**
     * This test case is green as the expectedException tells JUnit that an exception
     * of the specified type with the given severity and a message containing the given string
     * is expected. The severity property is specific to this custom exception type, so the
     * functionality provided directly by the ExpectedException class is not enough to implement
     * this verification. Therefore, Hamcrest matchers are used.
     * This verification is based on standard matchers provided by Hamcrest. The advantage of
     * this particular approach (opposed to the next test method below this one) is that there
     * is no need to implement a custom matcher. The drawback is that each test method using this
     * approach has to specify the name(s) of the specific exception properties (severity in this
     * case), which makes the concerned test cases brittle. For instance, if the specific exception
     * properties are renamed, the concerned test cases will have to be updated as well, and there
     * is no compile-time check discovering the problem (i.e. if undiscovered, the problem will
     * explode at run-time).
     */
    @Test
    public void passingTestWithAdvancedExceptionVerificationBasedOnStandardMatchers() throws Exception {
        // it is crucial to setup the expectations before the actual invocation of the SUT
        this.expectedException.expect(CustomException.class);
        this.expectedException.expectMessage("this is just a demo");
        this.expectedException.expect(hasProperty("severity", equalTo(Severity.END_OF_THE_WORLD)));

        TroubleMaker.startEndOfTheWorld();
    }

    /**
     * This test case is green. It combines the ExpectedException instance with a custom matcher that
     * eliminates the brittleness of the previous test method. If the severity property will be renamed,
     * it is enough to modify the exception class and the matcher. The test methods will remain unchanged.
     * This test method also illustrates syntactic sugar in form of helper methods that makes the usages
     * of the custom matcher more readable/intent revealing.
     */
    @Test
    public void passingTestWithAdvancedExceptionVerificationBasedOnCustomMatcher() throws Exception {
        // it is crucial to setup the expectations before the actual invocation of the SUT
        this.expectedException.expect(CustomException.class);
        this.expectedException.expect(customExceptionWithSeverity(Severity.END_OF_THE_WORLD));
        this.expectedException.expectMessage("this is just a demo");

        TroubleMaker.startEndOfTheWorld();
    }

    /**
     * This is just a syntactic sugar method that makes the applications of the matcher more readable
     * and intent revealing.
     */
    private static TypeSafeMatcher<CustomException> customExceptionWithSeverity(Severity severity) {
        return new SeverityMatcher(severity);
    }

    /**
     * Custom matcher that verifying the severity of the given {@link CustomException} instance.
     */
    private static class SeverityMatcher extends TypeSafeMatcher<CustomException> {

        private final Severity severity;

        SeverityMatcher(Severity severity) {
            this.severity = severity;
        }

        @Override
        protected boolean matchesSafely(CustomException exception) {
            return this.severity == exception.getSeverity();
        }

        @Override
        public void describeTo(Description description) {
            String message = "%s instance with severity %s.";
            message = String.format(message, CustomException.class.getName(), this.severity);
            description.appendText(message);
        }

        @Override
        protected void describeMismatchSafely(CustomException exception, Description description) {
            String message = "%s instance with severity %s.";
            message = String.format(message, CustomException.class.getName(), exception.getSeverity());
            description.appendText(message);
        }
    }
}
