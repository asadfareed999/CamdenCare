package com.example.asadfareed.twidlee2.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import com.camdencare.app.R

class LoginFragment() : Fragment() {

    private lateinit var enterButton: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val view: View = inflater.inflate(R.layout.fragment_login, container, false)
        enterButton=view.findViewById(R.id.btn_enter)
        enterButton.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToHomeFragment()
            view.findNavController().navigate(action)        }
        return view
    }
}