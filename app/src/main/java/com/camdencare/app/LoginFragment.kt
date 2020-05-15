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
import com.camdencare.app.networking.BaseWebservices
import com.camdencare.app.networking.OnResponseListener
import com.camdencare.app.networking.responsemodels.ResponseLogin
import retrofit2.Call

class LoginFragment() : Fragment() {

    private lateinit var enterButton: Button
    private val apiEndpointClient = BaseWebservices.getApiEndpointClient()
    private lateinit var loginApiListener: OnResponseListener<ResponseLogin>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_login, container, false)
        enterButton = view.findViewById(R.id.btn_enter)
        enterButton.setOnClickListener {
           val action = LoginFragmentDirections.actionLoginFragmentToHomeFragment()
            view.findNavController().navigate(action)
            }
            exeLoginApi()
            return view
    }

    private fun exeLoginApi() {
        val call = apiEndpointClient.login("80394651", "03321745601")
        BaseWebservices.executeApi(call, loginApiListener)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        loginApiListener = object : OnResponseListener<ResponseLogin> {
            override fun onSuccess(response: ResponseLogin?) {

            }

            override fun onFailure(t: Throwable) {
            }

            override fun onCancel() {
            }

        }
    }
}