package com.cuongpq.basemvvm.ui.profile.update_skill

import android.content.Context
import com.cuongpq.basemvvm.data.local.AppDatabase
import com.cuongpq.basemvvm.data.model.job.skill.Skill
import com.cuongpq.basemvvm.data.remote.InteractCommon
import com.cuongpq.basemvvm.data.sqlite.SQLiteHelper
import com.cuongpq.basemvvm.ui.base.viewmodel.BaseViewModel
import com.cuongpq.basemvvm.ui.employer.create_job.create_request.CreateRequestViewModel
import java.util.concurrent.Executor
import javax.inject.Inject

class UpdateSkillViewModel @Inject constructor(
    appDatabase: AppDatabase,
    interactCommon: InteractCommon,
    scheduler: Executor
) : BaseViewModel<UpdateSkillCallBack>(appDatabase, interactCommon, scheduler){

    lateinit var idAccount : String
    private lateinit var sqLiteHelper : SQLiteHelper
    private var experienceList : ArrayList<Skill>? = null
    private var educationList : ArrayList<Skill>? = null
    private var certificationList : ArrayList<Skill>? = null
    private var languageList : ArrayList<Skill>? = null

    companion object{
        val ADD_EXPERIENCE_2 = 1
        val ADD_EDUCATION_2  = 2
        val ADD_CERTIFICATION_2  = 3
        val ADD_LANGUAGE_2  = 4
        val ADD_EXPERIENCE_SUCCESS_2  = 5
        val ADD_EDUCATION_SUCCESS_2  = 6
        val ADD_CERTIFICATION_SUCCESS_2  = 7
        val ADD_LANGUAGE_SUCCESS_2  = 8
        val DELETE_EXPERIENCE_SUCCESS_2  = 9
        val DELETE_EDUCATION_SUCCESS_2  = 10
        val DELETE_CERTIFICATION_SUCCESS_2  = 11
        val DELETE_LANGUAGE_SUCCESS_2  = 12
        val CLICK_UPDATE = 13
    }
    init {

    }
    fun onClickNext(){
        uiEventLiveData.value = CLICK_UPDATE
    }
    fun onClickAddExperience(){
        uiEventLiveData.value = ADD_EXPERIENCE_2
    }
    fun onClickAddEducation(){
        uiEventLiveData.value = ADD_EDUCATION_2
    }
    fun onClickAddCertification(){
        uiEventLiveData.value = ADD_CERTIFICATION_2
    }
    fun onClickAddLanguage(){
        uiEventLiveData.value = ADD_LANGUAGE_2
    }
    fun addExperience(edExperience: String?,context: Context?){
        sqLiteHelper = SQLiteHelper(context, "Data.sqlite", null, 5)
        sqLiteHelper.QueryData("INSERT INTO UserSkill VALUES(null,'$edExperience','1','$idAccount')")
        uiEventLiveData.value = ADD_EXPERIENCE_SUCCESS_2
    }
    fun addEducation(edEducation: String?,context: Context?){
        sqLiteHelper = SQLiteHelper(context, "Data.sqlite", null, 5)
        sqLiteHelper.QueryData("INSERT INTO UserSkill VALUES(null,'$edEducation','2','$idAccount')")
        uiEventLiveData.value = ADD_EDUCATION_SUCCESS_2
    }
    fun addCertification(edCertification: String?,context: Context?){
        sqLiteHelper = SQLiteHelper(context, "Data.sqlite", null, 5)
        sqLiteHelper.QueryData("INSERT INTO UserSkill VALUES(null,'$edCertification','3','$idAccount')")
        uiEventLiveData.value = ADD_CERTIFICATION_SUCCESS_2
    }
    fun addLanguage(edLanguage: String?,context: Context?){
        sqLiteHelper = SQLiteHelper(context, "Data.sqlite", null, 5)
        sqLiteHelper.QueryData("INSERT INTO UserSkill VALUES(null,'$edLanguage','4','$idAccount')")
        uiEventLiveData.value = ADD_LANGUAGE_SUCCESS_2
    }
    //get List Experience
    fun getExperience(context: Context?){
        experienceList = ArrayList()
        sqLiteHelper = SQLiteHelper(context, "Data.sqlite", null, 5)
        val data = sqLiteHelper.GetData("SELECT * FROM UserSkill WHERE IdAccount = '$idAccount' AND type = '1'")
        while (data.moveToNext()) {
            val id = data.getInt(0)
            val name = data.getString(1)
            val type = data.getInt(2)
            val idAccount = data.getString(3)
            val skill = Skill(id,name,type,idAccount)
            experienceList!!.add(skill)
        }
    }
    //get List Education
    fun getEducation(context: Context?){
        educationList = ArrayList()
        sqLiteHelper = SQLiteHelper(context, "Data.sqlite", null, 5)
        val data = sqLiteHelper.GetData("SELECT * FROM UserSkill WHERE IdAccount = '$idAccount' AND type = '2'")
        while (data.moveToNext()) {
            val id = data.getInt(0)
            val name = data.getString(1)
            val type = data.getInt(2)
            val idJob = data.getString(3)
            val skill = Skill(id,name,type,idJob)
            educationList!!.add(skill)
        }
    }

    //get List Certification
    fun getCertification(context: Context?){
        certificationList = ArrayList()
        sqLiteHelper = SQLiteHelper(context, "Data.sqlite", null, 5)
        val data = sqLiteHelper.GetData("SELECT * FROM UserSkill WHERE IdAccount = '$idAccount' AND type = '3'")
        while (data.moveToNext()) {
            val id = data.getInt(0)
            val name = data.getString(1)
            val type = data.getInt(2)
            val idJob = data.getString(3)
            val skill = Skill(id,name,type,idJob)
            certificationList!!.add(skill)
        }
    }

    // get List Language
    fun getLanguage(context: Context?){
        languageList = ArrayList()
        sqLiteHelper = SQLiteHelper(context, "Data.sqlite", null, 5)
        val data = sqLiteHelper.GetData("SELECT * FROM UserSkill WHERE IdAccount = '$idAccount' AND type = '4'")
        while (data.moveToNext()) {
            val id = data.getInt(0)
            val name = data.getString(1)
            val type = data.getInt(2)
            val idJob = data.getString(3)
            val skill = Skill(id,name,type,idJob)
            languageList!!.add(skill)
        }
    }
    fun getListExperience() : ArrayList<Skill>{
        return experienceList!!
    }
    fun getListEducation() : ArrayList<Skill>{
        return educationList!!
    }
    fun getListCertification() : ArrayList<Skill>{
        return certificationList!!
    }
    fun getListLanguage() : ArrayList<Skill>{
        return languageList!!
    }

    fun deleteItem(skill : Skill,context: Context?,type: Int){
        sqLiteHelper = SQLiteHelper(context, "Data.sqlite", null, 5)
        sqLiteHelper.QueryData("DELETE FROM UserSkill WHERE IdAccount = '$idAccount' AND Id = '${skill.id}'")
        if(type == 1){
            uiEventLiveData.value = DELETE_EXPERIENCE_SUCCESS_2
        }else if(type == 2){
            uiEventLiveData.value = DELETE_EDUCATION_SUCCESS_2
        }else if(type == 3){
            uiEventLiveData.value = DELETE_CERTIFICATION_SUCCESS_2
        }else if(type == 4){
            uiEventLiveData.value = DELETE_LANGUAGE_SUCCESS_2
        }
    }
}