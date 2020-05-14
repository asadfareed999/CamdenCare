package com.example.asadfareed.twidlee2.fragments

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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.camdencare.app.R
import com.example.asadfareed.twidlee2.adapter.TestAdapter
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_home.view.*
import org.json.JSONObject


class HomeFragment() : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TestAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val view: View = inflater.inflate(R.layout.fragment_home, container, false)
        recyclerView = view.fragmentHomeRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        adapter = TestAdapter()
        recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()
        return view
    }
}