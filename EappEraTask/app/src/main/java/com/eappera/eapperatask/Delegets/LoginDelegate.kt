package com.eappera.eapperatask.Delegets

interface LoginDelegate {
    fun validUserName(isValid :Boolean)
    fun validPass(isValid :Boolean)
    fun login()
}