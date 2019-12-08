package jp.choccho.kiroku.datagateway

interface DataGateway {
    fun <T, Q> apply(request: DataGetRequest<T, Q>): T
    fun <T> apply(request: DataSaveRequest<T>)
}