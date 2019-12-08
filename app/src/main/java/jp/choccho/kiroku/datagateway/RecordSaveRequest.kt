package jp.choccho.kiroku.datagateway

import io.realm.Realm
import jp.choccho.kiroku.models.Record

class RecordSaveRequest(override val data: Record): DataSaveRequest<Record> {
    override fun save() {
        val realm = Realm.getDefaultInstance()
        realm.beginTransaction()
        realm.copyToRealm(data)
        realm.commitTransaction()
    }
}