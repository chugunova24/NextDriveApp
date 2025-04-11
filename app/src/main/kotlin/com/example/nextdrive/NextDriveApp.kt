package com.example.nextdrive

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import com.example.nextdrive.di.appModule
import com.example.nextdrive.di.settingsModule
import com.example.nextdrive.di.signupModule
//import org.koin.core.context.GlobalContext.startKoin


import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class NextDriveApp : Application() {
    var isDarkTheme = mutableStateOf(false)

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@NextDriveApp)
            modules(
                appModule,
                signupModule,
                settingsModule
            )
        }
    }
}



//
//@ExperimentalSerializationApi
//class NextDriveApp : Application(), ImageLoaderFactory {
//
//    private val isDebug = BuildConfig.DEBUG
//
//    override fun onCreate() {
//        prepareLogger()
//        super.onCreate()
//    }
//
//    private fun prepareLogger() {
//        val delegates = mutableListOf<LoggerDelegate>()
//
//        if (isDebug) {
//            delegates.add(AndroidLoggerDelegate())
//        }
//
//        Logger.setDefaultDelegates(*delegates.toTypedArray())
//    }
//
//    override fun newImageLoader(): ImageLoader = ImageLoader.Builder(this)
//        .components {
//            // SVGs
//            add(SvgDecoder.Factory())
//        }
//        .memoryCache {
//            MemoryCache.Builder(this)
//                // Set the max size to 25% of the app's available memory.
//                .maxSizePercent(0.25)
//                .build()
//        }
//        .memoryCachePolicy(CachePolicy.ENABLED)
//        .diskCache {
//            DiskCache.Builder()
//                .directory(cacheDir.resolve("image_cache"))
//                .maxSizeBytes(2L * 1024 * 1024) // 2MB
//                .build()
//        }
//        .okHttpClient {
//            // Don't limit concurrent network requests by host.
//            val dispatcher = Dispatcher().apply { Dispatcher.maxRequestsPerHost =
//                Dispatcher.maxRequests
//            }
//
//            // Lazily create the OkHttpClient that is used for network operations.
//            OkHttpClient.Builder()
//                .dispatcher(dispatcher)
//                .addNetworkInterceptor { chain ->
//                    val response = chain.proceed(chain.request())
//
//                    /**
//                     * Workaround to fix for the error in coil
//                     * causing an empty header line to result in an unhandled error:
//                     *
//                     * java.lang.IllegalArgumentException: name is empty
//                     * at okhttp3.Headers$Companion.checkName(Headers.kt:438)
//                     * at okhttp3.Headers$Companion.access$checkName(Headers.kt:362)
//                     * at okhttp3.Headers$Builder.addUnsafeNonAscii(Headers.kt:270)
//                     * at coil.util.-Utils.addUnsafeNonAscii(Utils.kt:239)
//                     * at coil.network.CacheResponse.<init>(CacheResponse.kt:29)
//                     * at coil.fetch.HttpUriFetcher.toCacheResponse(HttpUriFetcher.kt:255)
//                     */
//                    val newResponse = response.newBuilder()
//                        .removeHeader("")
//                        .build()
//                    newResponse
//                }
//                .build()
//        }
//        // Show a short crossfade when loading images asynchronously.
//        .crossfade(true)
//        /**
//         * Rely on OkHttp which handles Last-Modified, Cache-Control, If-Modified-Since,
//         * and other similar cache control headers and choose
//         * read from/write to the disk cache policy.
//         */
//        .respectCacheHeaders(true)
//        // Enable logging to the standard Android log if this is a debug build.
//        .apply { if (isDebug) logger(DebugLogger()) }
//        .build()
//}
