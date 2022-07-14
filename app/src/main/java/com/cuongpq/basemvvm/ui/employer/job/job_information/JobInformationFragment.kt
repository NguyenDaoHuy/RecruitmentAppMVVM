package com.cuongpq.basemvvm.ui.employer.job.job_information


import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.cuongpq.basemvvm.R
import com.cuongpq.basemvvm.data.model.User
import com.cuongpq.basemvvm.data.model.job.Job
import com.cuongpq.basemvvm.data.model.job.skill.Skill
import com.cuongpq.basemvvm.databinding.FragmentJobInformationBinding
import com.cuongpq.basemvvm.ui.base.fragment.BaseMvvmFragment
import com.cuongpq.basemvvm.ui.base.viewmodel.BaseViewModel
import com.cuongpq.basemvvm.ui.candidate.answer.AnswerFragment
import com.cuongpq.basemvvm.ui.employer.create_job.create_request.SkillAdapter

class JobInformationFragment(val user: User) : BaseMvvmFragment<JobInformationCallBack, JobInformationViewModel>(),
    JobInformationCallBack,SkillAdapter.ISkillAdapter {

    private lateinit var job : Job
    private lateinit var listSkill : ArrayList<Skill>
    private lateinit var listExperience : ArrayList<Skill>
    private lateinit var listEducation : ArrayList<Skill>
    private lateinit var listCertification : ArrayList<Skill>
    private lateinit var listLanguage : ArrayList<Skill>

    override fun initComponents() {
        getBindingData().jobInforViewModel = mModel
        job = arguments!!.getSerializable("job") as Job
        mModel.uiEventLiveData.observe(this){
            when(it){
                BaseViewModel.FINISH_ACTIVITY -> finishActivity()
                JobInformationViewModel.ON_CLICK_APPLY -> onClickApply()
            }
        }
        getListSkill()
        setView()
        initRecyclerViewExperience()
        initRecyclerViewEducation()
        initRecyclerViewCertification()
        initRecyclerViewLanguage()
    }

    fun setView(){
        getBindingData().jobName.setText(job.jobName)
        getBindingData().jobDescription.setText(job.description)
        getBindingData().companyName.setText(job.company!!.companyName)
        Glide.with(context!!).load(job.company!!.companyAvatar)
            .placeholder(R.drawable.logo_bacha)
            .error(R.drawable.logo_bacha)
            .into(getBindingData().logoCompany)
        getBindingData().jobRight.setText(job.right)
        getBindingData().tvEmployName.setText("Anh / chị : " + job.employer!!.name)
        getBindingData().tvEmployEmail.setText("Email : " + job.employer!!.email)
        getBindingData().tvEmployPhone.setText("Số điện thoại : " + job.employer!!.phone)
        if(user.permission == 0){
            getBindingData().btnApply.visibility = View.GONE
        }else if(user.permission == 1){
            getBindingData().btnApply.visibility = View.VISIBLE
        }
    }

    fun getListSkill(){
        listSkill = ArrayList()
        listExperience = ArrayList()
        listEducation = ArrayList()
        listCertification = ArrayList()
        listLanguage = ArrayList()
        listSkill = job.listSkill!!
        for (skill in listSkill){
            if(skill.type == 1){
                listExperience.add(skill)
            }else if(skill.type == 2){
                listEducation.add(skill)
            }else if(skill.type == 3){
                listCertification.add(skill)
            }else if(skill.type == 4){
                listLanguage.add(skill)
            }
        }
    }

    fun onClickApply(){
        val fragmentTransaction = requireFragmentManager().beginTransaction()
        fragmentTransaction.replace(R.id.fragmentMain2, AnswerFragment(job))
        fragmentTransaction.addToBackStack(AnswerFragment.TAG)
        fragmentTransaction.commit()
    }

    override fun getLayoutMain(): Int {
        return R.layout.fragment_job_information
    }

    override fun setEvents() {

    }

    override fun getBindingData() = mBinding as FragmentJobInformationBinding

    override fun getViewModel(): Class<JobInformationViewModel> {
        return JobInformationViewModel::class.java
    }
    override fun error(id: String, error: Throwable) {
        showMessage(error.message!!)
    }
    companion object {
        val TAG: String = JobInformationFragment::class.java.name
    }

    override fun count(type: Int): Int {
        when(type){
            1 -> {
                if (listExperience == null){
                    return 0
                }
                return listExperience.size
            }
            2 -> {
                if (listEducation == null){
                    return 0
                }
                return listEducation.size
            }
            3 -> {
                if (listCertification == null){
                    return 0
                }
                return listCertification.size
            }
            4 -> {
                if (listLanguage == null){
                    return 0
                }
                return listLanguage.size
            }
            else -> 0
        }
        return 0
    }

    override fun getSkill(position: Int, type: Int): Skill {
        when(type){
            1 -> {
                return listExperience.get(position)
            }
            2 -> {
                return listEducation.get(position)
            }
            3 -> {
                return listCertification.get(position)
            }
            4 -> {
                return listLanguage.get(position)
            }
            else -> 0
        }
        return listExperience.get(position)
    }

    override fun onClickDeleteSkill(position: Int, type: Int) {

    }
    fun initRecyclerViewExperience(){
        val experienceAdapter = SkillAdapter(this,1,2)
        getBindingData().rcvInfoExperience.layoutManager = LinearLayoutManager(context)
        getBindingData().rcvInfoExperience.setAdapter(experienceAdapter)
    }
    fun initRecyclerViewEducation(){
        val educationAdapter = SkillAdapter(this,2,2)
        getBindingData().rcvInfoEducation.layoutManager = LinearLayoutManager(context)
        getBindingData().rcvInfoEducation.setAdapter(educationAdapter)
    }
    fun initRecyclerViewCertification(){
        val certificationAdapter = SkillAdapter(this,3,2)
        getBindingData().rcvInfoCerticifation.layoutManager = LinearLayoutManager(context)
        getBindingData().rcvInfoCerticifation.setAdapter(certificationAdapter)
    }
    fun initRecyclerViewLanguage(){
        val languageAdapter = SkillAdapter(this,4,2)
        getBindingData().rcvInfoLanguage.layoutManager = LinearLayoutManager(context)
        getBindingData().rcvInfoLanguage.setAdapter(languageAdapter)
    }
}