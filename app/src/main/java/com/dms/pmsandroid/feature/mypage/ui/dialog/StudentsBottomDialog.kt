package com.dms.pmsandroid.feature.mypage.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import com.dms.pmsandroid.R
import com.dms.pmsandroid.base.Event
import com.dms.pmsandroid.databinding.DialogStudentsBottomBinding
import com.dms.pmsandroid.feature.mypage.ui.StudentInflater
import com.dms.pmsandroid.feature.mypage.ui.fragment.MyPageFragment
import com.dms.pmsandroid.feature.mypage.viewmodel.MyPageViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class StudentsBottomDialog(private val fragment: MyPageFragment, private val vm: MyPageViewModel) :
    BottomSheetDialogFragment() {

    private lateinit var binding: DialogStudentsBottomBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.dialog_students_bottom, container, false)
        binding.lifecycleOwner = this
        binding.studentAddCl.setOnClickListener {
            dismiss()
            fragment.showAddStudentDialog()
        }
        return binding.root
    }

    private val mainColor by lazy {
        requireContext().getColor(R.color.blue)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.studentLl.removeAllViews()
        val students = vm.students.value
        if (!students.isNullOrEmpty()) {
            var ct = 0
            for (student in students) {
                val inflater = StudentInflater(requireContext())

                if ((vm.studentIndex.value?.peekContent() ?: 0) == ct) {
                    inflater.findViewById<TextView>(R.id.student_name).setTextColor(mainColor)
                }

                val dataContainer = inflater.findViewById<TextView>(R.id.student_index_tv)
                dataContainer.text = ct.toString()

                inflater.findViewById<ConstraintLayout>(R.id.student_cl).setOnClickListener {
                    vm.studentIndex.value = Event((dataContainer as TextView).text.toString().toInt())
                    dismiss()
                }

                inflater.findViewById<TextView>(R.id.student_name).text = student.studentName

                inflater.findViewById<View>(R.id.student_delete_btn).setOnClickListener {
                    vm.deleteStudent(student.studentNumber)
                    dismiss()
                }
                binding.studentLl.addView(inflater)
                ct += 1
            }
        }

    }

}