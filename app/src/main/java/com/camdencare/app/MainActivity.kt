package com.camdencare.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.asadfareed.twidlee2.fragments.HomeFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_main)
        loadFragment(HomeFragment(),this)
    }

    fun loadFragment(fragment: Fragment, activity: FragmentActivity?) {
        val transaction = activity!!.supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container_fragment, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
