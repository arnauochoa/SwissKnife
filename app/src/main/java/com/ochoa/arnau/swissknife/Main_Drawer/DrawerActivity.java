package com.ochoa.arnau.swissknife.Main_Drawer;

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

import com.ochoa.arnau.swissknife.Calculator.CalculatorFragment;
import com.ochoa.arnau.swissknife.Memory.MemoryFragment;
import com.ochoa.arnau.swissknife.Ranking.RankingFragment;
import com.ochoa.arnau.swissknife.Music.MusicFragment;
import com.ochoa.arnau.swissknife.Profile.ProfileFragment;
import com.ochoa.arnau.swissknife.R;

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
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new RankingFragment(), "RANKING_FRAGMENT").commit();
                break;
            case R.id.music:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new MusicFragment(), "MUSIC_FRAGMENT").commit();
                break;
            case R.id.calculator:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new CalculatorFragment(), "MEMORY_FRAGMENT").commit();
                break;
            case R.id.logout:
                // TODO: Log Out
                break;
        }
        return true;
    }
}