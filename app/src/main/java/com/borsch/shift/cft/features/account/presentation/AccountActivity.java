package com.borsch.shift.cft.features.account.presentation;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.borsch.shift.cft.R;
import com.borsch.shift.cft.features.BaseActivity;
import com.borsch.shift.cft.features.MvpPresenter;
import com.borsch.shift.cft.features.account.domain.model.Account;
import com.borsch.shift.cft.features.account.domain.model.UserValidInfo;
import com.borsch.shift.cft.features.borsch.domain.model.Fridge;
import com.borsch.shift.cft.features.borsch.presentation.ContentPresenterFactory;
import com.borsch.shift.cft.features.borsch.presentation.ContentPresenter;
import com.borsch.shift.cft.features.borsch.presentation.ContentView;
import com.borsch.shift.cft.features.borsch.presentation.PlaceholderFragment;
import com.borsch.shift.cft.features.borsch.presentation.SectionsPagerAdapter;

public final class AccountActivity extends BaseActivity implements AccountView, ContentView{

    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private AccountPresenter presenter;
    private Button edit;
    private Button exit;

    private UserValidInfo userValidInfo;

    private TextView firstName;
    private TextView secondName;
    private TextView city;
    private TextView university;
    private TextView dormitory;
    private TextView room;
    private TextView eatRate;
    private TextView cookRate;

    private ContentPresenter contentPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        userValidInfo = (UserValidInfo)getIntent().getParcelableExtra("UserValidInfo");

        initView();
    }

    private void initView() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);

        mTabLayout = (TabLayout) findViewById(R.id.tablayout);
        mTabLayout.setupWithViewPager(mViewPager);

        /**<DrawerLayout>*/
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        mTitle = mDrawerTitle = getTitle();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                toolbar, R.string.nav_header_desc, R.string.nav_header_desc) {
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu();
            }
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        firstName = (TextView) findViewById(R.id.firstName);
        secondName = (TextView) findViewById(R.id.secondName);
        city = (TextView) findViewById(R.id.city);
        university = (TextView) findViewById(R.id.university);
        dormitory = (TextView) findViewById(R.id.dormitory);
        room = (TextView) findViewById(R.id.room);
        eatRate = (TextView) findViewById(R.id.eatRate);
        cookRate = (TextView) findViewById(R.id.cookRate);

        edit = (Button) findViewById(R.id.edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onEditClick(userValidInfo);
            }
        });

        exit = (Button) findViewById(R.id.exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onExitClicked(userValidInfo);
            }
        });
    }

    protected void onStart() {
        super.onStart();
        presenter.onViewReady(userValidInfo);

    }

    @Override
    protected AccountActivity getMvpView() {
        return this;
    }

    @Override
    protected AccountActivity getContentMvpView() {
        return this;
    }

    @Override
    protected MvpPresenter<AccountView> getPresenter() {
        presenter = PresenterFactory.createPresenter(this);
        return presenter;
    }

    @Override
    protected ContentPresenter getContentPresenter() {
        contentPresenter =  ContentPresenterFactory.createContentPresenter(this);
        return  contentPresenter;
    }


    @Override
    public void showAccount(Account account) {

        firstName.setText(account.getFirstName());
        secondName.setText(account.getSecondName());
        city.setText(account.getCity());
        university.setText(account.getUniversity());
        dormitory.setText(account.getDormitory());
        room.setText(account.getRoom());
        eatRate.setText(String.valueOf(account.getEatRate()));
        cookRate.setText(String.valueOf(account.getCookRate()));
    }

    @Override
    public void showFridge(Fridge fridge) {
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    public void exitActivity() {
        super.onStop();
            Intent intent = new Intent(AccountActivity.this, UserLoginActivity.class);
            startActivity(intent);
        }

    @Override
    public void hideActivity(String activ, UserValidInfo user) {
        super.onStop();
        if(activ == "Edit") {
            Intent intent = new Intent(AccountActivity.this, EditActivity.class);
            intent.putExtra("UserValidInfo", user);
            startActivity(intent);
        }
    }

    private void setupViewPager(ViewPager viewPager) {

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mSectionsPagerAdapter.addFragment(new PlaceholderFragment(), "Холодильник");
        mSectionsPagerAdapter.addFragment(new PlaceholderFragment(), "Рецепты");
        mSectionsPagerAdapter.addFragment(new PlaceholderFragment(), "Ответы");
        mSectionsPagerAdapter.addFragment(new PlaceholderFragment(), "Запросы");

        viewPager.setAdapter(mSectionsPagerAdapter);


    }
}
