package jp.choccho.kiroku.datagateway

interface DataGetRequest<T, Q> {
    val query: Q
    fun get(): T
}