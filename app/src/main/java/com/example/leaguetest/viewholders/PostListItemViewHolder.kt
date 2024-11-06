package com.example.leaguetest.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.example.leaguetest.databinding.ItemPostListBinding
import com.example.leaguetest.models.post.UserPost
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation

class PostListItemViewHolder(
    private val binding: ItemPostListBinding,
    private val onClick: ((UserPost) -> Unit)?,
    private val picasso: Picasso
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(userPost: UserPost) {

        binding.root.setOnClickListener {
            onClick?.let { click -> click(userPost) }
        }

        binding.userName.text = userPost.user.username
        binding.title.text = userPost.post.title
        binding.descTV.text = userPost.post.body

        if (userPost.user.avatar?.isNullOrEmpty()?.not() == true) {
            picasso.load(userPost.user.avatar)
                .transform(CropCircleTransformation())
                .into(binding.image)
        }

    }

}