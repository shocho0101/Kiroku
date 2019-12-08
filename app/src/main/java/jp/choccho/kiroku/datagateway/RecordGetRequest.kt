package jp.choccho.kiroku.datagateway

import io.realm.Realm
import jp.choccho.kiroku.models.Record

class RecordGetRequest(override val query: RecordGetRequestQuery): DataGetRequest<List<Record>, RecordGetRequestQuery> {
    override fun get(): List<Record> {
        val realm = Realm.getDefaultInstance()
        val realmQuery = realm.where(Record::class.java)
        if (query.date != null) {
            realmQuery.equalTo("date", query.date)
        }
        if (query.head != null) {
            realmQuery.equalTo("head.id", query.head.id)
        }
        return realmQuery.findAll().toList()
    }
}