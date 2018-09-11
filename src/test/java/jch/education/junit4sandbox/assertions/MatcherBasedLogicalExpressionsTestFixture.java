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

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Assert;
import org.junit.Test;

/**
 * Illustrates composition of matcher-based assertions combining several conditions to
 * a logical expression.
 */
public class MatcherBasedLogicalExpressionsTestFixture {

	/**
	 * Complex assert combining two matchers using the logical AND operator.
	 * The assert will pass only if both matchers will pass. If the assert will
	 * fail, the test result will involve a nice string representation of the
	 * logical expression.
	 */
	@Test
	public void combinationOfMatcherUsingLogicalAndOperator() {
		final int actual = 16;

		Assert.assertThat(actual, allOf(
				greaterThan(10),
				lessThan(16)));
	}

	/**
	 * Complex assert combining two matchers using the logical OR operator.
	 * The assert will pass if any of the particular matchers will pass. If the
	 * assert will fail, the test result will involve a nice string representation
	 * of the logical expression.
	 */
	@Test
	public void combinationOfMatchersUsingLogicalOrOperator() {
		final String actual = "I am just a human being with many mistakes.";

		Assert.assertThat(actual, anyOf(
				containsString("perfect"),
				containsString("superman"),
				containsString("terminator")));
	}
}
