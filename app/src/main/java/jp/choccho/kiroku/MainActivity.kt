package jp.choccho.kiroku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import jp.choccho.kiroku.datagateway.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.my_toolbar
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var adapter: RecordRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(my_toolbar)

        val layoutManager = LinearLayoutManager(this)
        mainRecyclerView.layoutManager = layoutManager

        val date = date(Date(calendarView.date))
        val query = RecordGetRequestQuery(date = date)
        val request = RecordGetRequest(query)
        val records = KirokuDataGateway.apply(request)

        adapter = RecordRecyclerViewAdapter(records)
        mainRecyclerView.adapter = adapter

        val itemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        mainRecyclerView.addItemDecoration(itemDecoration)

        recordButton.setOnClickListener {
            val query = HeadGetRequestQuery(isActive = true)
            val request = HeadGetRequest(query)
            val heads = KirokuDataGateway.apply(request)
            if (heads.isNotEmpty()) {
                val intent = Intent(this, RecordActivity::class.java)
                startActivity(intent)
            }
        }

        calendarView.setOnDateChangeListener { _ , year , month , day ->
            val date = date(year, month, day)
            val query = RecordGetRequestQuery(date = date)
            val request = RecordGetRequest(query)
            val records = KirokuDataGateway.apply(request)
            adapter.records = records
            adapter.notifyDataSetChanged()
        }

    }

    override fun onResume() {
        super.onResume()

        val date = date(Date(calendarView.date))
        val query = RecordGetRequestQuery(date = date)
        val request = RecordGetRequest(query)
        val records = KirokuDataGateway.apply(request)

        adapter.records = records
        adapter.notifyDataSetChanged()
    }

    fun date(date: Date): Date {
        val calendar = Calendar.getInstance()
        calendar.time = date
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        return calendar.time
    }

    fun date(year: Int, month: Int, day: Int): Date {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.DAY_OF_MONTH, day)
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        return calendar.time
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
        return super.onOptionsItemSelected(item)
    }
}
