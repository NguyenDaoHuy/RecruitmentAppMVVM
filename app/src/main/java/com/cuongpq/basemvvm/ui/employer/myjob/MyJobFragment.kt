package com.cuongpq.basemvvm.ui.employer.myjob

import com.cuongpq.basemvvm.R
import com.cuongpq.basemvvm.databinding.FragmentMyJobBinding
import com.cuongpq.basemvvm.ui.base.fragment.BaseMvvmFragment
import com.cuongpq.basemvvm.ui.base.viewmodel.BaseViewModel
import com.cuongpq.basemvvm.ui.employer.add_job.AddJobFragment

class MyJobFragment : BaseMvvmFragment<MyJobCallBack, MyJobViewModel>() , MyJobCallBack{

    override fun setEvents() {

    }

    override fun initComponents() {
         getBindingData().myJobViewModel = mModel
         mModel.uiEventLiveData.observe(this){
             when(it){
                 BaseViewModel.FINISH_ACTIVITY -> finishActivity()
                 MyJobViewModel.GO_ADD_JOB -> goToAddJob()

             }
         }
    }
    fun goToAddJob(){
        val fragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentMain, AddJobFragment())
        fragmentTransaction.addToBackStack(AddJobFragment.TAG)
        fragmentTransaction.commit()
    }

    override fun getBindingData() = mBinding as FragmentMyJobBinding

    override fun getViewModel(): Class<MyJobViewModel> {
        return MyJobViewModel::class.java
    }
    override fun error(id: String, error: Throwable) {
        showMessage(error.message!!)
    }

    override fun getLayoutMain(): Int {
        return R.layout.fragment_my_job
    }

}