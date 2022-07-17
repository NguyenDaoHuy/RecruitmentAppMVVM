package com.cuongpq.basemvvm.ui.account.update_skill

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.cuongpq.basemvvm.R
import com.cuongpq.basemvvm.data.model.User
import com.cuongpq.basemvvm.data.model.job.skill.Skill
import com.cuongpq.basemvvm.databinding.FragmentUpdateSkillBinding
import com.cuongpq.basemvvm.ui.base.fragment.BaseMvvmFragment
import com.cuongpq.basemvvm.ui.base.viewmodel.BaseViewModel
import com.cuongpq.basemvvm.ui.employer.create_job.create_request.SkillAdapter

class UpdateSkillFragment(var user : User?) : BaseMvvmFragment<UpdateSkillCallBack,UpdateSkillViewModel>(),UpdateSkillCallBack,SkillAdapter.ISkillAdapter {

    private var edExperience : String? = null
    private var edEducation : String? = null
    private var edCertification : String? = null
    private var edLanguage : String? = null

    override fun setEvents() {

    }

    override fun initComponents() {
         getBindingData().updateSkillViewModel = mModel
         mModel.idAccount = user!!.idAccount
         mModel.uiEventLiveData.observe(this){
             when(it){
                 BaseViewModel.FINISH_ACTIVITY -> finishActivity()
                 UpdateSkillViewModel.ADD_EXPERIENCE_2 -> onAddExperience()
                 UpdateSkillViewModel.ADD_EXPERIENCE_SUCCESS_2 -> onAddExperienceSuccess()
                 UpdateSkillViewModel.ADD_EDUCATION_2 -> onAddEducation()
                 UpdateSkillViewModel.ADD_EDUCATION_SUCCESS_2 -> onAddEducationSuccess()
                 UpdateSkillViewModel.ADD_CERTIFICATION_2-> onAddCertification()
                 UpdateSkillViewModel.ADD_CERTIFICATION_SUCCESS_2 -> onAddCertificationSuccess()
                 UpdateSkillViewModel.ADD_LANGUAGE_2 -> onAddLanguage()
                 UpdateSkillViewModel.ADD_LANGUAGE_SUCCESS_2 -> onAddLanguageSuccess()
                 UpdateSkillViewModel.DELETE_EXPERIENCE_SUCCESS_2 -> onDeleteExperienceSuccess()
                 UpdateSkillViewModel.DELETE_EDUCATION_SUCCESS_2 -> onDeleteEducationSuccess()
                 UpdateSkillViewModel.DELETE_CERTIFICATION_SUCCESS_2 -> onDeleteCertificationSuccess()
                 UpdateSkillViewModel.DELETE_LANGUAGE_SUCCESS_2 -> onDeleteLanguageSuccess()
                 UpdateSkillViewModel.CLICK_UPDATE -> upDateSuccess()
             }
         }
        mModel.getExperience(context)
        initRecyclerViewExperience()

        mModel.getEducation(context)
        initRecyclerViewEducation()

        mModel.getCertification(context)
        initRecyclerViewCertification()

        mModel.getLanguage(context)
        initRecyclerViewLanguage()
    }

    override fun getBindingData() = mBinding as FragmentUpdateSkillBinding

    override fun getLayoutMain(): Int {
        return R.layout.fragment_update_skill
    }

    override fun getViewModel(): Class<UpdateSkillViewModel> {
        return UpdateSkillViewModel::class.java
    }
    override fun error(id: String, error: Throwable) {
        showMessage(error.message!!)
    }
    companion object {
        val TAG = UpdateSkillFragment::class.java.name
    }

    private fun upDateSuccess(){
        Toast.makeText(context,"Cập nhập thành công",Toast.LENGTH_SHORT).show()
        requireActivity().supportFragmentManager.popBackStack()
    }

    override fun count(type: Int): Int {
        when(type){
            1 -> {
                return mModel.getListExperience().size
            }
            2 -> {
                return mModel.getListEducation().size
            }
            3 -> {
                return mModel.getListCertification().size
            }
            4 -> {
                return mModel.getListLanguage().size
            }
            else -> 0
        }
        return 0
    }

    override fun getSkill(position: Int, type: Int): Skill {
        when(type){
            1 -> {
                return mModel.getListExperience()[position]
            }
            2 -> {
                return mModel.getListEducation()[position]
            }
            3 -> {
                return mModel.getListCertification()[position]
            }
            4 -> {
                return mModel.getListLanguage()[position]
            }
            else -> 0
        }
        return mModel.getListExperience()[position]
    }

