import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import android.util.Log
import com.project.kotlin9.ui.music.AudioData
import java.io.IOException

class AudioPlayer(private val context: Context) {
    private var mediaPlayer: MediaPlayer? = null
    private var audioList: List<AudioData>? = null
    private var currentTrackIndex = 0

    fun setAudioList(audioList: List<AudioData>) {
        this.audioList = audioList
    }

    fun play() {
        if (mediaPlayer == null) {
            initializeMediaPlayer()
        }
        mediaPlayer?.start()
    }

    fun pause() {
        mediaPlayer?.pause()
    }

    fun stop() {
        mediaPlayer?.stop()
        mediaPlayer?.reset()
    }

    fun release() {
        mediaPlayer?.release()
        mediaPlayer = null
    }

    fun next() {
        if (audioList != null && audioList!!.isNotEmpty()) {
            currentTrackIndex = (currentTrackIndex + 1) % audioList!!.size
            playTrack(currentTrackIndex)
        }
    }

    fun previous() {
        if (audioList != null && audioList!!.isNotEmpty()) {
            currentTrackIndex = if (currentTrackIndex > 0) currentTrackIndex - 1 else audioList!!.size - 1
            playTrack(currentTrackIndex)
        }
    }

    fun seekTo(position: Int) {
        mediaPlayer?.seekTo(position)
    }

    fun initializeMediaPlayer() {
        mediaPlayer = MediaPlayer().apply {
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build()
            )
            setOnCompletionListener {
                next()
            }
        }

    }

    fun playTrack(index: Int) {
        try {
            mediaPlayer?.reset()
            val data = audioList!![index].data
            mediaPlayer?.setDataSource(context.applicationContext, Uri.parse(data))
            mediaPlayer?.prepare()
            mediaPlayer?.start()
        } catch (e: IOException) {
            Log.e("AudioPlayer", "Error playing track", e)
        }
    }
}
