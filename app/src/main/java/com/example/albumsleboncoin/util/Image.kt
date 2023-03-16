package com.example.albumsleboncoin.util

import com.facebook.shimmer.Shimmer

/**
 * This class provides utils functions for images.
 */

val shimmer: Shimmer = Shimmer.AlphaHighlightBuilder()
    .setDuration(1800)
    .setBaseAlpha(0.7f)
    .setHighlightAlpha(0.6f)
    .setDirection(Shimmer.Direction.LEFT_TO_RIGHT)
    .setAutoStart(true)
    .build()