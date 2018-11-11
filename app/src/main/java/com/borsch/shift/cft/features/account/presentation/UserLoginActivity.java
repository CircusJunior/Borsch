package com.borsch.shift.cft.features.account.presentation;


import android.content.Context;
import android.content.Intent;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.borsch.shift.cft.R;
import com.borsch.shift.cft.features.BaseActivity;
import com.borsch.shift.cft.features.MvpPresenter;
import com.borsch.shift.cft.features.MvpView;
import com.borsch.shift.cft.features.UserActivity;
import com.borsch.shift.cft.features.account.domain.model.Account;
import com.borsch.shift.cft.features.account.domain.model.UserValidInfo;
import com.borsch.shift.cft.features.borsch.presentation.ContentView;

public final class UserLoginActivity extends UserActivity implements AccountView {

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private EditText mRepitPassword;
    private View mProgressView;
    private View mLoginFormView;
    private View mPasswordFormView;
    private AccountPresenter presenter;
    private Button mEmailSignInButton;
    private Button mRegisrationButton;
    private int counterClickRegistration;

    public static final String APP_PREFERENCES = "mysettings";
    public static final String APP_PREFERENCES_NAME = "Login";
    public static final String APP_PREFERENCES_TOKEN = "Token";
    public static final String APP_PREFERENCES_ID = "idUser";
    public SharedPreferences mSettings;

    @Override
    protected UserLoginActivity getMvpView() {
        return this;
    }

    @Override
    protected MvpPresenter<AccountView> getPresenter() {
        presenter = PresenterFactory.createPresenter(this);
        return presenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        initView();
    }

    private void initView() {
        mEmailView = (AutoCompleteTextView) findViewById(R.id.login);
        mPasswordView = (EditText) findViewById(R.id.password);
        mRepitPassword = (EditText) findViewById(R.id.repit_password);
        mPasswordFormView = (View) findViewById(R.id.iput_repit_password);

        mEmailSignInButton = (Button) findViewById(R.id.login_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.checkLogIn(mEmailView, mPasswordView, mSettings, APP_PREFERENCES_NAME, APP_PREFERENCES_TOKEN);
            }
        });

        counterClickRegistration = 0;
        mRegisrationButton = (Button) findViewById(R.id.registration_button);
        mRegisrationButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(counterClickRegistration == 0){
                    oneClickRegistration();
                } else {
                    twoClickRegistration();
                }
            }
        });

        mLoginFormView = findViewById(R.id.login_form);

    }

    private void oneClickRegistration(){
        counterClickRegistration = 1;
        mEmailSignInButton.setVisibility(View.GONE);
        mPasswordFormView.setVisibility(View.VISIBLE);
    }

    private void twoClickRegistration(){

        if(mPasswordView.getText().toString().equals(mRepitPassword.getText().toString())){
            presenter.setRegistration(mEmailView, mPasswordView, mSettings, APP_PREFERENCES_NAME, APP_PREFERENCES_TOKEN);
        } else {
            showError("Пароли не совпадают!");
        }

    }

    @Override
    public void showAccount(Account account) {

    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void hideActivity(String active, UserValidInfo user) {
        super.onStop();
        Intent intent = new Intent(UserLoginActivity.this, AccountActivity.class);

        intent.putExtra("UserValidInfo", user);
        startActivity(intent);
    }

    @Override
    public void exitActivity() {

    }
}

