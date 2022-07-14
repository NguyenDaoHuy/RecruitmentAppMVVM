package com.cuongpq.basemvvm.ui.employer.create_job.create_question

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cuongpq.basemvvm.data.model.job.question.Question
import com.cuongpq.basemvvm.databinding.ItemSkillBinding

class QuestionAdapter(private val inter : IQuestionAdapter) :
    RecyclerView.Adapter<QuestionAdapter.Companion.QuestionViewHolder>(){

    companion object {
        class QuestionViewHolder(val binding: ItemSkillBinding) : RecyclerView.ViewHolder(binding.root)
    }
    interface IQuestionAdapter {
        fun count(): Int
        fun getQuestion(position: Int): Question
        fun onClickDeleteQuestion(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val binding = ItemSkillBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return QuestionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        var question = inter.getQuestion(position)
        holder.binding.skillName.setText("- " + question.content)
        holder.binding.btnDeleteSkill.setOnClickListener { v-> inter.onClickDeleteQuestion(position)}
    }

    override fun getItemCount(): Int {
        return inter.count()
    }
}