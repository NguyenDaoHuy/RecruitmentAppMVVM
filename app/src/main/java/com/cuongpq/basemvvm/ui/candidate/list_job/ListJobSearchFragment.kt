package com.cuongpq.basemvvm.ui.candidate.list_job

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.compose.ui.text.toLowerCase
import androidx.recyclerview.widget.LinearLayoutManager
import com.cuongpq.basemvvm.R
import com.cuongpq.basemvvm.data.model.User
import com.cuongpq.basemvvm.data.model.job.Job
import com.cuongpq.basemvvm.databinding.FragmentListJobSearchBinding
import com.cuongpq.basemvvm.ui.base.fragment.BaseMvvmFragment
import com.cuongpq.basemvvm.ui.employer.job.job_information.JobInformationFragment
import com.cuongpq.basemvvm.ui.employer.job.my_job.JobAdapter

class ListJobSearchFragment(var user: User?) : BaseMvvmFragment<ListJobCallBack,ListJobSearchViewModel>(),ListJobCallBack,JobAdapter.IJob {

    private var filterList : ArrayList<Job>? = null
    private var jobList : ArrayList<Job>? = null

    override fun initComponents() {
        getBindingData().listJobSearchViewModel = mModel
        getBindingData().searchView.clearFocus()
        mModel.getJobSearchDataFromDB(requireContext())
        initRecyclerViewoJob()
        onSearch()
    }

    fun onSearch(){
        getBindingData().searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                filterList(newText)
                return false
            }
        })
    }

    override fun getLayoutMain(): Int {
        return R.layout.fragment_list_job_search
    }

    override fun setEvents() {

    }

    override fun getBindingData() = mBinding as FragmentListJobSearchBinding

    override fun getViewModel(): Class<ListJobSearchViewModel> {
        return ListJobSearchViewModel::class.java
    }

    override fun error(id: String, error: Throwable) {
        showMessage(error.message!!)
    }
    companion object{
        val TAG = ListJobSearchFragment::class.java.name
    }

    fun initRecyclerViewoJob(){
        val jobAdapter = JobAdapter(this, requireContext(),user!!)
        getBindingData().rcvListJob.layoutManager = LinearLayoutManager(context)
        getBindingData().rcvListJob.setAdapter(jobAdapter)
    }

    override fun count(): Int {
        jobList = mModel.getListJobSearch()
        return jobList!!.size
    }

    override fun getJob(position: Int): Job {
        jobList = mModel.getListJobSearch()
        return jobList!!.get(position)
    }

    override fun onClickJob(position: Int) {
        val job = mModel.getListJobSearch().get(position)
        val jobInformationFragment = JobInformationFragment(user!!)
        val bundle = Bundle()
        bundle.putSerializable("job",job)
        jobInformationFragment.arguments = bundle
        val fragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentMain2, jobInformationFragment)
        fragmentTransaction.addToBackStack(JobInformationFragment.TAG)
        fragmentTransaction.commit()
    }

    override fun onClickPower(position: Int) {

    }

    override fun onClickDelete(position: Int) {

    }
    private fun filterList(text : String){
        filterList = ArrayList()
        for (job in mModel.getListJobSearch()){
            if(job.jobName.toLowerCase().contains(text.toLowerCase())){
                filterList!!.add(job)
            }
        }
        if(filterList!!.isEmpty()){
            Toast.makeText(context,"Không có dữ liệu",Toast.LENGTH_SHORT).show()
        }else{
             jobList = filterList
             getBindingData().rcvListJob.adapter!!.notifyDataSetChanged()
        }
    }

}