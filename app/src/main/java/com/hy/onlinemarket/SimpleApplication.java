package com.hy.onlinemarket;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;



public class SimpleApplication  extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
        RealmConfiguration config= new RealmConfiguration.Builder()
                .name("market.realm")
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);

    }
}
