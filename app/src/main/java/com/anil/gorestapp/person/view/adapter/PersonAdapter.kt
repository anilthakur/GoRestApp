package com.anil.gorestapp.person.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.anil.gorestapp.R
import com.anil.gorestapp.person.entities.ResultItem

class PersonAdapter : RecyclerView.Adapter<PersonAdapter.ViewHolder>() {

    private var userList: ArrayList<ResultItem?>? = null

    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item_view, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: PersonAdapter.ViewHolder, position: Int) {
        userList?.get(position)?.let { holder.bindItems(it) }
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return userList?.size ?: 0
    }

    //the class is hodling the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(user: ResultItem) {
            val textViewName = itemView.findViewById(R.id.textViewUsername) as TextView
            val textViewAddress = itemView.findViewById(R.id.textViewAddress) as TextView
            textViewName.text = user.firstName + " " + user.lastName
            textViewAddress.text = user.address
        }
    }

    fun setUserList(userList: ArrayList<ResultItem?>?) {
        this.userList = userList
        notifyDataSetChanged()
    }
}