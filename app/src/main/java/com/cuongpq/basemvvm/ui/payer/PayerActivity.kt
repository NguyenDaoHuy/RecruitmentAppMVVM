package com.cuongpq.basemvvm.ui.payer

import android.widget.Toast
import com.android.volley.*
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.cuongpq.basemvvm.R
import com.cuongpq.basemvvm.databinding.ActivityPayerBinding
import com.cuongpq.basemvvm.ui.base.activity.BaseMVVMActivity
import com.cuongpq.basemvvm.ui.base.viewmodel.BaseViewModel
import com.stripe.android.PaymentConfiguration
import com.stripe.android.paymentsheet.PaymentSheet
import com.stripe.android.paymentsheet.PaymentSheetResult
import org.json.JSONException
import org.json.JSONObject

class PayerActivity : BaseMVVMActivity<PayerActivityCallBack,PayerActivityViewModel>(),PayerActivityCallBack{

    private var SECRET_KEY : String = "sk_test_51LKdlnFE2VHDpf9G5XhkTsjlzRxgd7DDH5aPTtZy37xNbjPJOz8hJY6IvonqwtcnjnJZbep8pzogdnJ4PZyqbzEt00UXrDSDnN"
    private var PUBLISH_KEY : String = "pk_test_51LKdlnFE2VHDpf9GUFQtCwNsEdaPQidrn5x4A2WWAYgQu05Ys84SHNCFkOVp1iFLpAyj64PQv09gsVZYtOZXATHW00VSketTnk"
    private var paymentSheet : PaymentSheet? = null
    private var customerID: String? = null
    private var EphericalKey: String? = null
    private var ClientSecret: String? = null

    override fun initComponents() {
        getBindingData().payerActivityViewModel = mModel
        mModel.uiEventLiveData.observe(this){
            when(it){
                BaseViewModel.FINISH_ACTIVITY -> finish()
                PayerActivityViewModel.ON_CLICK_PAY -> PaymentFlow()
            }
        }
        payer()
    }

    private fun payer(){
        PaymentConfiguration.init(this,PUBLISH_KEY)
        paymentSheet = PaymentSheet(
            this
        ) { paymentSheetResult: PaymentSheetResult? ->
            onPaymentResult(paymentSheetResult!!)
        }

        val stringRequest: StringRequest = object : StringRequest(
            Method.POST, "https://api.stripe.com/v1/customers",
            Response.Listener { response ->
                try {
                    val `object` = JSONObject(response)
                    customerID = `object`.getString("id")
                    getEphericalKey(customerID!!)
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }, Response.ErrorListener { }) {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val header: MutableMap<String, String> = HashMap()
                header["Authorization"] = "Bearer$SECRET_KEY"
                return header
            }
        }
        val requestQueue = Volley.newRequestQueue(this)
        requestQueue.add(stringRequest)

    }

    override fun getLayoutMain(): Int {
        return R.layout.activity_payer
    }

    override fun setEvents() {

    }

    override fun getBindingData() = mBinding as ActivityPayerBinding

    override fun getViewModel(): Class<PayerActivityViewModel> {
        return PayerActivityViewModel::class.java
    }

    override fun error(id: String, error: Throwable) {
        showMessage(error.message!!)
    }

    private fun onPaymentResult(paymentSheetResult: PaymentSheetResult) {
        if (paymentSheetResult is PaymentSheetResult.Completed) {
            Toast.makeText(this, "payment success", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getEphericalKey(customerID: String) {
        val stringRequest: StringRequest = object : StringRequest(
            Method.POST, "https://api.stripe.com/v1/ephemeral_keys",
            Response.Listener { response ->
                try {
                    val `object` = JSONObject(response)
                    EphericalKey = `object`.getString("id")
                    getClientSecret(customerID)
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }, Response.ErrorListener { }) {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val header: MutableMap<String, String> = java.util.HashMap()
                header["Authorization"] = "Bearer$SECRET_KEY"
                header["Stripe-Version"] = "2020-08-27"
                return header
            }

            @Throws(AuthFailureError::class)
            override fun getParams(): Map<String, String>? {
                val params: MutableMap<String, String> = java.util.HashMap()
                params["customer"] = customerID
                return params
            }
        }
        val requestQueue = Volley.newRequestQueue(this)
        requestQueue.add(stringRequest)
    }

    private fun getClientSecret(customerID: String) {
        val stringRequest: StringRequest = object : StringRequest(
            Method.POST, "https://api.stripe.com/v1/payment_intents",
            Response.Listener { response ->
                try {
                    val `object` = JSONObject(response)
                    ClientSecret = `object`.getString("client_secret")
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }, Response.ErrorListener { }) {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val header: MutableMap<String, String> = java.util.HashMap()
                header["Authorization"] = "Bearer$SECRET_KEY"
                return header
            }

            @Throws(AuthFailureError::class)
            override fun getParams(): Map<String, String>? {
                val params: MutableMap<String, String> = java.util.HashMap()
                params["customer"] = customerID
                params["amount"] = "1000" + "00"
                params["currency"] = "usd"
                params["automatic_payment_methods[enabled]"] = "true"
                return params
            }
        }
        val requestQueue = Volley.newRequestQueue(this)
        requestQueue.add(stringRequest)
    }

    private fun PaymentFlow() {
        paymentSheet!!.presentWithPaymentIntent(
            ClientSecret!!, PaymentSheet.Configuration(
                "ABC Company", PaymentSheet.CustomerConfiguration(
                    customerID!!,
                    EphericalKey!!
                )
            )
        )
    }
}