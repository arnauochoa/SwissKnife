package com.ochoa.arnau.swissknife.Login_Register;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.ochoa.arnau.swissknife.Data.LoginHelper;
import com.ochoa.arnau.swissknife.Main_Drawer.DrawerActivity;
import com.ochoa.arnau.swissknife.R;


public class PagerHolderLogin extends FragmentActivity implements OnFragmentInteractionListener {

    LoginHelper loginHelper;
    SharedPreferences settings;

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
        tabLayout.setTabTextColors(Color.GRAY, Color.BLACK); // TODO: canviar colors
        tabLayout.setupWithViewPager(viewPager);

        loginHelper = new LoginHelper(getApplicationContext());

        settings = getSharedPreferences(String.valueOf(R.string.app_name), Context.MODE_PRIVATE);
        checkLogIn();
    }

    private void checkLogIn() {
        boolean UserLogged = settings.getBoolean("UserLogged", false);
        if(UserLogged){
            Intent intent = new Intent(this, DrawerActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public boolean logIn(EditText username, EditText password){
        Boolean correctPsw = false;

        Cursor cursor = loginHelper.getPasswordByName(String.valueOf(username.getText().toString()));

        String enteredPassword = String.valueOf(password.getText().toString());
        String dbPassword = null;

        if (cursor.moveToFirst()) {
            dbPassword = cursor.getString(cursor.getColumnIndex("password"));

            if (enteredPassword.equals(dbPassword)) {
                SharedPreferences.Editor editor = settings.edit();
                editor.putBoolean("UserLogged", true);
                editor.apply();
                correctPsw = true;
            }
        }else{
            Toast.makeText(getApplicationContext(), getString(R.string.user_not_exist), Toast.LENGTH_SHORT).show();
        }
        return correctPsw;
    }


    @Override
    public void incorrectPassword() {
        Toast.makeText(getApplicationContext(), getString(R.string.incorrect_password), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void emptyField() {
        Toast.makeText(getApplicationContext(), getString(R.string.empty_field), Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean addUser(EditText username, EditText password) {
        boolean validUser = false;

        Cursor cursor = loginHelper.checkIfExists(String.valueOf(username.getText().toString()));

        if (!cursor.moveToFirst()) {
            validUser = true;

            ContentValues valuesToStore = new ContentValues();
            valuesToStore.put("name", String.valueOf(username.getText().toString()));
            valuesToStore.put("password", String.valueOf(password.getText().toString()));
            loginHelper.createUser(valuesToStore, "Users");

            Toast.makeText(getApplicationContext(),getString(R.string.register_ok_toast), Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(),getString(R.string.register_not_ok_toast), Toast.LENGTH_SHORT).show();
        }
        return validUser;
    }
}
