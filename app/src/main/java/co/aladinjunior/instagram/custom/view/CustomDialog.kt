package co.aladinjunior.instagram.custom.view

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import co.aladinjunior.instagram.R
import co.aladinjunior.instagram.databinding.DialogCustomBinding

class CustomDialog(context: Context) : Dialog(context) {

    private lateinit var binding: DialogCustomBinding
    private lateinit var buttons: Array<TextView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogCustomBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }



     fun addButtons(vararg items: Int, listener: View.OnClickListener) {
         buttons = Array(items.size) {
             TextView(context)
         }

         for(i in 0 until items.size){
             val button = buttons[i]
             val item = items[i]
             button.id = item
             button.setText(item)
             button.setOnClickListener {
                 listener.onClick(button)
                 dismiss()
             }
         }

     }

    override fun show() {
        super.show()
        val layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
        LinearLayout.LayoutParams.WRAP_CONTENT)
        layoutParams.setMargins(30,30,30,30)
        for (button in buttons){
            binding.dialogContainer.addView(button, layoutParams)
        }
    }
}