package com.cuongpq.basemvvm.ui.noticification

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cuongpq.basemvvm.data.model.NotificationItem
import com.cuongpq.basemvvm.databinding.ItemNotificationBinding

class NoticificationAdapter(private val inter : INotification) : RecyclerView.Adapter<NoticificationAdapter.Companion.NotificationViewHolder>(){

    companion object{
        class NotificationViewHolder(val binding: ItemNotificationBinding) : RecyclerView.ViewHolder(binding.root)
    }
    interface INotification{
        fun count(): Int
        fun getNotification(position: Int): NotificationItem
        fun onClickViewProfile(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        val binding = ItemNotificationBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NotificationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        val notificationItem = inter.getNotification(position)
        holder.binding.tvContentNotification.text = notificationItem.candidate!!.name+" đã ứng tuyển vào công việc "+notificationItem.job!!.jobName
        holder.itemView.setOnClickListener { v-> inter.onClickViewProfile(position) }
    }

    override fun getItemCount(): Int {
        return inter.count()
    }
}