package com.zamulk.ezshifaassesmentmuti.views.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zamulk.ezshifaassesmentmuti.databinding.ListItemsPostsBinding
import com.zamulk.ezshifaassesmentmuti.models.PostsModel

class PostsAdapter(
    private val mContext: Context,
) : RecyclerView.Adapter<PostsAdapter.PostsViewHolder>() {
    private var allPostsList: ArrayList<PostsModel>

    init {
        allPostsList = ArrayList()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    inner class PostsViewHolder(val binding: ListItemsPostsBinding) :
        RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        val binding =
            ListItemsPostsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostsViewHolder(binding)
    }

    fun updatePostItems(allPostsList: ArrayList<PostsModel>) {
        this.allPostsList = allPostsList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        val postsModel: PostsModel = allPostsList[position]


        with(holder) {
            with(binding) {
                with(postsModel) {
                    tvPostTitle.text = title
                    Glide.with(mContext).load(thumbnailUrl).into(ivPostImg)
                }
            }

        }
    }


    override fun getItemCount(): Int {
        return allPostsList.size
    }

}
