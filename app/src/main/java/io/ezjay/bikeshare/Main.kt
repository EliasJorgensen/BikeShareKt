package io.ezjay.bikeshare

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.SyncUser

class Main : Application() {
    override fun onCreate() {
        super.onCreate()

        Realm.init(this)
        Realm.setDefaultConfiguration(
            RealmConfiguration.Builder()
                    .deleteRealmIfMigrationNeeded()
                    .build())
    }
}