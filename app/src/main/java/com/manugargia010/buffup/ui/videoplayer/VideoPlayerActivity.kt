package com.manugargia010.buffup.ui.videoplayer

import android.net.Uri
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import com.manugargia010.buffsdk.model.Answer
import com.manugargia010.buffsdk.model.Buff
import com.manugargia010.buffsdk.view.OnBuffAnswerClickedListener
import com.manugargia010.buffup.R
import com.manugargia010.buffup.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_video_player.*
import javax.inject.Inject

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class VideoPlayerActivity : BaseActivity(), VideoPlayerPresenter.View, OnBuffAnswerClickedListener {
    private var stream: String? = null
    private val player: SimpleExoPlayer? = null

    @Inject
    lateinit var presenter: VideoPlayerPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFullScreen()
        setContentView(R.layout.activity_video_player)

        presenter.setUp(intent.getIntExtra(ARG_EXTRA_STREAM_ID, INVALID_STREAM_ID))
        stream = "https://buffup-public.s3.eu-west-2.amazonaws.com/video/toronto+nba+cut+3.mp4"//intent.getStringExtra(ARG_EXTRA_VIDEO_URL)
        stream?.let { prepareExoPlayer(it) }
    }

    private fun setFullScreen() {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        supportActionBar?.hide()
    }


    private fun prepareExoPlayer(videoUrl: String) {
        val player = SimpleExoPlayer.Builder(this).build()
        playerView.player = player

        val dataSourceFactory = DefaultDataSourceFactory(this,
            Util.getUserAgent(this, getString(R.string.app_name))
        )
        val videoSource = ProgressiveMediaSource.Factory(dataSourceFactory)
            .createMediaSource(Uri.parse(videoUrl))

        // Prepare the player and start as soon as possible.
        player.prepare(videoSource)
        player.playWhenReady = true

    }

    private fun initializeBuffView(buff: Buff) {
        buffView.showBuffControls(this, buff)
    }

    override fun onStop() {
        player?.stop()
        player?.release()
        super.onStop()
    }

    override fun onBuffAnswerClicked(answer: Answer) {
        Toast.makeText(this, "com.manugargia010.domain.model.buff.Answer clicked",Toast.LENGTH_LONG).show()
    }

    override fun showProgress() {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideProgress() {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun renderErrorMessage(exception: Throwable) {
        Toast.makeText(this, exception.message, Toast.LENGTH_LONG).show()
    }

    override fun renderBuff(buffData: Buff) {
        initializeBuffView(buffData)
    }

    companion object {
        val ARG_EXTRA_VIDEO_URL = "video_url"
        val ARG_EXTRA_STREAM_ID = "stream_id"
        val INVALID_STREAM_ID = -1

    }
}

