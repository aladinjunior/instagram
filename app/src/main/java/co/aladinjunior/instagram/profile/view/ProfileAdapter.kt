package co.aladinjunior.instagram.profile.view

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import co.aladinjunior.instagram.R
import co.aladinjunior.instagram.commom.model.Post
import com.bumptech.glide.Glide

class ProfileAdapter : RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder>() {

    var items: List<Post> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        return ProfileViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_profile_photos, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        holder.bind(items[position].photoUrl)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ProfileViewHolder(view: View) : RecyclerView.ViewHolder(view){
        fun bind(photoUrl: String?){
            Glide.with(itemView.context).load(photoUrl.toString()).into(itemView.findViewById(R.id.profile_img_photos))
        }
    }

}