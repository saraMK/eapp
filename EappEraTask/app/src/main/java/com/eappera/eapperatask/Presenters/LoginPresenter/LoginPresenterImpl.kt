package com.eappera.eapperatask.Presenters.LoginPresenter

import com.eappera.eapperatask.Delegets.LoginDelegate

class LoginPresenterImpl(loginDelegate: LoginDelegate) : LoginPresenter {
    val loginDelegate: LoginDelegate = loginDelegate

    override fun login(userName: String, pass: String) {

        if (validate(userName, pass))
            loginDelegate.login()

    }

    private fun validate(userName: String, pass: String): Boolean {
        var isValid: Boolean = true

        if (userName.length > 3) {
            loginDelegate.validUserName(true)
        } else {
            loginDelegate.validUserName(false)
            isValid = false
        }

        if (pass.length > 5) {
            loginDelegate.validPass(true)
        } else {
            loginDelegate.validPass(false)
            isValid = false
        }


        return isValid
    }
}