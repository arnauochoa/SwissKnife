package com.ochoa.arnau.swissknife.Profile;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.ochoa.arnau.swissknife.Main_Drawer.OnFragmentInteractionListener_Main;
import com.ochoa.arnau.swissknife.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment implements View.OnClickListener{

    FloatingActionButton editFab;

    ImageButton locationButton;

    TextView usernameView;

    OnFragmentInteractionListener_Main mListener;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        editFab = (FloatingActionButton) rootView.findViewById(R.id.edit_picture_fab);
        editFab.setOnClickListener(this);

        locationButton = (ImageButton) rootView.findViewById(R.id.location_button);
        locationButton.setOnClickListener(this);

        SharedPreferences settings = getActivity().getSharedPreferences(getString(R.string.app_name), 0);
        String username = settings.getString("username", getString(R.string.no_username));

        usernameView = (TextView) rootView.findViewById(R.id.username_textView);
        usernameView.setText(username);

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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.edit_picture_fab:
                // TODO: Editar foto
                break;
            case R.id.location_button:
                // TODO: Editar localitzacio
                break;
        }

    }
}
