package com.osg.mvipractice.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.osg.mvipractice.R
import com.osg.mvipractice.data.User
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_layout.view.*

class MainAdapter(private val users:ArrayList<User>) : RecyclerView.Adapter<MainAdapter.DataViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int)
            = DataViewHolder(
        LayoutInflater.from(p0.context).inflate(R.layout.item_layout, p0, false)
    )

    override fun onBindViewHolder(p0: MainAdapter.DataViewHolder, p1: Int)
    =p0.bind(users[p1])


    override fun getItemCount(): Int = users.size

    class DataViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(user: User){
            itemView.textViewUserName.text=user.name
            itemView.textViewUserEmail.text=user.email
            Glide.with(itemView.imageViewAvatar.context).load(user.avatar).into(itemView.imageViewAvatar)
        }
    }

    fun addData(list: List<User>)
    {
        users.addAll(list)
    }
}