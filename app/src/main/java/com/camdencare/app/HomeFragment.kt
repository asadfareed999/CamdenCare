package com.camdencare.app

import CamdenCarePreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.camdencare.app.networking.BaseWebservices
import com.camdencare.app.networking.OnResponseListener
import com.camdencare.app.networking.responsemodels.Orders
import com.camdencare.app.networking.responsemodels.ResponseOrders
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_home.view.*


class HomeFragment() : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TestAdapter
    private lateinit var textViewLogout: TextView
    private lateinit var textViewName: TextView
    private lateinit var textViewAge: TextView
    private lateinit var textViewMrn: TextView
    private val apiEndpointClient = BaseWebservices.getApiEndpointClient()
    private lateinit var orderApiListener: OnResponseListener<ResponseOrders>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val view: View = inflater.inflate(R.layout.fragment_home, container, false)
        val camdenCarePreferences=CamdenCarePreferences(view.context)
        initViews(view)
        setProfileInfo( view)
        clickListeners( view)
        exeOrdersApi(camdenCarePreferences.getMrn())
        return view
    }

    private fun exeOrdersApi(patientId: String) {
        val call = apiEndpointClient.orders(patientId)
        BaseWebservices.executeApi(call, orderApiListener)
    }

    private fun clickListeners( view: View) {
        val camdenCarePreferences=CamdenCarePreferences(view.context)
        textViewLogout.setOnClickListener {
            camdenCarePreferences.logout()
            val action = HomeFragmentDirections.actionHomeFragmentToLoginFragment()
            view.findNavController().navigate(action)
        }
    }

    private fun setProfileInfo(view: View) {
        val camdenCarePreferences=CamdenCarePreferences(view.context)
        textViewName.text = camdenCarePreferences.getName()
        textViewAge.text = camdenCarePreferences.getAge()
        val textMRN = view.context.getString(R.string.Str_mrn) + camdenCarePreferences.getMrn()
        textViewMrn.text = textMRN
    }

    private fun initViews(view: View) {
        recyclerView = view.fragmentHomeRecyclerView
        textViewLogout = view.findViewById(R.id.tv_logout)
        textViewName = view.findViewById(R.id.tv_name)
        textViewAge = view.findViewById(R.id.tv_age)
        textViewMrn = view.findViewById(R.id.tv_id)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        orderApiListener = object : OnResponseListener<ResponseOrders> {
            override fun onSuccess(response: ResponseOrders?) {
                val ordersList: List<Orders> = response!!.orders
                recyclerView.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
                adapter = TestAdapter(ordersList)
                recyclerView.adapter = adapter
                adapter.notifyDataSetChanged()
            }

            override fun onFailure(t: Throwable) {
                Snackbar.make(view, t.message.toString(), Snackbar.LENGTH_LONG).show()
            }

            override fun onCancel() {
            }

        }
    }
}