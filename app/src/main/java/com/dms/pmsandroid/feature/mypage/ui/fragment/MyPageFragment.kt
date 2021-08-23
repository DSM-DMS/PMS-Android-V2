package com.dms.pmsandroid.feature.mypage.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.dms.pmsandroid.R
import com.dms.pmsandroid.base.BaseFragment
import com.dms.pmsandroid.base.EventObserver
import com.dms.pmsandroid.databinding.FragmentMypageBinding
import com.dms.pmsandroid.feature.mypage.ui.ChangeNameDialog
import com.dms.pmsandroid.feature.mypage.ui.LogoutDialog
import com.dms.pmsandroid.feature.mypage.ui.MyPageAddStudentDialog
import com.dms.pmsandroid.feature.mypage.ui.StudentsBottomDialog
import com.dms.pmsandroid.feature.mypage.viewmodel.MyPageViewModel
import com.dms.pmsandroid.ui.MainActivity
import com.dms.pmsandroid.ui.MainViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MyPageFragment : BaseFragment<FragmentMypageBinding>(R.layout.fragment_mypage) {
    override val vm: MyPageViewModel by viewModel()
    private val mainVm: MainViewModel by inject()

    private val logoutDialog by lazy {
        LogoutDialog(vm)
    }

    private val studentsBottomDialog by lazy {
        StudentsBottomDialog(this, vm)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.inputBasicInfo()
        observeEvent()

        binding.run {

           changePwCv.setOnClickListener {
                (activity as MainActivity).startChangePassword()
            }
           logoutCv.setOnClickListener {
                logoutDialog.show(requireActivity().supportFragmentManager, "logoutDialog")
            }

           studentNameTv.setOnClickListener {
                showStudentBottomDialog()
            }

           startAddStudentBtn.setOnClickListener {
                showAddStudentDialog()
            }


        }

    }

    private fun showStudentBottomDialog() {
        studentsBottomDialog.show(
            requireActivity().supportFragmentManager,
            "studentBottomDialog"
        )
    }

    override fun observeEvent() {
        mainVm.doneToken.observe(viewLifecycleOwner, {
            if (it) {
                vm.inputBasicInfo()
            }
        })
        vm.run {
            binding.introOutingCv.setOnClickListener {
                val number = info.value!!.students[0].studentNumber
                (activity as MainActivity).startOuting(number)
            }

            toastMessage.observe(viewLifecycleOwner, EventObserver{
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            })
            info.observe(viewLifecycleOwner, {
                binding.run {
                    nickName = it.name
                    if (!it.students.isNullOrEmpty()) {
                        studentNumber = it.students[0].studentNumber
                        studentName = it.students[0].studentName
                    } else {
                        successCertifitcation.value = false
                        studentNumber = null
                        studentName = null
                    }
                }
            })

            basicInfo.observe(viewLifecycleOwner, {
                binding.run {
                    pluspoint = it.bonusPoint
                    minuspoint = it.minusPoint
                    stay = it.stayStatus
                    if (it.mealApplied) {
                        binding.mealAppliedImg.setImageDrawable(context?.let { it1 ->
                            ContextCompat.getDrawable(
                                it1, R.drawable.ic_baseline_radio_button_unchecked_24
                            )
                        })
                    }else
                        binding.mealAppliedImg.setImageDrawable(context?.let { it1->
                            ContextCompat.getDrawable(it1,R.drawable.ic_baseline_clear_24)
                        })
                }
            })
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

    private val addStudentDialog by lazy {
        MyPageAddStudentDialog(vm)
    }

    fun showAddStudentDialog() {
        activity?.supportFragmentManager?.let { it1 ->
            addStudentDialog.show(
                it1,
                "AddStudentDialog"
            )
        }
    }
}