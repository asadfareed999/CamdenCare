package com.example.asadfareed.twidlee2.fragments

import CamdenCarePreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import com.camdencare.app.R

class StartupFragment() : Fragment() {

    private lateinit var loginButton: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val view: View = inflater.inflate(R.layout.fragment_startup, container, false)
        loginButton=view.findViewById(R.id.btn_login)
        loginButton.setOnClickListener {
            val camdenCarePreferences=CamdenCarePreferences(view.context)
            if (camdenCarePreferences.isLogin()) {
                val action = StartupFragmentDirections.actionLoginFragmentToHomeFragment()
                view.findNavController().navigate(action)
            }else{
                val action = StartupFragmentDirections.actionStartupFragmentToLoginFragment()
                view.findNavController().navigate(action)
            }
        }
        return view
    }

}