package com.dms.pmsandroid.feature.mypage.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
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
import org.koin.android.ext.android.bind
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.random.Random
import com.jakewharton.rxbinding4.view.clicks
import java.util.concurrent.TimeUnit

class MyPageFragment : BaseFragment<FragmentMypageBinding>(R.layout.fragment_mypage) {
    override val vm: MyPageViewModel by viewModel()
    private val mainVm: MainViewModel by inject()

    private val logoutDialog by lazy {
        LogoutDialog(vm)
    }

    private val studentsBottomDialog by lazy {
        StudentsBottomDialog(this, vm)
    }

    val showAddStudentDialog by lazy {
        MyPageAddStudentDialog(vm)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.inputBasicInfo()
        observeEvent()
        changeComment()

        binding.run {

            fun showStudentBottomDialog() {
                studentsBottomDialog.show(
                    requireActivity().supportFragmentManager,
                    "studentBottomDialog"
                )
            }

            fun showAddStudentDialog() {
                showAddStudentDialog.show(
                    requireActivity().supportFragmentManager,
                    "studentAddDialog"

                )
            }

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

            binding.logoutCv.clicks().debounce(200, TimeUnit.MILLISECONDS).subscribe {
                logoutDialog.show(requireActivity().supportFragmentManager, "logoutDialog")
            }

            binding.studentNameTv.clicks().debounce(200, TimeUnit.MILLISECONDS).subscribe {
                showStudentBottomDialog()
            }

            binding.startAddStudentBtn.clicks().debounce(200, TimeUnit.MILLISECONDS).subscribe {
                showAddStudentDialog()
            }
        }
    }


    var changecomment = arrayOf<String>("즐거운 DSM 생활중 입니다", "코로나에 유의하세요!", "다음주는 집 가는 날")
    fun changeComment() {
        val randomIndex = Random.nextInt(3)
        binding.introCommentTv.text = changecomment[randomIndex]
    }


    @SuppressLint("ResourceAsColor")
    override fun observeEvent() {
        mainVm.doneToken.observe(viewLifecycleOwner, {
            if (it) {
                vm.inputBasicInfo()
            }
        })
        vm.run {

            binding.plusLayout.setOnClickListener {
                val number = info.value!!.students[0].studentNumber
                (activity as MainActivity).startPoint(number)
            }

            binding.minusLayout.setOnClickListener {
                val number = info.value!!.students[0].studentNumber
                (activity as MainActivity).startPoint(number)
            }

            binding.introOutingCv.setOnClickListener {
                val number = info.value!!.students[0].studentNumber
                (activity as MainActivity).startOuting(number)
            }

            toastMessage.observe(viewLifecycleOwner, EventObserver {
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
                    if (it.stayStatus == "4") {
                        binding.stayTv.setText("잔류")
                        binding.stayTv.setTextColor(R.color.blue)
                    } else if (it.stayStatus == "1") {
                        binding.stayTv.setText("금요귀가")
                        binding.stayTv.setTextColor(R.color.red)
                    } else if (it.stayStatus == "2") {
                        binding.stayTv.setText("토요귀가")
                        binding.stayTv.setTextColor(R.color.green)
                    } else if (it.stayStatus == "3") {
                        binding.stayTv.setText("토요귀사")
                        binding.stayTv.setTextColor(R.color.gray)
                    } else {
                        binding.stayTv.setText("미선택")
                    }

                    if (it.mealApplied) {
                        binding.mealAppliedImg.setImageDrawable(context?.let { it1 ->
                            ContextCompat.getDrawable(
                                it1, R.drawable.ic_baseline_radio_button_unchecked_24
                            )
                        })
                    } else
                        binding.mealAppliedImg.setImageDrawable(context?.let { it1 ->
                            ContextCompat.getDrawable(it1, R.drawable.ic_baseline_clear_24)
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
}

