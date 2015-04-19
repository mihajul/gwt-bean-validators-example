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

package de.knightsoftnet.validationexample.client.ui.page.settings;

import de.knightsoftnet.validationexample.client.ui.widget.LanguageSelectorWidget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

/**
 * View of the validator test Settings.
 *
 * @author Manfred Tremmel
 *
 */
public class SettingsViewGwtImpl extends Composite implements SettingsViewInterface {

  /**
   * bind ui.
   */
  private static SettingsViewUiBinder uiBinder = GWT.create(SettingsViewUiBinder.class);

  /**
   * view interface.
   */
  interface SettingsViewUiBinder extends UiBinder<Widget, SettingsViewGwtImpl> {
  }

  /**
   * language list box.
   */
  @UiField
  LanguageSelectorWidget language;

  /**
   * reference to the activity.
   */
  private SettingsPresenterInterface activity;

  /**
   * default constructor.
   */
  public SettingsViewGwtImpl() {
    super();
    this.initWidget(uiBinder.createAndBindUi(this));
  }

  @Override
  public final void setPresenter(final SettingsPresenterInterface psettingsPresenter) {
    this.activity = psettingsPresenter;
  }

  /**
   * language changes.
   *
   * @param pchangeEvent change event.
   */
  @UiHandler("language")
  final void onChange(final ValueChangeEvent<String> pchangeEvent) {
    this.activity.changeLanguage(pchangeEvent.getValue());
  }
}
