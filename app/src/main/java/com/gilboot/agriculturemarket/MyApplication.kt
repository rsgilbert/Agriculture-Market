package com.gilboot.agriculturemarket

import androidx.multidex.MultiDexApplication
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber


// Instantiate certain elements in the application such as Timber
class MyApplication : MultiDexApplication() {

    /**
     *  Coroutine scope for the application
     */
    private val applicationScope = CoroutineScope(Dispatchers.Default)


    /**
     * onCreate is called before the first screen is shown to the user.
     *
     * Use it to setup any background tasks, running expensive setup operations in a background
     * thread to avoid delaying app start.
     */
    override fun onCreate() {
        super.onCreate()
        delayedUnit()
    }


    private fun delayedUnit() {
        applicationScope.launch {
            Timber.plant(Timber.DebugTree())
        }
    }


}
