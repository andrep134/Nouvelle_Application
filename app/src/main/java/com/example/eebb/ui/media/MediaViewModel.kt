package com.example.eebb.ui.media

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.eebb.ui.model.SampleContent
import com.example.eebb.ui.model.Sermon

class MediaViewModel : ViewModel() {
    private val _sermons = MutableLiveData<List<Sermon>>(SampleContent.sermons)
    val sermons: LiveData<List<Sermon>> = _sermons
}
