package io.ezjay.bikeshare

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class Main : Application() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
    }

    companion object {
        fun getRealmConfig() : RealmConfiguration =
            RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build()
    }
}