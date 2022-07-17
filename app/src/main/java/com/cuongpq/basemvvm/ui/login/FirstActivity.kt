package com.cuongpq.basemvvm.ui.login

import android.os.Handler
import android.view.View
import androidx.fragment.app.Fragment
import com.cuongpq.basemvvm.R
import com.cuongpq.basemvvm.data.sqlite.SQLiteHelper
import com.cuongpq.basemvvm.databinding.ActivityFirstBinding

import com.cuongpq.basemvvm.ui.base.activity.BaseMVVMActivity
import com.cuongpq.basemvvm.ui.login.signin.LoginFragment

class FirstActivity : BaseMVVMActivity<FirstCallBack,FirstViewModel>(),FirstCallBack{

    var sqLiteHelper: SQLiteHelper? = null

    override fun getLayoutMain() = R.layout.activity_first

    override fun setEvents() {

    }

    override fun initComponents() {
        getBindingData().firstViewModel = mModel
        sqLiteHelper = SQLiteHelper(this, "Data.sqlite", null, 5)
        createTableDatabase()
        Handler().postDelayed({
            getFragment(LoginFragment())
            getBindingData().imgSplash.visibility = View.GONE
            getBindingData().txtSplash.visibility = View.GONE
        },1500)
    }

    private fun createTableDatabase() {
        sqLiteHelper!!.QueryData(
            "CREATE TABLE IF NOT EXISTS Company(Id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "CompanyName NVARCHAR(100)," +
                    "CompanyAvatar NVARCHAR(200)," +
                    "CompanyAdress NVARCHAR(150))"
        )
        sqLiteHelper!!.QueryData(
            "CREATE TABLE IF NOT EXISTS Job4(Id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "JobCode NVARCHAR(30)," +
                    "JobName NVARCHAR(100)," +
                    "description NVARCHAR(300)," +
                    "IdEmployer NVARCHAR(100)," +
                    "IdCompany INTEGER," +
                    "right NVARCHAR(300)," +
                    "amount INTEGER," +
                    "status INTEGER)"
        )
        sqLiteHelper!!.QueryData(
            "CREATE TABLE IF NOT EXISTS User(Id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "IdAccount NVARCHAR(100)," +
                    "email NVARCHAR(100)," +
                    "password NVARCHAR(100)," +
                    "userName NVARCHAR(300)," +
                    "age INTEGER," +
                    "phone NVARCHAR(20)," +
                    "permission INTEGER," +
                    "IdCompany INTEGER)"
        )
        sqLiteHelper!!.QueryData(
            "CREATE TABLE IF NOT EXISTS Skill1(Id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name NVARCHAR(100)," +
                    "type INTEGER," +
                    "IdJob NVARCHAR(30))"
        )
        sqLiteHelper!!.QueryData(
            "CREATE TABLE IF NOT EXISTS Question(Id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "content NVARCHAR(200)," +
                    "IdJob NVARCHAR(30))"
        )
        sqLiteHelper!!.QueryData(
            "CREATE TABLE IF NOT EXISTS UserSkill(Id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name NVARCHAR(100)," +
                    "type INTEGER," +
                    "IdAccount NVARCHAR(30))"
        )
        sqLiteHelper!!.QueryData(
            "CREATE TABLE IF NOT EXISTS Apply(Id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "IdCandidate NVARCHAR(100)," +
                    "IdJob INTEGER," +
                    "IdEmployer NVARCHAR(100))"
        )
        sqLiteHelper!!.QueryData(
            "CREATE TABLE IF NOT EXISTS Answer(Id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "IdCandidate NVARCHAR(100)," +
                    "IdQuestion INTEGER," +
                    "content NVARCHAR(500))"
        )
    }

    override fun getBindingData() = mBinding as ActivityFirstBinding

    override fun getViewModel(): Class<FirstViewModel> {
        return FirstViewModel::class.java
    }
    override fun error(id: String, error: Throwable) {
        showMessage(error.message!!)
    }
    private fun getFragment(fragment : Fragment?){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentLogin1,fragment!!)
        fragmentTransaction.commit()
    }
}