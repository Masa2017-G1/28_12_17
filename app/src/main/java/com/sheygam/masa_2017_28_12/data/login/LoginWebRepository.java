package com.sheygam.masa_2017_28_12.data.login;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.sheygam.masa_2017_28_12.data.dao.Auth;
import com.sheygam.masa_2017_28_12.data.dao.AuthToken;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by gregorysheygam on 28/12/2017.
 */

public class LoginWebRepository implements ILoginWebRepository {
    private static final String BASE_URL = "https://telranstudentsproject.appspot.com/_ah/api/contactsApi/v1";
    private OkHttpClient client;
    private Gson gson;

    public LoginWebRepository(OkHttpClient client, Gson gson) {
        this.client = client;
        this.gson = gson;
    }

    @Override
    public void registration(String email, String password, ILoginWebRepositoryCallback callback) {
        new RegTask(email,password,callback).execute();
    }

    @Override
    public void login(String email, String password, ILoginWebRepositoryCallback callback) {
        new LoginTask(email,password,callback).execute();
    }

    private class LoginTask extends AsyncTask<Void,Void,Void>{
        String email, password;
        ILoginWebRepositoryCallback callback;

        public LoginTask(String email, String password, ILoginWebRepositoryCallback callback) {
            this.email = email;
            this.password = password;
            this.callback = callback;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Auth auth = new Auth(email,password);
            String data = gson.toJson(auth);
            MediaType json = MediaType.parse("application/json; charset=utf-8");
            RequestBody body = RequestBody.create(json,data);

            Request request = new Request.Builder()
                    .url(BASE_URL + "/login")
                    .post(body)
                    .build();

            try {
                Response response = client.newCall(request).execute();
                if(response.code() < 400){
                    String jsonData = response.body().string();
                    Log.d("MY_TAG", "doInBackground: login:" + jsonData);
                    AuthToken token = gson.fromJson(jsonData,AuthToken.class);
                    callback.onLoginSuccess(token.getToken());
                }else if(response.code() == 401){
                    callback.onLoginError("Wrong login or password!");
                }else{
                    callback.onLoginError("Server error!");
                    String error = response.body().string();
                    Log.d("MY_TAG", "doInBackground: login error: " + error);
                }
            } catch (IOException e) {
                e.printStackTrace();
                callback.onLoginError("Connection error!");
            }
            return null;
        }
    }

    private class RegTask extends AsyncTask<Void,Void,Void>{
        String email, password;
        ILoginWebRepositoryCallback callback;

        public RegTask(String email, String password, ILoginWebRepositoryCallback callback) {
            this.email = email;
            this.password = password;
            this.callback = callback;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Auth auth = new Auth(email,password);
            String data = gson.toJson(auth);
            MediaType json = MediaType.parse("application/json; charset=utf-8");
            RequestBody body = RequestBody.create(json,data);
            Request request = new Request.Builder()
                    .url(BASE_URL + "/registration")
                    .post(body)
                    .build();

            try {
                Response response = client.newCall(request).execute();
                if(response.code() < 400){
                    String jsonData = response.body().string();
                    Log.d("MY_TAG", "doInBackground: reg: " + jsonData);
                    AuthToken token = gson.fromJson(jsonData,AuthToken.class);
                    callback.onRegSuccess(token.getToken());
                }else if(response.code() == 409){
                    callback.onRegError("User already exist!");
                }else{
                    String error = response.body().string();
                    Log.d("MY_TAG", "doInBackground: reg error: " + error);
                    callback.onRegError("Server error!");
                }
            } catch (IOException e) {
                e.printStackTrace();
                callback.onRegError("Connection error!");
            }
            return null;
        }
    }
}
