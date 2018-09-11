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
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

/**
 * This experimental test fixture illustrates how you can provide description
 * for particular test cases. This way, you can eliminate one of the drawbacks
 * described in {@link OriginalApproachTestFixture}. However, the other drawbacks
 * apply to this test fixture as well. Concerning support for parametrized test
 * cases, NUnit is simply head and shoulders above JUnit.
 */
@RunWith(Parameterized.class)
public class ApproachWithTestCaseIdentificationTestFixture {

	/**
	 * Zero is the default, so the annotation argument could be omitted in this case.
	 */
	@Parameter(0)
	public int input;

	@Parameter(1)
	public int expectedOutcome;

	/**
	 * The Parameters annotation allows to prescribe a description for the particular
	 * test cases, thus eliminating one of the drawbacks.
	 */
    @Parameters(name = "sqr({0}) = {1}")
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
