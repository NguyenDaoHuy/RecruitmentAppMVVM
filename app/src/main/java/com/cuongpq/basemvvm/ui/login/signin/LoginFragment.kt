package com.cuongpq.basemvvm.ui.login.signin

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import com.cuongpq.basemvvm.R
import com.cuongpq.basemvvm.data.model.User
import com.cuongpq.basemvvm.databinding.FragmentLoginBinding
import com.cuongpq.basemvvm.ui.CustomProgressDialog
import com.cuongpq.basemvvm.ui.base.fragment.BaseMvvmFragment
import com.cuongpq.basemvvm.ui.base.viewmodel.BaseViewModel
import com.cuongpq.basemvvm.ui.candidate.CandidateMainActivity
import com.cuongpq.basemvvm.ui.employer.EmployerMainActivity
import com.cuongpq.basemvvm.ui.login.signup.RegisterFragment

class LoginFragment : BaseMvvmFragment<LoginCallBack,LoginViewModel>(),LoginCallBack{

    private val saveInformation = "tk_mk"
    private lateinit var dialog : CustomProgressDialog
    private var email : String = ""
    private var password : String =""


    override fun getLayoutMain(): Int {
        return R.layout.fragment_login
    }

    override fun setEvents() {

    }

    override fun initComponents() {
         getBindingData().loginViewModel = mModel
         getBindingData().lifecycleOwner = viewLifecycleOwner
         dialog = CustomProgressDialog(context)
         mModel.uiEventLiveData.observe(this){
             when(it) {
                 BaseViewModel.FINISH_ACTIVITY -> finishActivity()
                 LoginViewModel.START_REGISTER_FAGMENT -> getFragmentRegiter()
                 LoginViewModel.LOG_IN_SUCCESS -> getLogInSuccess()
                 LoginViewModel.LOG_IN_EROR -> getLogInError()
                 LoginViewModel.GET_DATA_FROM_UI_AND_LOGIN -> logIn()
             }
         }
    }

    fun logIn(){
            dialog.show()
            email = getBindingData().editTextEmail.text.toString().trim()
            password = getBindingData().editTextPassword.text.toString().trim()
            if(email.isEmpty() || password.isEmpty()){
                dialog.dismiss()
                getBindingData().tvThongBaoDangNhap.text = "Vui lòng nhập đủ thông tin"
                return
            }else{
                mModel.email = email
                mModel.password = password
                mModel.onLogIn()
            }
    }

    override fun getBindingData() = mBinding as FragmentLoginBinding

    override fun getViewModel(): Class<LoginViewModel> {
        return LoginViewModel::class.java
    }
    override fun error(id: String, error: Throwable) {
        showMessage(error.message!!)
    }

    private fun getFragmentRegiter(){
        val fragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentLogin1,RegisterFragment())
        fragmentTransaction.addToBackStack(RegisterFragment.TAG)
        fragmentTransaction.commit()
    }
    private fun getLogInError(){
        dialog.dismiss()
        showMessage("Đăng nhập không thành công !")
        getBindingData().tvThongBaoDangNhap.text = "Sai email hoặc mật khẩu"
    }

    private fun getLogInSuccess(){
        dialog.dismiss()
        showMessage("Đăng nhập thành công")
        val user : User = mModel.getUser()!!
        if(user.permission == 0){
            val intent = Intent(context, EmployerMainActivity::class.java)
            val bundle = Bundle()
            bundle.putSerializable("user",user)
            intent.putExtras(bundle)
            startActivity(intent)
        }else if(user.permission == 1){
            val intent = Intent(context, CandidateMainActivity::class.java)
            val bundle = Bundle()
            bundle.putSerializable("user",user)
            intent.putExtras(bundle)
            startActivity(intent)
        }
        setLocate()
    }

    private fun setLocate() {
        if(getBindingData().cbSave.isChecked){
            val sharedPreferences: SharedPreferences = requireActivity().getSharedPreferences(saveInformation, Context.MODE_PRIVATE)
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putString("Email", getBindingData().editTextEmail.text.toString().trim())
            editor.putString("Password", getBindingData().editTextPassword.text.toString().trim())
            editor.putBoolean("Save", getBindingData().cbSave.isChecked)
            editor.apply()
        }else{
            val sharedPreferences: SharedPreferences = requireActivity().getSharedPreferences(saveInformation, Context.MODE_PRIVATE)
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.remove("Email")
            editor.remove("Password")
            editor.remove("Save")
            editor.apply()
        }
    }

    override fun onResume() {
        super.onResume()
        val sharedPreferences: SharedPreferences = requireActivity().getSharedPreferences(saveInformation, Context.MODE_PRIVATE)
        val emailResume: String = sharedPreferences.getString("Email", "")!!
        val passwordResume: String = sharedPreferences.getString("Password", "")!!
        val save: Boolean = sharedPreferences.getBoolean("Save", false)
        if (save) {
            getBindingData().editTextEmail.setText(emailResume)
            getBindingData().editTextPassword.setText(passwordResume)
            getBindingData().cbSave.isChecked = true
        }
    }
}