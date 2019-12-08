package jp.choccho.kiroku

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import jp.choccho.kiroku.datagateway.HeadGetRequest
import jp.choccho.kiroku.datagateway.HeadGetRequestQuery
import jp.choccho.kiroku.datagateway.HeadSaveRequest
import jp.choccho.kiroku.datagateway.KirokuDataGateway
import jp.choccho.kiroku.models.Head

import kotlinx.android.synthetic.main.activity_setting.*

class SettingsActivity : AppCompatActivity() {

    lateinit var adapter: SettingRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        setSupportActionBar(my_toolbar)

        val layoutManager = LinearLayoutManager(this)
        recylerView.layoutManager = layoutManager

        val query = HeadGetRequestQuery(isActive = true)
        val request = HeadGetRequest(query)
        val heads = KirokuDataGateway.apply(request)
        adapter = SettingRecyclerViewAdapter(heads)
        recylerView.adapter = adapter

        val itemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        recylerView.addItemDecoration(itemDecoration)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.setting, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val editText = EditText(this)
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle("新しい項目を入力してください")
        dialog.setView(editText)
        dialog.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which ->
            val text = editText.text.toString()
            val head = Head(title = text, isActive = true)
            val request = HeadSaveRequest(head)
            KirokuDataGateway.apply(request)
            reload()
        })
        dialog.setNegativeButton("キャンセル", null)
        dialog.show()
        return super.onOptionsItemSelected(item)
    }

    fun reload() {
        val query = HeadGetRequestQuery(isActive = true)
        val request = HeadGetRequest(query)
        val heads = KirokuDataGateway.apply(request)
        adapter.heads = heads
        adapter.notifyDataSetChanged()
    }
}
