package example.projects.kotlin.keddittestkotlin

import dagger.Component
import javax.inject.Singleton

/**
 * Created by Denis Taranenko on 24.01.2018 - 8:36.
 */

@Singleton
@Component(modules = arrayOf(
        AppModule::class,
        NewsModule::class,
        NetworkModule::class)
)
interface NewsComponent{
    fun inject(newsFragment: NewsFragment)
}