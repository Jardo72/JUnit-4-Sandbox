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

/**
 * Simple immutable object representing a point in a 3D space. I was considering
 * to use java.awt.Point, but it has just two properties, which is slightly
 * insufficient for the purposes of this example. With three or more properties,
 * the primary idea of this example is more obvious.
 */
public class Point3D {

	public final int x;

	public final int y;

	public final int z;

	public Point3D(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
}
