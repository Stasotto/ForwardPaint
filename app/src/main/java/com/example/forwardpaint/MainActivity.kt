package com.example.forwardpaint

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.forwardpaint.presentation.fragments.MainFragment

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        openMainFragment()
    }

    private fun openMainFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.containerFrag, MainFragment.newInstance(), MainFragment.TAG)
            .commit()
    }
}