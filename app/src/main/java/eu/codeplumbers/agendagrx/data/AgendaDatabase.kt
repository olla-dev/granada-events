package eu.codeplumbers.agendagrx.data

import androidx.room.Database
import androidx.room.RoomDatabase
import eu.codeplumbers.agendagrx.data.model.Event
import eu.codeplumbers.agendagrx.data.model.EventDao

@Database(entities = [Event::class], version = 1)
abstract class AgendaDatabase : RoomDatabase() {
    abstract fun eventDao(): EventDao
}