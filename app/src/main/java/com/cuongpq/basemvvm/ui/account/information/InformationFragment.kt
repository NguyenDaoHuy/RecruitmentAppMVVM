package com.cuongpq.basemvvm.ui.account.information

import android.annotation.SuppressLint
import androidx.recyclerview.widget.LinearLayoutManager
import com.cuongpq.basemvvm.R
import com.cuongpq.basemvvm.data.model.User
import com.cuongpq.basemvvm.data.model.job.skill.Skill
import com.cuongpq.basemvvm.databinding.FragmentInformationBinding
import com.cuongpq.basemvvm.ui.base.fragment.BaseMvvmFragment
import com.cuongpq.basemvvm.ui.base.viewmodel.BaseViewModel
import com.cuongpq.basemvvm.ui.employer.create_job.create_request.SkillAdapter


class InformationFragment(private val user : User) : BaseMvvmFragment<InformationCallBack,InformationViewModel>(),InformationCallBack,SkillAdapter.ISkillAdapter {



    override fun initComponents() {
        getBindingData().informationViewModel = mModel
        mModel.uiEventLiveData.observe(this){
            when(it){
                BaseViewModel.FINISH_ACTIVITY -> finishActivity()
            }
        }
        getViewInformation()
    }

    @SuppressLint("SetTextI18n")
    private fun getViewInformation(){
        getBindingData().tvUserName.text = user.name
        getBindingData().tvUserEmail.text = user.email
        getBindingData().userAge.text = ""+user.age
        getBindingData().userPhone.text = user.phone
        mModel.getSkillUserExperience(user.idAccount,requireContext())
        mModel.getSkillUserEducation(user.idAccount,requireContext())
        mModel.getSkillUserCertification(user.idAccount,requireContext())
        mModel.getSkillUserLanguage(user.idAccount,requireContext())
        initRecyclerViewExperience()
        initRecyclerViewEducation()
        initRecyclerViewCertification()
        initRecyclerViewLanguage()
    }

    override fun getLayoutMain(): Int {
        return R.layout.fragment_information
    }

    override fun setEvents() {

    }

    override fun getBindingData() = mBinding as FragmentInformationBinding

    override fun getViewModel(): Class<InformationViewModel> {
        return InformationViewModel::class.java
    }

    override fun error(id: String, error: Throwable) {
        showMessage(error.message!!)
    }

    override fun count(type: Int): Int {
        when(type){
            1 -> {
                return mModel.getListSkillUserExperience().size
            }
            2 -> {
                return mModel.getListSkillUserEducation().size
            }
            3 -> {
                return mModel.getListSkillUserCertification().size
            }
            4 -> {
                return mModel.getListSkillUserLanguage().size
            }
            else -> 0
        }
        return 0
    }

    override fun getSkill(position: Int, type: Int): Skill {
        when(type){
            1 -> {
                return mModel.getListSkillUserExperience()[position]
            }
            2 -> {
                return mModel.getListSkillUserEducation()[position]
            }
            3 -> {
                return mModel.getListSkillUserCertification()[position]
            }
            4 -> {
                return mModel.getListSkillUserLanguage()[position]
            }
            else -> 0
        }
        return mModel.getListSkillUserExperience()[position]
    }

    override fun onClickDeleteSkill(position: Int, type: Int) {

    }

    private fun initRecyclerViewExperience(){
        val experienceAdapter = SkillAdapter(this,1,2)
        getBindingData().rcvInfoExperience.layoutManager = LinearLayoutManager(context)
        getBindingData().rcvInfoExperience.adapter = experienceAdapter
    }
    private fun initRecyclerViewEducation(){
        val educationAdapter = SkillAdapter(this,2,2)
        getBindingData().rcvInfoEducation.layoutManager = LinearLayoutManager(context)
        getBindingData().rcvInfoEducation.adapter = educationAdapter
    }
    private fun initRecyclerViewCertification(){
        val certificationAdapter = SkillAdapter(this,3,2)
        getBindingData().rcvInfoCerticifation.layoutManager = LinearLayoutManager(context)
        getBindingData().rcvInfoCerticifation.adapter = certificationAdapter
    }
    private fun initRecyclerViewLanguage(){
        val languageAdapter = SkillAdapter(this,4,2)
        getBindingData().rcvInfoLanguage.layoutManager = LinearLayoutManager(context)
        getBindingData().rcvInfoLanguage.adapter = languageAdapter
    }

    companion object{
        val TAG = InformationFragment::class.java.name
    }
}