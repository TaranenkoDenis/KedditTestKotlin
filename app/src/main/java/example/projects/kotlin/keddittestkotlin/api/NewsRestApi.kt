package example.projects.kotlin.keddittestkotlin.api

import example.projects.kotlin.keddittestkotlin.net.RedditApi
import example.projects.kotlin.keddittestkotlin.net.RedditNewsResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject

/**
 * Created by Denis Taranenko on 11.01.2018 - 8:38.
 */
//class NewsRestApi() : NewsAPI {
//
//    private val redditApi: RedditApi
//
//    init {
//        val retrofit = Retrofit.Builder()
//                .baseUrl("https://www.reddit.com")
//                .addConverterFactory(MoshiConverterFactory.create())
//                .build()
//
//        redditApi = retrofit.create(RedditApi::class.java)
//    }
//
//    override fun getNews(after: String, limit: String): Call<RedditNewsResponse> {
//        return redditApi.getTop(after, limit)
//    }
//}

class NewsRestApi @Inject constructor(private val redditApi: RedditApi): NewsAPI{

    override fun getNews(after: String, limit: String): Call<RedditNewsResponse> {
        return redditApi.getTop(after, limit)
    }
}