package com.sheygam.masa_2017_28_12.presenters.login.view;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;
import com.sheygam.masa_2017_28_12.ImageActivity;
import com.sheygam.masa_2017_28_12.R;
import com.sheygam.masa_2017_28_12.business.login.LoginInteractor;
import com.sheygam.masa_2017_28_12.data.login.LoginStoreRepository;
import com.sheygam.masa_2017_28_12.data.login.LoginWebRepository;
import com.sheygam.masa_2017_28_12.presenters.login.presenter.ILoginPresenter;
import com.sheygam.masa_2017_28_12.presenters.login.presenter.LoginPresenter;

import java.util.ArrayList;

import okhttp3.OkHttpClient;

public class LoginActivity extends AppCompatActivity implements ILoginView, View.OnClickListener {

    private EditText inputEmail, inputPassword;
    private Button registrationBtn, loginBtn;
    private ProgressBar progress;
    private ILoginPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Handler handler = new Handler();
        Gson gson = new Gson();
        OkHttpClient client = new OkHttpClient();
        LoginWebRepository webRepository = new LoginWebRepository(client,gson);
        LoginStoreRepository storeRepository = new LoginStoreRepository(this);
        LoginInteractor interactor = new LoginInteractor(webRepository,storeRepository);
        presenter = new LoginPresenter(handler,interactor);

        inputEmail = findViewById(R.id.inputEmail);
        inputPassword = findViewById(R.id.inputPassword);
        registrationBtn = findViewById(R.id.regBtn);
        loginBtn = findViewById(R.id.loginBtn);
        progress = findViewById(R.id.myProgress);
        loginBtn.setOnClickListener(this);
        registrationBtn.setOnClickListener(this);

        loginBtn.setOnClickListener(v -> {
            cleanInputErrors();
            presenter.makeLogin(inputEmail.getText().toString(),inputPassword.getText().toString());
        });

        registrationBtn.setOnClickListener(v -> {
            cleanInputErrors();
            presenter.makeRegistration(inputEmail.getText().toString(),inputPassword.getText().toString());
        });

//        ArrayList<String> list = new ArrayList<>();
//        for (int i = 0; i < 100; i++){
//            list.add("Vasya" + i);
//        }
//
//        for(String str : list){
//            System.out.println(str);
//        }
//
//        list.forEach(System.out::println);
    }

    @Override
    public void showProgress() {
        inputEmail.setVisibility(View.INVISIBLE);
        inputPassword.setVisibility(View.INVISIBLE);
        loginBtn.setVisibility(View.INVISIBLE);
        registrationBtn.setVisibility(View.INVISIBLE);
        progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        inputPassword.setVisibility(View.VISIBLE);
        inputEmail.setVisibility(View.VISIBLE);
        loginBtn.setVisibility(View.VISIBLE);
        registrationBtn.setVisibility(View.VISIBLE);
        progress.setVisibility(View.GONE);
    }

    @Override
    public void showError(String error) {
        new AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage(error)
                .setPositiveButton("Ok",null)
                .create()
                .show();
    }

    @Override
    public void showSuccess(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, ImageActivity.class));
    }

    @Override
    public void invalidEmail() {
        inputEmail.setError("Email needs contains @!");
    }

    @Override
    public void invalidPassword() {
        inputPassword.setError("Password length needs bigger 4 symbols!");
    }

    private void cleanInputErrors(){
        inputPassword.setError(null);
        inputEmail.setError(null);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.loginBtn){
            cleanInputErrors();
            presenter.makeLogin(inputEmail.getText().toString(),inputPassword.getText().toString());
        }else if(v.getId() == R.id.regBtn){
            cleanInputErrors();
            presenter.makeRegistration(inputEmail.getText().toString(),inputPassword.getText().toString());
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.bindView(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.unbindView();
    }
}
