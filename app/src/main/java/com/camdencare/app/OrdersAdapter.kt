package com.camdencare.app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.camdencare.app.networking.responsemodels.Orders
import com.camdencare.app.utilities.ORDER_STATUS_DELIVERED
import com.camdencare.app.utilities.ORDER_STATUS_VERIFIED
import com.camdencare.app.utilities.Utils


class OrdersAdapter(private val homeFragment: HomeFragment, private val ordersList: List<Orders>) :
    RecyclerView.Adapter<OrdersAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_list_test
                , parent, false
            )
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(position)
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return ordersList.size
    }

    //the class is holding the list view
    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {

        private val textViewDate: TextView = itemView.findViewById(R.id.tv_list_date)
        private val textViewTest: TextView = itemView.findViewById(R.id.tv_list_test)
        private val textViewStatus: TextView = itemView.findViewById(R.id.tv_list_status)
        private val textViewReport: TextView = itemView.findViewById(R.id.tv_list_view_report)

        fun bindItems(
            position: Int
        ) {
            val order = ordersList[position]

            val date = Utils.parseDate(order.created_at)

            textViewDate.text = date
            textViewTest.text = order.test
            val statusString = order.status
            if (statusString.equals(ORDER_STATUS_VERIFIED, true)
                || statusString.equals(ORDER_STATUS_DELIVERED, true)
            ) {
                val status =
                    homeFragment.getString(R.string.str_home_listitem_result_ready)
                textViewStatus.text = status
                textViewReport.visibility = View.VISIBLE
                textViewReport.setOnClickListener(this)
            } else {
                val status =
                    homeFragment.getString(R.string.str_home_listitem_result_notready)
                textViewStatus.text = status
                textViewReport.setOnClickListener(null)
                textViewReport.visibility = View.INVISIBLE

            }
        }

        override fun onClick(v: View?) {
            homeFragment.viewReport(ordersList[adapterPosition].id)
        }
    }
}