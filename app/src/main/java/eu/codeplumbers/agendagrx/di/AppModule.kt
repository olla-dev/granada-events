package eu.codeplumbers.agendagrx.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import eu.codeplumbers.agendagrx.AgendaApplication
import eu.codeplumbers.agendagrx.data.AgendaDatabase
import eu.codeplumbers.agendagrx.data.model.EventDao
import eu.codeplumbers.agendagrx.data.repository.EventRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule  {

    @Provides
    fun providesEventDao(agendaDatabase: AgendaDatabase) : EventDao = agendaDatabase.eventDao()

    @Provides
    @Singleton
    fun providesAgendaDatabase(@ApplicationContext context: Context): AgendaDatabase
            = Room.databaseBuilder(context, AgendaDatabase::class.java,"AgendaGRX_DB").build()

    @Provides
    fun providesEventRepository(eventDao: EventDao) : EventRepository
            = EventRepository(eventDao)
}