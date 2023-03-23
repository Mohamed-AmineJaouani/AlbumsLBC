package com.example.albumsleboncoin.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.albumsleboncoin.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val albumsViewModel: AlbumViewModel by viewModels()
    private var albumAdapter: AlbumAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.swipeRL.setOnRefreshListener {
            albumAdapter?.clearAlbumList()
            albumsViewModel.refreshData()
        }

        initAdapter()

        lifecycleScope.launchWhenStarted {
            albumsViewModel.albumUiState.collect { uiState ->
                when (uiState) {
                    is AlbumUiState.Idl -> Unit
                    is AlbumUiState.Loading -> {
                        Timber.d("Retrieving albums loading...")
                        binding.progressBar.visibility = View.VISIBLE
                        binding.swipeRL.isRefreshing = true
                        displayErrorMessage(View.GONE)
                    }
                    is AlbumUiState.Success -> {
                        Timber.d("Albums: ${uiState.albums}")
                        albumAdapter?.setAlbumList(uiState.albums)
                        binding.swipeRL.isRefreshing = false
                        binding.progressBar.visibility = View.GONE
                        displayErrorMessage(View.GONE)
                    }
                    is AlbumUiState.Error -> {
                        Timber.e("Error during retrieving albums")
                        Toast.makeText(this@MainActivity, "Error", Toast.LENGTH_SHORT).show()
                        binding.progressBar.visibility = View.GONE
                        binding.swipeRL.isRefreshing = false
                        displayErrorMessage(View.VISIBLE)
                    }
                }
            }
        }
    }

    private fun displayErrorMessage(visibility: Int) {
        binding.ivAlbumThumbnail.visibility = visibility
        binding.tvAlbumTitle.visibility = visibility
    }

    private fun initAdapter() {
        albumAdapter = AlbumAdapter()
        binding.rvAlbumList.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.rvAlbumList.adapter = albumAdapter
    }
}