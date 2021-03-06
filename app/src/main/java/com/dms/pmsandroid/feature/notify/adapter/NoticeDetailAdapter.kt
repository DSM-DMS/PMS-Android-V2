package com.dms.pmsandroid.feature.notify.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dms.pmsandroid.databinding.ItemCommentNoticeBinding
import com.dms.pmsandroid.databinding.ItemNoticeDetailHeaderBinding
import com.dms.pmsandroid.feature.notify.model.CommentModel
import com.dms.pmsandroid.feature.notify.ui.activity.NoticeDetailActivity
import com.dms.pmsandroid.feature.notify.viewmodel.NoticeDetailViewModel
import com.jakewharton.rxbinding4.view.clicks
import java.util.concurrent.TimeUnit

class NoticeDetailAdapter(private val viewModel: NoticeDetailViewModel,private val activity:NoticeDetailActivity) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var comments = ArrayList<CommentModel>()
    private val _HEADER = 0
    private val _COMMENT = 1

    inner class NoticeDetailHeaderViewHolder(private val binding: ItemNoticeDetailHeaderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            binding.vm = viewModel
            binding.noticeIndFileIv.clicks().debounce(200,TimeUnit.MILLISECONDS).subscribe {
                activity.showContent()
            }
        }
    }

    inner class NoticeDetailCommentViewHolder(private val binding: ItemCommentNoticeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.vm = viewModel
            comments[position].let { comment ->
                binding.id = comment.id
                binding.commentBodyTv.text = comment.body
                binding.commentWriterTv.text = comment.user?.name?:"익명의 사용자"
                binding.date = comment.uploadDate
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            0 -> {
                val binding = ItemNoticeDetailHeaderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                NoticeDetailHeaderViewHolder(binding)
            }
            else -> {
                val binding = ItemCommentNoticeBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                NoticeDetailCommentViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (position == 0) {
            (holder as NoticeDetailHeaderViewHolder).bind()
        } else {
            (holder as NoticeDetailCommentViewHolder).bind(position - 1)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            _HEADER
        } else {
            _COMMENT
        }
    }

    override fun getItemCount(): Int = comments.size + 1

    fun setItems(comments: List<CommentModel>) {
        this.comments = comments as ArrayList<CommentModel>
        notifyDataSetChanged()
    }
}