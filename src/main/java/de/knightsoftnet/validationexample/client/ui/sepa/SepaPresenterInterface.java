package de.knightsoftnet.validationexample.client.ui.sepa;

import de.knightsoftnet.validationexample.client.basepage.BasePresenterInterface;

/**
 * Activity/Presenter of the login, interface.
 *
 * @author Manfred Tremmel
 *
 */
public interface SepaPresenterInterface extends BasePresenterInterface {
  /**
   * try to send data.
   */
  void tryToSend();
}