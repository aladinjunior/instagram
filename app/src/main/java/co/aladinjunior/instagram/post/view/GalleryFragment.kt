package co.aladinjunior.instagram.post.view

import android.net.Uri
import android.view.MenuItem
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.GridLayoutManager
import co.aladinjunior.instagram.R
import co.aladinjunior.instagram.commom.base.BaseFragment
import co.aladinjunior.instagram.commom.util.DependencyInjector
import co.aladinjunior.instagram.databinding.FragmentGalleryBinding
import co.aladinjunior.instagram.post.Post
import co.aladinjunior.instagram.post.data.PostLocalDataSource
import co.aladinjunior.instagram.post.data.PostRepository
import co.aladinjunior.instagram.post.presentation.GalleryPresenter
import co.aladinjunior.instagram.post.view.CameraFragment.Companion.URI
import co.aladinjunior.instagram.post.view.CameraFragment.Companion.URI_KEY

class GalleryFragment : BaseFragment<FragmentGalleryBinding, Post.Presenter>(
    R.layout.fragment_gallery,
    FragmentGalleryBinding::bind
), Post.View{

    override lateinit var presenter: Post.Presenter
    private lateinit var adapter: PicturesAdapter


    override fun setupPresenter() {
        presenter = DependencyInjector.galleryPresenter(this, DependencyInjector.postRepository(requireContext()))
    }

    override fun setupViews() {
        adapter = PicturesAdapter{uri ->
            binding?.galleryScrolling?.smoothScrollTo(0, 0)
            binding?.galleryImgSelected?.setImageURI(uri)
            presenter.setPic(uri)
        }

        binding?.galleryRv?.layoutManager = GridLayoutManager(requireContext(), 3)
        binding?.galleryRv?.adapter = adapter

        presenter.fetchPics()
    }

    override fun showMessageOnScreen(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun loadAllPics(pictures: List<Uri>) {
        binding?.galleryScrolling?.smoothScrollTo(0,0)
        binding?.galleryImgSelected?.setImageURI(pictures.firstOrNull())
        presenter.setPic(pictures.first())
        adapter.items = pictures
        adapter.notifyDataSetChanged()
    }


    override fun getMenu(): Int {
        return R.menu.menu_share
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_share ->{
                setFragmentResult(URI_KEY, bundleOf(URI to presenter.getPic()))
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        presenter.fetchPics()
        super.onResume()
    }

    override fun onPause() {
        presenter.onDestroy()
        super.onPause()
    }


}
