package com.ochoa.arnau.swissknife;

import android.widget.EditText;

/**
 * Created by arnau on 31/01/2017.
 */

public interface OnFragmentInteractionListener {
    boolean logIn(EditText username, EditText password);
    void incorrectPassword();

    void addUser(EditText username, EditText password);
}
