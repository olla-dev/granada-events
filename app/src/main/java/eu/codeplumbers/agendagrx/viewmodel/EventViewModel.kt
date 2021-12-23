package eu.codeplumbers.agendagrx.viewmodel

import androidx.lifecycle.*
import dagger.hilt.android.scopes.ViewModelScoped
import eu.codeplumbers.agendagrx.data.model.Event
import eu.codeplumbers.agendagrx.data.repository.EventRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

@ViewModelScoped
class EventViewModel(private val eventRepository: EventRepository) : ViewModel(){
    val getEvents: LiveData<List<Event>>
        get() =
        eventRepository.getEvents.flowOn(Dispatchers.Main)
            .asLiveData(context = viewModelScope.coroutineContext)

    suspend fun insert(event: Event){
        viewModelScope.launch {
            eventRepository.insert(event)
        }
    }
}