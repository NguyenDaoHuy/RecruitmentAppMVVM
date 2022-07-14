package com.cuongpq.basemvvm.ui.employer.create_job.create_request

import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.cuongpq.basemvvm.R
import com.cuongpq.basemvvm.data.model.job.skill.Skill
import com.cuongpq.basemvvm.databinding.FragmentCreateRequestBinding
import com.cuongpq.basemvvm.ui.base.fragment.BaseMvvmFragment
import com.cuongpq.basemvvm.ui.base.viewmodel.BaseViewModel
import com.cuongpq.basemvvm.ui.employer.create_job.create_question.CreateQuestionFragment


class CreateRequestFragment(var idJob : String?) :
    BaseMvvmFragment<CreateRequestCallBack,CreateRequestViewModel>(),CreateRequestCallBack ,
    SkillAdapter.ISkillAdapter{

    private var edExperience : String? = null
    private var edEducation : String? = null
    private var edCertification : String? = null
    private var edLanguage : String? = null

    override fun initComponents() {
            getBindingData().createRequestViewModel = mModel
            mModel.idJob = idJob!!
            mModel.uiEventLiveData.observe(this){
                when(it){
                    BaseViewModel.FINISH_ACTIVITY -> finishActivity()
                    CreateRequestViewModel.ADD_EXPERIENCE -> onAddExperience()
                    CreateRequestViewModel.ADD_EXPERIENCE_SUCCESS -> onAddExperienceSuccess()
                    CreateRequestViewModel.ADD_EDUCATION -> onAddEducation()
                    CreateRequestViewModel.ADD_EDUCATION_SUCCESS -> onAddEducationSuccess()
                    CreateRequestViewModel.ADD_CERTIFICATION -> onAddCertification()
                    CreateRequestViewModel.ADD_CERTIFICATION_SUCCESS -> onAddCertificationSuccess()
                    CreateRequestViewModel.ADD_LANGUAGE -> onAddLanguage()
                    CreateRequestViewModel.ADD_LANGUAGE_SUCCESS -> onAddLanguageSuccess()
                    CreateRequestViewModel.CLICK_NEXT -> onClickNextToQuestion()
                    CreateRequestViewModel.DELETE_EXPERIENCE_SUCCESS -> onDeleteExperienceSuccess()
                    CreateRequestViewModel.DELETE_EDUCATION_SUCCESS -> onDeleteEducationSuccess()
                    CreateRequestViewModel.DELETE_CERTIFICATION_SUCCESS -> onDeleteCertificationSuccess()
                    CreateRequestViewModel.DELETE_LANGUAGE_SUCCESS -> onDeleteLanguageSuccess()
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

    override fun getLayoutMain(): Int {
        return R.layout.fragment_create_request
    }

    override fun setEvents() {

    }

    override fun getBindingData() = mBinding as FragmentCreateRequestBinding

    override fun getViewModel(): Class<CreateRequestViewModel> {
        return CreateRequestViewModel::class.java
    }
    override fun error(id: String, error: Throwable) {
        showMessage(error.message!!)
    }
    companion object{
        val TAG = CreateRequestFragment::class.java.name
    }

    fun onClickNextToQuestion(){
        val fragmentTransaction = requireFragmentManager().beginTransaction()
        fragmentTransaction.replace(R.id.fragmentMain,CreateQuestionFragment(idJob))
        fragmentTransaction.addToBackStack(CreateQuestionFragment.TAG)
        fragmentTransaction.commit()
    }
    // add Experience
    fun onAddExperience(){
        edExperience = getBindingData().edExperience.text.toString().trim()
        if(edExperience!!.isEmpty()){
            Toast.makeText(context,"Nhập dữ liệu",Toast.LENGTH_SHORT).show()
        }else{
            mModel.addExperience(edExperience,context)
        }
    }
    fun onAddExperienceSuccess(){
        Toast.makeText(context,"Thêm kỹ năng thành công",Toast.LENGTH_SHORT).show()
        getBindingData().edExperience.setText("")
        mModel.getExperience(context)
        Log.e(mModel.getListExperience().size.toString(),"Test")
        getBindingData().rcvExperience.adapter!!.notifyDataSetChanged()
    }
    fun initRecyclerViewExperience(){
        val experienceAdapter = SkillAdapter(this,1,1)
        getBindingData().rcvExperience.layoutManager = LinearLayoutManager(context)
        getBindingData().rcvExperience.setAdapter(experienceAdapter)
    }

    // add Education
    fun onAddEducation(){
        edEducation = getBindingData().edEducation.text.toString().trim()
        if(edEducation!!.isEmpty()){
            Toast.makeText(context,"Nhập dữ liệu",Toast.LENGTH_SHORT).show()
        }else{
            mModel.addEducation(edEducation,context)
        }
    }

    fun onAddEducationSuccess(){
        Toast.makeText(context,"Thêm học vấn thành công",Toast.LENGTH_SHORT).show()
        getBindingData().edEducation.setText("")
        mModel.getEducation(context)
        Log.e(mModel.getListEducation().size.toString(),"Test")
        getBindingData().rcvEducation.adapter!!.notifyDataSetChanged()
    }
    fun initRecyclerViewEducation(){
        val educationAdapter = SkillAdapter(this,2,1)
        getBindingData().rcvEducation.layoutManager = LinearLayoutManager(context)
        getBindingData().rcvEducation.setAdapter(educationAdapter)
    }

    // add Certification
    fun onAddCertification(){
        edCertification = getBindingData().edCertification.text.toString().trim()
        if(edCertification!!.isEmpty()){
            Toast.makeText(context,"Nhập dữ liệu",Toast.LENGTH_SHORT).show()
        }else{
            mModel.addCertification(edCertification,context)
        }
    }
    fun onAddCertificationSuccess(){
        Toast.makeText(context,"Thêm chứng chỉ thành công",Toast.LENGTH_SHORT).show()
        getBindingData().edCertification.setText("")
        mModel.getCertification(context)
        Log.e(mModel.getListCertification().size.toString(),"Test")
        getBindingData().revCertification.adapter!!.notifyDataSetChanged()
    }
    fun initRecyclerViewCertification(){
        val certificationAdapter = SkillAdapter(this,3,1)
        getBindingData().revCertification.layoutManager = LinearLayoutManager(context)
        getBindingData().revCertification.setAdapter(certificationAdapter)
    }

    // add Language
    fun onAddLanguage(){
        edLanguage = getBindingData().edLanguage.text.toString().trim()
        if(edLanguage!!.isEmpty()){
            Toast.makeText(context,"Nhập dữ liệu",Toast.LENGTH_SHORT).show()
        }else{
            mModel.addLanguage(edLanguage,context)
        }
    }
    fun onAddLanguageSuccess(){
        Toast.makeText(context,"Thêm ngôn ngữ thành công",Toast.LENGTH_SHORT).show()
        getBindingData().edLanguage.setText("")
        mModel.getLanguage(context)
        Log.e(mModel.getListLanguage().size.toString(),"Test")
        getBindingData().rcvLanguage.adapter!!.notifyDataSetChanged()
    }
    fun initRecyclerViewLanguage(){
        val languageAdapter = SkillAdapter(this,4,1)
        getBindingData().rcvLanguage.layoutManager = LinearLayoutManager(context)
        getBindingData().rcvLanguage.setAdapter(languageAdapter)
    }

    override fun count(type : Int): Int {
        when(type){
            1 -> {
                if (mModel.getListExperience() == null){
                    return 0
                }
                return mModel.getListExperience().size
            }
            2 -> {
                if (mModel.getListEducation() == null){
                    return 0
                }
                return mModel.getListEducation().size
            }
            3 -> {
                if (mModel.getListCertification() == null){
                    return 0
                }
                return mModel.getListCertification().size
            }
            4 -> {
                if (mModel.getListLanguage() == null){
                    return 0
                }
                return mModel.getListLanguage().size
            }
            else -> 0
        }
        return 0
    }

    override fun getSkill(position: Int,type : Int): Skill {
        when(type){
             1 -> {
                 return mModel.getListExperience().get(position)
             }
             2 -> {
                 return mModel.getListEducation().get(position)
             }
             3 -> {
                 return mModel.getListCertification().get(position)
             }
             4 -> {
                 return mModel.getListLanguage().get(position)
             }
             else -> 0
        }
        return mModel.getListExperience().get(position)
    }

    override fun onClickDeleteSkill(position: Int,type : Int) {
        when(type){
            1 -> {
                var skill = mModel.getListExperience().get(position)
                mModel.deleteItem(skill,context,1)
            }
            2 -> {
                var skill = mModel.getListEducation().get(position)
                mModel.deleteItem(skill,context,2)
            }
            3 -> {
                var skill = mModel.getListCertification().get(position)
                mModel.deleteItem(skill,context,3)
            }
            4 -> {
                var skill = mModel.getListLanguage().get(position)
                mModel.deleteItem(skill,context,4)
            }
            else -> 0
        }
    }
    fun onDeleteExperienceSuccess(){
        Toast.makeText(context,"Xóa thành công",Toast.LENGTH_SHORT).show()
        mModel.getExperience(context)
        getBindingData().rcvExperience.adapter!!.notifyDataSetChanged()
    }
    fun onDeleteEducationSuccess(){
        Toast.makeText(context,"Xóa thành công",Toast.LENGTH_SHORT).show()
        mModel.getEducation(context)
        getBindingData().rcvEducation.adapter!!.notifyDataSetChanged()
    }
    fun onDeleteCertificationSuccess(){
        Toast.makeText(context,"Xóa thành công",Toast.LENGTH_SHORT).show()
        mModel.getCertification(context)
        getBindingData().revCertification.adapter!!.notifyDataSetChanged()
    }
    fun onDeleteLanguageSuccess(){
        Toast.makeText(context,"Xóa thành công",Toast.LENGTH_SHORT).show()
        mModel.getLanguage(context)
        getBindingData().rcvLanguage.adapter!!.notifyDataSetChanged()
    }
}