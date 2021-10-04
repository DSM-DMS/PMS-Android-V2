package com.dms.pmsandroid.feature.login.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import com.dms.pmsandroid.R
import com.dms.pmsandroid.base.BaseFragment
import com.dms.pmsandroid.databinding.FragmentFindPasswordBinding
import com.dms.pmsandroid.feature.login.viewmodel.FindPassWordViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class FindPasswordFragment :
    BaseFragment<FragmentFindPasswordBinding>(R.layout.fragment_find_password) {

    override val vm: FindPassWordViewModel by sharedViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.backBtn.setOnClickListener {
            finishFindPassword()
        }

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                finishFindPassword()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }

    private fun finishFindPassword() {
        val fragment = requireActivity().supportFragmentManager
        val fragmentManager = fragment.beginTransaction()
        fragmentManager.run {
            setCustomAnimations(R.anim.silde_in_up, R.anim.slide_out_down)
            replace(R.id.login_container, LoginFragment()).commit()
        }
        vm.clear()
    }

    override fun observeEvent() {
        vm.run {
            email.observe(viewLifecycleOwner, {
                vm.doneInput.value = it.contains('@') && it.contains('.')
            })
            toastMessage.observe(viewLifecycleOwner, {
                if(it!=null){
                    Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                }
            })
            doneResetPassword.observe(viewLifecycleOwner, {
                finishFindPassword()
            })
        }
    }
}