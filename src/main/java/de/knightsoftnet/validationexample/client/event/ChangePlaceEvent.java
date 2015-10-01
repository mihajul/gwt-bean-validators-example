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

package de.knightsoftnet.validationexample.client.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;
import com.google.web.bindery.event.shared.HandlerRegistration;

/**
 * event used for place changes (navigate from one page to another).
 *
 * @author Manfred Tremmel
 *
 */
public class ChangePlaceEvent extends GwtEvent<ChangePlaceEvent.ChangePlaceHandler> {
  private static final Type<ChangePlaceHandler> TYPE = new Type<ChangePlaceHandler>();

  public interface ChangePlaceHandler extends EventHandler {
    void onChangePlace(ChangePlaceEvent pevent);
  }

  public interface ChangePlaceHandlers extends HasHandlers {
    HandlerRegistration addChangePlaceHandler(ChangePlaceHandler phandler);
  }

  private final String token;

  public ChangePlaceEvent(final String ptoken) {
    super();
    this.token = ptoken;
  }

  public static Type<ChangePlaceHandler> getType() {
    return TYPE;
  }

  @Override
  protected void dispatch(final ChangePlaceHandler phandler) {
    phandler.onChangePlace(this);
  }

  @Override
  public Type<ChangePlaceHandler> getAssociatedType() {
    return TYPE;
  }

  public String getToken() {
    return this.token;
  }
}
