package co.aladinjunior.instagram.search.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import co.aladinjunior.instagram.R

class SearchAdapter : RecyclerView.Adapter<SearchAdapter.ProfileViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        return ProfileViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_search_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        holder.bind(R.drawable.ic_insta_add)
    }

    override fun getItemCount(): Int {
        return 30
    }

    inner class ProfileViewHolder(view: View) : RecyclerView.ViewHolder(view){
        fun bind(image: Int){
            itemView.findViewById<ImageView>(R.id.search_img_user)
                .setImageResource(image)
        }
    }

}