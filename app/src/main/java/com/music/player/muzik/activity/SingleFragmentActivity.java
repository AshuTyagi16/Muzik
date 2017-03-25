package com.music.player.muzik.activity;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.music.player.muzik.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Ashu on 3/25/2017.
 */

public abstract class SingleFragmentActivity extends AppCompatActivity {


    protected abstract Fragment createFragment();

    protected abstract String setActionBarTitle();

    protected abstract boolean showActionBar();

    @ColorRes
    protected abstract int setActionBarColor();

    @BindView(R.id.toolbar)
    Toolbar mToolBar;

    @LayoutRes
    protected int getLayoutResId() {
        return R.layout.activity_single_fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        ButterKnife.bind(this);
        setSupportActionBar(mToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);
        if (fragment == null) {
            fragment = createFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }

        customizeActionBar();

    }


    private void customizeActionBar() {
        if (showActionBar()) {
            mToolBar.setVisibility(View.VISIBLE);
            if (setActionBarTitle() != null) {
                mToolBar.setTitle(setActionBarTitle());
            }
            if (setActionBarColor() != 0) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    mToolBar.setBackground(new ColorDrawable(getColor(setActionBarColor())));
                } else {
                    mToolBar.setBackground(new ColorDrawable(getResources().getColor(setActionBarColor())));
                }
            }

        } else {
            mToolBar.setVisibility(View.GONE);
        }
    }
//
//    @OnClick(R.id.action_bar_home_icon)
//    public void homeButtonClick() {
//        hideKeyboard();
//        this.onBackPressed();
//    }

    protected boolean hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (getCurrentFocus() != null) {
            return imm.hideSoftInputFromWindow(getCurrentFocus().getApplicationWindowToken(), 0);
        }
        return false;
    }

}
