package com.dms.pmsandroid.feature.mypage.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.dms.pmsandroid.R
import com.dms.pmsandroid.base.BaseDialog
import com.dms.pmsandroid.base.BaseFragment
import com.dms.pmsandroid.databinding.FragmentMypageBinding
import com.dms.pmsandroid.feature.mypage.ui.ChangeNameDialog
import com.dms.pmsandroid.feature.mypage.ui.LogoutDialog
import com.dms.pmsandroid.feature.mypage.ui.MyPageAddStudentDialog
import com.dms.pmsandroid.feature.mypage.viewmodel.MyPageViewModel
import com.dms.pmsandroid.ui.MainActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MyPageFragment : BaseFragment<FragmentMypageBinding>(R.layout.fragment_mypage) {
    override val vm: MyPageViewModel by viewModel()

    private val logoutDialog by lazy {
        LogoutDialog(vm)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.inputBasicInfo()
        observeEvent()


        binding.introOutingCv.setOnClickListener {
            (activity as MainActivity).startOuting()
        }

        binding.changePwCv.setOnClickListener {
            (activity as MainActivity).startChangePassword()
        }
        binding.logoutCv.setOnClickListener {
            logoutDialog.show(requireActivity().supportFragmentManager,"logoutDialog")
        }

    }

    override fun observeEvent() {
        vm.run {
            toastMessage.observe(viewLifecycleOwner, {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            })
            info.observe(viewLifecycleOwner, {
                binding.run {
                    nickName = it.name
                    if(!it.students.isNullOrEmpty()){
                        studentNumber = it.students[0].studentNumber
                        studentName = it.students[0].studentName
                    }
                }
            })



            BasicInfo.observe(viewLifecycleOwner, {
                binding.run {
                    pluspoint = it.bonusPoint
                    minuspoint = it.minusPoint
                    stay = it.stayStaus
                    if (it.mealApplied == false) {
                        binding.mealAppliedTv.setImageDrawable(context?.let { it1 ->
                            ContextCompat.getDrawable(
                                it1, R.drawable.ic_baseline_radio_button_unchecked_24
                            )
                        })
                    }
                }
            })

            binding.startAddStudentBtn.setOnClickListener {
                activity.let {
                    val dialog = MyPageAddStudentDialog(vm)
                    activity?.supportFragmentManager?.let { it1 ->
                        dialog.show(
                            it1,
                            "AddStudentDialog"
                        )
                    }
                }
            }
            binding.studentParentEditImg.setOnClickListener {
                activity.let {
                    val dialog = ChangeNameDialog(vm)
                    activity?.supportFragmentManager?.let { it ->
                        dialog.show(
                            it, "ChangeName"
                        )
                    }
                }
            }
        }
    }
}