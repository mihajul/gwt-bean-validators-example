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

package de.knightsoftnet.validationexample.client.ui.page.sepa;

import de.knightsoftnet.mtwidgets.client.ui.widget.BicSuggestBox;
import de.knightsoftnet.mtwidgets.client.ui.widget.CountryListBox;
import de.knightsoftnet.mtwidgets.shared.models.CountryEnum;
import de.knightsoftnet.validationexample.shared.models.SepaData;
import de.knightsoftnet.validators.client.decorators.UniversalDecoratorWithIcons;
import de.knightsoftnet.validators.client.editor.BeanValidationEditorDriver;
import de.knightsoftnet.validators.client.event.FormSubmitEvent;
import de.knightsoftnet.validators.client.event.FormSubmitHandler;
import de.knightsoftnet.validators.server.data.CreateClass;
import de.knightsoftnet.validators.shared.data.IbanLengthMapSharedConstants;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;

/**
 * View of the validator test Sepa.
 *
 * @author Manfred Tremmel
 *
 */
public class SepaViewGwtImpl extends ViewImpl
    implements SepaPresenter.MyView, FormSubmitHandler<SepaData> {

  /**
   * view interface.
   */
  interface Binder extends UiBinder<Widget, SepaViewGwtImpl> {
  }

  /**
   * interface of the driver to combine ui and bean.
   */
  interface Driver extends BeanValidationEditorDriver<SepaData, SepaViewGwtImpl> {
  }

  @UiField
  Label bankName;

  @UiField
  UniversalDecoratorWithIcons<String> accountOwner;

  @UiField
  UniversalDecoratorWithIcons<CountryEnum> countryCode;

  @UiField
  UniversalDecoratorWithIcons<String> iban;

  @UiField
  UniversalDecoratorWithIcons<String> bic;

  @Ignore
  @UiField
  Label logMessages;

  @Ignore
  @UiField
  Button sepaButton;

  /**
   * create driver out of the interface.
   */
  private final Driver driver;

  /**
   * reference to the activity.
   */
  private SepaPresenter presenter;

  /**
   * constructor with injected parameters.
   *
   * @param pdriver editor driver
   * @param puiBinder ui binder
   */
  @Inject
  public SepaViewGwtImpl(final Driver pdriver, final Binder puiBinder) {
    super();
    this.initWidget(puiBinder.createAndBindUi(this));
    this.driver = pdriver;
    this.driver.initialize(this);
    this.driver.setSubmitButton(this.sepaButton);
    this.driver.addFormSubmitHandler(this);
    ((BicSuggestBox) this.bic.getWidget()).setBankNameWidget(this.bankName);
    // limit countries to sepa countries
    final IbanLengthMapSharedConstants ibanMap =
        CreateClass.create(IbanLengthMapSharedConstants.class);
    final CountryEnum[] countryList = new CountryEnum[ibanMap.ibanLengths().size()];
    int pos = 0;
    for (final String entry : ibanMap.ibanLengths().keySet()) {
      countryList[pos++] = CountryEnum.valueOf(entry);
    }
    ((CountryListBox) this.countryCode.getWidget()).fillCountryEntries(countryList);
  }

  @Override
  public final void setPresenter(final SepaPresenter ppresenter) {
    this.presenter = ppresenter;
  }

  @Override
  public final void fillForm(final SepaData puser) {
    this.driver.edit(puser);
  }

  @Override
  public final void showMessage(final String pmessage) {
    this.logMessages.setText(pmessage);
  }

  @Override
  public final void setFocusOnFirstWidget() {
    this.bic.setFocus(true);
  }

  @Override
  public void onFormSubmit(final FormSubmitEvent<SepaData> pevent) {
    this.presenter.tryToSend();
  }

  @Override
  public void setConstraintViolations(final ArrayList<ConstraintViolation<?>> pvalidationErrorSet) {
    this.driver.setConstraintViolations(pvalidationErrorSet);
  }
}
