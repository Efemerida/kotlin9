package com.project.kotlin9.ui.music

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface JamendoApi {
    @GET("tracks")
    suspend fun getTracks(
        @Query("client_id") clientId: String,
        @Query("format") format: String = "json",
        @Query("limit") limit: Int = 10
    ): Response<TracksResponse>
}

data class TracksResponse(
    val results: List<Track>
)

data class Track(
    val id: String,
    val name: String,
    val artist_name: String,
    val audio: String
)
