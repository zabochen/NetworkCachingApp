package ua.ck.networkcachingapp.presentation.internal

import androidx.annotation.MainThread
import androidx.collection.ArraySet
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer

/**
 * https://github.com/hadilq/LiveEvent
 *
 * Live Event:
 * This library holds a class to handle single live events in Android MVVM architectural pattern.
 * This class is extended form LiveData class, from androidx.lifecycle:lifecycle-extensions library,
 * to propagate the data as an event, which means it emits data just once.
 *
 * Usage:
 * This source has a sample app where you can find LiveEventViewModel in it, in which the LiveEvent class is used as follows.
 *
 *  class LiveEventViewModel : ViewModel() {
        private val clickedState = LiveEvent<String>()
        val stateUpdated: LiveData<String> = clickedState

        fun clicked() {
            clickedState.value = ...
        }
    }
 */

class LiveEvent<T> : MediatorLiveData<T>() {

    private val observers = ArraySet<ObserverWrapper<in T>>()

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        val wrapper = ObserverWrapper(observer)
        observers.add(wrapper)
        super.observe(owner, wrapper)
    }

    @MainThread
    override fun removeObserver(observer: Observer<in T>) {
        if (observers.remove(observer)) {
            super.removeObserver(observer)
            return
        }
        val iterator = observers.iterator()
        while (iterator.hasNext()) {
            val wrapper = iterator.next()
            if (wrapper.observer == observer) {
                iterator.remove()
                super.removeObserver(wrapper)
                break
            }
        }
    }

    @MainThread
    override fun setValue(t: T?) {
        observers.forEach { it.newValue() }
        super.setValue(t)
    }

    private class ObserverWrapper<T>(val observer: Observer<T>) : Observer<T> {

        private var pending = false

        override fun onChanged(t: T?) {
            if (pending) {
                pending = false
                observer.onChanged(t)
            }
        }

        fun newValue() {
            pending = true
        }
    }
}