package com.cuongpq.basemvvm.ui.employer.job.my_job

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cuongpq.basemvvm.R
import com.cuongpq.basemvvm.data.model.User
import com.cuongpq.basemvvm.data.model.job.Job
import com.cuongpq.basemvvm.databinding.ItemJobBinding

class JobAdapter(private val inter : IJob, val context: Context,val user : User): RecyclerView.Adapter<JobAdapter.Companion.JobViewHolder>() {
    companion object {
        class JobViewHolder(val binding: ItemJobBinding) : RecyclerView.ViewHolder(binding.root)
    }
    interface IJob {
        fun count(): Int
        fun getJob(position: Int): Job
        fun onClickJob(position: Int)
        fun onClickPower(position: Int)
        fun onClickDelete(position: Int)
    }

//    public fun setFilteredList(filteredList : List<Job>){
//
//        notifyDataSetChanged()
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobViewHolder {
        val binding = ItemJobBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return JobViewHolder(binding)
    }

    override fun onBindViewHolder(holder: JobViewHolder, position: Int) {
        var job = inter.getJob(position)
        if(user.permission == 0){
            holder.binding.imgPower.setVisibility(View.VISIBLE)
            holder.binding.btnDeleteJob.setVisibility(View.VISIBLE)
        }else if(user.permission == 1){
            holder.binding.imgPower.setVisibility(View.GONE)
            holder.binding.btnDeleteJob.setVisibility(View.GONE)
        }
        holder.binding.jobName.setText(job.jobName)
        holder.binding.companyName.setText(job.company!!.companyName)
        Glide.with(context).load(job.company!!.companyAvatar)
            .placeholder(R.drawable.logo_bacha)
            .error(R.drawable.logo_bacha)
            .into(holder.binding.avtCompany)
        if(job.status == 1){
            holder.binding.imgPower.setImageResource(R.drawable.ic_power_on)
        }else if(job.status == 2){
            holder.binding.imgPower.setImageResource(R.drawable.ic_power_off)
        }
        holder.itemView.setOnClickListener { v-> inter.onClickJob(position) }
        holder.binding.imgPower.setOnClickListener { v -> inter.onClickPower(position) }
        holder.binding.btnDeleteJob.setOnClickListener { v-> inter.onClickDelete(position) }
    }

    override fun getItemCount(): Int {
        return inter.count()
    }

}

