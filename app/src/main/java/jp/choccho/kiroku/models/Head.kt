package jp.choccho.kiroku.models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class Head(
    @PrimaryKey open var id: String = UUID.randomUUID().toString(),
    var title: String = "",
    var isActive: Boolean = false
) : RealmObject()