package com.cuongpq.basemvvm.ui.profile.update_skill

import com.cuongpq.basemvvm.R
import com.cuongpq.basemvvm.databinding.FragmentUpdateSkillBinding
import com.cuongpq.basemvvm.ui.base.fragment.BaseMvvmFragment
import com.cuongpq.basemvvm.ui.employer.add_job.AddJobFragment

class UpdateSkillFragment : BaseMvvmFragment<UpdateSkillCallBack,UpdateSkillViewModel>(),UpdateSkillCallBack {

    override fun setEvents() {

    }

    override fun initComponents() {

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
}