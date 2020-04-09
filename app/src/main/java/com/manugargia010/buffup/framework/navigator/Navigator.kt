package com.manugargia010.buffup.framework.navigator

import com.manugargia010.domain.model.stream.Stream
import android.content.Intent
import android.os.Bundle
import com.manugargia010.buffup.ui.base.BaseActivity
import com.manugargia010.buffup.ui.videoplayer.VideoPlayerActivity
import javax.inject.Inject

class Navigator @Inject constructor() {

    var currentActivity: BaseActivity? = null

    private fun toActivity(activity: Class<*>, bundle: Bundle? = null) {
        val intent = Intent(currentActivity, activity)
        if (bundle != null) {
            intent.putExtras(bundle)
        }
        currentActivity?.startActivity(intent)
    }

    fun toVideoPlayerActivity(stream: Stream) {
        val bundle = Bundle()
        bundle.putInt(VideoPlayerActivity.ARG_EXTRA_STREAM_ID, stream.id)
        bundle.putString(VideoPlayerActivity.ARG_EXTRA_VIDEO_URL, stream.url)
        toActivity(VideoPlayerActivity::class.java, bundle)
    }

}