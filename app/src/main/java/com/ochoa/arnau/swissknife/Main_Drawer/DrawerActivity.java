package com.ochoa.arnau.swissknife.Main_Drawer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.ochoa.arnau.swissknife.Calculator.CalculatorActivity;
import com.ochoa.arnau.swissknife.Login_Register.PagerHolderLogin;
import com.ochoa.arnau.swissknife.Memory.MemoryFragment;
import com.ochoa.arnau.swissknife.Music.MusicActivity;
import com.ochoa.arnau.swissknife.Profile.ProfileFragment;
import com.ochoa.arnau.swissknife.R;
import com.ochoa.arnau.swissknife.Ranking.RankingActivity;

public class DrawerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, OnFragmentInteractionListener_Main{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_drawer);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        );
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView =(NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Fragment f = new ProfileFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, f, "MEMORY_FRAGMENT")
                .commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.profile:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new ProfileFragment(), "PROFILE_FRAGMENT").commit();
                break;
            case R.id.memory:
                Log.d("Drawer", "onNavigationItemSelected: memory");
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new MemoryFragment(), "MEMORY_FRAGMENT").commit();
                break;
            case R.id.ranking:
                startActivity(new Intent(this, RankingActivity.class));
                break;
            case R.id.music:
                startActivity(new Intent(this, MusicActivity.class));
                break;
            case R.id.calculator:
                startActivity(new Intent(this, CalculatorActivity.class));
                break;
            case R.id.logout:
                SharedPreferences settings = getSharedPreferences(String.valueOf(R.string.app_name), 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putBoolean("UserLogged", false);
                editor.apply();

                startActivity(new Intent(this, PagerHolderLogin.class));
                finish();
                break;
        }
        return true;
    }
}
