package com.camdencare.app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.camdencare.app.networking.responsemodels.Orders
import com.camdencare.app.utilities.Utils


class TestAdapter(val homeFragment: HomeFragment, ordersList: List<Orders>) :
    RecyclerView.Adapter<TestAdapter.ViewHolder>() {

    val testList = ordersList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_list_test
                , parent, false
            )
        return ViewHolder(homeFragment, v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(testList, position)
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return testList.size
    }

    //the class is hodling the list view
    inner class ViewHolder(val homeFragment: HomeFragment, itemView: View) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {

        private val textViewDate: TextView = itemView.findViewById(R.id.tv_list_date)
        private val textViewTest: TextView = itemView.findViewById(R.id.tv_list_test)
        private val textViewStatus: TextView = itemView.findViewById(R.id.tv_list_status)
        private val btnView: Button = itemView.findViewById(R.id.btnView)

        fun bindItems(
            testList: List<Orders>,
            position: Int
        ) {
            val date = Utils.parseDate(testList.get(position).created_at)
            textViewDate.text = date
            textViewTest.text = testList.get(position).test
            val statusString = testList.get(position).status
            if (statusString.equals("verified", true)
                || statusString.equals("delivered", true)) {
                toggleBtnViewVisibility(visible = true)
                btnView.setOnClickListener(this)
            } else {
                toggleBtnViewVisibility(visible = false)
            }
            if (position == 0 || position == 2) {
                toggleBtnViewVisibility(visible = true)

            }
            btnView.setOnClickListener(this)

        }

        override fun onClick(v: View?) {
            homeFragment.viewReport(testList[adapterPosition].order_id)
        }

        fun toggleBtnViewVisibility(visible: Boolean) {
            if (visible) {
                btnView.visibility = View.VISIBLE
                textViewStatus.visibility = View.GONE
            } else {
                btnView.visibility = View.GONE
                textViewStatus.visibility = View.VISIBLE
            }
        }
    }
}