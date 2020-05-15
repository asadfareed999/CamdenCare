package com.camdencare.app

import CamdenCarePreferences
import android.app.ActionBar
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.camdencare.app.R
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_home.view.*
import org.json.JSONObject


class HomeFragment() : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TestAdapter
    private lateinit var textViewLogout: TextView
    private lateinit var textViewName: TextView
    private lateinit var textViewAge: TextView
    private lateinit var textViewMrn: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val view: View = inflater.inflate(R.layout.fragment_home, container, false)
        val camdenCarePreferences=CamdenCarePreferences(view.context)
        recyclerView = view.fragmentHomeRecyclerView
        textViewLogout=view.findViewById(R.id.tv_logout)
        textViewName=view.findViewById(R.id.tv_name)
        textViewAge=view.findViewById(R.id.tv_age)
        textViewMrn=view.findViewById(R.id.tv_id)
        textViewName.text=camdenCarePreferences.getName()
        textViewAge.text=camdenCarePreferences.getAge()
        textViewMrn.text=camdenCarePreferences.getMrn()
        textViewLogout.setOnClickListener {
            camdenCarePreferences.logout()
            val action = HomeFragmentDirections.actionHomeFragmentToLoginFragment()
            view.findNavController().navigate(action)
        }
        recyclerView.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        adapter = TestAdapter()
        recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()
        return view
    }
}