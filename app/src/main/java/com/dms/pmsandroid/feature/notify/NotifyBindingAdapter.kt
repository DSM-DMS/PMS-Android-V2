package com.dms.pmsandroid.feature.notify

import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.dms.pmsandroid.R
import com.dms.pmsandroid.feature.notify.model.CommentModel
import com.dms.pmsandroid.feature.notify.ui.NoticeCommentLayout
import java.text.SimpleDateFormat
import java.util.*

object NotifyBindingAdapter {

    @JvmStatic
    @BindingAdapter("showReComments","commentId")
    fun showReComments(linearLayout: LinearLayout,reComments:HashMap<Int,List<CommentModel>?>,id:Int){
        if(!reComments[id].isNullOrEmpty()){
            for(reComment in reComments[id]!!){
                val layout = NoticeCommentLayout(linearLayout.context)
                layout.findViewById<TextView>(R.id.re_comment_body_tv).text = reComment.body
                layout.findViewById<TextView>(R.id.re_comment_writer_tv).text = reComment.user.name
                linearLayout.addView(layout)
            }
        }
    }

    @JvmStatic
    @BindingAdapter("showTimeAdapter")
    fun timeAdapter(textView: TextView, time:Date?){
        if(time!=null){
            val dateFormat=SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSz", Locale.KOREA)
            val currentDateTime= System.currentTimeMillis()
            val date=Date(currentDateTime)
            val currentTime=dateFormat.format(date)
            val getTime=dateFormat.format(time)
            val longCurrentTime=dateFormat.parse(currentTime).time
            val longGetTime=dateFormat.parse(getTime).time
            val diff=(longCurrentTime-longGetTime)/1000
            val dayDiff=(diff/86400)
            if(dayDiff<0||dayDiff>=31){
                val dateFormat= SimpleDateFormat("yyyy-MM-dd",Locale.KOREA)
                textView.text=dateFormat.format(time)
            }else{
                if(dayDiff<=0){
                    when(diff) {
                        in 0..60->
                            textView.text = "방금"
                        in 61..120->textView.text="1분전"
                        in 121..3600->
                            textView.text="${diff/60}분 전"
                        in 3601..7200->textView.text="1시간 전"
                        else ->textView.text="${diff/3600}시간 전"
                    }
                }else{
                    when(dayDiff){
                        1.toLong() ->textView.text="어제"
                        in 2..6->textView.text="${dayDiff}일 전"
                        else -> textView.text="${dayDiff/7}주 전"
                    }
                }
            }
        }

    }
}