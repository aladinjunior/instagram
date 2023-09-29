package co.aladinjunior.instagram.post.view

import android.net.Uri
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import co.aladinjunior.instagram.R
import co.aladinjunior.instagram.commom.base.BaseFragment
import co.aladinjunior.instagram.commom.util.DependencyInjector
import co.aladinjunior.instagram.databinding.FragmentGalleryBinding
import co.aladinjunior.instagram.post.Post
import co.aladinjunior.instagram.post.data.PostLocalDataSource
import co.aladinjunior.instagram.post.data.PostRepository
import co.aladinjunior.instagram.post.presentation.GalleryPresenter

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
        adapter.items = pictures
        adapter.notifyDataSetChanged()
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
