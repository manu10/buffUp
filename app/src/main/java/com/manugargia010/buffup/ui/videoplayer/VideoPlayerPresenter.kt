package com.manugargia010.buffup.ui.videoplayer

import com.manugargia010.buffup.ui.base.BasePresenter
import com.manugargia010.domain.model.Response
import com.manugargia010.domain.model.buff.Buff
import com.manugargia010.usecases.GetBuff
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

class VideoPlayerPresenter(
    view: View,
    private val getBuff: GetBuff
) : BasePresenter<VideoPlayerPresenter.View>(view){

    interface View {
        fun showProgress()
        fun hideProgress()
        fun renderErrorMessage(exception: Throwable)
        fun renderBuff(buffData: com.manugargia010.buffsdk.model.Buff)
    }

    private fun processGetBuffResponse(response: Response<Buff>?) {
        when (response) {
            is Response.Success -> view?.renderBuff(mapDomainDataToPresentationData(response.data))
            is Response.Error -> view?.renderErrorMessage(response.exception)//todo: improve error handling
        }
    }

    private fun mapDomainDataToPresentationData(domainBuffData: Buff): com.manugargia010.buffsdk.model.Buff {
        with (domainBuffData) {
            return com.manugargia010.buffsdk.model.Buff(
                time_to_show,
                com.manugargia010.buffsdk.model.Author(author.first_name, author.last_name, author.image),
                com.manugargia010.buffsdk.model.Question(question.title),
                answers.map { com.manugargia010.buffsdk.model.Answer(it.title) }
            )
        }
    }

    fun setUp(streamId: Int) {
        //there should be a use case that fetch the buff for the stream id.
        //To simplify it's hardcoded
        getBuff(1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { view.showProgress() }
            .doAfterTerminate { view.hideProgress() }
            .subscribe ({
                processGetBuffResponse(it)
            },{ })
            .addTo(disposables)

    }

}


