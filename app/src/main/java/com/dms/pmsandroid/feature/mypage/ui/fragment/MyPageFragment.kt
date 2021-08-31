package com.dms.pmsandroid.feature.mypage.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.dms.pmsandroid.R
import com.dms.pmsandroid.base.BaseFragment
import com.dms.pmsandroid.base.EventObserver
import com.dms.pmsandroid.databinding.FragmentMypageBinding
import com.dms.pmsandroid.feature.mypage.ui.dialog.ChangeNameDialog
import com.dms.pmsandroid.feature.mypage.ui.dialog.LogoutDialog
import com.dms.pmsandroid.feature.mypage.ui.dialog.MyPageAddStudentDialog
import com.dms.pmsandroid.feature.mypage.ui.dialog.StudentsBottomDialog
import com.dms.pmsandroid.feature.mypage.viewmodel.MyPageViewModel
import com.dms.pmsandroid.ui.MainActivity
import com.dms.pmsandroid.ui.MainViewModel
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

    private fun showStudentBottomDialog() {
        studentsBottomDialog.show(
            requireActivity().supportFragmentManager,
            "studentBottomDialog"
        )
    }

    fun showAddStudentDialog() {
        activity?.supportFragmentManager?.let { it1 ->
            MyPageAddStudentDialog(vm).show(
                it1,
                "AddStudentDialog"
            )
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.inputBasicInfo()
        observeEvent()

        binding.run {

            changePwCv.setOnClickListener {
                (activity as MainActivity).startChangePassword()
            }
            startAddStudentBtn.setOnClickListener {
                showAddStudentDialog()
            }
            logoutCv.clicks().debounce(200, TimeUnit.MILLISECONDS).subscribe {
                logoutDialog.show(requireActivity().supportFragmentManager, "logoutDialog")
            }

            studentNameTv.clicks().debounce(200, TimeUnit.MILLISECONDS).subscribe {
                showStudentBottomDialog()
            }

            startAddStudentBtn.clicks().debounce(200, TimeUnit.MILLISECONDS).subscribe {
                showAddStudentDialog()
            }

        }
    }


    private var changecomment = arrayOf("즐거운 DSM 생활중 입니다", "안전한 수준의 벌점입니다", "규칙을 잘 준수한 학생입니다")
    private var dangerComment = arrayOf("1차봉사위기에 처했습니다", "벌점을 더받으면 위험합니다", "아슬아슬한 수준의 벌점입니다")
    private var overComment = arrayOf("하고싶은걸 많이 한 학생입니다", "행복한 DSM 생활중 입니다", "벌점은 학교생활의 일부일 뿐입니다")
    private fun changeComment(minusPoint: Int) {
        val randomIndex = Random.nextInt(3)
        when (minusPoint) {
            in 0..10 -> {
                binding.introCommentTv.text = changecomment[randomIndex]
            }
            in 11..14 -> {
                binding.introCommentTv.text = dangerComment[randomIndex]
            }
            else -> {
                binding.introCommentTv.text = overComment[randomIndex]
            }
        }

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
                val number = info.value!!.students[studentIndex.value!!.peekContent()].studentNumber
                (activity as MainActivity).startPoint(number)
            }

            binding.minusLayout.setOnClickListener {
                val number = info.value!!.students[studentIndex.value!!.peekContent()].studentNumber
                (activity as MainActivity).startPoint(number)
            }

            binding.introOutingCv.setOnClickListener {
                val number = info.value!!.students[studentIndex.value!!.peekContent()].studentNumber
                (activity as MainActivity).startOuting(number)
            }
            studentIndex.observe(viewLifecycleOwner, EventObserver {
                if (info.value != null) {
                    loadStudentInfo()
                    val student = info.value!!.students[it]
                    binding.studentName = student.studentName
                    binding.studentNumber = student.studentNumber
                }
            })
            toastMessage.observe(viewLifecycleOwner, EventObserver {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            })
            info.observe(viewLifecycleOwner, {
                binding.run {
                    nickName = it.name
                    if (!it.students.isNullOrEmpty()) {
                        val student = it.students[studentIndex.value!!.peekContent()]
                        studentNumber = student.studentNumber
                        studentName = student.studentName
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
                    changeComment((it.minusPoint ?: "0").toInt())
                    if (it.stayStatus == "4") {
                        binding.stayTv.setText("잔류")
                        binding.stayTv.setTextColor(requireContext().getColor(R.color.blue))
                    } else if (it.stayStatus == "1") {
                        binding.stayTv.setText("금요귀가")
                        binding.stayTv.setTextColor(requireContext().getColor(R.color.red))
                    } else if (it.stayStatus == "2") {
                        binding.stayTv.setText("토요귀가")
                        binding.stayTv.setTextColor(requireContext().getColor(R.color.green))
                    } else if (it.stayStatus == "3") {
                        binding.stayTv.setText("토요귀사")
                        binding.stayTv.setTextColor(requireContext().getColor(R.color.gray))
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

