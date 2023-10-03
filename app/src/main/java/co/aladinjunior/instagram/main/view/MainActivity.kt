package co.aladinjunior.instagram.main.view

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import co.aladinjunior.instagram.R
import co.aladinjunior.instagram.post.view.AddFragment
import co.aladinjunior.instagram.commom.extension.replaceFragment
import co.aladinjunior.instagram.databinding.ActivityMainBinding
import co.aladinjunior.instagram.home.view.HomeFragment
import co.aladinjunior.instagram.profile.view.ProfileFragment
import co.aladinjunior.instagram.search.view.SearchFragment
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener, AddFragment.AddListener, SearchFragment.SearchListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var homeFragment: HomeFragment
    private lateinit var searchFragment: Fragment
    private lateinit var addFragment: Fragment
    private lateinit var profileFragment: ProfileFragment
    private lateinit var currentFragment: Fragment


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
        addFragment = AddFragment()
        profileFragment = ProfileFragment()


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
        } else {
            params.scrollFlags = 0
            coordinatorParams.behavior = null
        }
        binding.mainAppbar.layoutParams = coordinatorParams
    }

    override fun goToSearchedProfile(uuid: String) {
        val fragment = ProfileFragment()
        fragment.arguments = bundleOf(USER_ID_KEY to uuid)
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.main_fragment, fragment, fragment.javaClass.simpleName + "detail")
            addToBackStack(null)
            commit()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var scrollingToolbar = false
        when (item.itemId) {
            R.id.bottom_nav_home -> currentFragment = homeFragment
            R.id.bottom_nav_add_photo -> currentFragment = addFragment
            R.id.bottom_nav_search -> currentFragment = searchFragment
            R.id.bottom_nav_profile -> {
                currentFragment = profileFragment
                scrollingToolbar = true
            }

        }


        setScrollingToolbar(scrollingToolbar)

        replaceFragment(R.id.main_fragment, currentFragment)

        return true
    }

    override fun onPostCreated() {
        homeFragment.presenter.clearCache()
        if(supportFragmentManager.findFragmentByTag(profileFragment.javaClass.simpleName) != null){
            profileFragment.presenter.clearCache()
        }


        binding.mainBottomNav.selectedItemId = R.id.bottom_nav_home
    }

    companion object{
        const val USER_ID_KEY = "user_id_key"
    }
}