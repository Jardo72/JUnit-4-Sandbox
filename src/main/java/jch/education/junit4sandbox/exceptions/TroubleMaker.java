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

/**
 * Dummy system under test (SUT) class.
 */
public class TroubleMaker {

    /**
     * Avoids instantiation of this "utility" class by other classes.
     */
    private TroubleMaker() {}

    public static void makeSeriousProblem() throws CustomException {
        final String message = "Do not worry, there are much more threatening problems (e.g. end of the world :-)";
        throw new CustomException(Severity.SERIOUS_PROBLEM, message);
    }

    public static void startEndOfTheWorld() throws CustomException {
        final String message = "Do not worry, this is just a demo. The actual end of the world is planned slightly later :-)";
        throw new CustomException(Severity.END_OF_THE_WORLD, message);
    }
}
