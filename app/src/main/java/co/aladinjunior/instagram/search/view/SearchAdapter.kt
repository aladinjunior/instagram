package co.aladinjunior.instagram.search.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import co.aladinjunior.instagram.R
import co.aladinjunior.instagram.commom.model.UserAuth

class SearchAdapter(private val onItemClick: (String) -> Unit) : RecyclerView.Adapter<SearchAdapter.ProfileViewHolder>() {

    var items: List<UserAuth> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        return ProfileViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_search_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ProfileViewHolder(view: View) : RecyclerView.ViewHolder(view){
        fun bind(users: UserAuth){
            itemView.findViewById<ImageView>(R.id.search_img_user)
                .setImageURI(users.photoUri)
            itemView.findViewById<TextView>(R.id.search_text_user)
                .text = users.name
            itemView.setOnClickListener {
                onItemClick(users.uuid)
            }
        }
    }

}