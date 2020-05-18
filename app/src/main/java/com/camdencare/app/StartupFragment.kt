package com.camdencare.app

import com.camdencare.app.prefrences.CamdenCarePreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.camdencare.app.utilities.DELAY_SPLASH
import java.util.*
import kotlin.concurrent.timerTask

class StartupFragment() : Fragment() {

    private lateinit var loginButton: Button
    private lateinit var camdenCarePreferences: CamdenCarePreferences
    private lateinit var timer: Timer
    private lateinit var timerTask: TimerTask

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_startup, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        camdenCarePreferences = CamdenCarePreferences(requireContext())
        timer = Timer()
        timerTask = timerTask {
            val action = StartupFragmentDirections.actionLoginFragmentToHomeFragment()
            view.findNavController().navigate(action)
        }

        loginButton = view.findViewById(R.id.btn_login)
        loginButton.setOnClickListener {
            val action = StartupFragmentDirections.actionStartupFragmentToLoginFragment()
            view.findNavController().navigate(action)

        }

        if (camdenCarePreferences.isLogin()) {
            loginButton.visibility = View.INVISIBLE
            navigateToHomeAfterDelay()
        }
    }

    override fun onDestroy() {
        timer.cancel()
        timer.purge()
        super.onDestroy()
    }

    private fun navigateToHomeAfterDelay() {
        timer.schedule(timerTask, DELAY_SPLASH)
    }

}