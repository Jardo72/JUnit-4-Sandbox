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
 * This experimental test fixture illustrates a newer approach. However, this
 * approach isn't much better than the original one. This approach eliminates the
 * constructor, and JUnits directly sets the values of the instance fields corresponding
 * to the parameters. However, all drawbacks described in {@link OriginalApproachTestFixture}
 * apply to this approach as well.
 */
@RunWith(Parameterized.class)
public class NewerApproachWithoutConstructorTestFixture {

	/**
	 * Parameter fields have to be instance fields, and they must be public.
	 */
	@Parameter
	public int input;

	/**
	 * The int value passed to the annotation is an index of the parameter. For
	 * the first parameter, zero is to be used. As it is the default, the int value
	 * can be omitted for the first parameter.
	 */
	@Parameter(1)
	public int expectedOutcome;

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
