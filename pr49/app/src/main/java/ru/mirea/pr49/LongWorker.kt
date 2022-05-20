package ru.mirea.pr49

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class LongWorker(appContext: Context, workerParams: WorkerParameters):
    Worker(appContext, workerParams) {
    override fun doWork(): Result {
        Log.d("test_worker", "long_worker_start")
        val data1: String = inputData.getString("final_word")?:"none"
        val data2: Int = inputData.getInt("count", 100)
        var p: Long = 0L

        for (i in 1..data1.length*10000)
            for (j in 1..data2)
                p += i / j

        Log.d("test_worker", "long_worker_stop with rezult $p")
        // Indicate whether the work finished successfully with the Result
        return Result.success()
    }
}