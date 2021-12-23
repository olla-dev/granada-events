package eu.codeplumbers.agendagrx.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "events")
data class Event(
    @PrimaryKey(autoGenerate = true) val id:Long,
    val title:String,
    val image:String,
    val date:String,
    val url:String,
    val location:String
)