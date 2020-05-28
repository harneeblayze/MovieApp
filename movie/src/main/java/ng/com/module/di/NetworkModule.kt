package ng.com.module.di

import dagger.Module
import dagger.Provides
import ng.com.core.di.CoreNetworkModule
import ng.com.module.network.MovieApi
import retrofit2.Retrofit

@Module(includes = [CoreNetworkModule::class])
object MovieNetworkModule {

    @Provides
    @JvmStatic
    internal fun provideMovieApi(retrofit: Retrofit): MovieApi =
        retrofit.create(MovieApi::class.java)
}