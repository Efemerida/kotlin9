package com.example.yourapp.fragments

import AudioPlayer
import MusicAdapter
import TrackAdapter
import android.Manifest
import android.content.ContentResolver
import android.content.Context
import android.content.pm.PackageManager
import android.database.Cursor
import android.media.MediaPlayer
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.project.kotlin9.R
import com.project.kotlin9.ui.music.AudioData
import com.project.kotlin9.ui.music.Track
import java.io.IOException

class LocalMusicFragment : Fragment() {

    private lateinit var trackAdapter: TrackAdapter
    private lateinit var mediaPlayer: MediaPlayer
    private val REQUEST_PERMISSION_CODE = 123


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_local_music, container, false)



        var songs = getAllAudio(requireContext())
        Log.d("songs", songs.toString())

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

        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(Manifest.permission.READ_MEDIA_AUDIO),
            REQUEST_PERMISSION_CODE)


        mediaPlayer = MediaPlayer()

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerViewLocal)
        recyclerView.layoutManager = LinearLayoutManager(context)

        trackAdapter = TrackAdapter(songs) { track ->
            playTrack(track)
        }
        recyclerView.adapter = trackAdapter



        return view
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

    companion object {
        fun getAllAudio(context: Context): List<Track> {
            val audioList = mutableListOf<Track>()
            val contentResolver: ContentResolver = context.contentResolver
            val uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
            val projection = arrayOf(
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.DATA
            )
            val sortOrder = "${MediaStore.Audio.Media.TITLE} ASC"
            val cursor: Cursor? = contentResolver.query(uri, projection, null, null, sortOrder)

            cursor?.use {
                val idColumn = it.getColumnIndexOrThrow(MediaStore.Audio.Media._ID)
                val titleColumn = it.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE)
                val artistColumn = it.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)
                val dataColumn = it.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA)

                while (it.moveToNext()) {
                    val id = it.getString(idColumn)
                    val title = it.getString(titleColumn)
                    val artist = it.getString(artistColumn)
                    val data = it.getString(dataColumn)

                    val audioData = Track(id, title, artist, data)
                    audioList.add(audioData)
                }
            }

            return audioList
        }
        }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }

}
