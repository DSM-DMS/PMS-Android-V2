package com.dms.pmsandroid.data.local

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

class SharedPreferenceStorage (private val context: Context){
    private lateinit var pref:SharedPreferences

    fun getInfo(content: String?): String{
        if(pref == null)pref = context.getSharedPreferences(content,MODE_PRIVATE)
        return if (content == "pms"){
            "Bearer " + pref.getString(content,"")
        } else
            pref.getString(content, "").toString()
    }

    fun saveInfo(info: String, content: String){
        if(pref == null) pref = context.getSharedPreferences(content, MODE_PRIVATE)
        val editor:SharedPreferences.Editor = pref!!.edit()
        editor.putString(content,info)
        editor.apply()
    }

    fun clearToken(content: String){
        if(pref == null) pref = context.getSharedPreferences(content, MODE_PRIVATE)
        pref.edit().remove(content).apply()
    }
}