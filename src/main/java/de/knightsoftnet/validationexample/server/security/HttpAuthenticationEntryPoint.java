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

package de.knightsoftnet.validationexample.server.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * http authentication entry point for gwt applications. based on the work of
 * https://github.com/imrabti/gwtp-spring-security
 *
 * @author Manfred Tremmel
 */
@Component
public class HttpAuthenticationEntryPoint implements AuthenticationEntryPoint {
  @Override
  public void commence(final HttpServletRequest prequest, final HttpServletResponse presponse,
      final AuthenticationException pauthException) throws IOException {
    presponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, pauthException.getMessage());
  }
}
