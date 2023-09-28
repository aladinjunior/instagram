package co.aladinjunior.instagram.profile.view


import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import co.aladinjunior.instagram.R
import co.aladinjunior.instagram.commom.base.BaseFragment
import co.aladinjunior.instagram.commom.model.Post
import co.aladinjunior.instagram.commom.model.UserAuth
import co.aladinjunior.instagram.commom.util.DependencyInjector
import co.aladinjunior.instagram.databinding.FragmentProfileBinding
import co.aladinjunior.instagram.profile.Profile

class ProfileFragment : BaseFragment<FragmentProfileBinding, Profile.Presenter>(
    R.layout.fragment_profile,
    FragmentProfileBinding::bind
), Profile.View {

    override lateinit var presenter: Profile.Presenter
    private lateinit var adapter: ProfileAdapter


    override fun setupViews() {
        adapter = ProfileAdapter()
        binding?.profileRv?.adapter = adapter
        binding?.profileRv?.layoutManager = GridLayoutManager(requireContext(), 3)


        presenter.fetchUserProfile()
        presenter.fetchUserPosts()
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
    }

    override fun displayRequestFailure(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    override fun displayFullPosts(posts: List<Post>) {
        presenter.fetchUserPosts()
        adapter.items = posts
        adapter.notifyDataSetChanged()
    }
}