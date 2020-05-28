package ng.com.module.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ng.com.view.MovieActivity
import ng.com.view.MovieDetailActivity
import ng.com.view.MovieFragment

@Module(includes = [
    MoviesViewModelModule::class,

    MovieNetworkModule::class
])
abstract class MovieModule {

    @ContributesAndroidInjector
    abstract fun bindMovieActivity(): MovieActivity

    @ContributesAndroidInjector
    abstract fun bindMovieDetailActivity(): MovieDetailActivity

    @ContributesAndroidInjector
    abstract fun bindMovieFragment(): MovieFragment
}