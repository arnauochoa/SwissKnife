package com.ochoa.arnau.swissknife.Login_Register;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class PagerAdapterLogin extends FragmentPagerAdapter {

    private String tabTitles[] = new String[] { "Login", "Register"};
    private Fragment tab = null;

    public PagerAdapterLogin(FragmentManager fm) {
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                tab = new LoginFragment();
                break;
            case 1:
                tab = new RegisterFragment();
                break;
        }
        return tab;
    }
}
