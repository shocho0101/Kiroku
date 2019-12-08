package jp.choccho.kiroku

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import jp.choccho.kiroku.models.Record

class RecordRecyclerViewAdapter(var records: List<Record>): RecyclerView.Adapter<RecordRecyclerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordRecyclerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.record_cell, parent, false)

        return RecordRecyclerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return records.count()
    }

    override fun onBindViewHolder(holder: RecordRecyclerViewHolder, position: Int) {
        holder.setRecord(records[position])
    }
}