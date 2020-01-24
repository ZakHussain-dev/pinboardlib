package com.zhussain.pinboardlib.cache

import android.graphics.Bitmap
import android.util.Log
import androidx.collection.LruCache

/**
 * save images on RAM/Memory..
 *
 */
class MemoryCache(newMaxSize: Int) : ImageCache {
    private val cache: LruCache<String, Bitmap>

    init {
        var cacheSize: Int
        if (newMaxSize > Config.maxMemory) {
            cacheSize = Config.defaultCacheSize;
            Log.d("MemoryCache", "New Value of cache is bigger than max cache available on system!")
        } else {
            cacheSize = newMaxSize
        }
        cache = object : LruCache<String, Bitmap>(cacheSize) {
            override fun sizeOf(key: String, value: Bitmap): Int {
                return (value.rowBytes) * (value.height) / 1024
            }
        }
    }

    override fun put(url: String, bitmap: Bitmap) {
        cache.put(url, bitmap)
    }

    override fun get(url: String): Bitmap? {
        return cache.get(url)
    }

    override fun clear() {
        cache.evictAll()
    }


}