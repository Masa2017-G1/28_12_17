package com.sheygam.masa_2017_28_12.business.login;

/**
 * Created by gregorysheygam on 28/12/2017.
 */

public interface ILoginInteractorCallback {
    void onSuccess();
    void onError(String error);
}
