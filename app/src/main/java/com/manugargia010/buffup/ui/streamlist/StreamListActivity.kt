package com.manugargia010.buffup.ui.streamlist

import com.manugargia010.domain.model.stream.Stream
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.manugargia010.buffup.R
import com.manugargia010.buffup.ui.base.BaseActivity
import com.manugargia010.buffup.ui.extension.gone
import com.manugargia010.buffup.ui.extension.visible
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_stream_list.*
import javax.inject.Inject

class StreamListActivity : BaseActivity(),
    StreamListPresenter.View,
    StreamsAdapter.OnStreamClickedListener {

    private val streamsAdapter = StreamsAdapter(this)
    @Inject
    lateinit var presenter: StreamListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stream_list)
        streamList.layoutManager = LinearLayoutManager(this)
        streamList.adapter = streamsAdapter
        presenter.onCreate()
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun renderStreams(streams: List<Stream>) {
        streamsAdapter.items = streams
    }

    override fun showProgress() {
        streamListLoading.visible()
    }

    override fun hideProgress() {
        streamListLoading.gone()
    }

    override fun renderErrorMessage(exception: Throwable) {
        Toast.makeText(this, exception.message, Toast.LENGTH_LONG).show()
    }

    override fun onStreamClicked(stream: Stream) {
        navigator.toVideoPlayerActivity(stream)
    }
}

