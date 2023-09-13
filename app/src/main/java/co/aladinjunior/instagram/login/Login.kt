package co.aladinjunior.instagram.login

import androidx.annotation.StringRes

interface Login {

    interface View{

        fun displayProgress(enabled: Boolean)
        fun displayInvalidEmail(@StringRes message: Int)
        fun displayInvalidPassword(@StringRes message: Int)
        fun authenticateUser()
        fun cantAuthenticateUser(@StringRes message: Int)

    }

}