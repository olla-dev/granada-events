package eu.codeplumbers.agendagrx.data.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class EventResponse(
    @SerializedName("title")
    @Expose
    val title:String,

    @SerializedName("image")
    @Expose
    val image:String,

    @SerializedName("date")
    @Expose
    val date:String,

    @SerializedName("url")
    @Expose
    val url:String,

    @SerializedName("location")
    @Expose
    val location:String
)

