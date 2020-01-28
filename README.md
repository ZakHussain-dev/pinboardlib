# Pinboardlib

An image loading library for Android backed by Kotlin. Easy to download, display and cache remote images in Android apps.

## Features

- Image downloading
- In-Memory Caching

### Sample App

<img src ="https://user-images.githubusercontent.com/15200843/73289621-085fb500-4223-11ea-9f2b-0182f5668f63.jpeg" width ="240" height = "430"> <img src ="https://user-images.githubusercontent.com/15200843/73290082-cedb7980-4223-11ea-9f55-6bb47981d37f.jpeg" width ="240" height = "430"> <img src ="https://user-images.githubusercontent.com/15200843/73290242-19f58c80-4224-11ea-9d45-46b44c28fb57.jpeg" width ="240" height = "430">


### Quick Start

To load an image into an ``ImageView``,use the ``displayImage`` extension function:


```
val CACHE_SIZE : Int = (Runtime.getRuntime().maxMemory()/16).toInt() // setup maximum cache.

val imageLoader = PinboardLib.getInstance(this,CACHE_SIZE)  //PinboardLib instance

imageLoader.displayImage(URL,imageView , R.drawable.place_holder)
```

#### Clear cache

```
imageLoader.clearcache()
```

#### To Cancel loading for specific url

```
imageLoader.cancel(url)
```

#### To cancel all tasks

```
imageLoader.cancelAll()
```

### Deployment

- Add it in your root build.gradle:

```
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```

- Add dependency:

```
implementation 'com.github.ZakHussain-dev:pinboardlib:alpha-v.1.0'
```
