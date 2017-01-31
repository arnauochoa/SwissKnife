package com.ochoa.arnau.swissknife.Login_Register;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.ochoa.arnau.swissknife.Main_Drawer.DrawerActivity;
import com.ochoa.arnau.swissknife.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment implements View.OnClickListener{

    EditText username;
    EditText password;

    Button reg_button;

    OnFragmentInteractionListener mListener;

    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_register, container, false);

        username = (EditText) rootview.findViewById(R.id.usernameEditText);
        password = (EditText) rootview.findViewById(R.id.passwordEditText);

        reg_button = (Button) rootview.findViewById(R.id.register_button);
        reg_button.setOnClickListener(this);

        return rootview;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        mListener = (OnFragmentInteractionListener) context;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register_button:
                mListener.addUser(username, password);
                Intent intent;
                intent = new Intent(getActivity(), DrawerActivity.class);
                startActivity(intent);
                break;
        }
    }
}
