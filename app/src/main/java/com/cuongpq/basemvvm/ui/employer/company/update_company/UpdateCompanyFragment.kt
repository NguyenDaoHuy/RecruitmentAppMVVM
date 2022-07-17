package com.cuongpq.basemvvm.ui.employer.company.update_company

import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import com.cuongpq.basemvvm.R
import com.cuongpq.basemvvm.data.model.Company
import com.cuongpq.basemvvm.data.model.User
import com.cuongpq.basemvvm.databinding.FragmentUpdateCompanyBinding
import com.cuongpq.basemvvm.ui.base.fragment.BaseMvvmFragment
import com.cuongpq.basemvvm.ui.base.viewmodel.BaseViewModel
import com.cuongpq.basemvvm.ui.employer.company.create_company.CreateCompanyFragment

class UpdateCompanyFragment(var user : User?) : BaseMvvmFragment<UpdateCompanyCallBack, UpdateCompanyViewModel>(),
    UpdateCompanyCallBack {

//    private var companyArrayList : ArrayList<Company>? = null
    private var companyAdapter : CompanyAdapter? = null
    private var company : Company? = null
    override fun initComponents() {
        getBindingData().updateCompanyViewModel = mModel
        mModel.uiEventLiveData.observe(this){
            when(it){
                BaseViewModel.FINISH_ACTIVITY -> finishActivity()
                UpdateCompanyViewModel.COMPANY_COMFIRM -> onComfirm()
                UpdateCompanyViewModel.SET_COMPANY -> setCompanySuccess()
                UpdateCompanyViewModel.ON_CLICK_CREATE_COMPANY -> onClickCreateCompany()
            }
        }
        getData()
    }

    fun getData(){
        mModel.getDataCompanyFromDatabase(requireActivity())
        companyAdapter = CompanyAdapter(requireContext(),mModel.getCompanyArrayList())
        getBindingData().spinerCompany.adapter = companyAdapter
        getBindingData().spinerCompany.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, p3: Long) {
                company = parent!!.getItemAtPosition(position) as Company?
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }
    }
    private fun onComfirm(){
         mModel.setCompany(company!!,requireActivity(), user!!)
    }
    private fun setCompanySuccess(){
         requireActivity().supportFragmentManager.popBackStack()
         Toast.makeText(context,"Cập nhập thành công",Toast.LENGTH_SHORT).show()
    }
    private fun onClickCreateCompany(){
        val fragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentMain,CreateCompanyFragment())
        fragmentTransaction.addToBackStack(CreateCompanyFragment.TAG)
        fragmentTransaction.commit()
    }

    override fun getLayoutMain(): Int {
        return R.layout.fragment_update_company
    }
    override fun setEvents() {

    }

    override fun getBindingData() = mBinding as FragmentUpdateCompanyBinding

    override fun getViewModel(): Class<UpdateCompanyViewModel> {
        return UpdateCompanyViewModel::class.java
    }
    override fun error(id: String, error: Throwable) {
        showMessage(error.message!!)
    }
    companion object {
        val TAG = UpdateCompanyFragment::class.java.name
    }

}