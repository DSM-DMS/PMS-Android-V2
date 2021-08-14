package com.dms.pmsandroid.feature.mypage.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.dms.pmsandroid.R
import com.dms.pmsandroid.base.BaseFragment
import com.dms.pmsandroid.databinding.FragmentMypageBinding
import com.dms.pmsandroid.feature.mypage.model.ChangeNameRequest
import com.dms.pmsandroid.feature.mypage.ui.ChangeNameDialog
import com.dms.pmsandroid.feature.mypage.ui.MyPageAddStudentDialog
import com.dms.pmsandroid.feature.mypage.viewmodel.MyPageViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MyPageFragment : BaseFragment<FragmentMypageBinding>(R.layout.fragment_mypage) {
    override val vm: MyPageViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.inputBasicInfo()
        vm.changeName()
    }


    //vm.basicInfo() 들어와야함
    //Student Number를 어떻게 가져 오느냐


    override fun observeEvent() {
        vm.run {
            toastMessage.observe(viewLifecycleOwner, {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            })
            info.observe(viewLifecycleOwner, {
                binding.run {
                    nickName = it.name
                }
            })

            inputBasicInfo().run {
                binding.introCondidtionCv.visibility
                binding.introOutingCv.visibility
                binding.introCommentCv.visibility
            }


            BasicInfo.observe(viewLifecycleOwner, {
                binding.run {
                    pluspoint = it.bonusPoint
                    minuspoint = it.minusPoint
                    if (it.mealApplied == false) {
                        binding.mealAppliedTv.setImageDrawable(context?.let { it1 ->
                            ContextCompat.getDrawable(
                                it1, R.drawable.ic_baseline_radio_button_unchecked_24
                            )
                        })
                    }
                    stay = it.stayStaus
                }
            })

            //다시 실행 ?
            changeName().run {
                info.observe(viewLifecycleOwner, {
                    binding.run {
                        nickName = it.name
                    }
                })
            }
            binding.startAddStudentBtn.setOnClickListener {
                activity.let {
                    val dialog = MyPageAddStudentDialog()
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
                    val dialog = ChangeNameDialog()
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