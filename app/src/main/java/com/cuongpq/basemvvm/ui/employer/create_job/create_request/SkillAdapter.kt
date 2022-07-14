package com.cuongpq.basemvvm.ui.employer.create_job.create_request

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cuongpq.basemvvm.data.model.job.skill.Skill
import com.cuongpq.basemvvm.databinding.ItemSkillBinding

class SkillAdapter(private val inter : ISkillAdapter, var type : Int,var delete : Int) :
    RecyclerView.Adapter<SkillAdapter.Companion.SkillViewHolder>(){

    companion object {
        class SkillViewHolder(val binding: ItemSkillBinding) : RecyclerView.ViewHolder(binding.root)
    }

    interface ISkillAdapter {
        fun count(type : Int): Int
        fun getSkill(position: Int,type : Int): Skill
        fun onClickDeleteSkill(position: Int,type : Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SkillViewHolder {
        val binding = ItemSkillBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return SkillViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SkillViewHolder, position: Int) {
        var skill = inter.getSkill(position,type)
        if(delete == 1){
            holder.binding.btnDeleteSkill.visibility = View.VISIBLE
        }else if(delete == 2){
            holder.binding.btnDeleteSkill.visibility = View.GONE
        }
        holder.binding.skillName.setText("- " + skill.name)
        holder.binding.btnDeleteSkill.setOnClickListener { v-> inter.onClickDeleteSkill(position,type) }
    }

    override fun getItemCount(): Int {
        return inter.count(type)
    }
}