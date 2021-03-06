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

package de.knightsoftnet.validationexample.client.services;

import de.knightsoftnet.validationexample.shared.models.UserData;
import de.knightsoftnet.validators.client.services.UserRestServiceTemplate;
import de.knightsoftnet.validators.shared.ResourcePaths;

import javax.ws.rs.Path;

/**
 * Definition of the login/logout remote services.
 *
 * @author Manfred Tremmel
 */
@Path(ResourcePaths.User.ROOT)
public interface UserRestService extends UserRestServiceTemplate<UserData> {
}
