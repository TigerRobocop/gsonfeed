package com.unibratec.livia.gsonfeed;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class UserDetailsActivity extends AppCompatActivity {

    public static final String EXTRA_USER = "user";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        User user = (User)intent.getSerializableExtra(EXTRA_USER);

        UserDetailsFragment detailsFragment = UserDetailsFragment.newInstance(user);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        ft.replace(R.id.details, detailsFragment, UserDetailsFragment.TAG_DETAILS);
        ft.commit();

    }
}