    override fun onClickDeleteSkill(position: Int, type: Int) {
        when(type){
            1 -> {
                val skill = mModel.getListExperience()[position]
                mModel.deleteItem(skill,context,1)
            }
            2 -> {
                val skill = mModel.getListEducation()[position]
                mModel.deleteItem(skill,context,2)
            }
            3 -> {
                val skill = mModel.getListCertification()[position]
                mModel.deleteItem(skill,context,3)
            }
            4 -> {
                val skill = mModel.getListLanguage()[position]
                mModel.deleteItem(skill,context,4)
            }
            else -> 0
        }
    }
    // add Experience
    private fun onAddExperience(){
        edExperience = getBindingData().edExperience.text.toString().trim()
        if(edExperience!!.isEmpty()){
            Toast.makeText(context,"Nhập dữ liệu", Toast.LENGTH_SHORT).show()
        }else{
            mModel.addExperience(edExperience,context)
        }
    }
    @SuppressLint("NotifyDataSetChanged")
    private fun onAddExperienceSuccess(){
        Toast.makeText(context,"Thêm kỹ năng thành công",Toast.LENGTH_SHORT).show()
        getBindingData().edExperience.setText("")
        mModel.getExperience(context)
        Log.e(mModel.getListExperience().size.toString(),"Test")
        getBindingData().rcvExperience.adapter!!.notifyDataSetChanged()
    }
    private fun initRecyclerViewExperience(){
        val experienceAdapter = SkillAdapter(this,1,1)
        getBindingData().rcvExperience.layoutManager = LinearLayoutManager(context)
        getBindingData().rcvExperience.adapter = experienceAdapter
    }

    // add Education
    private fun onAddEducation(){
        edEducation = getBindingData().edEducation.text.toString().trim()
        if(edEducation!!.isEmpty()){
            Toast.makeText(context,"Nhập dữ liệu",Toast.LENGTH_SHORT).show()
        }else{
            mModel.addEducation(edEducation,context)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun onAddEducationSuccess(){
        Toast.makeText(context,"Thêm học vấn thành công",Toast.LENGTH_SHORT).show()
        getBindingData().edEducation.setText("")
        mModel.getEducation(context)
        Log.e(mModel.getListEducation().size.toString(),"Test")
        getBindingData().rcvEducation.adapter!!.notifyDataSetChanged()
    }
    private fun initRecyclerViewEducation(){
        val educationAdapter = SkillAdapter(this,2,1)
        getBindingData().rcvEducation.layoutManager = LinearLayoutManager(context)
        getBindingData().rcvEducation.adapter = educationAdapter
    }

    // add Certification
    private fun onAddCertification(){
        edCertification = getBindingData().edCertification.text.toString().trim()
        if(edCertification!!.isEmpty()){
            Toast.makeText(context,"Nhập dữ liệu",Toast.LENGTH_SHORT).show()
        }else{
            mModel.addCertification(edCertification,context)
        }
    }
    @SuppressLint("NotifyDataSetChanged")
    fun onAddCertificationSuccess(){
        Toast.makeText(context,"Thêm chứng chỉ thành công",Toast.LENGTH_SHORT).show()
        getBindingData().edCertification.setText("")
        mModel.getCertification(context)
        Log.e(mModel.getListCertification().size.toString(),"Test")
        getBindingData().revCertification.adapter!!.notifyDataSetChanged()
    }
    private fun initRecyclerViewCertification(){
        val certificationAdapter = SkillAdapter(this,3,1)
        getBindingData().revCertification.layoutManager = LinearLayoutManager(context)
        getBindingData().revCertification.adapter = certificationAdapter
    }

    // add Language
    private fun onAddLanguage(){
        edLanguage = getBindingData().edLanguage.text.toString().trim()
        if(edLanguage!!.isEmpty()){
            Toast.makeText(context,"Nhập dữ liệu",Toast.LENGTH_SHORT).show()
        }else{
            mModel.addLanguage(edLanguage,context)
        }
    }
    @SuppressLint("NotifyDataSetChanged")
    fun onAddLanguageSuccess(){
        Toast.makeText(context,"Thêm ngôn ngữ thành công",Toast.LENGTH_SHORT).show()
        getBindingData().edLanguage.setText("")
        mModel.getLanguage(context)
        Log.e(mModel.getListLanguage().size.toString(),"Test")
        getBindingData().rcvLanguage.adapter!!.notifyDataSetChanged()
    }
    private fun initRecyclerViewLanguage(){
        val languageAdapter = SkillAdapter(this,4,1)
        getBindingData().rcvLanguage.layoutManager = LinearLayoutManager(context)
        getBindingData().rcvLanguage.adapter = languageAdapter
    }

    @SuppressLint("NotifyDataSetChanged")
    fun onDeleteExperienceSuccess(){
        Toast.makeText(context,"Xóa thành công",Toast.LENGTH_SHORT).show()
        mModel.getExperience(context)
        getBindingData().rcvExperience.adapter!!.notifyDataSetChanged()
    }
    @SuppressLint("NotifyDataSetChanged")
    fun onDeleteEducationSuccess(){
        Toast.makeText(context,"Xóa thành công",Toast.LENGTH_SHORT).show()
        mModel.getEducation(context)
        getBindingData().rcvEducation.adapter!!.notifyDataSetChanged()
    }
    @SuppressLint("NotifyDataSetChanged")
    fun onDeleteCertificationSuccess(){
        Toast.makeText(context,"Xóa thành công",Toast.LENGTH_SHORT).show()
        mModel.getCertification(context)
        getBindingData().revCertification.adapter!!.notifyDataSetChanged()
    }
    @SuppressLint("NotifyDataSetChanged")
    fun onDeleteLanguageSuccess(){
        Toast.makeText(context,"Xóa thành công",Toast.LENGTH_SHORT).show()
        mModel.getLanguage(context)
        getBindingData().rcvLanguage.adapter!!.notifyDataSetChanged()
    }
}