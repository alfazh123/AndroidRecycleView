package com.example.exambasicandroid

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ListCampusAdapter(private val listCampus: ArrayList<Campuses>): RecyclerView.Adapter<ListCampusAdapter.ListViewHolder>() {

    private lateinit var onCampusItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClicked(data: Campuses)
    }

    fun setOnClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onCampusItemClickCallback = onItemClickCallback
    }

    class ListViewHolder(item: View): RecyclerView.ViewHolder(item) {
        val imgCampusesLogo: ImageView = item.findViewById(R.id.campus_logo)
        val nameCampuses: TextView = item.findViewById(R.id.campus_name)
        val shortDesc: TextView = item.findViewById(R.id.campus_short_desc)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.campus_card, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listCampus.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (logo, name, shortDesc) = listCampus[position]
        holder.imgCampusesLogo.setImageResource(logo)
        holder.nameCampuses.text = name
        holder.shortDesc.text = shortDesc

        holder.itemView.setOnClickListener {
            onCampusItemClickCallback.onItemClicked(listCampus[holder.adapterPosition])
        }
    }
}