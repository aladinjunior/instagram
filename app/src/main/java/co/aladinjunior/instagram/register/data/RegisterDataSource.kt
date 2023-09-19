package co.aladinjunior.instagram.register.data

import android.net.Uri

interface RegisterDataSource {
    fun registrate(email: String, callback: RegisterCallback)
    fun registrate(email: String, name: String, password: String, callback: RegisterCallback)
    fun attachPhoto(uri: Uri, callback: RegisterCallback)
}