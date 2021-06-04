package com.dms.pmsandroid.feature.login.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import com.dms.pmsandroid.R
import com.dms.pmsandroid.base.BaseFragment
import com.dms.pmsandroid.databinding.FragmentRegisterBinding
import com.dms.pmsandroid.feature.login.viewmodel.RegisterViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterFragment : BaseFragment<FragmentRegisterBinding>(R.layout.fragment_register) {

    private val vm: RegisterViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        binding.vm = vm
        observeToast()
        observeInputData()
        observeFinish()
    }

    private fun observeInputData() {
        vm.userEmail.observe(viewLifecycleOwner, Observer {
            vm.nEmptyEmail.value = !it.isNullOrBlank()
            checkDoneRegister()
        })

        vm.userName.observe(viewLifecycleOwner, Observer {
            vm.nEmptyName.value = !it.isNullOrBlank()
            checkDoneRegister()
        })

        vm.userPassword.observe(viewLifecycleOwner, Observer {
            vm.nEmptyPassword.value = !it.isNullOrBlank() && it.length > 7 && it.length < 21
            passwordErrorMessage()
            checkDoneRegister()
        })

        vm.userPasswordCheck.observe(viewLifecycleOwner, Observer {
            vm.samePassword.value = vm.userPassword.value == vm.userPasswordCheck.value
            checkPasswordError()
            checkDoneRegister()
        })
    }

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

    private fun observeToast() {
        vm.toastMessage.observe(viewLifecycleOwner, Observer { message ->
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        })
    }

    private fun observeFinish(){
        vm.finishRegister.observe(viewLifecycleOwner, Observer {
            if(it){
                finishRegister()
            }
        })
    }

    private fun finishRegister(){
        val fragment = activity!!.supportFragmentManager
        val fragmentManager = fragment.beginTransaction()
        fragmentManager.replace(R.id.login_container,LoginFragment()).commit()
    }

}