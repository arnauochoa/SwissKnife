package com.ochoa.arnau.swissknife;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

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
        return inflater.inflate(R.layout.fragment_login, container, false);

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

        username = (EditText) getView().findViewById(R.id.usernameEditText);
        password = (EditText) getView().findViewById(R.id.passwordEditText);

        login_button = (Button) getView().findViewById(R.id.login_button);
        login_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_button:
                if (mListener.logIn(username,password)){
                    Intent intent;
                    intent = new Intent(getActivity(), DrawerActivity.class);
                    startActivity(intent);
                }
                else{
                    mListener.incorrectPassword();
                }
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
