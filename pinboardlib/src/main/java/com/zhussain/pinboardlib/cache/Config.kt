package com.zhussain.pinboardlib.cache

class Config {
    companion object{
        val maxMemory = Runtime.getRuntime().maxMemory()
        val defaultCacheSize = (maxMemory/4).toInt()
    }
}