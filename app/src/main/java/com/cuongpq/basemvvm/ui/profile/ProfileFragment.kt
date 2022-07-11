package com.cuongpq.basemvvm.ui.profile

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.bumptech.glide.Glide
import com.cuongpq.basemvvm.R
import com.cuongpq.basemvvm.data.model.User
import com.cuongpq.basemvvm.databinding.FragmentProfileBinding
import com.cuongpq.basemvvm.ui.base.fragment.BaseMvvmFragment
import com.cuongpq.basemvvm.ui.base.viewmodel.BaseViewModel
import com.cuongpq.basemvvm.ui.dialog.RateUsDialog
import com.cuongpq.basemvvm.ui.login.FirstActivity
import com.cuongpq.basemvvm.ui.profile.update_skill.UpdateSkillFragment
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import java.io.IOException


class ProfileFragment(var user : User?) : BaseMvvmFragment<ProfileCallBack,ProfileViewModel>(),ProfileCallBack {

    private var mUri: Uri? = null

    override fun setEvents() {

    }

    override fun initComponents() {
        getBindingData().profileViewModel = mModel
        getBindingData().lifecycleOwner = viewLifecycleOwner
        mModel.uiEventLiveData.observe(this) {
            when (it) {
                BaseViewModel.FINISH_ACTIVITY -> finishActivity()
                ProfileViewModel.LOG_OUT -> onClickLogOut()
                ProfileViewModel.GO_UPDATE_SKILL -> goToUpdateSkill()
                ProfileViewModel.SHOW_DIALOG -> showDiaLog()
                ProfileViewModel.ON_CLICK_AVT -> onClickRequestPermission()
                ProfileViewModel.ON_CLICK_SET_AVT -> updateAvatar()
            }
        }
        setInformationUser()
        setUserAvatar()
    }

    private fun setInformationUser() {
        getBindingData().tvUserEmail.text = user!!.email
        getBindingData().tvUserName.text = user!!.name
    }


    override fun getBindingData() = mBinding as FragmentProfileBinding

    override fun getLayoutMain(): Int {
        return R.layout.fragment_profile
    }

    override fun getViewModel(): Class<ProfileViewModel> {
        return ProfileViewModel::class.java
    }

    override fun error(id: String, error: Throwable) {
        showMessage(error.message!!)
    }

    fun goToUpdateSkill(){
        val fragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentMain, UpdateSkillFragment())
        fragmentTransaction.addToBackStack(UpdateSkillFragment.TAG)
        fragmentTransaction.commit()
    }

    fun onClickLogOut(){
        val alertDialog = AlertDialog.Builder(getContext())
            .setTitle("Xác nhận đăng xuất")
            .setMessage("Bạn có chắc chắn muốn đăng xuất ?")
            .setPositiveButton(
                "Có",
                DialogInterface.OnClickListener { dialog: DialogInterface?, which: Int ->
                    mModel.onLogOut()
                    val intent = Intent(context, FirstActivity::class.java)
                    startActivity(intent)
                })
            .setNegativeButton(
                "Không",
                DialogInterface.OnClickListener { dialog: DialogInterface?, which: Int -> })
            .create()
        alertDialog.show()
    }
    fun showDiaLog(){
        val rateUsDialog = RateUsDialog(requireContext())
        rateUsDialog.getWindow()!!.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.transparent)))
        rateUsDialog.setCancelable(false)
        rateUsDialog.show()
    }

    private fun setUserAvatar() {
        val user: FirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ?: return
        Glide.with(this).load(user.getPhotoUrl()).error(R.drawable.avatardefult1)
            .into(getBindingData().imAvatar)
    }

    private fun onClickRequestPermission() {
        getBindingData().btnSetAvt.visibility = View.VISIBLE
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            openGallery()
            return
        }
        if (activity?.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            openGallery()
        } else {
            val permisstions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
            this.requestPermissions(permisstions, MY_REQUEST_CODE)
        }
    }

    fun setBitmapImageView(bitmapImageView: Bitmap?) {
        Glide.with(getBindingData().imAvatar).load(bitmapImageView).into(getBindingData().imAvatar)
    }

    fun setmUri(mUri: Uri?) {
        this.mUri = mUri
    }

    private fun updateAvatar() {
        val user: FirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ?: return
        val profileUpdates: UserProfileChangeRequest = UserProfileChangeRequest.Builder()
            .setPhotoUri(mUri)
            .build()
        user.updateProfile(profileUpdates)
            .addOnCompleteListener(OnCompleteListener<Void> { task: Task<Void?> ->
                getBindingData().btnSetAvt.setVisibility(View.INVISIBLE)
                if (task.isSuccessful) {
                    Toast.makeText(getActivity(), "Sucess", Toast.LENGTH_SHORT).show()
                    setUserAvatar()
                }
            })
    }


    private val intentActivityResultLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.getResultCode() === Activity.RESULT_OK) {
                val intent: Intent = result.data ?: return@registerForActivityResult
                val uri: Uri = intent.getData()!!
                setmUri(uri)
                try {
                    val bitmap: Bitmap =
                        MediaStore.Images.Media.getBitmap(activity?.getContentResolver(), uri)
                    setBitmapImageView(bitmap)
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String?>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == MY_REQUEST_CODE) {
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openGallery()
            } else {
                Toast.makeText(getContext(), "Please give access", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun openGallery() {
        val intent = Intent()
        intent.setType("image/*")
        intent.setAction(Intent.ACTION_GET_CONTENT)
        intentActivityResultLauncher.launch(Intent.createChooser(intent, "Pick image"))
    }

    companion object {
        const val MY_REQUEST_CODE = 10
    }
}