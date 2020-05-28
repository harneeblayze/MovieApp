package ng.com.movielist.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjection
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import ng.com.module.di.MovieModule
import ng.com.module.di.MovieNetworkModule
import ng.com.movielist.MovieApplication
import javax.inject.Singleton

@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        MovieModule::class
    ]
)
@Singleton
interface ApplicationComponent : AndroidInjector<MovieApplication>{

    @Component.Builder
    interface Builder{

        @BindsInstance
        fun application(application: Application) : Builder

        fun build(): ApplicationComponent
    }
}
