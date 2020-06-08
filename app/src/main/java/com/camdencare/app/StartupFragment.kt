package com.camdencare.app

import android.content.Intent
import android.net.Uri
import com.camdencare.app.prefrences.CamdenCarePreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.camdencare.app.utilities.DELAY_SPLASH
import com.camdencare.app.utilities.DURATION_ANIM_LOGIN_BTN
import com.camdencare.app.utilities.URL_CAMDENHEALTHSYS
import java.util.*
import kotlin.concurrent.timerTask

class StartupFragment() : Fragment() {

    private lateinit var loginButton: Button
    private lateinit var ivCamdenCareHoriz: ImageView
    private lateinit var camdenCarePreferences: CamdenCarePreferences
    private lateinit var timer: Timer
    private lateinit var timerTask: TimerTask
    private lateinit var timerLogin: Timer
    private lateinit var timerTaskLogin: TimerTask

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
        timerLogin = Timer()
        timerTaskLogin = timerTask {
            requireActivity().runOnUiThread {
                loginButton.visibility = View.VISIBLE
                loginButton.apply {
                    alpha = 0f
                    visibility = View.VISIBLE
                    animate()
                        .alpha(1f)
                        .duration = DURATION_ANIM_LOGIN_BTN
                }
            }
        }

        ivCamdenCareHoriz = view.findViewById(R.id.img_camdencare_logo)
        ivCamdenCareHoriz.setOnClickListener {
            Intent().apply {
                action = Intent.ACTION_VIEW
                data = Uri.parse(URL_CAMDENHEALTHSYS)
                startActivity(this)
            }
        }
        loginButton = view.findViewById(R.id.btn_login)
        loginButton.visibility = View.INVISIBLE
        loginButton.setOnClickListener {
            val action = StartupFragmentDirections.actionStartupFragmentToLoginFragment()
            view.findNavController().navigate(action)

        }

        if (camdenCarePreferences.isLogin()) {
            navigateToHomeAfterDelay()
        } else {
            showLoginBtnAfterDelay()
        }
    }

    private fun showLoginBtnAfterDelay() {
        timerLogin.schedule(timerTaskLogin, DELAY_SPLASH)
    }

    override fun onDestroy() {
        timer.cancel()
        timer.purge()
        timerLogin.cancel()
        timerLogin.purge()
        super.onDestroy()
    }

    private fun navigateToHomeAfterDelay() {
        timer.schedule(timerTask, DELAY_SPLASH)
    }

}