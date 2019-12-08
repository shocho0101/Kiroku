package jp.choccho.kiroku.datagateway

class KirokuDataGateway {
    companion object: DataGateway {
        override fun <T, Q> apply(request: DataGetRequest<T, Q>): T {
            return request.get()
        }

        override fun <T> apply(request: DataSaveRequest<T>) {
            request.save()
        }
    }
}