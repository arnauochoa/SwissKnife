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
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.ochoa.arnau.swissknife.Data.DatabaseHelper;
import com.ochoa.arnau.swissknife.Main_Drawer.DrawerActivity;
import com.ochoa.arnau.swissknife.R;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import io.fabric.sdk.android.Fabric;


public class PagerHolderLogin extends FragmentActivity implements OnFragmentInteractionListener {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "25YYvET2Ci9R0TNAATVvUfK2K";
    private static final String TWITTER_SECRET = "Rgfrxf0AJ8thcsAl4Lwwwuyjn3jKIodmirD8cqjH7k6Je0jZv3";

    private TwitterLoginButton loginButton;

    DatabaseHelper databaseHelper;
    SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
        setContentView(R.layout.activity_pager_holder_login);

        // Get the ViewPager and set it's PagerAdapter so that it can display items
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new PagerAdapterLogin(getSupportFragmentManager()));

        // Give the TabLayout the ViewPager (material)
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);

        //normal color - selected color
        tabLayout.setTabTextColors(Color.GRAY, Color.BLACK); // TODO: canviar colors
        tabLayout.setupWithViewPager(viewPager);

        databaseHelper = new DatabaseHelper(getApplicationContext());

        loginButton = (TwitterLoginButton) findViewById(R.id.twitter_login_button);
        loginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                TwitterSession session = result.data;

                SharedPreferences settings = getSharedPreferences(String.valueOf(R.string.app_name), 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putString("username", session.getUserName());
                editor.apply();

                startActivity(new Intent(getApplicationContext(), DrawerActivity.class));
                finish();

            }
            @Override
            public void failure(TwitterException exception) {
                Log.d("TwitterKit", "Login with Twitter failure", exception);
            }
        });


        settings = getSharedPreferences(String.valueOf(R.string.app_name), Context.MODE_PRIVATE);
        checkLogIn();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        loginButton.onActivityResult(requestCode, resultCode, data);
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

        Cursor cursor = databaseHelper.getPasswordByName(String.valueOf(username.getText().toString()));

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

        Cursor cursor = databaseHelper.checkIfExists(String.valueOf(username.getText().toString()));

        if (!cursor.moveToFirst()) {
            validUser = true;

            ContentValues valuesToStore = new ContentValues();
            valuesToStore.put("name", String.valueOf(username.getText().toString()));
            valuesToStore.put("password", String.valueOf(password.getText().toString()));
            databaseHelper.createUser(valuesToStore, "Users");

            SharedPreferences.Editor editor = settings.edit();
            editor.putBoolean("UserLogged", true);
            editor.apply();

            Toast.makeText(getApplicationContext(),getString(R.string.register_ok_toast), Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(),getString(R.string.register_not_ok_toast), Toast.LENGTH_SHORT).show();
        }
        return validUser;
    }
}
