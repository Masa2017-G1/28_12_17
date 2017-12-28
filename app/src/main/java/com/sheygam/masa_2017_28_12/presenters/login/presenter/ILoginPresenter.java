package com.sheygam.masa_2017_28_12.presenters.login.presenter;

import com.sheygam.masa_2017_28_12.presenters.login.view.ILoginView;

/**
 * Created by gregorysheygam on 28/12/2017.
 */

public interface ILoginPresenter {
    void bindView(ILoginView view);
    void unbindView();
    void makeLogin(String email, String password);
    void makeRegistration(String email, String password);
}
