package co.aladinjunior.instagram.commom.base

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.annotation.LayoutRes
import androidx.annotation.MenuRes
import androidx.fragment.app.Fragment

abstract class BaseFragment<T, P : BasePresenter>(
    @LayoutRes private val layoutId: Int,
    private val bind: (View) -> T
) : Fragment(layoutId) {

    protected var binding: T? = null
    abstract var presenter: P


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = bind(view)
        if(savedInstanceState == null) setupViews()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getMenu()?.let {
            setHasOptionsMenu(true)
        }
        setupPresenter()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        getMenu()?.let {
            menu.clear()
            inflater.inflate(it, menu)
            super.onCreateOptionsMenu(menu, inflater)
        }

    }
    abstract fun setupPresenter()

    override fun onDestroy() {
        binding = null
        presenter.onDestroy()
        super.onDestroy()
    }

    abstract fun setupViews()

    @MenuRes
    open fun getMenu(): Int? {
        return null
    }
}