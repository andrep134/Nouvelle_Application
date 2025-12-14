package com.example.eebb.ui.calendar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.eebb.ui.model.Event
import com.example.eebb.ui.model.SampleContent

class CalendarViewModel : ViewModel() {
    private val _events = MutableLiveData<List<Event>>(SampleContent.upcomingEvents)
    val events: LiveData<List<Event>> = _events
}
