package co.aladinjunior.instagram.add.view

import android.app.Activity
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.content.ContextCompat
import co.aladinjunior.instagram.R
import co.aladinjunior.instagram.add.Add
import co.aladinjunior.instagram.post.view.CameraFragment.Companion.URI
import co.aladinjunior.instagram.commom.util.DependencyInjector
import co.aladinjunior.instagram.databinding.ActivityAddBinding
import java.lang.IllegalStateException

class AddActivity : AppCompatActivity(), Add.View{

    private lateinit var binding: ActivityAddBinding
    private lateinit var uri: Uri
    override lateinit var presenter: Add.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.addToolbar)
        val drawable = ContextCompat.getDrawable(this, R.drawable.ic_arrow_back)
        supportActionBar?.setHomeAsUpIndicator(drawable)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        uri = intent.extras?.getParcelable(URI) ?: throw IllegalStateException("photo not found")
        binding.addImgCaption.setImageURI(uri)

        presenter = DependencyInjector.addPresenter(this, DependencyInjector.addRepository())

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_share, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                finish()
                return true

            }
            R.id.action_share ->{
                presenter.createPost(uri, binding.addEditCaption.text.toString())
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSuccesPost() {
        setResult(Activity.RESULT_OK)
        finish()
    }

    override fun postingFailure(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}