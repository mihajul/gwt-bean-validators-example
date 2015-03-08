package de.knightsoftnet.validationexample.client.ui.address;

import de.knightsoftnet.validationexample.shared.models.CountryEnum;
import de.knightsoftnet.validationexample.shared.models.PostalAddressData;
import de.knightsoftnet.validators.client.decorators.UniversalDecoratorWithIcons;
import de.knightsoftnet.validators.client.editor.BeanValidationEditorDriver;
import de.knightsoftnet.validators.client.event.FormSubmitEvent;
import de.knightsoftnet.validators.client.event.FormSubmitHandler;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * View of the validator test Sepa.
 *
 * @author Manfred Tremmel
 *
 */
public class AddressViewGwtImpl extends Composite implements AddressViewInterface {

  /**
   * bind ui.
   */
  private static AddressViewUiBinder uiBinder = GWT.create(AddressViewUiBinder.class);

  /**
   * view interface.
   */
  interface AddressViewUiBinder extends UiBinder<Widget, AddressViewGwtImpl> {
  }

  /**
   * post office box.
   */
  @UiField
  UniversalDecoratorWithIcons<String> postOfficeBox;

  /**
   * street.
   */
  @UiField
  UniversalDecoratorWithIcons<String> street;

  /**
   * street number.
   */
  @UiField
  UniversalDecoratorWithIcons<String> streetNumber;

  /**
   * street number additional.
   */
  @UiField
  UniversalDecoratorWithIcons<String> streetNumberAdditional;

  /**
   * extended.
   */
  @UiField
  UniversalDecoratorWithIcons<String> extended;

  /**
   * postal code.
   */
  @UiField
  UniversalDecoratorWithIcons<String> postalCode;

  /**
   * locality/city/town..
   */
  @UiField
  UniversalDecoratorWithIcons<String> locality;

  /**
   * region.
   */
  @UiField
  UniversalDecoratorWithIcons<String> region;

  /**
   * country code.
   */
  @UiField
  UniversalDecoratorWithIcons<CountryEnum> countryCode;

  /**
   * label to display messages.
   */
  @Ignore
  @UiField
  Label logMessages;

  /**
   * Address button.
   */
  @Ignore
  @UiField
  Button addressButton;

  /**
   * interface of the driver to combine ui and bean.
   */
  interface Driver extends BeanValidationEditorDriver<PostalAddressData, AddressViewGwtImpl> {
  }

  /**
   * create driver out of the interface.
   */
  private final Driver driver = GWT.create(Driver.class);

  /**
   * reference to the activity.
   */
  private AddressPresenterInterface activity;

  /**
   * default constructor.
   */
  public AddressViewGwtImpl() {
    super();
    this.initWidget(uiBinder.createAndBindUi(this));
    this.driver.initialize(this);
    this.driver.setSubmitButton(this.addressButton);
    this.driver.addFormSubmitHandler(new FormSubmitHandler<PostalAddressData>() {
      @Override
      public void onFormSubmit(final FormSubmitEvent<PostalAddressData> pevent) {
        AddressViewGwtImpl.this.activity.tryToSend();
      }
    });
  }

  @Override
  public final void setPresenter(final AddressPresenterInterface psepaPresenter) {
    this.activity = psepaPresenter;
  }

  @Override
  public final void fillForm(final PostalAddressData puser) {
    this.driver.edit(puser);
  }

  @Override
  public final void showMessage(final String pmessage) {
    this.logMessages.setText(pmessage);
  }

  @Override
  public final BeanValidationEditorDriver<PostalAddressData,
      ? extends AddressViewInterface> getDriver() {
    return this.driver;
  }

  @Override
  public final void setFocusOnFirstWidget() {
    this.street.setFocus(true);
  }
}