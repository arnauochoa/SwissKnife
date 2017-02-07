package com.ochoa.arnau.swissknife.Profile;

import android.net.Uri;

/**
 * Created by arnau on 07/02/2017.
 */
// TODO: 07/02/2017
public class User{ //extends RealmObject

    private String name;
    private Uri uri = null;

    public User(String name) {
        this.name = name;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }
}
