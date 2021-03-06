/**
 *  Copyright 2013 LiveRamp
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

public class Path {

  private final String path;

  public Path(String path) {
    if (!path.startsWith("/")) {
      throw new IllegalArgumentException(Path.class.getSimpleName() + " must start with '/': " + path);
    }
    this.path = path;
  }

  public String get() {
    return path;
  }

  @Override
  public String toString() {
    return FormatUtils.formatToString(this, path);
  }
}
