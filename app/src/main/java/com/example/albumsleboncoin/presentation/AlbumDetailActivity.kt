package com.example.albumsleboncoin.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.albumsleboncoin.R
import com.example.albumsleboncoin.databinding.ActivityAlbumDetailBinding
import com.example.albumsleboncoin.util.shimmer
import com.facebook.shimmer.ShimmerDrawable
import com.squareup.picasso.Picasso

class AlbumDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAlbumDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlbumDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvDetailAlbumTitle.text = intent.getStringExtra(ALBUM_TITLE)

        val shimmerPlaceholder = ShimmerDrawable().apply {
            setShimmer(shimmer)
        }
        Picasso.get()
            .load(intent.getStringExtra(ALBUM_URL))
            .resize(600, 600)
            .centerCrop()
            .placeholder(shimmerPlaceholder)
            .error(R.drawable.empty_data)
            .into(binding.ivDetailAlbumUrl)
    }

    companion object {

        private const val ALBUM_URL = "ALBUM_URL"
        private const val ALBUM_TITLE = "ALBUM_TITLE"

        fun start(context: Context, url: String?, title: String?) {

            context.startActivity(Intent(context, AlbumDetailActivity::class.java).apply {
                putExtra(ALBUM_URL, url)
                putExtra(ALBUM_TITLE, title)
            })
        }
    }
}