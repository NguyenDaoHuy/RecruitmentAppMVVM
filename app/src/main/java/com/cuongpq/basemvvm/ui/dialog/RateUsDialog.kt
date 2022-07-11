package com.cuongpq.basemvvm.ui.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.cuongpq.basemvvm.R
import com.cuongpq.basemvvm.databinding.RateUsDialogLayoutBinding

class RateUsDialog(context: Context) : Dialog(context) {
    private var binding: RateUsDialogLayoutBinding? = null
    private var userRate = 0f
    override fun onCreate(savedInstanceState: Bundle) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.rate_us_dialog_layout, null, false)
        setContentView(binding!!.getRoot())
        onClick()
    }

    private fun onClick() {
        binding!!.ratingBar.setOnRatingBarChangeListener(object :
            RatingBar.OnRatingBarChangeListener {
            override fun onRatingChanged(ratingBar: RatingBar, rating: Float, fromUser: Boolean) {
                if (rating <= 1) {
                    binding!!.imgStatus.setImageResource(R.drawable.one_star)
                } else if (rating <= 2) {
                    binding!!.imgStatus.setImageResource(R.drawable.two_star)
                } else if (rating <= 3) {
                    binding!!.imgStatus.setImageResource(R.drawable.three_star)
                } else if (rating <= 4) {
                    binding!!.imgStatus.setImageResource(R.drawable.four_star)
                } else if (rating <= 5) {
                    binding!!.imgStatus.setImageResource(R.drawable.five_star)
                }
                animateImage(binding!!.imgStatus)
                userRate = rating
            }
        })
        binding!!.btnRateNow.setOnClickListener(View.OnClickListener {
            dismiss()
            Toast.makeText(context, "Ok", Toast.LENGTH_SHORT).show()
        })
        binding!!.btnLater.setOnClickListener(View.OnClickListener { dismiss() })
    }

    private fun animateImage(ratingImage: ImageView) {
        val scaleAnimation = ScaleAnimation(0F, 1f, 0F, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        scaleAnimation.setFillAfter(true)
        scaleAnimation.setDuration(200)
        binding!!.imgStatus.setAnimation(scaleAnimation)
    }
}