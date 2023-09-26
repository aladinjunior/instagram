package co.aladinjunior.instagram.add.view

import android.Manifest
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
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

        if (allPermissionsGranted()){
            startCamera()
        } else {
            getPermission.launch(REQUIRED_PERMISSION)
        }
    }

    private val getPermission = registerForActivityResult(ActivityResultContracts.RequestPermission()){
        if (allPermissionsGranted()) startCamera()
        else Toast.makeText(requireContext(), R.string.camera_access_denied, Toast.LENGTH_SHORT).show()
    }

    private fun startCamera(){
    }

    private fun allPermissionsGranted() : Boolean {
        return ContextCompat.checkSelfPermission(requireContext(), REQUIRED_PERMISSION) == PackageManager.PERMISSION_GRANTED
    }

    companion object {
        const val REQUIRED_PERMISSION = Manifest.permission.CAMERA
    }

}