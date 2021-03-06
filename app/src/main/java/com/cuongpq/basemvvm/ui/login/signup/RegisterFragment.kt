package com.cuongpq.basemvvm.ui.login.signup

import com.cuongpq.basemvvm.R
import com.cuongpq.basemvvm.databinding.FragmentRegisterBinding
import com.cuongpq.basemvvm.ui.CustomProgressDialog
import com.cuongpq.basemvvm.ui.base.fragment.BaseMvvmFragment
import com.cuongpq.basemvvm.ui.base.viewmodel.BaseViewModel

class RegisterFragment : BaseMvvmFragment<RegisterCallBack, RegisterViewModel>(), RegisterCallBack {

    private var email : String = ""
    private var password : String =""
    private var passwordCF : String =""
    private var userName : String =""
    private var age : Int = 0
    private var phone : String =""
    private var permission : Int = 0
    private lateinit var dialog : CustomProgressDialog

    override fun setEvents() {

    }

    override fun initComponents() {
        getBindingData().registerViewModel = mModel
        dialog = CustomProgressDialog(context)
        mModel.uiEventLiveData.observe(this){
            when(it) {
                BaseViewModel.FINISH_ACTIVITY -> finishActivity()
                RegisterViewModel.BACK_LOGIN ->onBackToLoginFragment()
                RegisterViewModel.REGISTER_SUCCESS -> onSignupSuccess()
                RegisterViewModel.REGISTER_ERROR -> onSignupError()
                RegisterViewModel.GET_DATA_FROM_UI_AND_REGISTER -> onClickRegister()
            }
        }
    }

    override fun getLayoutMain(): Int {
        return R.layout.fragment_register
    }

    private fun onClickRegister(){
           dialog.show()
           email = getBindingData().edAccount.text.toString().trim()
           password = getBindingData().edPassword .text.toString().trim()
           passwordCF = getBindingData().cfPassword .text.toString().trim()
           userName = getBindingData().edUserName .text.toString().trim()
           val strAge = getBindingData().edAge .text.toString().trim()
           phone =  getBindingData().edPhone .text.toString().trim()
           if(getBindingData().rdEmployer.isChecked){
               permission = 0
           }else if(getBindingData().rdCandidate.isChecked){
               permission = 1
           }
           if(email.isEmpty() ||
               password.isEmpty() ||
               passwordCF.isEmpty() ||
               userName.isEmpty() ||
               strAge.isEmpty() ||
               phone.isEmpty()){
               dialog.dismiss()
               getBindingData().tvThongBaoDangKi.text = "Vui l??ng nh???p ????? th??ng tin"
               return
           }else if(password.length < 6){
               dialog.dismiss()
               getBindingData().tvThongBaoDangKi.text = "M???t kh???u ph???i h??n 6 k?? t???"
               return
           }else if(password != passwordCF){
               dialog.dismiss()
               getBindingData().tvThongBaoDangKi.text = "M???t kh???u kh??ng kh???p"
               return
           }else{
               age = strAge.toInt()
               mModel.email = email
               mModel.password = password
               mModel.userName = userName
               mModel.age = age
               mModel.phone = phone
               mModel.permission = permission
               mModel.onRegister(activity!!)
           }

    }
    private fun onSignupSuccess(){
        dialog.dismiss()
        requireActivity().supportFragmentManager.popBackStack()
        showMessage("????ng k?? th??nh c??ng !")
    }
    private fun onSignupError(){
        dialog.dismiss()
        getBindingData().tvThongBaoDangKi.text = "Email ???? t???n t???i !"
        showMessage("????ng k?? kh??ng th??nh c??ng !")
    }

    private fun onBackToLoginFragment(){
        requireActivity().supportFragmentManager.popBackStack()
    }


    override fun getBindingData() = mBinding as FragmentRegisterBinding

    override fun getViewModel(): Class<RegisterViewModel> {
        return RegisterViewModel::class.java
    }

    override fun error(id: String, error: Throwable) {
        showMessage(error.message!!)
    }
    companion object {
        val TAG = RegisterFragment::class.java.name
    }

}