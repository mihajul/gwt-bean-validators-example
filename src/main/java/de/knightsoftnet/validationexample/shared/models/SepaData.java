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

package de.knightsoftnet.validationexample.shared.models;

import de.knightsoftnet.mtwidgets.shared.models.CountryEnum;
import de.knightsoftnet.validators.shared.BankCountry;
import de.knightsoftnet.validators.shared.BicValue;
import de.knightsoftnet.validators.shared.IbanFormated;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * The <code>SepaData</code> class implements contains data of a sepa bank account.
 *
 * @author Manfred Tremmel
 */
@BankCountry
public class SepaData {

  @NotEmpty
  private String bankName;

  @NotEmpty
  private String accountOwner;

  @NotNull
  private CountryEnum countryCode;

  @IbanFormated
  @NotEmpty
  private String iban;

  @BicValue
  @NotEmpty
  private String bic;

  public String getBankName() {
    return this.bankName;
  }

  public void setBankName(final String pbankName) {
    this.bankName = pbankName;
  }

  public String getAccountOwner() {
    return this.accountOwner;
  }

  public void setAccountOwner(final String paccountOwner) {
    this.accountOwner = paccountOwner;
  }

  public CountryEnum getCountryCode() {
    return this.countryCode;
  }

  public void setCountryCode(final CountryEnum pcountryCode) {
    this.countryCode = pcountryCode;
  }

  public String getIban() {
    return this.iban;
  }

  public void setIban(final String piban) {
    this.iban = piban;
  }

  public String getBic() {
    return this.bic;
  }

  public void setBic(final String pbic) {
    this.bic = pbic;
  }

  @Override
  public String toString() {
    return "SepaData [bankName=" + this.bankName + ", accountOwner=" + this.accountOwner
        + ", countryCode=" + this.countryCode + ", iban=" + this.iban + ", bic=" + this.bic + "]";
  }
}
