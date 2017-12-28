package com.sheygam.masa_2017_28_12.data.login;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by gregorysheygam on 28/12/2017.
 */

public class LoginStoreRepository implements ILoginStoreRepository {
    private Context context;

    public LoginStoreRepository(Context context) {
        this.context = context;
    }

    @Override
    public void saveToken(String token) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AUTH",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("TOKEN",token);
        editor.commit();
    }
}
