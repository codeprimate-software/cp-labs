/*
 * Copyright 2011-Present Author or Authors.
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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

import java.util.function.Function;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Unit Tests for {@link Thread}.
 *
 * @author John Blum
 * @see java.lang.Thread
 * @since 1.0.0
 */
@ExtendWith(MockitoExtension.class)
@SuppressWarnings("unused")
public class ThreadUnitTests {

  @Mock
  private Runnable mockRunnable;

  private Thread newThread(String name) {
    return newThread(name, Function.identity());
  }

  private Thread newThread(String name, Function<Thread, Thread> threadFunction) {
    return threadFunction.apply(new Thread(this.mockRunnable, name));
  }

  @Test
  public void threadNameIsCorrect() {

    Thread thread = newThread("TestThread", Mockito::spy);

    assertThat(thread.getName()).isEqualTo("TestThread");

    verify(thread, times(1)).getName();
    verifyNoInteractions(this.mockRunnable);
  }
}
