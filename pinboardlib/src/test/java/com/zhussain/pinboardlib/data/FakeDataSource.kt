package com.zhussain.pinboardlib.data

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.zhussain.pinboardlib.R
import com.zhussain.pinboardlib.cache.CacheRepository
import com.zhussain.pinboardlib.cache.Config
import com.zhussain.pinboardlib.cache.ImageCache
import com.zhussain.pinboardlib.cache.MemoryCache
import org.junit.runner.RunWith
import org.robolectric.Robolectric

@RunWith(AndroidJUnit4::class)
class FakeDataSource : ImageCache{
    lateinit var  memoryCache: MemoryCache
    lateinit var  cacheRepository : CacheRepository
    init {
        memoryCache = MemoryCache(Config.defaultCacheSize)
        cacheRepository = CacheRepository(ApplicationProvider.getApplicationContext(),Config.defaultCacheSize)
    }
    override fun put(url: String, bitmap: Bitmap) {
        val url1 = "https://images.unsplash.com/profile-1441738874514-bf742aedca3c"
        val url2 = "https://images.unsplash.com/profile-1441738874514-bf742aedca3c"
    }

    override fun get(url: String): Bitmap? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun clear() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}