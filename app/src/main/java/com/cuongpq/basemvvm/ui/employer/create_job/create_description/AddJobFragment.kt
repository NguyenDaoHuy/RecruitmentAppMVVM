package com.cuongpq.basemvvm.ui.employer.create_job.create_description

import android.app.AlertDialog
import android.content.DialogInterface
import android.widget.Toast
import com.cuongpq.basemvvm.R
import com.cuongpq.basemvvm.data.model.User
import com.cuongpq.basemvvm.databinding.FragmentAddJobBinding
import com.cuongpq.basemvvm.ui.base.fragment.BaseMvvmFragment
import com.cuongpq.basemvvm.ui.base.viewmodel.BaseViewModel
import com.cuongpq.basemvvm.ui.employer.create_job.create_request.CreateRequestFragment
import com.cuongpq.basemvvm.ui.employer.company.update_company.UpdateCompanyFragment

class AddJobFragment(private var user: User?) : BaseMvvmFragment<CreateJobCallBack, AddJobViewModel>(),
    CreateJobCallBack {

    private lateinit var jobCode :String
    lateinit var jobName : String
    private lateinit var description :String
    var amount :Int = 0

    override fun setEvents() {

    }

    override fun initComponents() {
        getBindingData().addJobViewModel = mModel
        mModel.uiEventLiveData.observe(this){
            when(it){
                 BaseViewModel.FINISH_ACTIVITY -> finishActivity()
                 AddJobViewModel.ADD_JOB_SUCCESS -> addJobSuccess()
                 AddJobViewModel.ADD_JOB_ERROR -> addJobError()
                 AddJobViewModel.NEXT_TO_REQUEST -> onAddJob()
                 AddJobViewModel.ADD_JOB_EXISTS -> addJobExists()
            }
        }
    }

    override fun getBindingData() = mBinding as FragmentAddJobBinding

    override fun getViewModel(): Class<AddJobViewModel> {
        return AddJobViewModel::class.java
    }
    override fun error(id: String, error: Throwable) {
        showMessage(error.message!!)
    }

    override fun getLayoutMain(): Int {
        return R.layout.fragment_add_job
    }
    companion object {
        val TAG = AddJobFragment::class.java.name
    }

    private fun onAddJob() {
        jobCode = getBindingData().edJobCode.text.toString().trim()
        jobName = getBindingData().edJobName.text.toString().trim()
        description = getBindingData().edDescription.text.toString()
        if (jobCode.isEmpty() ||
            jobName.isEmpty() ||
            description.isEmpty()
        ) {
            Toast.makeText(context, "Nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show()
        } else {
            mModel.jobCode = jobCode
            mModel.jobName = jobName
            mModel.description = description
            mModel.addJobToDatabase(requireActivity(), user!!)
        }
    }
    private fun addJobSuccess(){
        val fragmentTransaction = fragmentManager!!.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentMain,CreateRequestFragment(jobCode,user,1))
        fragmentTransaction.addToBackStack(CreateRequestFragment.TAG)
        fragmentTransaction.commit()
    }
    private fun addJobError(){
        val alertDialog = AlertDialog.Builder(context)
            .setTitle("Thông báo")
            .setMessage("Bạn chưa cập nhập công ty ! ")
            .setPositiveButton(
                "Cập nhập ngay",
                DialogInterface.OnClickListener { dialog: DialogInterface?, which: Int ->
                    val fragmentTransaction = fragmentManager!!.beginTransaction()
                    fragmentTransaction.replace(R.id.fragmentMain, UpdateCompanyFragment(user))
                    fragmentTransaction.addToBackStack(UpdateCompanyFragment.TAG)
                    fragmentTransaction.commit()
                })
            .setNegativeButton(
                "Để sau",
                DialogInterface.OnClickListener { dialog: DialogInterface?, which: Int -> })
            .create()
        alertDialog.show()
    }
    private fun addJobExists(){
        Toast.makeText(context, "Đã tồn tại mã", Toast.LENGTH_SHORT).show()
    }
}