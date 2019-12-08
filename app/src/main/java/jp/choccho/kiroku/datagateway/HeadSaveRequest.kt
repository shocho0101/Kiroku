package jp.choccho.kiroku.datagateway

import io.realm.Realm
import jp.choccho.kiroku.models.Head

class HeadSaveRequest(override val data: Head): DataSaveRequest<Head> {
    override fun save() {
        val realm = Realm.getDefaultInstance()
        realm.beginTransaction()
        realm.copyToRealm(data)
        realm.commitTransaction()
    }
}