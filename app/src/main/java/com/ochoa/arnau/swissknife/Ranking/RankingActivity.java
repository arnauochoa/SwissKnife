package com.ochoa.arnau.swissknife.Ranking;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.ochoa.arnau.swissknife.R;

public class RankingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.getMenu().getItem(0).setChecked(true);

        Fragment fragment = new EasyRankingFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.rankin_container, fragment)
                .commit();

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment f = null;
                        switch (item.getItemId()) {
                            case R.id.easy_bottom:
                                f = new EasyRankingFragment();
                                break;
                            case R.id.medium_bottom:
                                f = new MediumRankingFragment();
                                break;
                            case R.id.hard_bottom:
                                f = new HardRankingFragment();
                                break;
                        }
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fragment_container,f)
                                .commit();
                        return true;
                    }
                });
    }
}
