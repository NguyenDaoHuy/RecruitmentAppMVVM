package com.cuongpq.basemvvm.ui.employer.update_company

import android.app.Activity
import com.cuongpq.basemvvm.data.local.AppDatabase
import com.cuongpq.basemvvm.data.model.Company
import com.cuongpq.basemvvm.data.model.User
import com.cuongpq.basemvvm.data.remote.InteractCommon
import com.cuongpq.basemvvm.data.sqlite.SQLiteHelper
import com.cuongpq.basemvvm.ui.base.viewmodel.BaseViewModel
import java.util.concurrent.Executor
import javax.inject.Inject

class UpdateCompanyViewModel @Inject constructor(
    appDatabase: AppDatabase,
    interactCommon: InteractCommon,
    scheduler: Executor
) : BaseViewModel<UpdateCompanyCallBack>(appDatabase,interactCommon,scheduler) {
    var sqLiteHelper: SQLiteHelper? = null
    var listCompany : ArrayList<Company>? = null
    init {

    }
    companion object{
        val COMPANY_COMFIRM = 1000
        val SET_COMPANY = 1001
    }

    fun getDataCompanyFromDatabase(activity: Activity){
        sqLiteHelper = SQLiteHelper(activity, "Data.sqlite", null, 5)
        listCompany = ArrayList()
//        sqLiteHelper!!.QueryData("INSERT INTO Company VALUES(null,'BacHaSoft','https://bachasoftware.com/wp-content/uploads/2020/07/BHSoft_400.png','Ha Noi')")
//        sqLiteHelper!!.QueryData("INSERT INTO Company VALUES(null,'FPT Company','https://th.bing.com/th/id/OIP.pRJ2OBUhfSaUS9jmXD9DUQHaHa?w=172&h=180&c=7&r=0&o=5&dpr=1.25&pid=1.7','Ha Noi')")
//        sqLiteHelper!!.QueryData("INSERT INTO Company VALUES(null,'Misa Company','https://th.bing.com/th/id/OIP.WqAj8pdfMyAsDLDwyb9CIQHaHa?w=171&h=180&c=7&r=0&o=5&dpr=1.25&pid=1.7','Ha Noi')")
//        sqLiteHelper!!.QueryData("INSERT INTO Company VALUES(null,'Garena','https://th.bing.com/th/id/OIP.FSRxSya-zJRYtIYES4n5vgHaF7?pid=ImgDet&rs=1','Ha Noi')")
//        sqLiteHelper!!.QueryData("INSERT INTO Company VALUES(null,'Top CV','https://www.topcv.vn/v3/images/topcv-logo-gray.png','Ha Noi')")
//        sqLiteHelper!!.QueryData("INSERT INTO Company VALUES(null,'Ngân hàng BIDV','https://pluspng.com/img-png/bidv-png-bidv-ngan-hang-u-t-va-phat-tri-n-vi-t-nam-slogan-946.png','Ha Noi')")
        val data = sqLiteHelper!!.GetData("SELECT * FROM Company")
        while (data.moveToNext()) {
            val id = data.getInt(0)
            val name = data.getString(1)
            val avatar = data.getString(2)
            val adress = data.getString(3)
            listCompany!!.add(Company(id,name,avatar,adress))
        }
    }
    fun setCompany(company: Company,activity: Activity,user: User){
        sqLiteHelper = SQLiteHelper(activity, "Data.sqlite", null, 5)
        sqLiteHelper!!.QueryData("UPDATE User SET IdCompany = '${company.idCompany}' WHERE IdAccount ='${user.idAccount}'")
        uiEventLiveData.value = SET_COMPANY
    }
    fun getCompanyArrayList(): ArrayList<Company>{
         return listCompany!!
    }
    fun onClickComfirm(){
        uiEventLiveData.value = COMPANY_COMFIRM
    }
}