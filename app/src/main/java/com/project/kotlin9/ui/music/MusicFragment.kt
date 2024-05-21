package com.project.kotlin9.ui.music

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
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.yourapp.MusicPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.project.kotlin9.R
import com.project.kotlin9.databinding.FragmentMusicBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import org.json.JSONException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit


class MusicFragment : Fragment() {
    private var _binding: FragmentMusicBinding? = null
    private val binding get() = _binding!!
    private var onopem = true;
   // private lateinit var mediaPlayer: MediaPlayer
    private var isokPlaying = false
    private var streamLink: String = "http://5.53.124.23:8000/ppr_90.mp3"


    private lateinit var localMusicRecyclerView: RecyclerView
    private var mediaPlayer: MediaPlayer? = null
    private lateinit var btnPlay: Button
    private lateinit var btnPause: Button
    private lateinit var btnStop: Button
    private var currentTrackPath: String? = null
    private val REQUEST_PERMISSION_CODE = 123
    private lateinit var player : AudioPlayer

    private val clientId = "6a670c0f"
    private lateinit var jamendoApi: JamendoApi
    private lateinit var trackAdapter: TrackAdapter
    private lateinit var mediaPlayer2: MediaPlayer
    private lateinit var requestQueue: RequestQueue

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_music, container, false)



        val viewPager = view.findViewById<androidx.viewpager2.widget.ViewPager2>(R.id.viewPager)
        val tabLayout = view.findViewById<TabLayout>(R.id.tabLayout)

        val adapter = MusicPagerAdapter(requireActivity())
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Локальная музыка"
                1 -> "Jamendo музыка"
                else -> null
            }
        }.attach()



//        localMusicRecyclerView = view.findViewById(R.id.localMusicRecyclerView)
//        localMusicRecyclerView.layoutManager = LinearLayoutManager(context)
//        btnPlay = view.findViewById(R.id.btnPlay)
//        btnPause = view.findViewById(R.id.btnPause)
//        btnStop = view.findViewById(R.id.btnStop)
//
//        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
//            != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(
//                requireActivity(),
//                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
//                REQUEST_PERMISSION_CODE
//            )
//        }


//        var songs = getAllAudio(requireContext())
//        player = AudioPlayer(requireContext())
//        player.initializeMediaPlayer()
//
//
//        localMusicRecyclerView.adapter = MusicAdapter(player, songs!!)
//
//        loadLocalMusic()
//
//        player.setAudioList(songs)
//
//
//
//        btnPlay.setOnClickListener {
//
//                ActivityCompat.requestPermissions(
//                    requireActivity(),
//                    arrayOf(Manifest.permission.READ_MEDIA_AUDIO),
//                    REQUEST_PERMISSION_CODE
//                )
//
//            player.play()
//        }
//
//        btnPause.setOnClickListener {
//            player.pause()
//        }
//
//        btnStop.setOnClickListener {
//            stopMusic()
//        }

//---------------------------------------------------------------
//        mediaPlayer = MediaPlayer()
//
//        val recyclerView: RecyclerView = localMusicRecyclerView
//        recyclerView.layoutManager = LinearLayoutManager(requireContext())
//
//        trackAdapter = TrackAdapter(emptyList()) { track ->
//            playTrack(track)
//        }
//        recyclerView.adapter = trackAdapter
//
//        requestQueue = Volley.newRequestQueue(requireContext())
//
//        loadTracks()



        return view




