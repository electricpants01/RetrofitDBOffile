package com.locotodevteam.postdb1.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.locotodevteam.postdb1.R
import com.locotodevteam.postdb1.databinding.RvPostItemBinding
import com.locotodevteam.postdb1.model.Post

class PostAdapter(val myPosts: List<Post>): RecyclerView.Adapter<PostAdapter.PostHolder>() {
    class PostHolder(val view: View): RecyclerView.ViewHolder(view)  {
        val postItemBinding = RvPostItemBinding.bind(view)
        fun render(post: Post){
            postItemBinding.title.text = post.title
        }

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): PostHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.rv_post_item,viewGroup, false)
        return PostHolder(view)
    }

    override fun onBindViewHolder(holder: PostHolder, position: Int) {
        val item = myPosts[position]
        holder.render(item)
    }

    override fun getItemCount(): Int = myPosts.size
}