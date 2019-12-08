package jp.choccho.kiroku.datagateway

import jp.choccho.kiroku.models.Head
import java.util.*

class RecordGetRequestQuery(
    val date: Date? = null,
    val head: Head? = null
)