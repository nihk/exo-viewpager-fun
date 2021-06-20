package com.example.exo_viewpager_fun

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

interface VideoDataRepository {
    fun videoData(): Flow<List<VideoData>>
}

class AssetVideoDataRepository : VideoDataRepository {
    override fun videoData(): Flow<List<VideoData>> {
        return flowOf(videoData)
    }

    companion object {
        private const val path = "file:///android_asset"

        private val videoData = listOf(
            VideoData(
                mediaUri = "$path/waves.mp4",
                firstFrameUri = "$path/waves_preview.png",
            ),
            VideoData(
                mediaUri = "$path/christmas.mp4",
                firstFrameUri = "$path/christmas_preview.png",
            ),
            VideoData(
                mediaUri = "$path/yellow.mp4",
                firstFrameUri = "$path/yellow_preview.png",
            ),
        )
    }
}
