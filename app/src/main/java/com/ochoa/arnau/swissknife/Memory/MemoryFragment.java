package com.ochoa.arnau.swissknife.Memory;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ochoa.arnau.swissknife.Main_Drawer.OnFragmentInteractionListener_Main;
import com.ochoa.arnau.swissknife.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MemoryFragment extends Fragment implements View.OnClickListener{

    TextView easy, medium, hard;

    OnFragmentInteractionListener_Main mListener;

    public MemoryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_memory, container, false);

        easy = (TextView) rootview.findViewById(R.id.easy);
        easy.setOnClickListener(this);

        medium = (TextView) rootview.findViewById(R.id.medium);
        medium.setOnClickListener(this);

        hard = (TextView) rootview.findViewById(R.id.hard);
        hard.setOnClickListener(this);

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
        Intent intent;
        switch (v.getId()){
            case R.id.easy:
                intent = new Intent(getActivity(), EasyMemoryActivity.class);
                startActivity(intent);
                break;
            case R.id.medium:
                intent = new Intent(getActivity(), MediumMemoryActivity.class);
                startActivity(intent);
                break;
            case R.id.hard:
                intent = new Intent(getActivity(), HardMemoryActivity.class);
                startActivity(intent);
                break;
        }
    }
}
