package com.ochoa.arnau.swissknife;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.ochoa.arnau.swissknife.Data.LoginHelper;


public class PagerHolderLogin extends FragmentActivity implements OnFragmentInteractionListener{

    LoginHelper loginHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager_holder_login);

        // Get the ViewPager and set it's PagerAdapter so that it can display items
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new PagerAdapterLogin(getSupportFragmentManager()));

        // Give the TabLayout the ViewPager (material)
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);

        //normal color - selected color
        tabLayout.setTabTextColors(Color.GREEN, Color.BLACK); // TODO: canviar colors
        tabLayout.setupWithViewPager(viewPager);
    }

    public boolean logIn(EditText username, EditText password){
        Boolean bool = false;

        loginHelper = new LoginHelper(getApplicationContext());

        Cursor cursor = loginHelper.getPasswordByName(String.valueOf(username.getText().toString()));

        String enteredPassword = String.valueOf(password.getText().toString());
        String dbPassword = null;

        if (cursor.moveToFirst()) {
            dbPassword = cursor.getString(cursor.getColumnIndex("password"));
        }

        if (enteredPassword.equals(dbPassword)) {
            bool = true;
        }
        return bool;
    }

    @Override
    public void incorrectPassword() {
        Toast.makeText(getApplicationContext(),"Incorrect password", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void addUser(EditText username, EditText password) {
        ContentValues valuesToStore = new ContentValues();
        valuesToStore.put("name", String.valueOf(username.getText()));
        valuesToStore.put("password", String.valueOf(password.getText()));
        loginHelper.createUser(valuesToStore, "Login");

        Toast.makeText(getApplicationContext(),getString(R.string.register_ok_toast), Toast.LENGTH_SHORT).show();
    }
}
