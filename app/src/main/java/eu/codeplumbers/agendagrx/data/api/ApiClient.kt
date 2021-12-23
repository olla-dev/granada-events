package eu.codeplumbers.agendagrx.data.api

import eu.codeplumbers.agendagrx.utils.Constants
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiClient {
    @GET("events.json")
    suspend fun getEvents() : Response<List<EventResponse>>

    companion object {
        var retrofitService: ApiClient? = null
        fun getInstance() : ApiClient {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(Constants.eventUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(ApiClient::class.java)
            }
            return retrofitService!!
        }

    }
}