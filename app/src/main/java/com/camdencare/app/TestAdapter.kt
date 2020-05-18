package com.camdencare.app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        private val textViewReport: TextView = itemView.findViewById(R.id.tv_list_view_report)

        fun bindItems(
            testList: List<Orders>,
            position: Int
        ) {
            val date =itemView.context.getString(R.string.Str_date)+" "+
                    Utils.parseDate(testList.get(position).created_at)
            textViewDate.text = date
            textViewTest.text = testList.get(position).test
            val statusString = testList.get(position).status
            if (statusString.equals("verified", true)
                || statusString.equals("delivered", true)) {
                //toggleBtnViewVisibility(visible = true)
                val status=itemView.context.getString(R.string.Str_results)+" Ready"
                textViewStatus.text=status
                textViewReport.visibility=View.VISIBLE
                textViewReport.setOnClickListener(this)
            } else {
                val status=itemView.context.getString(R.string.Str_results)+" Not Ready"
                textViewStatus.text=status
            }
           /* if (position==0 || position==2){
                val status=itemView.context.getString(R.string.Str_results)+" Ready"
                textViewStatus.text=status
                textViewReport.visibility=View.VISIBLE
                textViewReport.setOnClickListener(this)
            }*/
        }

        override fun onClick(v: View?) {
            homeFragment.viewReport(testList[adapterPosition].order_id)
        }

    }
}