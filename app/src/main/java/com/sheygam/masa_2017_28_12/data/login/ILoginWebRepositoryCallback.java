package com.sheygam.masa_2017_28_12.data.login;

/**
 * Created by gregorysheygam on 28/12/2017.
 */

public interface ILoginWebRepositoryCallback {
    void onLoginSuccess(String token);
    void onLoginError(String error);
    void onRegSuccess(String token);
    void onRegError(String error);
}
