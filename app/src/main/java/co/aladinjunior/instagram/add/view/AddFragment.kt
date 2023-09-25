package co.aladinjunior.instagram.add.view

import co.aladinjunior.instagram.R
import co.aladinjunior.instagram.add.Add
import co.aladinjunior.instagram.commom.base.BaseFragment
import co.aladinjunior.instagram.databinding.FragmentAddBinding
import com.google.android.material.tabs.TabLayoutMediator

class AddFragment : BaseFragment<FragmentAddBinding, Add.Presenter>(
    R.layout.fragment_add,
    FragmentAddBinding::bind), Add.View {
    override lateinit var presenter: Add.Presenter

    override fun setupPresenter() {
    }

    override fun setupViews() {
        val viewPager = binding?.addViewpager
        val tab = binding?.addTab
        val adapter = AddViewPagerAdapter(requireActivity())
        viewPager?.adapter = adapter

        if (tab != null && viewPager != null) {
            TabLayoutMediator(tab, viewPager) {tab, position ->
                tab.text = getString(adapter.tabs[position])
            }.attach()
        }
    }

}