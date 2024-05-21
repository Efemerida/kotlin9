import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project.kotlin9.R
import com.project.kotlin9.ui.music.AudioData

class MusicAdapter(
     val audioPlayer: AudioPlayer,
    private val musicList: List<AudioData>,
) : RecyclerView.Adapter<MusicAdapter.MusicViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_music, parent, false)
        return MusicViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MusicViewHolder, position: Int) {
        holder.itemView.setOnClickListener{
            audioPlayer.playTrack(position)
        }

    }

    override fun getItemCount(): Int {
        return musicList.size
    }

    class MusicViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.musicTitleTextView)

        fun bind(title: String, clickListener: (String) -> Unit) {
            titleTextView.text = title
            itemView.setOnClickListener { clickListener(title) }
        }
    }
}
