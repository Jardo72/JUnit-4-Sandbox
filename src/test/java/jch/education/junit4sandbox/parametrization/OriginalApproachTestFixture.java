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
package jch.education.junit4sandbox.parametrization;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * This experimental test fixture illustrates the original JUnit support for
 * parametrized tests. Frankly speaking, I perceive this approach as cumbersome
 * and ugly (especially compared to NUnit) because of several reasons:
 * <ul>
 * <li>The return value of the method defining the test data is generic. In
 * other words, regardless of the types of your test data, the return value is
 * always defined as a collection of object arrays.</li>
 * <li>It is impossible to have a test fixture with two or more parametrized test
 * methods, each having a distinct parameter values (or even distinct number and/or
 * types of parameters).</li>
 * <li>Just a brief look at the test method does not reveal the fact that the test
 * method is parametrized. In order to discover this fact, you have to look at the
 * entire test fixture. For someone who has no idea how the parametrization works,
 * it is not obvious (again, compare this with NUnit).
 * <li>If you look at the test runner, the values of the parameters used by particular
 * test cases are not visible. You just see the index (which entry in the collection).
 * I am sure you know what I am going to say - Nunit...</li>
 * </ul>
 */
@RunWith(Parameterized.class)
public class OriginalApproachTestFixture {

	private final int input;

	private final int expectedOutcome;

	public OriginalApproachTestFixture(int input, int expectedOutcome) {
		this.input = input;
		this.expectedOutcome = expectedOutcome;
	}

    @Parameters
    public static Collection<Object[]> data() {
    	final Object[][] testData = {
    			{ 0, 0 },
    			{ 1, 1 },
    			{ 2, 4 },
    			{ 3, 9 },
    			{ 4, 16 },
    			{ 5, 25 },
    			{ 10, 100 },
    			{ -1, 1 },
    			{-2, 4 },
    			{-5, 25 },
    			{-7, 49 },
    	};
        return Arrays.asList(testData);
    }

	@Test
	public void properSquareRootIsCalculatedForTheGivenValue() {
		assertEquals(this.expectedOutcome, SquareRoot.calculate(this.input));
	}
}
