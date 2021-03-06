package com.dms.pmsandroid.feature.notify

import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.dms.pmsandroid.R
import com.dms.pmsandroid.feature.notify.model.CommentModel
import com.dms.pmsandroid.feature.notify.ui.NoticeCommentLayout
import com.dms.pmsandroid.feature.notify.ui.activity.NoticeDetailActivity.Companion.doneInput
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.HashMap

object NotifyBindingAdapter {

    @JvmStatic
    @BindingAdapter("showReComments", "commentId")
    fun showReComments(
        linearLayout: LinearLayout,
        showReComments: HashMap<Int, List<CommentModel>?>,
        commentId: Int
    ) {
        if (!showReComments.isNullOrEmpty()) {
            if (!showReComments[commentId].isNullOrEmpty()) {
                for (reComment in showReComments[commentId]!!) {
                    val layout = NoticeCommentLayout(linearLayout.context)
                    layout.findViewById<TextView>(R.id.re_comment_body_tv).text = reComment.body
                    layout.findViewById<TextView>(R.id.re_comment_writer_tv).text =
                        reComment.user?.name ?: "익명의 사용자"
                    linearLayout.addView(layout)
                    doneInput[commentId] = true
                }
            }
        }
    }

    @JvmStatic
    @BindingAdapter("showTimeAdapter")
    fun timeAdapter(textView: TextView, time: Date?) {
        if (time != null) {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSz", Locale.KOREA)

            val longCurrentTime = System.currentTimeMillis()

            val getTime = dateFormat.format(time)
            val longGetTime = dateFormat.parse(getTime).time

            val diff = (longCurrentTime - longGetTime) / 1000
            val dayDiff = (diff / 86400)

            if (dayDiff < 0 || dayDiff >= 31) {
                val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.KOREA)
                textView.text = dateFormat.format(time)
            } else {
                if (dayDiff <= 0) {
                    when (diff) {

                        in -50000..60 ->
                            textView.text = "방금전"
                        in 61..120 -> textView.text = "1분전"
                        in 121..3600 ->
                            textView.text = "${diff / 60}분 전"
                        in 3601..7200 -> textView.text = "1시간 전"
                        else -> textView.text = "${diff / 3600}시간 전"
                    }
                } else {
                    when (dayDiff) {
                        1.toLong() -> textView.text = "어제"
                        in 2..6 -> textView.text = "${dayDiff}일 전"
                        else -> textView.text = "${dayDiff / 7}주 전"
                    }
                }
            }
        }

    }
}