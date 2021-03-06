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

package com.liveramp.megadesk.old.node;

import com.liveramp.megadesk.utils.FormatUtils;

public abstract class BaseNode implements Node {

  private final Path path;

  public BaseNode(Path path) {
    this.path = path;
  }

  @Override
  public Path getPath() {
    return path;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof BaseNode)) {
      return false;
    }

    BaseNode baseNode = (BaseNode)o;

    if (path != null ? !path.equals(baseNode.path) : baseNode.path != null) {
      return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return path != null ? path.hashCode() : 0;
  }

  @Override
  public String toString() {
    return FormatUtils.formatToString(this, getPath().get());
  }
}
