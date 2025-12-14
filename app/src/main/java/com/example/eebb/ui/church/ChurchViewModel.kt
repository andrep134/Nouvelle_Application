package com.example.eebb.ui.church

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.eebb.ui.model.Ministry
import com.example.eebb.ui.model.SampleContent

class ChurchViewModel : ViewModel() {
    private val _ministries = MutableLiveData<List<Ministry>>(SampleContent.ministries)
    val ministries: LiveData<List<Ministry>> = _ministries
}
