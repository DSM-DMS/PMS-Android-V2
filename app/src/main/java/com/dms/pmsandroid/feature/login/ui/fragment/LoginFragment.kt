package com.dms.pmsandroid.feature.login.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import com.dms.pmsandroid.R
import com.dms.pmsandroid.base.BaseFragment
import com.dms.pmsandroid.databinding.FragmentLoginBinding
import com.dms.pmsandroid.feature.login.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class LoginFragment : BaseFragment<FragmentLoginBinding>(R.layout.fragment_login) {

    override val vm: LoginViewModel by sharedViewModel()


    private var lastTimeBackPressed: Long = -1500

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (System.currentTimeMillis() - lastTimeBackPressed <= 1500) {
                    requireActivity().moveTaskToBack(true)
                    requireActivity().finish()
                    android.os.Process.killProcess(android.os.Process.myPid())
                }
                lastTimeBackPressed = System.currentTimeMillis()
                Toast.makeText(requireContext(), "뒤로가기 버튼을 한 번 더 누르면 종료됩니다", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }

    override fun observeEvent() {
        vm.run {
            toastMessage.observe(viewLifecycleOwner, {
                if(it!=null){
                    Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                }
            })
            userEmail.observe(viewLifecycleOwner, {
                vm.emailDone.value = !it.isNullOrBlank()
                checkDone()
            })
            userPassword.observe(viewLifecycleOwner, {
                vm.passwordDone.value = !it.isNullOrBlank()
                checkDone()
            })
        }
    }

    private fun checkDone() {
        vm.doneInput.value = vm.emailDone.value!! && vm.passwordDone.value!!
    }

}