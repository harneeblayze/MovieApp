package ng.com.movielist

import android.app.Application
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import ng.com.movielist.di.DaggerApplicationComponent
import javax.inject.Inject

class MovieApplication :Application(), HasAndroidInjector{

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()

        DaggerApplicationComponent.builder().application(this).build().inject(this)
    }

    override fun androidInjector() = androidInjector
}