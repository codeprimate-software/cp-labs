/*
 * Copyright 2017-Present Author or Authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
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
package examples;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

/**
 * Unit Tests demonstrating that a child class can extend a parent class with a private constructor.
 *
 * @author John Blum
 * @see org.junit.jupiter.api.Test
 */
@SuppressWarnings("unused")
public class PrivateConstructorExtensionUnitTests {

	@Test
	void childExtendingParentWithPrivateConstructor() {
		Child child = new Child();
		assertThat(child).isNotNull();
	}

	static class Parent {
		private Parent() { }
	}

	static class Child extends Parent {

	}
}
