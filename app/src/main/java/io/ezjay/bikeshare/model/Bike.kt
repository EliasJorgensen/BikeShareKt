package io.ezjay.bikeshare.model

import io.realm.RealmModel
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class Bike (
    @PrimaryKey var id: Long? = null,
    var name: String? = null,
    var type: String? = null,
    var location: String? = "",
    var hourlyPrice: Float? = null,
    var picture: ByteArray? = ByteArray(0),
    var available: Boolean = true
) : RealmModel