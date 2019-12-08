package jp.choccho.kiroku.datagateway

interface DataSaveRequest<T> {
    val data: T
    fun save()
}