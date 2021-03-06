/*
 * Copyright (C) 2014-2016 LinkedIn Corp. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use
 * this file except in compliance with the License. You may obtain a copy of the
 * License at  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed
 * under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
 * CONDITIONS OF ANY KIND, either express or implied.
 */

package gobblin.util.deprecation;

import java.util.List;

import gobblin.configuration.State;


/**
 * Utilities to handle deprecations in Gobblin.
 */
public class DeprecationUtils {

  /**
   * Sets an option in a {@link State} to the first available value of a list of deprecatedKeys. For example, if
   * an option "optiona" was previously called "optionb" or "optionc",
   * calling {@link #renameDeprecatedKeys(State, String, List)} will search for the first available key-value pair
   * with key optiona, optionb, or optionc, and set optiona to that value.
   *
   * @param state {@link State} to modify.
   * @param currentKey current name of an option.
   * @param deprecatedKeys all other names that {@link State}s could use to refer to that option.
   */
  public static void renameDeprecatedKeys(State state, String currentKey, List<String> deprecatedKeys) {
    if (state.contains(currentKey)) {
      return;
    }
    for (String oldKey : deprecatedKeys) {
      if (state.contains(oldKey)) {
        state.setProp(currentKey, state.getProp(oldKey));
        return;
      }
    }
  }

}
