package com.cuongpq.basemvvm.ui.employer.job.my_job

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.cuongpq.basemvvm.R
import com.cuongpq.basemvvm.data.model.User
import com.cuongpq.basemvvm.data.model.job.Job
import com.cuongpq.basemvvm.databinding.FragmentMyJobBinding
import com.cuongpq.basemvvm.ui.base.fragment.BaseMvvmFragment
import com.cuongpq.basemvvm.ui.base.viewmodel.BaseViewModel
import com.cuongpq.basemvvm.ui.employer.create_job.create_description.AddJobFragment
import com.cuongpq.basemvvm.ui.employer.job.job_information.JobInformationFragment

class MyJobFragment(private var user: User?) : BaseMvvmFragment<MyJobCallBack, MyJobViewModel>() , MyJobCallBack, JobAdapter.IJob{
    private var searchString : String = ""
    override fun setEvents() {

    }
    override fun initComponents() {
         getBindingData().myJobViewModel = mModel
         mModel.uiEventLiveData.observe(this){
             when(it){
                 BaseViewModel.FINISH_ACTIVITY -> finishActivity()
                 MyJobViewModel.GO_ADD_JOB -> goToAddJob()
                 MyJobViewModel.SET_POWER_SUCCESS -> setStatusSuccess()
                 MyJobViewModel.DELETE_SUCCESS -> deleteJobSuccess()
             }
         }
        mModel.getJobDataFromDB(requireContext(),user!!,"")
        if(mModel.getListJob().size == 0){
             getBindingData().tvThongBaoNull.visibility = View.VISIBLE
        }
        initRecyclerViewoJob()
        onSearch()
    }
    private fun initRecyclerViewoJob(){
        val jobAdapter = JobAdapter(this, requireContext(),user!!)
        getBindingData().rcvListMyJob.layoutManager = LinearLayoutManager(context)
        getBindingData().rcvListMyJob.adapter = jobAdapter
    }
    private fun goToAddJob(){
        val fragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentMain, AddJobFragment(user))
        fragmentTransaction.addToBackStack(AddJobFragment.TAG)
        fragmentTransaction.commit()
    }
    // H??M SEARCH
    private fun onSearch(){
        getBindingData().searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String): Boolean {
                searchString = newText
                mModel.getJobDataFromDB(requireContext(),user!!,newText)
                getBindingData().rcvListMyJob.adapter!!.notifyDataSetChanged();
                return false
            }
        })
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

    override fun count(): Int {
        return mModel.getListJob().size
    }

    override fun getJob(position: Int): Job {
        return mModel.getListJob()[position]
    }

    override fun onClickJob(position: Int) {
        val job = mModel.getListJob()[position]
        val jobInformationFragment = JobInformationFragment(user!!)
        val bundle = Bundle()
        bundle.putSerializable("job",job)
        jobInformationFragment.arguments = bundle
        val fragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentMain, jobInformationFragment)
        fragmentTransaction.addToBackStack(JobInformationFragment.TAG)
        fragmentTransaction.commit()
    }

    override fun onClickPower(position: Int) {
        val job = mModel.getListJob()[position]
        mModel.setStatus(job,requireContext())
    }

    override fun onClickDelete(position: Int) {
        val job = mModel.getListJob()[position]
        val alertDialog = AlertDialog.Builder(context)
            .setTitle("X??c nh???n x??a")
            .setMessage("B???n c?? ch???c ch???n mu???n x??a ?")
            .setPositiveButton(
                "C??"
            ) { _: DialogInterface?, _: Int ->
                mModel.deleteJob(job, requireContext())
            }
            .setNegativeButton(
                "Kh??ng"
            ) { _: DialogInterface?, _: Int -> }
            .create()
        alertDialog.show()
    }

    private fun setStatusSuccess(){
        mModel.getJobDataFromDB(requireContext(),user!!,searchString!!)
        getBindingData().rcvListMyJob.adapter!!.notifyDataSetChanged()
    }
    private fun deleteJobSuccess(){
        Toast.makeText(context,"X??a th??nh c??ng",Toast.LENGTH_SHORT).show()
        mModel.getJobDataFromDB(requireContext(),user!!,"")
        getBindingData().rcvListMyJob.adapter!!.notifyDataSetChanged()
    }

    companion object{
        val TAG = MyJobFragment::class.java.name
    }
}