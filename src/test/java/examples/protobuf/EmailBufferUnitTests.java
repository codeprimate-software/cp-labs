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
package examples.protobuf;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import io.codeprimate.example.model.proto.EmailBuffer;

import org.junit.jupiter.api.Test;

/**
 * Unit Tests for {@link EmailBuffer}.
 *
 * @author John Blum
 * @see io.codeprimate.example.model.proto.EmailBuffer
 * @see org.junit.jupiter.api.Test
 */
public class EmailBufferUnitTests {

	private static final boolean DEBUG = false;

	@Test
	void writeAndReadEmailBuffer() throws IOException {

		EmailBuffer.Email email = EmailBuffer.Email.newBuilder()
			.setFrom(newEmailAddress("jonDoe", "home.com"))
			.setTo(newEmailAddress("pieDoe", "work.com"))
			.setSubject("Test Email")
			.setMessage("This is a test! Are you receiving?")
			.build();

		assertThat(email).isNotNull();

		byte[] serializedEmail = email.toByteArray();
		byte[] javaSerializedEmail = javaSerialize(email);

		log("Protobuf Serialized Byte Size [%d]; Java Serialized Byte Size [%d]",
			serializedEmail.length, javaSerializedEmail.length);

		EmailBuffer.Email deserializedEmail = EmailBuffer.Email.parseFrom(serializedEmail);

		assertThat(deserializedEmail).isNotNull().isNotSameAs(email);
		assertThat(deserializedEmail).isEqualTo(email);
	}

	private void log(String message, Object... args) {
		if (DEBUG) {
			System.err.printf(message, args);
			System.err.flush();
		}
	}

	private EmailBuffer.Email.Address newEmailAddress(String name, String domain) {
		return EmailBuffer.Email.Address.newBuilder().setName(name).setDomain(domain).build();
	}

	private byte[] javaSerialize(Object target) throws IOException {

		ByteArrayOutputStream arrayOutput = new ByteArrayOutputStream();

		try (ObjectOutputStream objectOutput = new ObjectOutputStream(arrayOutput)) {
			objectOutput.writeObject(target);
		}

		return arrayOutput.toByteArray();
	}
}
