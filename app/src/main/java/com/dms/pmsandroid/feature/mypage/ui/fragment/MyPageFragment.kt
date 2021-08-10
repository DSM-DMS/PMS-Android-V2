package com.dms.pmsandroid.feature.mypage.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import com.dms.pmsandroid.R
import com.dms.pmsandroid.base.BaseFragment
import com.dms.pmsandroid.databinding.FragmentMypageBinding
import com.dms.pmsandroid.feature.mypage.model.StudentResponse
import com.dms.pmsandroid.feature.mypage.ui.MyPageAddStudentDialog
import com.dms.pmsandroid.feature.mypage.viewmodel.AddStudentViewModel
import com.dms.pmsandroid.feature.mypage.viewmodel.MyPageViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MyPageFragment : BaseFragment<FragmentMypageBinding>(R.layout.fragment_mypage) {
    override val vm: MyPageViewModel by viewModel()
    val dialogvm : AddStudentViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
        observeEvent()
        vm.inputBasicInfo()
    }

    //인증에 성공한 대로 기본 정보 가져오는
    fun getBasicInfo(){


    }

    override fun observeEvent() {
        vm.run {
            vm.toastMessage.observe(viewLifecycleOwner, {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            })
        }


        binding.startAddStudentBtn.setOnClickListener {
            activity.let {
                val dialog = MyPageAddStudentDialog()
                activity?.supportFragmentManager?.let { it1 -> dialog.show(it1,"AddStudentDialog") }
            }
        }
        dialogvm.run {
            dialogvm.studentCertification()
            vm.toastMessage.observe(viewLifecycleOwner, {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            })
        }

        vm.info.observe(viewLifecycleOwner,{
            binding.run {
                nickName = it.name
            }
        })

    }
}