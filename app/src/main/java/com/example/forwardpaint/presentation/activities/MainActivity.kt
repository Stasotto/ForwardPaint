package com.example.forwardpaint.presentation.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.forwardpaint.R
import com.example.forwardpaint.databinding.ActivityMainBinding
import com.example.forwardpaint.presentation.fragments.ChatFragment
import com.example.forwardpaint.presentation.fragments.MainFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val auth: FirebaseAuth by lazy { Firebase.auth }
    private val binding: ActivityMainBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        openFragment(MainFragment.newInstance(), MainFragment.TAG)
        setUserName()
        binding.bottomNav.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.chat -> openFragment(ChatFragment.newInstance(), ChatFragment.TAG)
                R.id.orders -> openFragment(MainFragment.newInstance(), MainFragment.TAG)
            }
            true

        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_actionbar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.signOut -> {
                auth.signOut()
                openAuthorizationActivity()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setUserName() {
        val userName = intent.getStringExtra("name")
        if (userName != null) {
            auth.currentUser?.updateProfile(userProfileChangeRequest {
                displayName = userName
            })

        }
    }

    private fun openFragment(fragment: Fragment, tag: String) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.containerFrag, fragment, tag)
            .commit()
    }

    private fun openAuthorizationActivity() {
        startActivity(Intent(this, AuthorizationActivity::class.java))
    }
}