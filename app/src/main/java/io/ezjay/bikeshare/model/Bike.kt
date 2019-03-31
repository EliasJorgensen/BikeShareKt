package io.ezjay.bikeshare.model

import io.realm.RealmModel
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import java.io.Serializable

@RealmClass
open class Bike (
    @PrimaryKey var id: Long = 0,
    var name: String? = "",
    var location: String? = ""
) : RealmModel