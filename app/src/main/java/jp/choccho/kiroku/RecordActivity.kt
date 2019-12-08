package jp.choccho.kiroku

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import jp.choccho.kiroku.datagateway.*
import jp.choccho.kiroku.models.Record
import kotlinx.android.synthetic.main.activity_record.*
import java.util.*

class RecordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record)

        val query = HeadGetRequestQuery(isActive = true)
        val request = HeadGetRequest(query)
        val heads = KirokuDataGateway.apply(request)
        var count = 0
        titleTextView.text = heads[0].title

        yesButton.setOnClickListener {
            val record = Record(head = heads[count], answer = true, data = editText.text.toString(), date = today())
            val request = RecordSaveRequest(record)
            KirokuDataGateway.apply(request)
            count++;
            if (count == heads.size) {
                finish()
            } else {
                titleTextView.text = heads[count].title
                editText.text.clear()
            }
        }


        noButton.setOnClickListener {
            val record = Record(head = heads[count], answer = false, data = editText.text.toString(), date = today())
            val request = RecordSaveRequest(record)
            KirokuDataGateway.apply(request)
            count++;
            if (count == heads.size) {
                finish()
            } else {
                titleTextView.text = heads[count].title
                editText.text.clear()
            }
        }


    }

    fun today(): Date {
        val date = Date()
        val calendar = Calendar.getInstance()
        calendar.time = date
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        return calendar.time
    }


}
