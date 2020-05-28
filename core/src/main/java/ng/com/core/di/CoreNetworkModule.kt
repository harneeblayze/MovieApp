package ng.com.core.di

import android.app.Application
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
object CoreNetworkModule {

    @Provides
    @JvmStatic
    internal fun providesApiKey() = Interceptor{chain ->
        val newRequest = chain.request().let {request ->
            val newurl = request.url().newBuilder()
                .addQueryParameter("api_key", "your-api-key")
                .build()
            request.newBuilder()
                .url(newurl)
                .build()
        }
        chain.proceed(newRequest)
    }

    @Provides
    @Singleton
    @JvmStatic
    internal fun provideOkHttp(app:Application, apiKeyInterceptor: Interceptor): OkHttpClient =  OkHttpClient.Builder().apply {
        readTimeout(120, TimeUnit.SECONDS)
        connectTimeout(120, TimeUnit.SECONDS)
        addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }).addInterceptor(apiKeyInterceptor)
    }.build()

    @Provides
    @Singleton
    @JvmStatic
    internal fun providesRetrofit(okHttpClient: OkHttpClient, app: Application): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
}