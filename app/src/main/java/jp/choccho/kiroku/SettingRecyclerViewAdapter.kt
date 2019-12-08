package jp.choccho.kiroku

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import jp.choccho.kiroku.models.Head

class SettingRecyclerViewAdapter(var heads: List<Head>): RecyclerView.Adapter<SettingRecyclerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SettingRecyclerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.setting_cell, parent, false)

        return SettingRecyclerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return heads.count()
    }

    override fun onBindViewHolder(holder: SettingRecyclerViewHolder, position: Int) {
        holder.textView.text = heads[position].title
    }
}