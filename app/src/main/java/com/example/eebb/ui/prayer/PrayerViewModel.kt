package com.example.eebb.ui.prayer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PrayerViewModel : ViewModel() {
    private val _submitted = MutableLiveData(false)
    val submitted: LiveData<Boolean> = _submitted

    fun submit() {
        _submitted.value = true
    }

    fun reset() {
        _submitted.value = false
    }
}
