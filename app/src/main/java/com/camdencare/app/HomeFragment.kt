package com.camdencare.app

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.camdencare.app.networking.ApiEndpointClient
import com.camdencare.app.networking.BaseWebservices
import com.camdencare.app.networking.OnResponseListener
import com.camdencare.app.networking.responsemodels.Orders
import com.camdencare.app.networking.responsemodels.ResponseOrders
import com.camdencare.app.prefrences.CamdenCarePreferences
import com.camdencare.app.utilities.URL_CAMDENHEALTHSYS
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_home.view.*


class HomeFragment() : Fragment() {

    enum class EnumGender(val gender: String) {
        male("M"),
        female("F"),
    }

    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: OrdersAdapter
    private lateinit var textViewLogout: TextView
    private lateinit var textViewName: TextView
    private lateinit var textViewPatient: TextView
    private lateinit var textViewMrn: TextView
    private val apiEndpointClient = BaseWebservices.getApiEndpointClient()
    private lateinit var orderApiListener: OnResponseListener<ResponseOrders>
    private lateinit var camdenCarePreferences: CamdenCarePreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_home, container, false)
        camdenCarePreferences = CamdenCarePreferences(view.context)
        initViews(view)
        initApiListener(view)
        setProfileInfo(view)
        clickListeners(view)
        swipeRefresh.isRefreshing = true
        exeOrdersApi(camdenCarePreferences.getMrn())
        return view
    }

    private fun initApiListener(view: View) {
        orderApiListener = object : OnResponseListener<ResponseOrders> {
            override fun onSuccess(response: ResponseOrders?) {
                swipeRefresh.isRefreshing = false
                val ordersList: List<Orders> = response!!.orders
                recyclerView.layoutManager =
                    LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
                adapter = OrdersAdapter(this@HomeFragment, ordersList)
                recyclerView.adapter = adapter
                adapter.notifyDataSetChanged()
            }

            override fun onFailure(t: Throwable) {
                swipeRefresh.isRefreshing = false
                Snackbar.make(view, t.message.toString(), Snackbar.LENGTH_LONG).show()
            }

            override fun onCancel() {
                swipeRefresh.isRefreshing = false
            }

        }
    }

    private fun exeOrdersApi(patientId: String) {
        val call = apiEndpointClient.orders(patientId)
        BaseWebservices.executeApi(call, orderApiListener)
    }

    private fun clickListeners(view: View) {

        swipeRefresh.setOnRefreshListener {
            exeOrdersApi(camdenCarePreferences.getMrn())

        }
        val camdenCarePreferences = CamdenCarePreferences(view.context)
        textViewLogout.setOnClickListener {
            camdenCarePreferences.logout()
            val action = HomeFragmentDirections.actionHomeFragmentToLoginFragment()
            view.findNavController().navigate(action)
        }
    }

    private fun setProfileInfo(view: View) {
        val camdenCarePreferences = CamdenCarePreferences(view.context)
        textViewName.text = camdenCarePreferences.getName()
        val age = camdenCarePreferences.getAge()
        val mrn = camdenCarePreferences.getMrn()
        val gender: String = camdenCarePreferences.getGender()
        val textPatient = if (gender.isEmpty()) {
            String.format(
                this@HomeFragment.getString(R.string.str_home_header_patient),
                age, mrn
            )
        } else {
            val enumGender = EnumGender.valueOf(gender)
            String.format(
                this@HomeFragment.getString(R.string.str_home_header_patient_2),
                age, enumGender.gender, mrn
            )
        }
        textViewPatient.text = textPatient
    }

    private fun initViews(view: View) {
        swipeRefresh = view.findViewById(R.id.swipeRefresh)
        recyclerView = view.fragmentHomeRecyclerView
        textViewLogout = view.findViewById(R.id.tv_logout)
        textViewName = view.findViewById(R.id.tv_name)
        textViewPatient = view.findViewById(R.id.tvPatient)
        val tvPoweredBy = view.findViewById<TextView>(R.id.tvPoweredBy)
        tvPoweredBy.setOnClickListener {
            Intent().apply {
                action = Intent.ACTION_VIEW
                data = Uri.parse(URL_CAMDENHEALTHSYS)
                startActivity(this)
            }
        }
    }

    fun viewReport(orderId: Int) {
        val downloadUrl = String.format(ApiEndpointClient.LAB_REPORT, orderId)
        val intent = Intent(Intent.ACTION_VIEW).apply {
            setData(Uri.parse(downloadUrl))
        }
        val queryIntentActivities = requireContext().packageManager.queryIntentActivities(
            intent,
            PackageManager.MATCH_DEFAULT_ONLY
        )
        val notEmpty = queryIntentActivities.isNotEmpty()
        if (notEmpty) {
            startActivity(intent)
        }

    }
}