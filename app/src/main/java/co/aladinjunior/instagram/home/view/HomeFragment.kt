package co.aladinjunior.instagram.home.view



import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import co.aladinjunior.instagram.R
import co.aladinjunior.instagram.commom.base.BaseFragment
import co.aladinjunior.instagram.commom.model.Post
import co.aladinjunior.instagram.commom.util.DependencyInjector
import co.aladinjunior.instagram.databinding.FragmentHomeBinding
import co.aladinjunior.instagram.home.Home


class HomeFragment : BaseFragment<FragmentHomeBinding, Home.Presenter>(
    R.layout.fragment_home,
    FragmentHomeBinding::bind
), Home.View {
    override lateinit var presenter: Home.Presenter
    private lateinit var adapter: HomeAdapter

    override fun setupViews() {
        adapter = HomeAdapter()
        binding?.homeRv?.layoutManager = LinearLayoutManager(requireContext())
        binding?.homeRv?.adapter = adapter

        presenter.fetchPosts()
    }

    override fun setupPresenter() {
        presenter = DependencyInjector.homePresenter(this, DependencyInjector.homeRepository())
    }

    override fun getMenu() = R.menu.menu_profile_toolbar

    override fun displayPostsFailure(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun displayFullPosts(posts: List<Post>) {
        adapter.items = posts
        adapter.notifyDataSetChanged()
    }


}
