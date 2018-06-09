package com.mobile.shopaid.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.mobile.shopaid.R
import com.mobile.shopaid.extensions.animateView
import com.mobile.shopaid.extensions.getFadeInAnimator
import kotlinx.android.synthetic.main.loading_layout.*
import kotlinx.android.synthetic.main.login_activity.*

class LoginActivity : BaseActivity() {

    companion object {
        private const val RC_SIGN_IN = 1
        private const val DELAY_ANIMATION = 1500L
    }

    private lateinit var gso: GoogleSignInOptions
    private lateinit var client: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.login_activity)

        root.run {
            animateView(getFadeInAnimator(DELAY_ANIMATION))
        }

        gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()
        client = GoogleSignIn.getClient(this, gso)

        login_sign_in_button.setOnClickListener({
            startActivity(Intent(this, CharityActivity::class.java))
        })
        google_sign_in_button.setOnClickListener {
            signIn()
        }
    }

    private fun signIn() {
        toggleLoading(true)
        val intent = client.signInIntent
        startActivityForResult(intent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val signIn = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleResult(signIn)
        }
    }

    private fun handleResult(completedTask: Task<GoogleSignInAccount>) {
        toggleLoading(false)
        try {
            val account = completedTask.getResult(ApiException::class.java)
            startActivity(Intent(this, CharityActivity::class.java))
        } catch (ex: ApiException) {
            // do nothing
        }
    }

    private fun toggleLoading(isLoading: Boolean) {
        loadingLayout.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}