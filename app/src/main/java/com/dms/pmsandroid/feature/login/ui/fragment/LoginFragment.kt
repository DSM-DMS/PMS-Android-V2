package com.dms.pmsandroid.feature.login.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import com.dms.pmsandroid.R
import com.dms.pmsandroid.base.BaseFragment
import com.dms.pmsandroid.databinding.FragmentLoginBinding
import com.dms.pmsandroid.feature.login.viewmodel.LoginViewModel
import com.dms.pmsandroid.ui.MainViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class LoginFragment : BaseFragment<FragmentLoginBinding>(R.layout.fragment_login) {

    private val vm : LoginViewModel by sharedViewModel()
    private val mainVm : MainViewModel by sharedViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        binding.vm = vm
        observeInput()
        observeToast()
        doneLogin()
    }

    private fun doneLogin(){
        vm.doneLogin.observe(viewLifecycleOwner, Observer {
            mainVm.doneToken.value = it
        })
    }

    private fun observeInput(){
        vm.userEmail.observe(viewLifecycleOwner, Observer {
            vm.emailDone.value = !it.isNullOrBlank()
            checkDone()
        })
        vm.userPassword.observe(viewLifecycleOwner, Observer{
            vm.passwordDone.value = !it.isNullOrBlank()
            checkDone()
        })

    }

    private fun checkDone(){
        vm.doneInput.value = vm.emailDone.value!!&&vm.passwordDone.value!!
    }

    private fun observeToast(){
        vm.toastMessage.observe(viewLifecycleOwner, Observer {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        })
    }


}