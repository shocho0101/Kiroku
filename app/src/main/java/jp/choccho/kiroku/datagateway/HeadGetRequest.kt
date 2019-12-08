package jp.choccho.kiroku.datagateway

import io.realm.Realm
import jp.choccho.kiroku.models.Head

class HeadGetRequest(override val query: HeadGetRequestQuery = HeadGetRequestQuery()): DataGetRequest<List<Head>, HeadGetRequestQuery> {
    override fun get(): List<Head> {
        val realm = Realm.getDefaultInstance()
        val realmQuery = realm.where(Head::class.java)
        if (query.isActive != null) {
            realmQuery.equalTo("isActive", query.isActive)
        }
        if (query.title != null) {
            realmQuery.contains("title", query.title)
        }
        return realmQuery.findAll().toList()
    }
}
