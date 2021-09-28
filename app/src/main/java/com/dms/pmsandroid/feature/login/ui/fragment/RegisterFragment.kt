package com.dms.pmsandroid.feature.login.ui.fragment

import android.widget.Toast
import com.dms.pmsandroid.R
import com.dms.pmsandroid.base.BaseFragment
import com.dms.pmsandroid.databinding.FragmentRegisterBinding
import com.dms.pmsandroid.feature.login.ui.WelcomeDialog
import com.dms.pmsandroid.feature.login.viewmodel.RegisterViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterFragment : BaseFragment<FragmentRegisterBinding>(R.layout.fragment_register) {

    override val vm: RegisterViewModel by viewModel()

    private fun passwordErrorMessage() {
        if (vm.nEmptyPassword.value!!) {
            binding.makePasswordLayout.error = null
        } else {
            binding.makePasswordLayout.error = "8~20자리 사이의 비밀번호를 입력해주세요"
        }
    }

    private fun checkPasswordError() {
        if (vm.samePassword.value!!) {
            binding.checkPasswordLayout.error = null
        } else {
            binding.checkPasswordLayout.error = "비밀번호가 다릅니다"
        }
    }

    private fun checkDoneRegister() {
        vm.doneInput.value =
            vm.nEmptyEmail.value!! && vm.nEmptyName.value!! && vm.nEmptyPassword.value!! && vm.samePassword.value!!
    }

    private fun doneRegister() {
        val fragment = requireActivity().supportFragmentManager
        val fragmentManager = fragment.beginTransaction()
        fragmentManager.setCustomAnimations(R.anim.silde_in_up, R.anim.slide_out_down)
        fragmentManager.replace(R.id.login_container, LoginFragment()).commit()
    }

    override fun observeEvent() {
        vm.run {
            finishRegister.observe(viewLifecycleOwner, {
                doneRegister()

            })
            doneRegister.observe(viewLifecycleOwner, {
                WelcomeDialog().show(requireActivity().supportFragmentManager, "welcomeDialog")
                doneRegister()
            })
            toastMessage.observe(viewLifecycleOwner, { message ->
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            })
            userEmail.observe(viewLifecycleOwner, {
                vm.nEmptyEmail.value = !it.isNullOrBlank()
                checkDoneRegister()
            })
            userName.observe(viewLifecycleOwner, {
                vm.nEmptyName.value = !it.isNullOrBlank()
                checkDoneRegister()
            })
            userPassword.observe(viewLifecycleOwner, {
                vm.nEmptyPassword.value = !it.isNullOrBlank() && it.length > 7 && it.length < 21
                passwordErrorMessage()
                checkDoneRegister()
            })
            userPasswordCheck.observe(viewLifecycleOwner, {
                vm.samePassword.value = vm.userPassword.value == vm.userPasswordCheck.value
                checkPasswordError()
                checkDoneRegister()
            })
        }
    }
}