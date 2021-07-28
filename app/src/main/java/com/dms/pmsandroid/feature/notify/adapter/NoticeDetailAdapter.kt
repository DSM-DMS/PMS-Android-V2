package com.dms.pmsandroid.feature.notify.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dms.pmsandroid.R
import com.dms.pmsandroid.databinding.ItemCommentNoticeBinding
import com.dms.pmsandroid.databinding.ItemNoticeDetailHeaderBinding
import com.dms.pmsandroid.feature.notify.model.CommentModel
import com.dms.pmsandroid.feature.notify.ui.NoticeCommentLayout
import com.dms.pmsandroid.feature.notify.viewmodel.NoticeDetailViewModel

class NoticeDetailAdapter(private val viewModel: NoticeDetailViewModel) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var comments = ArrayList<CommentModel>()
    private var reComments = HashMap<Int,List<CommentModel>?>()
    private val _HEADER = 0
    private val _COMMENT = 1

    inner class NoticeDetailHeaderViewHolder(private val binding: ItemNoticeDetailHeaderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            binding.vm = viewModel
        }
    }

    inner class NoticeDetailCommentViewHolder(private val binding: ItemCommentNoticeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            comments[position].let { comment ->
                binding.commentBodyTv.text = comment.body
                binding.commentWriterTv.text = comment.user.name
                binding.date = comment.uploadDate
                if(!reComments[comment.id].isNullOrEmpty()){
                    for(reComment in reComments[comment.id]!!){
                        val layout = NoticeCommentLayout(binding.commentLl.context)
                        layout.findViewById<TextView>(R.id.re_comment_body_tv).text = reComment.body
                        layout.findViewById<TextView>(R.id.re_comment_writer_tv).text = reComment.user.name
                        binding.commentLl.addView(layout)
                    }
                }
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

    fun setItems(comments:List<CommentModel>){
        this.comments = comments as ArrayList<CommentModel>
        notifyDataSetChanged()
    }

    fun setReComments(reComments:HashMap<Int,List<CommentModel>?>){
        this.reComments = reComments
        notifyDataSetChanged()
    }
}