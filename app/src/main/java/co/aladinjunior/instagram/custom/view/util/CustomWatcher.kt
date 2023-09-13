package co.aladinjunior.instagram.custom.view.util

import android.text.Editable
import android.text.TextWatcher

class CustomWatcher(private val function : (String) -> Unit) : TextWatcher {
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        function(s.toString())
    }

    override fun afterTextChanged(s: Editable?) {
    }
}