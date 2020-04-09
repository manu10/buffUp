package com.manugargia010.buffup.ui.streamlist

import com.manugargia010.buffup.configuration.TrampolineSchedulerRule
import com.manugargia010.data.StreamRemoteSource
import com.manugargia010.data.StreamRepository
import com.manugargia010.domain.model.Response
import com.manugargia010.usecases.GetStreams
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnit
import java.lang.Exception


class StreamListPresenterTest {

    @Rule @JvmField
    var schedulersRule = TrampolineSchedulerRule()

    @Rule @JvmField
    var mockitoRule = MockitoJUnit.rule()

    @Mock
    lateinit var getStream: GetStreams

    @Mock
    lateinit var streamRepository: StreamRepository

    @Mock
    lateinit var streamRemoteSource: StreamRemoteSource

    @Mock
    lateinit var streamListPresenterView: StreamListPresenter.View

    lateinit var presenter: StreamListPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        preparePresenter()

    }

    private fun preparePresenter() {
        presenter = StreamListPresenter(streamListPresenterView,getStream)
    }

    @Test
    fun testGetStreams() {
        `when`(getStream()).thenReturn(Single.just(Response.Success(listOf())))
        presenter.onCreate()
        verify(streamListPresenterView).showProgress()
        verify(streamListPresenterView).hideProgress()
        verify(streamListPresenterView).renderStreams(ArgumentMatchers.anyList())
 }

    @Test
    fun testGetStreamsFails() {
        `when`(getStream()).thenReturn(Single.just(Response.Error(Exception())))
        presenter.onCreate()
        verify(streamListPresenterView).showProgress()
        verify(streamListPresenterView).hideProgress()
        verify(streamListPresenterView).renderErrorMessage(com.nhaarman.mockitokotlin2.any())
    }
}