package com.dms.pmsandroid.presentation.base

import androidx.lifecycle.Observer

class EventObserver<T>(private val onEventUnHandledContent:(T)->Unit): Observer<Event<T>> {
    override fun onChanged(event: Event<T>?) {
        event?.getContentNotHandled()?.let {value->
            onEventUnHandledContent(value)
        }
    }
}