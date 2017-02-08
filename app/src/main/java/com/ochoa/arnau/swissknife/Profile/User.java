package com.ochoa.arnau.swissknife.Profile;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by arnau on 07/02/2017.
 */
public class User extends RealmObject {

    @PrimaryKey
    private String name;
    private String uri = null;
    private String address = "No address yet";
    private boolean hasUri = false;
    private boolean hasAddress = false;


    public void setName(String name) {
        this.name = name;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean hasUri() {
        return hasUri;
    }

    public void setHasUri(boolean hasUri) {
        this.hasUri = hasUri;
    }

    public boolean hasAddress() {
        return hasAddress;
    }

    public void setHasAddress(boolean hasAddress) {
        this.hasAddress = hasAddress;
    }
}
