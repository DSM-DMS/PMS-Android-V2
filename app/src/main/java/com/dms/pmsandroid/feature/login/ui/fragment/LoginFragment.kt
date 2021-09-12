package com.dms.pmsandroid.feature.login.ui.fragment

import android.widget.Toast
import com.dms.pmsandroid.R
import com.dms.pmsandroid.base.BaseFragment
import com.dms.pmsandroid.databinding.FragmentLoginBinding
import com.dms.pmsandroid.feature.login.viewmodel.LoginViewModel
import com.dms.pmsandroid.ui.MainViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class LoginFragment : BaseFragment<FragmentLoginBinding>(R.layout.fragment_login) {

    override val vm: LoginViewModel by sharedViewModel()

    override fun observeEvent() {
        vm.run {
            toastMessage.observe(viewLifecycleOwner, {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
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