package com.ochoa.arnau.swissknife.Login_Register;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
        View rootView = inflater.inflate(R.layout.fragment_register, container, false);

        username = (EditText) rootView.findViewById(R.id.usernameEditText);
        password = (EditText) rootView.findViewById(R.id.passwordEditText);

        reg_button = (Button) rootView.findViewById(R.id.register_button);
        reg_button.setOnClickListener(this);

        return rootView;
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
                if(String.valueOf(username.getText().toString()).equals("") ||
                        String.valueOf(password.getText().toString()).equals("")) {
                    mListener.emptyField();
                }else{
                    if (mListener.addUser(username, password)) {
                        SharedPreferences settings = getActivity().getSharedPreferences(String.valueOf(R.string.app_name), 0);
                        SharedPreferences.Editor editor = settings.edit();
                        editor.putString("username", String.valueOf(username.getText().toString()));
                        editor.apply();

                        Intent intent;
                        intent = new Intent(getActivity(), DrawerActivity.class);
                        startActivity(intent);
                        getActivity().finish();
                    }
                }
                break;
        }
    }
}
