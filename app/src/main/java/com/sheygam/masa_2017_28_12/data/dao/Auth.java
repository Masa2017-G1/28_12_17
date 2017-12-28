package com.sheygam.masa_2017_28_12.data.dao;

/**
 * Created by gregorysheygam on 28/12/2017.
 */

public class Auth {
    private String email, password;

    public Auth() {
    }

    public Auth(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
