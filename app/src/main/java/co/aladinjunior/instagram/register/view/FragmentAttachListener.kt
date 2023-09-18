package co.aladinjunior.instagram.register.view

interface FragmentAttachListener {
    fun goToNamePasswordScreen(email: String)
    fun goToWelcomeScreen(name: String)
    fun goToPhotoUploadScreen()
    fun goToMainScreen()
}