//        val root: View = binding.root
//        val playButton = binding.buttonplay
//        val pauseButton = binding.buttonpause
//        val upButton = binding.buttonup
//        val downButton = binding.buttondown
//        val stream = binding.editStream
//        val text = binding.currenttext
//        text.text = streamLink
//        playButton.setOnClickListener {
//            val streamUrl = stream.text.toString()
//            if (streamUrl.isEmpty() || "http://$streamUrl" == text.text) {
//                // Продолжить воспроизведение, если введенный текст пустой или совпадает с текущим потоком
//                if (onopem) {
//                    onopem = false
//                    Log.d("AAAAAAAAAAA", "onCreateView: First")
//                    playRadio(streamLink)
//                } else
//                    Log.d("AAAAAAAAAAA", "onCreateView: resume")
//                    resumeRadio()
//            } else {
//                Log.d("AAAAAAAAAAA", "onCreateView: notfirst")
//                stream.text = null
//                text.text = buildString {
//                    append("http://")
//                    append(streamUrl)
//                }
//                streamLink = "http://$streamUrl"
//                // Иначе начать проигрывание нового потока
//                playRadio(streamLink)
//            }
//        }
//        pauseButton.setOnClickListener {
//            pauseRadio()
//        }
//
//        upButton.setOnClickListener {
//            increaseVolume()
//        }
//
//        downButton.setOnClickListener {
//            decreaseVolume()
//        }
//
//        mediaPlayer = MediaPlayer().apply {
//            setAudioAttributes(
//                AudioAttributes.Builder()
//                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
//                    .build()
//            )
//
//            setOnPreparedListener {
//                // Музыка готова к воспроизведению
//
//                playButton.isEnabled = true
//                pauseButton.isEnabled = true
//                upButton.isEnabled = true
//                downButton.isEnabled = true
//
//            }
//            setOnErrorListener { _, what, _ ->
//                Log.d("a", "onCreateView: ERORRRRRRRRRRRRR")
//                // Произошла ошибка воспроизведения
////                isokPlaying = false
////                playButton.isEnabled = true
////                pauseButton.isEnabled = false
////                upButton.isEnabled = false
////                downButton.isEnabled = false
//                false
//            }
//        }
//        binding.buttontoRef.setOnClickListener {
//            findNavController().navigate(R.id.action_nav_music_to_refFragment)
//        }
//        mediaPlayer.setDataSource(streamLink)


    }

    private fun loadTracks() {
        val url = "https://api.jamendo.com/v3.0/tracks/?client_id=6a670c0f&format=json&limit=10"

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

                    trackAdapter = TrackAdapter(tracks) { track ->
                        playTrack(track)
                    }
                    localMusicRecyclerView.adapter = trackAdapter

                } catch (e: JSONException) {
                    e.printStackTrace()
                    Toast.makeText(requireContext(), "Error parsing JSON response", Toast.LENGTH_SHORT).show()
                }
            },
            { error ->
                Log.e("MusicActivity", "Error: ${error.message}")
                Toast.makeText(requireContext(), "Failed to load tracks", Toast.LENGTH_SHORT).show()
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
            mediaPlayer!!.reset()
            mediaPlayer!!.setDataSource(track.audio)
            mediaPlayer!!.prepare()
            mediaPlayer!!.start()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
    companion object {
        fun getAllAudio(context: Context): List<AudioData> {
            val audioList = mutableListOf<AudioData>()
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
                    val id = it.getLong(idColumn)
                    val title = it.getString(titleColumn)
                    val artist = it.getString(artistColumn)
                    val data = it.getString(dataColumn)

                    val audioData = AudioData(id, title, artist, data)
                    audioList.add(audioData)
                }
            }

            return audioList
        }
    }




    private fun loadLocalMusic() {
        val musicList = mutableListOf<String>()
        val projection = arrayOf(MediaStore.Audio.Media.TITLE, MediaStore.Audio.Media.DATA)
        val cursor = requireContext().contentResolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, projection, null, null, null)
        cursor?.use {
            while (it.moveToNext()) {
                val title = it.getString(it.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE))
                val data = it.getString(it.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA))
                musicList.add("$title::$data")
            }
        }
//        localMusicRecyclerView.adapter = MusicAdapter(player, musicList.map { it.split("::")[0] })
//        }
//        Log.d("tagg", musicList.toString())
    }

    private fun playMusic(path: String) {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer().apply {
                try {
                    setDataSource(path)
                    prepare()
                    start()
                } catch (e: IOException) {
                    e.printStackTrace()
                    Toast.makeText(requireContext(), "Unable to play music", Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            mediaPlayer?.start()
        }
    }

    private fun pauseMusic() {
        mediaPlayer?.pause()
    }

    private fun stopMusic() {
        mediaPlayer?.stop()
        mediaPlayer?.reset()
        mediaPlayer?.release()
        mediaPlayer = null
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
        mediaPlayer = null
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                loadLocalMusic()
            } else {
                Toast.makeText(requireContext(), "Permission denied", Toast.LENGTH_SHORT).show()
            }
        }
    }









//
//    private fun playRadio(streamUrl: String) {
//        mediaPlayer.apply {
//            reset()
//            setDataSource(streamUrl)
//            prepareAsync()
//            setOnPreparedListener {
//                // Музыка готова к воспроизведению
//                start()
//                isokPlaying = true
//            }
//            setOnErrorListener { _, what, _ ->
//                Log.d("a", "onCreateView: CONNNNNNNECT $what")
//                if(what==1){
//                    binding.currenttext.text="Не удалось подключиться"
//                    reset()
//                }
//                // Произошла ошибка воспроизведения
////                isokPlaying = false
////                playButton.isEnabled = true
////                pauseButton.isEnabled = false
////                upButton.isEnabled = false
////                downButton.isEnabled = false
//                false
//            }
//        }
//    }
//
//    private fun resumeRadio() {
//        if (!isokPlaying && streamLink != null) {
//            mediaPlayer.start()
//            isokPlaying = true
//        }
//    }
//
//    private fun pauseRadio() {
//        mediaPlayer.pause()
//        isokPlaying = false
//    }
//
//    private fun increaseVolume() {
//        val audioManager = requireContext().getSystemService(AUDIO_SERVICE) as AudioManager
//        audioManager.adjustVolume(AudioManager.ADJUST_RAISE, AudioManager.FLAG_PLAY_SOUND)
//    }
//
//    private fun decreaseVolume() {
//        val audioManager = requireContext().getSystemService(AUDIO_SERVICE) as AudioManager
//        audioManager.adjustVolume(AudioManager.ADJUST_LOWER, AudioManager.FLAG_PLAY_SOUND)
//    }

//
//    override fun onDestroy() {
//        super.onDestroy()
//        mediaPlayer.release() // Освобождаем ресурсы MediaPlayer
//    }

}