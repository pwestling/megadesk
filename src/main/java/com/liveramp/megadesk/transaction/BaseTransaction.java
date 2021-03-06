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

package com.liveramp.megadesk.transaction;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import com.liveramp.megadesk.state.Driver;
import com.liveramp.megadesk.state.Reference;
import com.liveramp.megadesk.state.Value;

public class BaseTransaction implements Transaction {

  private final Map<Reference, Binding> bindings;

  public BaseTransaction(Dependency dependency) {
    bindings = Maps.newHashMap();
    for (Driver driver : readDrivers(dependency)) {
      addBinding(driver, true);
    }
    for (Driver driver : writeDrivers(dependency)) {
      addBinding(driver, false);
    }
  }

  private static List<Driver> readDrivers(Dependency dependency) {
    List<Driver> result = Lists.newArrayList();
    // Snapshots
    for (Driver driver : dependency.snapshots()) {
      result.add(driver);
    }
    // Execution reads
    for (Driver driver : dependency.reads()) {
      result.add(driver);
    }
    return result;
  }

  private static List<Driver> writeDrivers(Dependency dependency) {
    List<Driver> result = Lists.newArrayList();
    // Execution writes
    for (Driver driver : dependency.writes()) {
      result.add(driver);
    }
    return result;
  }

  private void addBinding(Driver driver, boolean readOnly) {
    bindings.put(driver.reference(), new BaseBinding(driver.persistence().read(), readOnly));
  }

  @Override
  public <VALUE> Binding<VALUE> binding(Reference<VALUE> reference) {
    if (!bindings.containsKey(reference)) {
      throw new IllegalStateException(); // TODO message
    }
    return bindings.get(reference);
  }

  @Override
  public <VALUE> Value<VALUE> read(Reference<VALUE> reference) {
    return binding(reference).read();
  }

  @Override
  public <VALUE> VALUE get(Reference<VALUE> reference) {
    return binding(reference).get();
  }

  @Override
  public <VALUE> void write(Reference<VALUE> reference, Value<VALUE> value) {
    binding(reference).write(value);
  }
}
