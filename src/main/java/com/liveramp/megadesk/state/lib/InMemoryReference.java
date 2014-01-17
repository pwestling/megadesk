/**
 *  Copyright 2014 LiveRamp
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.liveramp.megadesk.state.lib;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.liveramp.megadesk.state.Reference;
import com.liveramp.megadesk.state.Value;

public class InMemoryReference<VALUE> implements Reference<VALUE> {

  private final ReadWriteLock lock = new ReentrantReadWriteLock();
  private Value<VALUE> value;

  public InMemoryReference() {
    this.value = null;
  }

  public InMemoryReference(Value<VALUE> value) {
    this.value = value;
  }

  @Override
  public ReadWriteLock lock() {
    return lock;
  }

  @Override
  public Value<VALUE> read() {
    return value;
  }

  @Override
  public void write(Value<VALUE> value) {
    this.value = value;
  }
}