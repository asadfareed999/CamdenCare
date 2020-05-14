package com.example.asadfareed.twidlee2.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.camdencare.app.R

class StartupFragment() : Fragment() {

    private lateinit var loginButton: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val view: View = inflater.inflate(R.layout.fragment_startup, container, false)
        loginButton=view.findViewById(R.id.btn_login)
        loginButton.setOnClickListener {
            loadFragment(LoginFragment(),view.context as FragmentActivity)
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