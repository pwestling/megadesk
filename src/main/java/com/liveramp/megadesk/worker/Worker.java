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

package com.liveramp.megadesk.worker;

import java.util.List;

import com.liveramp.megadesk.gear.Gear;

public interface Worker {

  void run(Gear gear);

  void run(Gear... gears);

  void run(List<Gear> gears);

  void complete(Gear gear) throws InterruptedException;

  void complete(Gear... gears) throws InterruptedException;

  void complete(List<Gear> gears) throws InterruptedException;

  void stop();

  void join() throws InterruptedException;
}
