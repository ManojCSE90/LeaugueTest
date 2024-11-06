package com.example.leaguetest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.leaguetest.models.post.UserPost
import com.example.leaguetest.databinding.ItemPostListBinding
import com.example.leaguetest.viewholders.PostListItemViewHolder
import com.squareup.picasso.Picasso
import javax.inject.Inject

class PostListAdapter @Inject constructor(private val picasso: Picasso) :
    RecyclerView.Adapter<PostListItemViewHolder>() {

    private val userPosts = mutableListOf<UserPost>()
    private var onclik: ((UserPost) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostListItemViewHolder {

        val binding =
            ItemPostListBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return PostListItemViewHolder(binding, onclik, picasso)
    }

    override fun getItemCount(): Int {
        return userPosts.size
    }

    fun updateData(list: List<UserPost>) {
        userPosts.addAll(list)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: PostListItemViewHolder, position: Int) {

        holder.bind(userPosts[position])
    }

    fun setOnClick(click: (UserPost) -> Unit) {
        onclik = click
    }
}