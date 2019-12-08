package jp.choccho.kiroku

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import jp.choccho.kiroku.models.Record
import kotlinx.android.synthetic.main.record_cell.view.*


class RecordRecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view){
    val titleTextView = view.cellTitleTextView
    val dataTextView = view.dataTextView
    val resultTextView = view.resultTextView

    fun setRecord(record: Record) {
        titleTextView.text = record.head!!.title
        dataTextView.text = record.data
        resultTextView.text = if (record.answer) "◯" else "✕"
    }
}