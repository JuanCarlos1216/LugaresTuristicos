package com.juanca.exploreit.utils

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T>(var data: List<T>) :
    RecyclerView.Adapter<BaseAdapter.BaseViewHolder<T>>() {

    private var filteredData: List<T> = data

    abstract fun getViewHolder(parent: ViewGroup): BaseViewHolder<T>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T> {
        return getViewHolder(parent)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        holder.bind(filteredData[position])
    }

    fun updateList(nData: List<T>) {
        data = nData
        filteredData = nData
        notifyDataSetChanged()
    }

    fun filter(predicate: (T) -> Boolean) {
        filteredData = if (predicate == { true }) {
            data
        } else {
            data.filter(predicate)
        }
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = filteredData.size

    abstract class BaseViewHolder<T>(view: View) : RecyclerView.ViewHolder(view) {
        abstract fun bind(entity: T)
    }
}