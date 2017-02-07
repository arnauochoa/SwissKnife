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
public class LoginFragment extends Fragment implements View.OnClickListener{

    EditText username;
    EditText password;

    Button login_button;

    OnFragmentInteractionListener mListener;

    public LoginFragment(){
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);


        username = (EditText) rootView.findViewById(R.id.usernameEditText);
        password = (EditText) rootView.findViewById(R.id.passwordEditText);

        login_button = (Button) rootView.findViewById(R.id.login_button);
        login_button.setOnClickListener(this);

        return rootView;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mListener = (OnFragmentInteractionListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_button:
                if(String.valueOf(username.getText().toString()).equals("") ||
                        String.valueOf(password.getText().toString()).equals("")) {
                    mListener.emptyField();
                }else{
                    if (mListener.logIn(username, password)) {
                        SharedPreferences settings = getActivity().getSharedPreferences(String.valueOf(R.string.app_name), 0);
                        SharedPreferences.Editor editor = settings.edit();
                        editor.putString("username", String.valueOf(username.getText().toString()));
                        editor.apply();

                        Intent intent;
                        intent = new Intent(getActivity(), DrawerActivity.class);
                        startActivity(intent);
                        getActivity().finish();
                    } else {
                        mListener.incorrectPassword();
                    }
                }
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
