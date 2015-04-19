/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license
 * agreements. See the NOTICE file distributed with this work for additional information regarding
 * copyright ownership. The ASF licenses this file to You under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License. You may obtain a
 * copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package de.knightsoftnet.validationexample.shared;

import de.knightsoftnet.validationexample.shared.models.UserData;

import org.apache.commons.lang3.StringUtils;

/**
 * The <code>LoggedInAllowedRights</code> class can be used for entries which are open for every
 * logged in user.
 *
 * @author Manfred Tremmel
 */
public class LoggedInAllowedRights implements UserRightsInterface {

  @Override
  public final boolean isAllowedToSee(final UserData puser) {
    return puser != null && StringUtils.isNotEmpty(puser.getUserName());
  }

  @Override
  public final boolean isAllowedToChange(final UserData puser) {
    return isAllowedToSee(puser);
  }
}
