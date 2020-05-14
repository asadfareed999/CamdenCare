package com.example.asadfareed.twidlee2.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.camdencare.app.R

class LoginFragment() : Fragment() {

    private lateinit var enterButton: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val view: View = inflater.inflate(R.layout.fragment_login, container, false)
        enterButton=view.findViewById(R.id.btn_enter)
        enterButton.setOnClickListener {
            loadFragment(HomeFragment(),view.context as FragmentActivity)
        }
        return view
    }

    fun loadFragment(fragment: Fragment, activity: FragmentActivity?) {
        val transaction = activity!!.supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container_fragment, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}