package com.dms.pmsandroid.base

open class Event<out T>(private val content: T) {
    var hasBeenHandled = false
        private set

    fun getContentNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    fun peekContent(): T = content
}