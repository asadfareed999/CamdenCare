package com.camdencare.app

import android.location.Location
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.camdencare.app.R
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class TestAdapter() : RecyclerView.Adapter<TestAdapter.ViewHolder>() {

    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val v =
                LayoutInflater.from(parent.context).inflate(R.layout.item_list_test
                    , parent, false)
            return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bindItems()
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return 26
    }

    //the class is hodling the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),View.OnClickListener {

        fun bindItems() {

        }

        override fun onClick(v: View?) {
        }
    }
}