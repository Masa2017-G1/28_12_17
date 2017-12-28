package com.sheygam.masa_2017_28_12.data.login;

/**
 * Created by gregorysheygam on 28/12/2017.
 */

public interface ILoginWebRepository {
    void registration(String email, String password, ILoginWebRepositoryCallback callback);
    void login(String email, String password, ILoginWebRepositoryCallback callback);
}
