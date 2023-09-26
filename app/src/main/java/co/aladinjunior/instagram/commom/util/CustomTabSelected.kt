package co.aladinjunior.instagram.commom.util

import com.google.android.material.tabs.TabLayout

class CustomTabSelected(private val function: (TabLayout.Tab?) -> Unit) : TabLayout.OnTabSelectedListener {
    override fun onTabSelected(tab: TabLayout.Tab?) {
        function(tab)
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {
    }

    override fun onTabReselected(tab: TabLayout.Tab?) {
    }
}