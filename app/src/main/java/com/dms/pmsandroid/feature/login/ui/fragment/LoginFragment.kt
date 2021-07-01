package com.dms.pmsandroid.feature.login.ui.fragment

import android.widget.Toast
import com.dms.pmsandroid.R
import com.dms.pmsandroid.base.BaseFragment
import com.dms.pmsandroid.databinding.FragmentLoginBinding
import com.dms.pmsandroid.feature.login.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class LoginFragment : BaseFragment<FragmentLoginBinding>(R.layout.fragment_login) {

    override val vm: LoginViewModel by sharedViewModel()

    override fun observeEvent() {
        vm.toastMessage.observe(viewLifecycleOwner, {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        })
        vm.userEmail.observe(viewLifecycleOwner, {
            vm.emailDone.value = !it.isNullOrBlank()
            checkDone()
        })
        vm.userPassword.observe(viewLifecycleOwner,{
            vm.passwordDone.value = !it.isNullOrBlank()
            checkDone()
        })
    }

    private fun checkDone(){
        vm.doneInput.value = vm.emailDone.value!!&&vm.passwordDone.value!!
    }

}