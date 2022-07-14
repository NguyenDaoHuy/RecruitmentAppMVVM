package com.cuongpq.basemvvm.ui.employer.update_company

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.cuongpq.basemvvm.R
import com.cuongpq.basemvvm.data.model.Company

class CompanyAdapter(context: Context ,companyList: List<Company>) :
    ArrayAdapter<Company>(context,0,companyList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return initView(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return initView(position, convertView, parent)
    }

    fun initView(position: Int,convertView: View?,parent: ViewGroup) : View{
        var companyLogo : ImageView
        var companyName : TextView
        val company = getItem(position)
        val view = LayoutInflater.from(context).inflate(R.layout.custom_spinner_company,parent,false)
        companyLogo = view.findViewById(R.id.companyLogo)
        companyName =  view.findViewById(R.id.companyName)
        companyName.setText(company!!.companyName)
        Glide.with(context).load(company.companyAvatar)
            .placeholder(R.drawable.logo_bacha)
            .error(R.drawable.logo_bacha)
            .into(companyLogo)
        return view
    }
}