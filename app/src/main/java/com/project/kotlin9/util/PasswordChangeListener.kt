package com.project.kotlin9.util

interface PasswordChangeListener {
    fun onChangePassword(oldPassword: String, newPassword: String, repeatPassword: String)
}