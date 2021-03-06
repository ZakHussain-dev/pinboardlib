package com.zhussain.pinboardlib
import android.content.Context
import android.graphics.Bitmap
import android.widget.ImageView
import com.zhussain.pinboardlib.async.DownloadImageTask
import com.zhussain.pinboardlib.async.DownloadTask
import com.zhussain.pinboardlib.cache.CacheRepository
import com.zhussain.pinboardlib.cache.Config
import java.lang.RuntimeException
import java.util.concurrent.Executors
import java.util.concurrent.Future

/**
 * Singleton design pattern with the following functions:
 * DisplayImage, Clear cache, CancelTask and Cancel All
 */

class PinboardLib private constructor(context: Context, cacheSize: Int){



    private val cache: CacheRepository = CacheRepository(context,cacheSize)
    private val executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors())

    private val mRunningDownloadList: HashMap<String,Future<Bitmap?>> = hashMapOf();

    /**
     *
     * This function responsible to display Image from cache Memory
     *
     * @param url url
     * @param imageView ImageView
     * @param placeHolder placeHolder
     *
     */

    public fun displayImage(url: String,imageView: ImageView,placeHolder: Int){
        var bitmap = cache.get(url); //
        bitmap?.let {
            imageView.setImageBitmap(it)
            return
        } ?: run {
            imageView.tag = url
            if (placeHolder != null)
                imageView.setImageResource(placeHolder)
            addDownloadImageTask( url, DownloadImageTask(url , imageView , cache))
        }
    }

    private fun addDownloadImageTask(url: String,downloadTask: DownloadTask<Bitmap?>) {
        mRunningDownloadList.put(url,executorService.submit(downloadTask))
    }

    /**
     * Clear cache memory
     */
    public fun clearcache() {
        cache.clear()
    }

    /**
     *
     * This function used for cancel loading task by using [URL]
     *
     * @param url pass url as a String
     *
     */
    public fun cancelTask(url: String){
        synchronized(this){
            mRunningDownloadList.forEach {
                if (it.key == url &&  !it.value.isDone)
                    it.value.cancel(true)
            }
        }
    }

    /**
     *
     * This function used to cancel All Loading Task
     */
    fun  cancelAll() {
        synchronized (this) {
            mRunningDownloadList.forEach{
                if ( !it.value.isDone)
                    it.value.cancel(true)
            }
            mRunningDownloadList.clear()
        }
    }

    /**
     * Get [PinboardLib] instance/object
     */

    companion object {
        private val INSTANCE: PinboardLib? = null
        @Synchronized
        fun getInstance(context: Context, cacheSize: Int = Config.defaultCacheSize): PinboardLib {
            return INSTANCE?.let { return INSTANCE }
                ?: run {
                    return PinboardLib(context, cacheSize)
                }
        }
    }
}