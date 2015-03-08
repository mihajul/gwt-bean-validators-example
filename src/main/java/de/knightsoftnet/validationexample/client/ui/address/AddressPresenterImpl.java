package de.knightsoftnet.validationexample.client.ui.address;

import de.knightsoftnet.validationexample.client.basepage.AbstractPresenter;
import de.knightsoftnet.validationexample.client.ClientFactoryInterface;
import de.knightsoftnet.validationexample.client.JsHelper;
import de.knightsoftnet.validationexample.shared.models.CountryEnum;
import de.knightsoftnet.validationexample.shared.models.PostalAddressData;
import de.knightsoftnet.validators.shared.exceptions.ValidationException;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.RepeatingCommand;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

/**
 * Activity/Presenter of the Sepa, implementation.
 *
 * @author Manfred Tremmel
 *
 */
public class AddressPresenterImpl extends AbstractPresenter implements AddressPresenterInterface {
  /**
   * three hundred milliseconds.
   */
  public static final int FOCUS_DELAY = 300;

  /**
   * address data to remember.
   */
  private final PostalAddressData addressData;

  /**
   * view of the page.
   */
  private AddressViewInterface view;

  /**
   * the constructor of the activity/presenter.
   *
   * @param pclientFactory client factory
   */
  public AddressPresenterImpl(final ClientFactoryInterface pclientFactory) {
    super(pclientFactory);
    this.addressData = new PostalAddressData();
    this.addressData.setCountryCode(CountryEnum.DE);
  }

  @Override
  public final void start(final AcceptsOneWidget ppanel, final EventBus peventBus) {
    GWT.runAsync(new RunAsyncCallback() {

      @Override
      public void onSuccess() {
        AddressPresenterImpl.this.view =
            AddressPresenterImpl.this.getClientFactory().getAddressView();
        AddressPresenterImpl.this.view.setPresenter(AddressPresenterImpl.this);
        AddressPresenterImpl.this.view.fillForm(AddressPresenterImpl.this.addressData);
        ppanel.setWidget(AddressPresenterImpl.this.view.asWidget());
        Scheduler.get().scheduleFixedDelay(new RepeatingCommand() {
          @Override
          public boolean execute() {
            AddressPresenterImpl.this.view.setFocusOnFirstWidget();
            return false;
          }
        }, FOCUS_DELAY);
      }

      @Override
      public void onFailure(final Throwable preason) {
        JsHelper.forceReload();
      }
    });
  }

  @Override
  public final void tryToSend() {
    this.getClientFactory().getAddressService()
        .sendPostalAddress(this.addressData, new AsyncCallback<PostalAddressData>() {
          @Override
          public void onFailure(final Throwable pcaught) {
            try {
              throw pcaught;
            } catch (final ValidationException e) {
              AddressPresenterImpl.this.getClientFactory().getSepaView().getDriver()
                  .setConstraintViolations(e.getValidationErrorSet());
            } catch (final Throwable e) {
              final AddressConstants constants = GWT.create(AddressConstants.class);
              AddressPresenterImpl.this.getClientFactory().getSepaView()
                  .showMessage(constants.messageAddressDataError());
            }
          }

          @Override
          public void onSuccess(final PostalAddressData presult) {
            final AddressConstants constants = GWT.create(AddressConstants.class);
            if (presult == null) {
              AddressPresenterImpl.this.getClientFactory().getSepaView()
                  .showMessage(constants.messageAddressDataError());
            } else {
              AddressPresenterImpl.this.getClientFactory().getSepaView()
                  .showMessage(constants.messageAddressDataOk());
            }
          }
        });
  }

  @Override
  public final String mayStop() {
    // ignore this
    return null;
  }

  @Override
  public final void onCancel() {
    // ignore this
  }

  @Override
  public final void onStop() {
    // ignore this
  }
}