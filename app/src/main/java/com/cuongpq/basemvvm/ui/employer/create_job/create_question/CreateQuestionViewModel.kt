package com.cuongpq.basemvvm.ui.employer.create_job.create_question

import android.content.Context
import com.cuongpq.basemvvm.data.local.AppDatabase
import com.cuongpq.basemvvm.data.model.job.question.Question
import com.cuongpq.basemvvm.data.remote.InteractCommon
import com.cuongpq.basemvvm.data.sqlite.SQLiteHelper
import com.cuongpq.basemvvm.ui.base.viewmodel.BaseViewModel
import java.util.concurrent.Executor
import javax.inject.Inject

class CreateQuestionViewModel  @Inject constructor(
    appDatabase: AppDatabase,
    interactCommon: InteractCommon,
    scheduler: Executor
) : BaseViewModel<CreateQuestionCallBack>(appDatabase, interactCommon, scheduler) {

    lateinit var idJob : String
    private var edQuestion : String? = null
    private lateinit var sqLiteHelper : SQLiteHelper
    private var questionList : ArrayList<Question>? = null
    init {

    }
    companion object{
        val ADD_QUESTION = 1
        val ADD_QUESTION_SUCCESS = 2
        val DELETE_QUESTION_SUCCESS = 3
        val ON_CLICK_NEXT_TO_RIGHT = 4
    }
    fun onClickNext(){
        uiEventLiveData.value = ON_CLICK_NEXT_TO_RIGHT
    }
    fun onClickAddQuestion(){
        uiEventLiveData.value = ADD_QUESTION
    }
    fun addQuestion(edQuestion: String?,context: Context?){
        sqLiteHelper = SQLiteHelper(context, "Data.sqlite", null, 5)
        sqLiteHelper.QueryData("INSERT INTO Question VALUES(null,'$edQuestion','$idJob')")
        uiEventLiveData.value = ADD_QUESTION_SUCCESS
    }
    fun getQuestion(context: Context?){
        questionList = ArrayList()
        sqLiteHelper = SQLiteHelper(context, "Data.sqlite", null, 5)
        val data = sqLiteHelper.GetData("SELECT * FROM Question WHERE IdJob = '$idJob'")
        while (data.moveToNext()) {
            val id = data.getInt(0)
            val content = data.getString(1)
            val idJob = data.getString(2)
            val question = Question(id,content,idJob)
            questionList!!.add(question)
        }
    }
    fun getListQuestion() : ArrayList<Question>{
        return questionList!!
    }
    fun deleteQuestion(question: Question,context: Context?){
        sqLiteHelper = SQLiteHelper(context, "Data.sqlite", null, 5)
        sqLiteHelper.QueryData("DELETE FROM Question WHERE IdJob = '$idJob' AND Id ='${question.id}'")
        uiEventLiveData.value = DELETE_QUESTION_SUCCESS
    }
}