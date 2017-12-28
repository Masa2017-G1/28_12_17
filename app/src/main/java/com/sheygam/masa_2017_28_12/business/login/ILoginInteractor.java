package com.sheygam.masa_2017_28_12.business.login;

/**
 * Created by gregorysheygam on 28/12/2017.
 */

public interface ILoginInteractor {
    void login(String email, String password, ILoginInteractorCallback callback) throws EmailValidException,PasswordValidException;
    void registration(String email, String password, ILoginInteractorCallback callback) throws EmailValidException, PasswordValidException;

}
