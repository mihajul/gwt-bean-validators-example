package de.knightsoftnet.validationexample.client.ui.sepa;

import de.knightsoftnet.validationexample.client.basepage.AbstractPresenter;
import de.knightsoftnet.validationexample.client.ClientFactoryInterface;
import de.knightsoftnet.validationexample.client.JsHelper;
import de.knightsoftnet.validationexample.shared.models.CountryEnum;
import de.knightsoftnet.validationexample.shared.models.SepaData;
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
public class SepaPresenterImpl extends AbstractPresenter implements SepaPresenterInterface {
  /**
   * three hundred milliseconds.
   */
  public static final int FOCUS_DELAY = 300;

  /**
   * sepa data to remember.
   */
  private final SepaData sepaData;

  /**
   * view of the page.
   */
  private SepaViewInterface view;

  /**
   * the constructor of the activity/presenter.
   *
   * @param pclientFactory client factory
   */
  public SepaPresenterImpl(final ClientFactoryInterface pclientFactory) {
    super(pclientFactory);
    this.sepaData = new SepaData();
    this.sepaData.setCountryCode(CountryEnum.DE);
  }

  @Override
  public final void start(final AcceptsOneWidget ppanel, final EventBus peventBus) {
    GWT.runAsync(new RunAsyncCallback() {

      @Override
      public void onSuccess() {
        SepaPresenterImpl.this.view = SepaPresenterImpl.this.getClientFactory().getSepaView();
        SepaPresenterImpl.this.view.setPresenter(SepaPresenterImpl.this);
        SepaPresenterImpl.this.view.fillForm(SepaPresenterImpl.this.sepaData);
        ppanel.setWidget(SepaPresenterImpl.this.view.asWidget());
        Scheduler.get().scheduleFixedDelay(new RepeatingCommand() {
          @Override
          public boolean execute() {
            SepaPresenterImpl.this.view.setFocusOnFirstWidget();
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
    this.getClientFactory().getSepaService().sendSepa(this.sepaData, new AsyncCallback<SepaData>() {
      @Override
      public void onFailure(final Throwable pcaught) {
        try {
          throw pcaught;
        } catch (final ValidationException e) {
          SepaPresenterImpl.this.getClientFactory().getSepaView().getDriver()
              .setConstraintViolations(e.getValidationErrorSet());
        } catch (final Throwable e) {
          final SepaConstants constants = GWT.create(SepaConstants.class);
          SepaPresenterImpl.this.getClientFactory().getSepaView()
              .showMessage(constants.messageSepaError());
        }
      }

      @Override
      public void onSuccess(final SepaData presult) {
        final SepaConstants constants = GWT.create(SepaConstants.class);
        if (presult == null) {
          SepaPresenterImpl.this.getClientFactory().getSepaView()
              .showMessage(constants.messageSepaError());
        } else {
          SepaPresenterImpl.this.getClientFactory().getSepaView()
              .showMessage(constants.messageSepaOk());
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