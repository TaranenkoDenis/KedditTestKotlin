package example.projects.kotlin.keddittestkotlin

import dagger.Module
import dagger.Provides
import example.projects.kotlin.keddittestkotlin.api.NewsAPI
import example.projects.kotlin.keddittestkotlin.api.NewsRestApi
import example.projects.kotlin.keddittestkotlin.net.RedditApi
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by Denis Taranenko on 24.01.2018 - 8:38.
 */
@Module
class NewsModule {

    @Provides
    @Singleton
    fun provideNewsApi(redditApi: RedditApi): NewsAPI{
        return NewsRestApi(redditApi)
    }

    @Provides
    @Singleton
    fun provideRedditApi(retrofit: Retrofit): RedditApi{
        return retrofit.create(RedditApi::class.java)
    }

    /**
     * NewsManager is automatically provided by Dagger as we set the @Inject annotation in the
     * constructor, so we can avoid adding a 'provider method' here.
     */
}