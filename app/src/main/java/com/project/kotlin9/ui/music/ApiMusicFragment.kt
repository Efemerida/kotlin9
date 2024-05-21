package com.example.yourapp.fragments

import TrackAdapter
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.project.kotlin9.R
import com.project.kotlin9.ui.music.Track

import org.json.JSONException
import java.io.IOException

class ApiMusicFragment : Fragment() {

    private lateinit var requestQueue: RequestQueue
    private lateinit var trackAdapter: TrackAdapter
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var loadBar: ProgressBar

    private val clientId = "YOUR_JAMENDO_CLIENT_ID" // Вставьте сюда ваш API ключ

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_api_music, container, false)

        loadBar = view.findViewById<ProgressBar>(R.id.progressBarApi)

        // Найти кнопки управления
        val playButton = view.findViewById<Button>(R.id.playButton)
        val pauseButton = view.findViewById<Button>(R.id.pauseButton)
        val stopButton = view.findViewById<Button>(R.id.stopButton)

        // Добавить обработчики кнопок
        playButton.setOnClickListener {
            mediaPlayer.start()
        }

        pauseButton.setOnClickListener {
            mediaPlayer.pause()
        }

        stopButton.setOnClickListener {
            mediaPlayer.stop()
        }

        mediaPlayer = MediaPlayer()

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerViewApi)
        recyclerView.layoutManager = LinearLayoutManager(context)

        trackAdapter = TrackAdapter(emptyList()) { track ->
            playTrack(track)
        }
        recyclerView.adapter = trackAdapter

        requestQueue = Volley.newRequestQueue(requireContext())

        loadTracks()

        return view
    }

    private fun loadTracks() {
        loadBar.visibility = View.VISIBLE

        val url = "https://api.jamendo.com/v3.0/tracks/?client_id=6a670c0f&format=json"

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                try {
                    val results = response.getJSONArray("results")
                    val tracks = mutableListOf<Track>()

                    for (i in 0 until results.length()) {
                        val trackJson = results.getJSONObject(i)
                        val track = Track(
                            id = trackJson.getString("id"),
                            name = trackJson.getString("name"),
                            artist_name = trackJson.getString("artist_name"),
                            audio = trackJson.getString("audio")
                        )
                        tracks.add(track)
                    }
                    loadBar.visibility = View.GONE
                    trackAdapter = TrackAdapter(tracks) { track ->
                        playTrack(track)
                    }
                    view?.findViewById<RecyclerView>(R.id.recyclerViewApi)?.adapter = trackAdapter

                } catch (e: JSONException) {
                    e.printStackTrace()
                    Toast.makeText(context, "Error parsing JSON response", Toast.LENGTH_SHORT).show()
                }
            },
            { error ->
                Log.e("ApiMusicFragment", "Error: ${error.message}")
                Toast.makeText(context, "Failed to load tracks", Toast.LENGTH_SHORT).show()
            }
        )

        // Установка времени таймаута
        jsonObjectRequest.retryPolicy = DefaultRetryPolicy(
            60000, // Время ожидания в миллисекундах
            DefaultRetryPolicy.DEFAULT_MAX_RETRIES, // Количество повторных попыток
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        )

        requestQueue.add(jsonObjectRequest)
    }

    private fun playTrack(track: Track) {
        try {
            mediaPlayer.reset()
            mediaPlayer.setDataSource(track.audio)
            mediaPlayer.prepare()
            mediaPlayer.start()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }
}
