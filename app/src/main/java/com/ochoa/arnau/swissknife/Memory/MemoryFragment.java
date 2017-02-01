package com.ochoa.arnau.swissknife.Memory;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ochoa.arnau.swissknife.Main_Drawer.OnFragmentInteractionListener_Main;
import com.ochoa.arnau.swissknife.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MemoryFragment extends Fragment {

    OnFragmentInteractionListener_Main mListener;

    public MemoryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_memory, container, false);
        Log.d("MEMORY", "onNavigationItemSelected: HELLO FROM MEMORY");
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

}
