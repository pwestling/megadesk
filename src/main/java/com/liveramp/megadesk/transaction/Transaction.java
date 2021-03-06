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

import com.liveramp.megadesk.state.Reference;
import com.liveramp.megadesk.state.Value;

public interface Transaction {

  <VALUE> Binding<VALUE> binding(Reference<VALUE> reference);

  <VALUE> Value<VALUE> read(Reference<VALUE> reference);

  <VALUE> VALUE get(Reference<VALUE> reference);

  <VALUE> void write(Reference<VALUE> reference, Value<VALUE> value);
}
