package ru.mirea.pr49

import android.content.Context
import android.util.Log
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters

class TextWorker(appContext: Context, workerParams: WorkerParameters):
    Worker(appContext, workerParams) {
    private val l:Char = 'd'
    private val j:Char = 'n'
    private val k:Char = 'f'
    private val p:Char = 'i'
    private val t:Char = 'e'
    private val m:Char = 'r'
    private val s:String = "friend"
    private var c:String = ""
    override fun doWork(): Result {
        Log.d("test_worker", "text_worker_start")



        s.forEachIndexed{ _, char -> when(char){
            'f' -> c += k
            'r' -> c += m
            'i' -> c += p
            'e' -> c += t
            'n' -> c += j
            'd' -> c += l
        } }


        Log.d("test_worker", "text_worker_stop")
        return Result.success(Data.Builder().putString("final_word",c).build())
    }
}
