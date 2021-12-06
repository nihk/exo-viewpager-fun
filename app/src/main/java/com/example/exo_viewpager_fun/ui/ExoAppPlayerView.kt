package com.example.exo_viewpager_fun.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.example.exo_viewpager_fun.R
import com.example.exo_viewpager_fun.databinding.PlayerViewBinding
import com.example.exo_viewpager_fun.players.AppPlayer
import com.example.exo_viewpager_fun.players.ExoAppPlayer
import com.example.exo_viewpager_fun.ui.extensions.layoutInflater

/**
 * An implementation of AppPlayerView that uses ExoPlayer APIs,
 * namely [com.google.android.exoplayer2.ui.PlayerView]
 */
class ExoAppPlayerView(layoutInflater: LayoutInflater) : AppPlayerView {
    override val view: View = layoutInflater.inflate(R.layout.player_view, null)
    private val binding = PlayerViewBinding.bind(view)

    override fun attach(appPlayer: AppPlayer) {
        binding.playerView.player = (appPlayer as ExoAppPlayer).exoPlayer
    }

    // ExoPlayer and PlayerView hold circular ref's to each other, so avoid leaking
    // Activity here by nulling it out.
    override fun detachPlayer() {
        binding.playerView.player = null
    }

    class Factory : AppPlayerView.Factory {
        override fun create(context: Context): AppPlayerView {
            return ExoAppPlayerView(context.layoutInflater)
        }
    }
}
