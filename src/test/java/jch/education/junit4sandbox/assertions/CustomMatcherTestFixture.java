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
package jch.education.junit4sandbox.assertions;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Assert;
import org.junit.Test;

/**
 * Illustrates asserts based on <code>assertThat</code> method and Hamcrest Matchers, including
 * a custom matcher.
 */
public class CustomMatcherTestFixture {

	/**
	 * This test method involves a sequence of individual asserts which should in fact
	 * form a single logical assert as they are verifying properties of a single object.
	 * The drawback of this approach is the fact that if one of the asserts will fail,
	 * subsequent assert(s) will not be executed at all, and the test result will not
	 * reveal the complete state of the verified object. For instance, if the X property
	 * will not have the expected value, you will not see the actual values of the Y and
	 * Z properties. Such information might help with the analysis of the failed test
	 * case, so it is not a good idea to throw it away voluntarily. In other words,
	 * I strongly discourage from using the approach.
	 */
	@Test
	public void sequenceOfIndividualAssertsWhichShouldRatherFormSingleLogicalAssert() {
		final Point3D point = new Point3D(10, 15, 27);

		// besides the primary drawback described above, this approach has additional
		// disadvantages:
		// - it is very likely that such sequences of asserts will be copied several
		//   times (i.e. unnecessary code duplication)
		// - there is a high potential for copy & paste errors
		Assert.assertEquals("X", 11, point.x);
		Assert.assertEquals("Y", 15, point.y);
		Assert.assertEquals("Z", 27, point.z);
	}

	/**
	 * In contrast with the test method above, this example involves just a single assert.
	 * The single assert is based on a custom Hamcrest matcher which:
	 * <ul>
	 * <li>verifies all three properties</li>
	 * <li>ensures that if the actual point deviates from the expectations, all three
	 * coordinates will appear in the test result (this statement is valid for the
	 * expected values as well as for the actual point)</li>
	 * </ul>
	 */
	@Test
	public void singleLogicalAssertBasedOnCustomHamcrestMatcher() {
		final Point3D point = new Point3D(10, 15, 27);

		Assert.assertThat(point, isPoint().withX(11).withY(15).withZ(27));
	}

	/**
	 * Another matcher based test method. This one illustrates that a matcher can also be
	 * implemented in a flexible way, allowing to verify an arbitrary subset of the properties
	 * of the verified object. The string representation of the expectations visible in the
	 * test result in case of a failed test case makes a clear distinction between irrelevant
	 * properties and those properties for which the expected value has been defined.
	 */
	@Test
	public void singleLogicalAssertBasedOnCustomHamcrestMatcherVerifyingSubsetOfProperties() {
		final Point3D point = new Point3D(10, 15, 27);

		Assert.assertThat(point, isPoint().withX(11).withZ(27));
	}

    /**
     * This is just a syntactic sugar method that makes the applications of the matcher more
     * readable and intent revealing.
     */
	private static Point3DMatcher isPoint() {
		return new Point3DMatcher();
	}

    /**
     * Custom matcher implementation. Besides the verification (i.e. comparison of actual versus
     * expected), this matcher also takes care about nice string representation of actual/expected
     * that will appear in the test result in case of failed test case.
     */
    private static class Point3DMatcher extends TypeSafeMatcher<Point3D> {

        private Integer x = null;

        private Integer y = null;

        private Integer z = null;

        public Point3DMatcher() {}

        Point3DMatcher withX(int x) {
        	this.x = new Integer(x);
        	return this;
        }

        Point3DMatcher withY(int y) {
        	this.y = new Integer(y);
        	return this;
        }

        Point3DMatcher withZ(int z) {
        	this.z = new Integer(z);
        	return this;
        }

        @Override
        protected boolean matchesSafely(Point3D point) {
        	return matches(this.x, point.x) && matches(this.y, point.y) && matches(this.z, point.z);
        }

        private static boolean matches(Integer expected, int actual) {
        	if (expected == null) {
        		// null value means there is no expectation for the given actual
        		// value,
        		return true;
        	}

        	return expected.intValue() == actual;
        }

        @Override
        public void describeTo(Description description) {
            String message = "%s instance with coordinates [%s; %s; %s].";
            message = String.format(message, Point3D.class.getName(), formatExpectedValue(this.x), formatExpectedValue(this.y), formatExpectedValue(this.z));
            description.appendText(message);
        }

        private static String formatExpectedValue(Integer value) {
        	if (value == null) {
        		return "no expectation";
        	}

        	return value.toString();
        }

        @Override
        protected void describeMismatchSafely(Point3D point, Description description) {
            String message = "%s instance with coordinates [%d; %d; %d].";
            message = String.format(message, point.getClass().getName(), point.x, point.y, point.z);
            description.appendText(message);
        }
    }
}
