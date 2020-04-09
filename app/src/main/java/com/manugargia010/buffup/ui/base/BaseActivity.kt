package com.manugargia010.buffup.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.manugargia010.buffup.framework.navigator.Navigator
import dagger.android.AndroidInjection
import javax.inject.Inject


abstract class BaseActivity : AppCompatActivity() {
    @Inject
    lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
    }

    override fun onResume() {
        super.onResume()
        navigator.currentActivity = this
    }

    protected fun cleanFragmentBackStack(){
        val fm = getSupportFragmentManager()
        for (i in 0 until fm.getBackStackEntryCount()) {
            fm.popBackStack()
        }
    }

    override fun onPause() {
        super.onPause()
        navigator.currentActivity = null
    }
}