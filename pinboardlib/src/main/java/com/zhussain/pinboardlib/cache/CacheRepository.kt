package com.zhussain.pinboardlib.cache

import android.content.Context
import android.graphics.Bitmap

/**
 *This class is responsible  for putting,
 * getting an image in the cache and clear all cache.
 */
class CacheRepository(context: Context, newMaxSize: Int): ImageCache {

    //val diskCache = DiskCache.getInstance(context)
    val memoryCache = MemoryCache(newMaxSize)

    override fun put(url: String, bitmap: Bitmap) {
        memoryCache.put(url,bitmap)
        //diskCache.put(url,bitmap)
    }

    override fun get(url: String): Bitmap? {
        return memoryCache.get(url) // ?:diskCache.get(url)
    }

    override fun clear() {
        memoryCache.clear()
       // diskCache.clear()
    }

}