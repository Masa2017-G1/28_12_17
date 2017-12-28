package com.sheygam.masa_2017_28_12.presenters.login.view;

/**
 * Created by gregorysheygam on 28/12/2017.
 */

public interface ILoginView {
    void showProgress();
    void hideProgress();
    void showError(String error);
    void showSuccess(String msg);
    void invalidEmail();
    void invalidPassword();
}
