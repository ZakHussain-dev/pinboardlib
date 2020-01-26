package com.zhussain.pinboard

class Util {
    companion object{
        val MEMORY_SIZE : Int = (Runtime.getRuntime().maxMemory()/16).toInt() // ~12MB
    }
}