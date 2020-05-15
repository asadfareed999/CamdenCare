package com.camdencare.app

import CamdenCarePreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.camdencare.app.R
import com.camdencare.app.networking.BaseWebservices
import com.camdencare.app.networking.OnResponseListener
import com.camdencare.app.networking.responsemodels.ResponseLogin
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import java.util.*


class LoginFragment() : Fragment() {

    private lateinit var enterButton: Button
    private val apiEndpointClient = BaseWebservices.getApiEndpointClient()
    private lateinit var loginApiListener: OnResponseListener<ResponseLogin>
    private lateinit var progressBar: ProgressBar
    private lateinit var inputLayoutMRN: TextInputLayout
    private lateinit var inputLayoutNumber: TextInputLayout


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_login, container, false)
        enterButton = view.findViewById(R.id.btn_enter)
        progressBar= requireActivity().findViewById(R.id.loadingBar)
        inputLayoutMRN=view.findViewById(R.id.textfield_mrn)
        inputLayoutNumber=view.findViewById(R.id.textfield_mobile_number)
        enterButton.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToHomeFragment()
            // view.findNavController().navigate(action) }
            exeLoginApi()
        }
            return view
    }

    private fun exeLoginApi() {
        val mrn:String=inputLayoutMRN.editText!!.text.toString()
        val number:String=inputLayoutNumber.editText!!.text.toString()
        if (mrn.isEmpty()){
            inputLayoutMRN.isErrorEnabled=true
            inputLayoutMRN.error="Enter a valid MRN"
        }else if (number.isEmpty()){
            inputLayoutNumber.isErrorEnabled=true
            inputLayoutMRN.isErrorEnabled=false
            inputLayoutNumber.error="Enter a valid Number"
        } else {
            inputLayoutNumber.isErrorEnabled=false
            progressBar.visibility=View.VISIBLE
            progressBar.isIndeterminate=true
            val call = apiEndpointClient.login(mrn, number)
            BaseWebservices.executeApi(call, loginApiListener)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        loginApiListener = object : OnResponseListener<ResponseLogin> {
            override fun onSuccess(response: ResponseLogin?) {
                progressBar.visibility=View.GONE
                val MRN:String=response!!.mrn
                val FullName:String=response.fullName
                val Age:String=response.age
                val camdenCarePreferences=CamdenCarePreferences(view.context)
                camdenCarePreferences.saveMRN(MRN)
                camdenCarePreferences.saveName(FullName)
                camdenCarePreferences.saveAge(Age)
                val action = LoginFragmentDirections.actionLoginFragmentToHomeFragment()
                view.findNavController().navigate(action)
            }

            override fun onFailure(t: Throwable) {
                progressBar.visibility=View.GONE
                Snackbar.make(view, t.message.toString(), Snackbar.LENGTH_LONG).show()
            }

            override fun onCancel() {
                progressBar.visibility=View.GONE
                Snackbar.make(view, "Login Cancelled", Snackbar.LENGTH_LONG).show()
            }

        }
    }
}