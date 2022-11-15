package com.udacity.asteroidradar

import android.app.Application
import android.os.Build
import androidx.work.*
import com.udacity.asteroidradar.work.RefreshDataWorker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class MyApp : Application() {

    val applicationScope = CoroutineScope(Dispatchers.Default)

    private fun setupRecurringWork() {
        val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.UNMETERED)  // when the user is using a WiFi with unlimited usage
                .setRequiresBatteryNotLow(true)
                .setRequiresCharging(true)
                .apply {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {       // when the device be idle, which means that the user isn't actively using the device and they will not have their usage impacted by the app's background processing
                        setRequiresDeviceIdle(true)
                    }
                }.build()

        val repeatingRequest = PeriodicWorkRequestBuilder<RefreshDataWorker>(1, TimeUnit.DAYS)  // ‘RefreshDataWorker’ is a WorkManager class
                .setConstraints(constraints)
                .build()

        WorkManager.getInstance(applicationContext).enqueueUniquePeriodicWork(  // using a periodic task
                RefreshDataWorker.WORK_NAME,    // The task should have a unique name.
                ExistingPeriodicWorkPolicy.KEEP,    // If another task with the same unique name enqueued, it will be discard otherwise you can use .REPLACE instead.
                repeatingRequest    // created a repeating request object
        )
    }

    override fun onCreate() {
        super.onCreate()
        applicationScope.launch {
            setupRecurringWork()
        }
    }
}
