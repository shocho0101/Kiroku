package jp.choccho.kiroku.models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class Record(
    @PrimaryKey open var id: String = UUID.randomUUID().toString(),
    var head: Head? = null,
    var answer: Boolean = false,
    var data: String = "",
    var date: Date = Date()
) : RealmObject()

