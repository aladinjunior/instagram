package co.aladinjunior.instagram.search.view

import android.app.SearchManager
import android.content.Context
import android.view.*
import androidx.appcompat.widget.SearchView

import androidx.recyclerview.widget.LinearLayoutManager
import co.aladinjunior.instagram.R
import co.aladinjunior.instagram.commom.base.BaseFragment
import co.aladinjunior.instagram.commom.model.UserAuth
import co.aladinjunior.instagram.commom.util.DependencyInjector
import co.aladinjunior.instagram.databinding.FragmentSearchBinding
import co.aladinjunior.instagram.search.Search


class SearchFragment : BaseFragment<FragmentSearchBinding, Search.Presenter>(
    R.layout.fragment_search, FragmentSearchBinding::bind
), Search.View {


    override lateinit var presenter: Search.Presenter
    private val adapter by lazy {SearchAdapter(onItemClick)}
    private var searchListener: SearchListener? = null


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is SearchListener){
            searchListener = context
        }
    }


    override fun setupPresenter() {
        presenter = DependencyInjector.searchPresenter(this, DependencyInjector.searchRepository())
    }



    override fun setupViews() {
        binding?.searchRv?.layoutManager = LinearLayoutManager(requireContext())
        binding?.searchRv?.adapter = adapter



    }
    private val onItemClick : (String) -> Unit = {uuid ->
        searchListener?.goToSearchedProfile(uuid)

    }

    override fun getMenu(): Int {
        return R.menu.menu_search
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        val searchManager = requireActivity().getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.menu_search).actionView as SearchView
        with(searchView){
            apply {
                setSearchableInfo(searchManager.getSearchableInfo(requireActivity().componentName))
                setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        return false
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        if (newText?.isNotEmpty() == true){
                            presenter.fetchUsers(newText)
                            return true
                        }
                        return false
                    }
                })
            }
        }



    }

    override fun displayUsers(user: List<UserAuth>) {
        binding?.searchEmptyUsers?.visibility = View.GONE
        binding?.searchRv?.visibility = View.VISIBLE
        adapter.items = user
        adapter.notifyDataSetChanged()
    }

    override fun displayEmptyUsers() {
        binding?.searchEmptyUsers?.visibility = View.VISIBLE
        binding?.searchRv?.visibility = View.GONE
    }

    interface SearchListener{
        fun goToSearchedProfile(uuid: String)
    }
}
