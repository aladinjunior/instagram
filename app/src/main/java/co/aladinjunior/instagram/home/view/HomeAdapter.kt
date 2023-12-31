package co.aladinjunior.instagram.home.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import co.aladinjunior.instagram.R
import co.aladinjunior.instagram.commom.model.Post
import com.bumptech.glide.Glide

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.ProfileViewHolder>() {

    var items: List<Post> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        return ProfileViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_home_post, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ProfileViewHolder(view: View) : RecyclerView.ViewHolder(view){
        fun bind(posts: Post){

            Glide.with(itemView.context).load(posts.photoUrl).into(itemView.findViewById(R.id.home_img_post))
            Glide.with(itemView.context).load(posts.photoUrl).into(itemView.findViewById(R.id.home_img_user))
            itemView.findViewById<TextView>(R.id.home_captions).text = posts.caption
            itemView.findViewById<TextView>(R.id.home_username).text = posts.publisher?.name
        }
    }

}