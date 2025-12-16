package com.example.eebb.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.eebb.ui.model.SampleContent

class HomeViewModel : ViewModel() {
    private val _heroEvent = MutableLiveData(SampleContent.featuredEvent)
    val heroEvent: LiveData<com.example.eebb.ui.model.Event> = _heroEvent

    private val _sermons = MutableLiveData(SampleContent.sermons)
    val sermons: LiveData<List<com.example.eebb.ui.model.Sermon>> = _sermons

    private val _highlights = MutableLiveData(SampleContent.highlights)
    val highlights: LiveData<List<com.example.eebb.ui.model.Highlight>> = _highlights

    private val _upcomingEvents = MutableLiveData(SampleContent.upcomingEvents)
    val upcomingEvents: LiveData<List<com.example.eebb.ui.model.Event>> = _upcomingEvents

    val verse = SampleContent.heroVerse
}
