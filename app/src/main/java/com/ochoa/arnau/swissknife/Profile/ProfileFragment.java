package com.ochoa.arnau.swissknife.Profile;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.location.Address;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.ochoa.arnau.swissknife.Data.DatabaseHelper;
import com.ochoa.arnau.swissknife.Main_Drawer.OnFragmentInteractionListener_Main;
import com.ochoa.arnau.swissknife.R;

import java.io.IOException;
import java.util.List;

import io.realm.Realm;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment implements View.OnClickListener{

    FloatingActionButton edit_fab;

    ImageButton location_button;

    OnFragmentInteractionListener_Main mListener;

    TextView usernameView, easyScore, mediumScore, hardScore;

    DatabaseHelper databaseHelper;

    ImageView image;

    List<Address> addressList;
    LocationManager locationManager;
    LocationListener locationListener;

    int eScore = 0;
    int mScore = 0;
    int hScore = 0;

    TextView addressTextView;

    User user;
    Realm realm;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        image = (ImageView) rootView.findViewById(R.id.profile_img);

        edit_fab = (FloatingActionButton) rootView.findViewById(R.id.edit_picture_fab);
        edit_fab.setOnClickListener(this);

        location_button = (ImageButton) rootView.findViewById(R.id.location_button);
        location_button.setOnClickListener(this);

        SharedPreferences settings = getActivity().getSharedPreferences(String.valueOf(R.string.app_name), Context.MODE_PRIVATE);
        String username = settings.getString("username", "Username not found");

        usernameView = (TextView) rootView.findViewById(R.id.username_textView);
        usernameView.setText(username);

        databaseHelper = new DatabaseHelper(getActivity().getApplicationContext());

        Cursor cursor;

        cursor = databaseHelper.getBestScoreByName(username, "easy");
        if (cursor.moveToFirst()){
            eScore = cursor.getInt(cursor.getColumnIndex("score"));
        }
        easyScore = (TextView) rootView.findViewById(R.id.easy_score);
        easyScore.setText("Easy Score: " + String.valueOf(eScore));

        cursor = databaseHelper.getBestScoreByName(username, "medium");
        if (cursor.moveToFirst()){
            mScore = cursor.getInt(cursor.getColumnIndex("score"));
        }
        mediumScore = (TextView) rootView.findViewById(R.id.medium_score);
        mediumScore.setText("Medium Score: " + String.valueOf(mScore));

        cursor = databaseHelper.getBestScoreByName(username, "hard");
        if (cursor.moveToFirst()){
            hScore = cursor.getInt(cursor.getColumnIndex("score"));
        }
        hardScore = (TextView) rootView.findViewById(R.id.hard_score);
        hardScore.setText("Hard Score: " + String.valueOf(hScore));

        realm = Realm.getDefaultInstance();
        user = realm.where(User.class).equalTo("name", username).findFirst();

        image = (ImageView) rootView.findViewById(R.id.profile_img);

        if(user.hasUri()){
            // TODO: 08/02/2017 Problema cyanogen permisos
//            try {
//                Uri imageUser = Uri.parse(user.getUri());
//                image.setImageBitmap(MediaStore.Images.Media.getBitmap(getActivity().getApplicationContext().getContentResolver(), imageUser));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }else{
            image.setImageResource(R.drawable.app_logo);
        }

        addressTextView = (TextView) rootView.findViewById(R.id.address);
        if (user.hasAddress()){
            String address = user.getAddress();
            addressTextView.setText(address);
        }else{
            addressTextView.setText("No address yet");
        }

        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mListener = (OnFragmentInteractionListener_Main) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.edit_picture_fab:
                Intent getImageAsContent = new Intent(Intent.ACTION_GET_CONTENT, null);
                getImageAsContent.setType("image/*");
                startActivityForResult(getImageAsContent, 1);
                break;
            case R.id.location_button:
                startActivity(new Intent(getActivity(), LocationActivity.class));
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            if(requestCode >= 1 && requestCode <= 3){
                Uri selectedImage = data.getData();

                realm.beginTransaction();
                user.setUri(selectedImage.toString());
                user.setHasUri(true);
                realm.commitTransaction();

                Log.v("PICK","Selected image uri" + selectedImage);
                try {
                    image.setImageBitmap(MediaStore.Images.Media.getBitmap(getActivity().getApplicationContext().getContentResolver(), selectedImage));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else{
            Log.v("Result","Something happened");
        }
    }
}
