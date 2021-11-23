package com.dms.pmsandroid.feature.mypage.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.dms.pmsandroid.R
import com.dms.pmsandroid.base.BaseFragment
import com.dms.pmsandroid.base.EventObserver
import com.dms.pmsandroid.databinding.FragmentMypageBinding
import com.dms.pmsandroid.feature.mypage.model.StudentResponse
import com.dms.pmsandroid.feature.mypage.ui.dialog.ChangeNameDialog
import com.dms.pmsandroid.feature.mypage.ui.dialog.LogoutDialog
import com.dms.pmsandroid.feature.mypage.ui.dialog.MyPageAddStudentDialog
import com.dms.pmsandroid.feature.mypage.ui.dialog.StudentsBottomDialog
import com.dms.pmsandroid.feature.mypage.viewmodel.MyPageViewModel
import com.dms.pmsandroid.ui.MainActivity
import com.dms.pmsandroid.ui.MainViewModel
import com.jakewharton.rxbinding4.view.clicks
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.random.Random
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
        MyPageAddStudentDialog(vm).show(
            requireActivity().supportFragmentManager,
            "AddStudentDialog"
        )

    }

    private fun showChangeName() {
        ChangeNameDialog(vm).show(requireActivity().supportFragmentManager, "changeNameDialog")
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.run {

            changePwCv.setOnClickListener {
                (activity as MainActivity).startChangePassword()
            }
            logoutCv.clicks().debounce(200, TimeUnit.MILLISECONDS).subscribe {
                logoutDialog.show(requireActivity().supportFragmentManager, "logoutDialog")
            }

            studentNameTv.clicks().debounce(200, TimeUnit.MILLISECONDS).subscribe {
                if (requireActivity().supportFragmentManager.findFragmentByTag("studentBottomDialog")?.isAdded != true) {
                    showStudentBottomDialog()
                }
            }

            startAddStudentBtn.clicks().debounce(200, TimeUnit.MILLISECONDS).subscribe {
                showAddStudentDialog()
            }
            studentParentEditImg.setOnClickListener {
                if (requireActivity().supportFragmentManager.findFragmentByTag("changeNameDialog")?.isAdded != true) {
                    showChangeName()
                }
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
                vm.loadBaseInfo()
            }
        })
        vm.run {
            binding.plusLayout.setOnClickListener {
                val number =
                    students.value?.get(studentIndex.value!!.peekContent())?.studentNumber ?: 0
                (activity as MainActivity).startPoint(number)
            }
            binding.minusLayout.setOnClickListener {
                val number =
                    students.value?.get(studentIndex.value!!.peekContent())?.studentNumber ?: 0
                (activity as MainActivity).startPoint(number)
            }
            binding.introOutingCv.setOnClickListener {
                val number =
                    students.value?.get(studentIndex.value!!.peekContent())?.studentNumber ?: 0
                (activity as MainActivity).startOuting(number)
            }

            toastMessage.observe(viewLifecycleOwner, EventObserver {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            })

            students.observe(viewLifecycleOwner, {
                if(it.isEmpty()){
                    successCertification.value = false
                }
                changeStudent(it[studentIndex.value!!.peekContent()])
            })

            studentIndex.observe(viewLifecycleOwner, EventObserver {
                vm.loadStudentInfo(it)
                vm.saveIndex(it)
                if (students.value != null) {
                    changeStudent(students.value!![it])
                }
            })

            studentInfo.observe(viewLifecycleOwner, {
                changeComment(it.minusPoint?.toInt() ?: 0)
                when (it.stayStatus) {
                    1 -> {
                        binding.stayTv.run {
                            text = "금요귀가"
                            setTextColor(requireContext().getColor(R.color.blue))
                        }
                    }
                    2 -> {
                        binding.stayTv.run {
                            text = "토요귀가"
                            setTextColor(requireContext().getColor(R.color.red))
                        }
                    }
                    3 -> {
                        binding.stayTv.run {
                            text = "토요귀사"
                            setTextColor(requireContext().getColor(R.color.red))
                        }
                    }
                    4 -> {
                        binding.stayTv.run {
                            text = "잔류"
                            setTextColor(requireContext().getColor(R.color.green))
                        }
                    }
                    else -> {
                        binding.stayTv.run {
                            text = "미선택"
                            setTextColor(requireContext().getColor(R.color.gray))
                        }
                    }
                }
            })

            inProgress.observe(viewLifecycleOwner, {
                if(it) {
                    binding.mypageShimmerFl.startShimmer()
                } else {
                    binding.mypageShimmerFl.hideShimmer()
                }
            })
        }
    }

    private fun changeStudent(student: StudentResponse) {
        binding.studentNumber = student.studentNumber
        binding.studentName = student.studentName
    }
}

