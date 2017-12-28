package com.sheygam.masa_2017_28_12.presenters.login.presenter;

import android.os.Handler;

import com.sheygam.masa_2017_28_12.business.login.EmailValidException;
import com.sheygam.masa_2017_28_12.business.login.ILoginInteractor;
import com.sheygam.masa_2017_28_12.business.login.ILoginInteractorCallback;
import com.sheygam.masa_2017_28_12.business.login.PasswordValidException;
import com.sheygam.masa_2017_28_12.presenters.login.view.ILoginView;



/**
 * Created by gregorysheygam on 28/12/2017.
 */

public class LoginPresenter implements ILoginPresenter, ILoginInteractorCallback {
    private ILoginView view;
    private Handler handler;
    private ILoginInteractor interactor;

    public LoginPresenter(Handler handler, ILoginInteractor interactor) {
        this.handler = handler;
        this.interactor = interactor;
    }

    @Override
    public void bindView(ILoginView view) {
        this.view = view;
    }

    @Override
    public void unbindView() {
        this.view = null;
    }

    @Override
    public void makeLogin(String email, String password) {
        try {
            interactor.login(email, password,this);
            view.showProgress();
        } catch (EmailValidException e) {
            e.printStackTrace();
            view.invalidEmail();
        } catch (PasswordValidException e) {
            e.printStackTrace();
            view.invalidPassword();
        }
    }

    @Override
    public void makeRegistration(String email, String password) {
        try {
            interactor.registration(email, password, this);
            view.showProgress();
        } catch (EmailValidException e) {
            e.printStackTrace();
            view.invalidEmail();
        } catch (PasswordValidException e) {
            e.printStackTrace();
            view.invalidPassword();
        }
    }

    @Override
    public void onSuccess() {
        handler.post(() -> {
            if (view != null){
                view.hideProgress();
                view.showSuccess("Status OK!");
            }
        });
    }

    @Override
    public void onError(final String error) {
        handler.post(() -> {
            if(view != null){
                view.hideProgress();
                view.showError(error);
            }
        });
    }
}
