package co.aladinjunior.instagram.main.view

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import co.aladinjunior.instagram.R
import co.aladinjunior.instagram.camera.view.CameraFragment
import co.aladinjunior.instagram.databinding.ActivityMainBinding
import co.aladinjunior.instagram.home.view.HomeFragment
import co.aladinjunior.instagram.profile.view.ProfileFragment
import co.aladinjunior.instagram.search.view.SearchFragment
import com.google.android.material.bottomnavigation.BottomNavigationMenu
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var homeFragment: Fragment
    private lateinit var searchFragment: Fragment
    private lateinit var cameraFragment: Fragment
    private lateinit var profileFragment: Fragment
    private var currentFragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.mainToolbar)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_insta_camera)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = ""

        homeFragment = HomeFragment()
        searchFragment = SearchFragment()
        cameraFragment = CameraFragment()
        profileFragment = ProfileFragment()

        binding.mainBottomNav.setOnNavigationItemSelectedListener(this)
        binding.mainBottomNav.selectedItemId = R.id.bottom_nav_home



    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.bottom_nav_home -> currentFragment = homeFragment
            R.id.bottom_nav_search -> currentFragment = searchFragment
            R.id.bottom_nav_add_photo-> currentFragment = cameraFragment
            R.id.bottom_nav_profile -> currentFragment = profileFragment
        }

        currentFragment?.let {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_fragment, it)
                .commit()
        }
        return true
    }


}