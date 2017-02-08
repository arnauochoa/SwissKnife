package com.ochoa.arnau.swissknife;

import android.app.Application;
import io.realm.Realm;
import io.realm.RealmConfiguration;


/**
 * Created by arnau on 08/02/2017.
 */

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
//        RealmConfiguration config = new RealmConfiguration
//                .Builder()
//                .deleteRealmIfMigrationNeeded()
//                .build();
    }
}
