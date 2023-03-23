package com.example.albumsleboncoin.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.albumsleboncoin.R
import com.example.albumsleboncoin.databinding.ItemAlbumBinding
import com.example.albumsleboncoin.domain.Album
import com.example.albumsleboncoin.util.shimmer
import com.facebook.shimmer.ShimmerDrawable
import com.squareup.picasso.Picasso

class AlbumAdapter :
    RecyclerView.Adapter<AlbumAdapter.ViewHolder>() {

    private var albums: List<Album>? = null

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        val binding =
            ItemAlbumBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        val albums = albums
        return if (albums.isNullOrEmpty())
            0
        else
            albums.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentAlbum: Album = albums?.get(position)!!
        holder.bind(currentAlbum)
    }

    fun setAlbumList(listAlbum: List<Album>) {
        albums = listAlbum
        notifyDataSetChanged()
    }

    inner class ViewHolder constructor(binding: ItemAlbumBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val cvAlbum: CardView = binding.cvAlbum
        private val tvAlbumName: TextView = binding.tvAlbumTitle
        private val ivAlbumThumbnail: ImageView = binding.ivAlbumThumbnail

        // This is the placeholder for the imageView
        private val shimmerPlaceholder = ShimmerDrawable().apply {
            setShimmer(shimmer)
        }

        fun bind(item: Album) {
            cvAlbum.setOnClickListener {
                AlbumDetailActivity.start(itemView.context, item.url, item.title)
            }

            tvAlbumName.text = item.title

            val url = item.thumbnailUrl

            Picasso.get()
                .load(url)
                .resize(150, 150)
                .placeholder(shimmerPlaceholder)
                .centerCrop()
                .error(R.drawable.empty_data)
                .into(ivAlbumThumbnail)
        }
    }
}