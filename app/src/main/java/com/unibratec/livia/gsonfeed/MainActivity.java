package com.unibratec.livia.gsonfeed;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements UserListFragment.OnUserClick {

    private FragmentManager mFragmentManager;
    private UserListFragment mUserListFragment;
    TabLayout mTb;
    ViewPager mVp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mVp = (ViewPager)findViewById(R.id.viewpager_main);
        mVp.setAdapter(new PageAdapter(getSupportFragmentManager()));

        mTb = (TabLayout) findViewById(R.id.tablayout_tabs);
        mTb.setupWithViewPager(mVp);
    }

    @Override
    public void userClick(User user) {
        if (isTablet()) {
            UserDetailsFragment fragment = UserDetailsFragment.newInstance(user);

            FragmentTransaction ft = mFragmentManager.beginTransaction();
            ft.replace(R.id.details, fragment, UserDetailsFragment.TAG_DETAILS);
            ft.commit();
        } else {
            Intent intent = new Intent(this, UserDetailsActivity.class);
            intent.putExtra(UserDetailsActivity.EXTRA_USER, user);
            startActivity(intent);
        }
    }

    private boolean isTablet() {
        return getResources().getBoolean(R.bool.tablet);
    }

    private class PageAdapter extends FragmentPagerAdapter {

        public PageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return new UserListFragment();
            } else {
                return new Fragment();
            }
        }

        @Override
        public int getCount() {
            return 2;
        }

        public CharSequence getPageTitle(int pos){
            if (pos == 0){
                return "List";
            }else   {
                return "Faves";
            }
        }
    }


}
