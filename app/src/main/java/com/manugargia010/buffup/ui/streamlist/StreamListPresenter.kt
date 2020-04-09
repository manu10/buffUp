package com.manugargia010.buffup.ui.streamlist

import com.manugargia010.domain.model.stream.Stream
import com.manugargia010.buffup.ui.base.BasePresenter
import com.manugargia010.domain.model.Response
import com.manugargia010.usecases.GetStreams
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

class StreamListPresenter(
    view: View,
    private val getStreams: GetStreams
) : BasePresenter<StreamListPresenter.View>(view) {

    interface View {

        fun renderStreams(streams: List<Stream>)
        fun showProgress()
        fun hideProgress()
        fun renderErrorMessage(exception: Throwable)
    }

    override fun onCreate() {
        fetchStreamsAndDisplayContent()
    }

    fun fetchStreamsAndDisplayContent() {
        getStreams()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { view.showProgress() }
            .doAfterTerminate { view.hideProgress() }
            .subscribe ({
                processGetStreamResponse(it)
            },{ view.hideProgress() })
            .addTo(disposables)
    }

    private fun processGetStreamResponse(response: Response<List<Stream>>?) {
        when (response) {
            is Response.Success -> view.renderStreams(response.data)
            is Response.Error -> view.renderErrorMessage(response.exception)//todo: improve error handling
        }
    }
}
