package eu.codeplumbers.agendagrx.data.repository

import androidx.annotation.WorkerThread
import eu.codeplumbers.agendagrx.data.model.Event
import eu.codeplumbers.agendagrx.data.model.EventDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class EventRepository @Inject constructor (private val eventDao: EventDao) {
    val getEvents: Flow<List<Event>> = eventDao.getEvents()

    @WorkerThread
    suspend fun insert(event:Event) = withContext(Dispatchers.IO){
        eventDao.insert(event)
    }
}