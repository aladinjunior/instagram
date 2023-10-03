package co.aladinjunior.instagram.profile.view


import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import co.aladinjunior.instagram.R
import co.aladinjunior.instagram.commom.base.BaseFragment
import co.aladinjunior.instagram.commom.model.Post
import co.aladinjunior.instagram.commom.model.UserAuth
import co.aladinjunior.instagram.commom.util.DependencyInjector
import co.aladinjunior.instagram.databinding.FragmentProfileBinding
import co.aladinjunior.instagram.main.view.MainActivity.Companion.USER_ID_KEY
import co.aladinjunior.instagram.profile.Profile
import com.google.android.material.bottomnavigation.BottomNavigationView

class ProfileFragment : BaseFragment<FragmentProfileBinding, Profile.Presenter>(
    R.layout.fragment_profile,
    FragmentProfileBinding::bind
), Profile.View, BottomNavigationView.OnNavigationItemSelectedListener {

    override lateinit var presenter: Profile.Presenter
    private lateinit var adapter: ProfileAdapter
    private var uuid: String? = null


    override fun setupViews() {
        uuid = arguments?.getString(USER_ID_KEY)
        adapter = ProfileAdapter()
        binding?.profileRv?.adapter = adapter
        binding?.profileRv?.layoutManager = GridLayoutManager(requireContext(), 3)

        binding?.profileTopNav?.setOnNavigationItemSelectedListener(this)

        presenter.fetchUserProfile(uuid)

    }

    override fun setupPresenter() {
        presenter =
            DependencyInjector.profilePresenter(this, DependencyInjector.profileRepository())
    }


    override fun getMenu(): Int {
        return R.menu.menu_profile_toolbar
    }


    override fun displayUserProfile(userAuth: UserAuth) {
        binding?.profilePostsCount?.text = userAuth.postCount.toString()
        binding?.profileFollowingCount?.text = userAuth.followingCount.toString()
        binding?.profileFollowersCount?.text = userAuth.followersCount.toString()
        binding?.profileTextUsername?.text = userAuth.name
        binding?.profileTextBio?.text = "TODO"
        binding?.profileImgIcon?.setImageURI(userAuth.photoUri)

        presenter.fetchUserPosts(uuid)
    }

    override fun displayRequestFailure(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    override fun displayFullPosts(posts: List<Post>) {
        adapter.items = posts
        adapter.notifyDataSetChanged()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.top_nav_profile_grid -> binding?.profileRv?.layoutManager = GridLayoutManager(requireContext(), 3)
            R.id.top_nav_profile_listed -> binding?.profileRv?.layoutManager = LinearLayoutManager(requireContext())
        }
        return true
    }
}