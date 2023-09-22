package co.aladinjunior.instagram.main.view

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import co.aladinjunior.instagram.R
import co.aladinjunior.instagram.camera.view.CameraFragment
import co.aladinjunior.instagram.databinding.ActivityMainBinding
import co.aladinjunior.instagram.home.view.HomeFragment
import co.aladinjunior.instagram.profile.view.ProfileFragment
import co.aladinjunior.instagram.search.view.SearchFragment
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.lang.IllegalStateException

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var homeFragment: Fragment
    private lateinit var searchFragment: Fragment
    private lateinit var cameraFragment: Fragment
    private lateinit var profileFragment: Fragment
    private lateinit var currentFragment: Fragment

    private lateinit var fragmentSavedState: HashMap<String, Fragment.SavedState>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.mainToolbar)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_insta_camera)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = ""

//        homeFragment = HomeFragment()
//        searchFragment = SearchFragment()
//        cameraFragment = CameraFragment()
//        profileFragment = ProfileFragment()
//
//        currentFragment = homeFragment
//
//        supportFragmentManager.beginTransaction().apply {
//            add(R.id.main_fragment, homeFragment, "0")
//            add(R.id.main_fragment, profileFragment, "1").hide(profileFragment)
//            add(R.id.main_fragment, cameraFragment, "2").hide(cameraFragment)
//            add(R.id.main_fragment, searchFragment, "3").hide(searchFragment)
//            commit()
//        }

        if(savedInstanceState == null){
            fragmentSavedState = HashMap()
        } else{
            savedInstanceState.getSerializable("fragmentState") as HashMap<String, Fragment.SavedState>
        }

        binding.mainBottomNav.setOnNavigationItemSelectedListener(this)
        binding.mainBottomNav.selectedItemId = R.id.bottom_nav_home


    }

    private fun setScrollingToolbar(enabled: Boolean) {
        val params = binding.mainToolbar.layoutParams as AppBarLayout.LayoutParams
        val coordinatorParams = binding.mainAppbar.layoutParams as CoordinatorLayout.LayoutParams
        if (enabled) {
            params.scrollFlags =
                AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL or AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS
            coordinatorParams.behavior = AppBarLayout.Behavior()
        } else{
            params.scrollFlags = 0
            coordinatorParams.behavior = null
        }
        binding.mainAppbar.layoutParams = coordinatorParams
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putSerializable("fragmentState", fragmentSavedState)
        super.onSaveInstanceState(outState)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val scrollingToolbar = false
        val newFragment = when(item.itemId){
            R.id.bottom_nav_home ->{
                HomeFragment()
            }
            R.id.bottom_nav_profile ->{
                ProfileFragment()
            }
            else -> null
        }

        val currentFragment = supportFragmentManager.findFragmentById(R.id.main_fragment)
        val fragmentTag = newFragment?.javaClass?.simpleName

        if (!currentFragment?.tag.equals(fragmentTag)) {
            currentFragment?.let { fragment ->
                fragmentSavedState.put(
                    fragment.tag!!,
                    supportFragmentManager.saveFragmentInstanceState(fragment)!!
                )
            }
        }
        newFragment?.setInitialSavedState(fragmentSavedState[fragmentTag])
        newFragment?.let {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.main_fragment, it, fragmentTag)
                addToBackStack(fragmentTag)
                commit()
            }
        }

        setScrollingToolbar(scrollingToolbar)

        return true
    }


}