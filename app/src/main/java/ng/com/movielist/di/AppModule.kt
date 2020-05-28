package ng.com.movielist.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ng.com.movielist.MainActivity

@Module
abstract class AppModule {

    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity
}