package com.dms.pmsandroid.feature.login.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.dms.pmsandroid.R
import com.dms.pmsandroid.base.BaseFragment
import com.dms.pmsandroid.databinding.FragmentLoginBinding
import com.dms.pmsandroid.feature.login.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : BaseFragment<FragmentLoginBinding>(R.layout.fragment_login) {

    private val vm : LoginViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        binding.vm = vm
        observeInput()
        observeToast()
    }

    private fun observeInput(){
        vm.userEmail.observe(viewLifecycleOwner, Observer {
            vm.emailDone.value = !it.isNullOrBlank()
        })
        vm.userPassword.observe(viewLifecycleOwner, Observer{
            vm.passwordDone.value = !it.isNullOrBlank()
        })
        vm.doneInput.value = vm.emailDone.value!!&&vm.passwordDone.value!!
    }

    private fun observeToast(){
        vm.toastMessage.observe(viewLifecycleOwner, Observer {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        })
    }


}