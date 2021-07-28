package com.dms.pmsandroid.feature.notify.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dms.pmsandroid.R
import com.dms.pmsandroid.base.BaseFragment
import com.dms.pmsandroid.databinding.FragmentInNoticeBinding
import com.dms.pmsandroid.feature.notify.adapter.NoticeAdapter
import com.dms.pmsandroid.feature.notify.viewmodel.NotifyViewModel
import com.dms.pmsandroid.ui.MainActivity
import com.jakewharton.rxbinding4.widget.textChanges
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.util.concurrent.TimeUnit

class InNoticeFragment : BaseFragment<FragmentInNoticeBinding>(R.layout.fragment_in_notice) {
    override val vm: NotifyViewModel by sharedViewModel()

    private val noticeAdapter: NoticeAdapter by lazy {
        NoticeAdapter(vm)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val noticeLayoutManager = LinearLayoutManager(binding.noticeRv.context)
        noticeLayoutManager.orientation = RecyclerView.VERTICAL
        binding.noticeRv.run {
            adapter = noticeAdapter
            layoutManager = noticeLayoutManager
        }

        vm.getNoticeList(0)
    }

    override fun observeEvent() {
        vm.noticeList.observe(viewLifecycleOwner, {
            noticeAdapter.setItems(it)
        })
        vm.clickedNoticeId.observe(viewLifecycleOwner, {
            (activity as MainActivity).startNoticeDetail(it, vm.clickedNoticeTitle)
        })
        binding.noticeEt.textChanges().debounce(500, TimeUnit.MILLISECONDS).map { it.toString() }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                if (it.isNotBlank()) {
                    Log.d("etError", "no $it")
                    vm.searchNotice(it.toString())
                    binding.noticeLl.visibility = View.GONE
                } else {
                    vm.resetNoticePage()
                    vm.getNoticeList(0)
                    binding.noticeLl.visibility = View.VISIBLE
                }
            }
    }
}