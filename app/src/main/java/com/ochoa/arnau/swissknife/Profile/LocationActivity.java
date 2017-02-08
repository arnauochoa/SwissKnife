package com.ochoa.arnau.swissknife.Profile;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.ochoa.arnau.swissknife.Main_Drawer.DrawerActivity;
import com.ochoa.arnau.swissknife.R;

import java.io.IOException;
import java.util.List;

import io.realm.Realm;

public class LocationActivity extends AppCompatActivity implements View.OnClickListener{
    List<Address> addressList;
    LocationManager locationManager;
    LocationListener locationListener;

    String addr;

    TextView t;

    Button buttonNo, buttonYes;

    User user;
    Realm realm;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        t = (TextView) findViewById(R.id.location);

        buttonNo = (Button) findViewById(R.id.button_no);
        buttonNo.setOnClickListener(this);
        buttonYes = (Button) findViewById(R.id.button_yes);
        buttonYes.setOnClickListener(this);

        SharedPreferences settings = getSharedPreferences(String.valueOf(R.string.app_name), Context.MODE_PRIVATE);
        String username = settings.getString("username", "Username not found");

        realm = Realm.getDefaultInstance();
        user = realm.where(User.class).equalTo("name", username).findFirst();

        locationManager = (LocationManager) this
                .getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {

            @Override
            public void onStatusChanged(String provider, int status,
                                        Bundle extras) {
            }

            @Override
            public void onProviderEnabled(String provider) {
            }

            @Override
            public void onProviderDisabled(String provider) {
            }

            @Override
            public void onLocationChanged(Location location) {
                // TODO Auto-generated method stub
                Geocoder gc = new Geocoder(getApplicationContext());
                try {
                    //5 mxresults
                    addressList = gc.getFromLocation(location.getLatitude(),
                            location.getLongitude(), 5);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < addressList.size(); ++i) {
                    if (i == 0) t.setText("");
                    t.setText(t.getText() + "\n" + addressList.get(i).getAddressLine(0));
                }
            }
        };

        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {


            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION},
                    0);
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_no:
                Log.d("BUTTN NO","onClick: ");
                startActivity(new Intent(this, DrawerActivity.class));
                finish();
                break;
            case R.id.button_yes:
                realm.beginTransaction();
                user.setAddress(addressList.get(3).getAddressLine(0));
                user.setHasAddress(true);
                realm.commitTransaction();

                startActivity(new Intent(this, DrawerActivity.class));
                finish();
                break;
        }
    }
}
