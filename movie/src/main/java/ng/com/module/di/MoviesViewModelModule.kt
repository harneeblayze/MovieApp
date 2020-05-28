package ng.com.module.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ng.com.core.di.ViewModelFactoryModule
import ng.com.core.di.ViewModelKey
import ng.com.module.viewmodels.MovieDetailViewModel
import ng.com.module.viewmodels.MoviesViewModel

@Module(includes = [ViewModelFactoryModule::class])
abstract class MoviesViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MoviesViewModel::class)
    abstract fun bindMoviesViewModel(viewModel: MoviesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MovieDetailViewModel::class)
    abstract fun bindMovieDetailViewModel(viewModel: MovieDetailViewModel): ViewModel

}