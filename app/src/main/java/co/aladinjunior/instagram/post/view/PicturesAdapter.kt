package co.aladinjunior.instagram.post.view


import android.graphics.BitmapFactory
import android.media.ThumbnailUtils
import android.net.Uri
import android.os.Build
import android.util.Size
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import co.aladinjunior.instagram.R

class PicturesAdapter(private val onClick: (image: Uri) -> Unit) : RecyclerView.Adapter<PicturesAdapter.PostViewHolder>() {

    var items: List<Uri> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_profile_photos, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class PostViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(image: Uri) {
            val bitmap =  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
                 itemView.context.contentResolver.loadThumbnail(image, Size(200, 200), null)
            } else {
                ThumbnailUtils.extractThumbnail(BitmapFactory.decodeFile(image.toString()),
                200, 200)
            }

            itemView.findViewById<ImageView>(R.id.profile_img_photos)
                .setImageBitmap(bitmap)
            itemView.setOnClickListener {
                onClick(image)
            }
        }
    }

}