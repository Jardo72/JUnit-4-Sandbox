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
package jch.education.junit4sandbox.setupteardown;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * This dummy test fixture illustrates the life-cycle of instances of test fixtures.
 * JUnit creates a new instance of test fixture class for each and every test case,
 * i.e. single instance is never reused for execution of two or more test cases.
 * Besides the life-cycle of test fixture instances, this dummy test fixture also
 * illustrates the inner workings of setup/tear-down methods.
 */
public class ExperimentalTestFixture {

	private static final AtomicInteger sequence = new AtomicInteger(1);

	private final int instanceId = ExperimentalTestFixture.sequence.getAndIncrement();

	/**
	 * A method marked as @BeforeClass must be static. It will be automatically invoked before
	 * the very first test case of this test fixture. It will not be repeated before subsequent
	 * test cases, so it will be invoked just once. Be aware of the fact that a test fixture
	 * class can (but does not have to) have two or more methods marked with as @BeforeClass.
	 * In such a case, all methods marked with the above mentioned annotation will be invoked,
	 * but there is no guarantee in which order. If a specific order of setup steps is required,
	 * implement just a single @BeforeClass method, and invoke the particular setup steps from
	 * the single method in the desired order.
	 */
	@BeforeClass
	public static void classLevelSetupOne() {
		dumpCurrentMethod();
	}

	@BeforeClass
	public static void classLevelSetupTwo() {
		dumpCurrentMethod();
	}

	/**
	 * A method marked as @Before must not be static. It will be automatically invoked before
	 * each and every test case of this test fixture. In other words, if there are two or more
	 * test cases, the method will be invoked repeatedly. Be aware of the fact that a test fixture
	 * class can (but does not have to) have two or more methods marked with as @Before. In such
	 * a case, all methods marked with the above mentioned annotation will be invoked, but
	 * there is no guarantee in which order. If a specific order of setup steps is required,
	 * implement just a single @Before method, and invoke the particular setup steps from
	 * the single method in the desired order.
	 */
	@Before
	public void setupOne() {
		dumpCurrentMethod(this.instanceId);
	}

	@Before
	public void setupTwo() {
		dumpCurrentMethod(this.instanceId);
	}

	/**
	 * A method marked as @After must not be static. It will be automatically invoked after
	 * each and every test case of this test fixture. In other words, if there are two or more
	 * test cases, the method will be invoked repeatedly. Be aware of the fact that a test fixture
	 * class can (but does not have to) have two or more methods marked with as @After. In such
	 * a case, all methods marked with the above mentioned annotation will be invoked, but
	 * there is no guarantee in which order. If a specific order of tear down steps is required,
	 * implement just a single @After method, and invoke the particular tear down steps from
	 * the single method in the desired order.
	 */
	@After
	public void tearDownOne() {
		dumpCurrentMethod(this.instanceId);
	}

	@After
	public void tearDownTwo() {
		dumpCurrentMethod(this.instanceId);
	}

	/**
	 * A method marked as @AfterClass must be static. It will be automatically invoked after
	 * the last test case of this test fixture. It will not be executed repeatedly for
	 * particular test cases, it will be invoked just once. Be aware of the fact that a test fixture
	 * class can (but does not have to) have two or more methods marked with as @AfterClass.
	 * In such a case, all methods marked with the above mentioned annotation will be invoked,
	 * but there is no guarantee in which order. If a specific order of tear down steps is required,
	 * implement just a single @AfterClass method, and invoke the particular tear down steps from
	 * the single method in the desired order.
	 */
	@AfterClass
	public static void classLevelTearDownOne() {
		dumpCurrentMethod();
	}

	@AfterClass
	public static void classLevelTearDownTwo() {
		dumpCurrentMethod();
	}

	@Test
	public void testCaseOne() {
		dumpCurrentMethod(this.instanceId);
	}

	@Test
	public void testCaseTwo() {
		dumpCurrentMethod(this.instanceId);
	}

	@Test
	public void testCaseThree() {
		dumpCurrentMethod(this.instanceId);
	}

	@Test
	public void testCaseFour() {
		dumpCurrentMethod(this.instanceId);
	}

	private static void dumpCurrentMethod() {
		String message = "Current method = %s";
		message = String.format(message, getCurrentMethodName());
		System.out.println(message);
	}

	private static void dumpCurrentMethod(int instanceId) {
		String message = "Current method = %s, current instance ID = %d...";
		message = String.format(message, getCurrentMethodName(), instanceId);
		System.out.println(message);
	}

	private static String getCurrentMethodName() {
		final StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		return stackTrace[3].getMethodName();
	}
}
