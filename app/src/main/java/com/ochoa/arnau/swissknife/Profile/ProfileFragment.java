package com.ochoa.arnau.swissknife.Profile;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.ochoa.arnau.swissknife.Main_Drawer.OnFragmentInteractionListener_Main;
import com.ochoa.arnau.swissknife.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment implements View.OnClickListener{

    FloatingActionButton edit_fab;

    ImageButton location_button;

    OnFragmentInteractionListener_Main mListener;


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_profile, container, false);

        edit_fab = (FloatingActionButton) rootview.findViewById(R.id.edit_picture_fab);
        edit_fab.setOnClickListener(this);

        location_button = (ImageButton) rootview.findViewById(R.id.location_button);
        location_button.setOnClickListener(this);

        return rootview;
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
