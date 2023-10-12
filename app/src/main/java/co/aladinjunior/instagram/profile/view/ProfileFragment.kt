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

        binding?.profileBttnEditProfile?.setOnClickListener {
            if (it.tag == true){
                binding?.profileBttnEditProfile?.text = getString(R.string.follow)
                binding?.profileBttnEditProfile?.tag = false
                presenter.followUser(uuid!!, false)
            } else if (it.tag == false){
                binding?.profileBttnEditProfile?.text = getString(R.string.unfollow)
                binding?.profileBttnEditProfile?.tag = true
                presenter.followUser(uuid!!, true)

            }
        }

        presenter.fetchUserProfile(uuid)

    }

    override fun setupPresenter() {
        presenter =
            DependencyInjector.profilePresenter(this, DependencyInjector.profileRepository())
    }


    override fun getMenu(): Int {
        return R.menu.menu_profile_toolbar
    }


    override fun displayUserProfile(userAuth: Pair<UserAuth, Boolean?>) {
        val (user, following) = userAuth

        binding?.profilePostsCount?.text = user.postCount.toString()
        binding?.profileFollowingCount?.text = user.followingCount.toString()
        binding?.profileFollowersCount?.text = user.followersCount.toString()
        binding?.profileTextUsername?.text = user.name
        binding?.profileTextBio?.text = "TODO"
        binding?.profileImgIcon?.setImageURI(user.photoUri)

        binding?.profileBttnEditProfile?.text = when(following) {
            null -> getString(R.string.edit_profile)
            true -> getString(R.string.unfollow)
            false -> getString(R.string.follow)
        }

        binding?.profileBttnEditProfile?.tag = following

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