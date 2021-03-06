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

package com.liveramp.megadesk.old.dependency.lib;

import java.util.ArrayList;
import java.util.List;

import com.liveramp.megadesk.old.dependency.Dependency;
import com.liveramp.megadesk.old.dependency.DependencyCheck;
import com.liveramp.megadesk.old.node.Node;
import com.liveramp.megadesk.old.node.Nodes;
import com.liveramp.megadesk.old.register.Participant;
import com.liveramp.megadesk.old.register.Register;
import com.liveramp.megadesk.old.register.Registers;
import com.liveramp.megadesk.utils.FormatUtils;

public class NodeHierarchyDependency implements Dependency {

  private final Node node;

  public NodeHierarchyDependency(Node node) {
    this.node = node;
  }

  @Override
  public DependencyCheck acquire(Participant participant) throws Exception {
    List<Register> registers = new ArrayList<Register>();
    registers.addAll(Nodes.getHierarchyRegisters(node));
    boolean result = Registers.register(registers, participant);
    if (result) {
      return DependencyCheck.ACQUIRED;
    } else {
      Registers.unregister(registers, participant);
      return DependencyCheck.STANDBY;
    }
  }

  @Override
  public void release(Participant participant) throws Exception {
    Registers.unregister(Nodes.getHierarchyRegisters(node), participant);
  }

  @Override
  public String toString() {
    return FormatUtils.formatToString(this, node.getPath().get());
  }
}
