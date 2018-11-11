package com.borsch.shift.cft.features.account.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.borsch.shift.cft.R;
import com.borsch.shift.cft.features.BaseActivity;
import com.borsch.shift.cft.features.MvpPresenter;
import com.borsch.shift.cft.features.MvpView;
import com.borsch.shift.cft.features.UserActivity;
import com.borsch.shift.cft.features.account.domain.model.Account;
import com.borsch.shift.cft.features.account.domain.model.UserValidInfo;
import com.borsch.shift.cft.features.borsch.presentation.ContentView;

public final class EditActivity extends UserActivity implements AccountView {

    private Button edit;
    private Button close;

    private EditText editFirstName;
    private EditText editSecondName;
    private EditText editCity;
    private EditText editUniversity;
    private EditText editDormitory;
    private EditText editRoom;
    private EditText editVkontakte;
    private EditText editTelegram;
    private EditText editEmail;

    private AccountPresenter presenter;
    private UserValidInfo userValidInfo;

    @Override
    protected EditActivity getMvpView() {
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
        setContentView(R.layout.activity_edit);

        userValidInfo = (UserValidInfo)getIntent().getParcelableExtra("UserValidInfo");

        initView();
    }

    private void initView(){

        editFirstName = (EditText) findViewById(R.id.edit_firstName);
        editSecondName = (EditText) findViewById(R.id.edit_secondName);
        editCity = (EditText) findViewById(R.id.edit_city);
        editUniversity = (EditText) findViewById(R.id.edit_university);
        editDormitory = (EditText) findViewById(R.id.edit_dormitory);
        editRoom = (EditText) findViewById(R.id.edit_room);
        editVkontakte = (EditText) findViewById(R.id.edit_vkontakte);
        editTelegram = (EditText) findViewById(R.id.edit_telegram);
        editEmail = (EditText) findViewById(R.id.edit_email);

        Button edit = (Button) findViewById(R.id.edit_button);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onCreateAccountClicked(userValidInfo, editFirstName, editSecondName, editCity, editUniversity, editDormitory,
                        editRoom, editVkontakte, editTelegram, editEmail);
            }
        });

        Button close = (Button) findViewById(R.id.edit_close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onCloseClicked(userValidInfo);
            }
        });
    }

    protected void onStart() {
        super.onStart();
        presenter.onViewReady(userValidInfo);
        //contentPresenter.onViewReady();
    }

    @Override
    public void showAccount(Account account) {
        editFirstName.setText(account.getFirstName());
        editSecondName.setText(account.getSecondName());
        editCity.setText(account.getCity());
        editUniversity.setText(account.getUniversity());
        editDormitory.setText(account.getDormitory());
        editRoom.setText(account.getRoom());
        editVkontakte.setText(account.getVkontakte());
        editTelegram.setText(account.getTelegram());
        editEmail.setText(account.getEmail());
    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void hideActivity(String active, UserValidInfo user) {
        super.onStop();
        Intent intent = new Intent(EditActivity.this, AccountActivity.class);
        intent.putExtra("UserValidInfo", user);
        startActivity(intent);
    }

    @Override
    public void exitActivity() {

    }
}
