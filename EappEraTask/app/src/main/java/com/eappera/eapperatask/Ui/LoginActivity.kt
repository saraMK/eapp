package com.eappera.eapperatask.Ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.eappera.eapperatask.Delegets.LoginDelegate
import com.eappera.eapperatask.Presenters.LoginPresenter.LoginPresenterImpl
import com.eappera.eapperatask.Presenters.LoginPresenter.LoginPresenter
import com.eappera.eapperatask.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() ,LoginDelegate, View.OnClickListener {

    var loginPresenter: LoginPresenter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginPresenter =LoginPresenterImpl(this)
        login_btn.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        loginPresenter!!.login(user_name_edittext.text.toString(),pass_edittext.text.toString())
    }
    override fun validUserName(isValid: Boolean) {
        if (isValid)
        user_name_edittext.setError(null)
        else user_name_edittext.setError(getString(R.string.invalid_user_name_msg))
    }

    override fun validPass(isValid: Boolean) {
        if (isValid)
            pass_edittext.setError(null)
        else pass_edittext.setError(getString(R.string.invalid_pass_msg))
    }

    override fun login() {

        // success login
        var intent= Intent(this ,LocationActivity::class.java)
        startActivity(intent)
    }
}
