package example.projects.kotlin.keddittestkotlin.api

import example.projects.kotlin.keddittestkotlin.net.RedditNewsResponse
import retrofit2.Call

/**
 * Created by Denis Taranenko on 28.12.2017 - 12:14.
 */
interface NewsAPI {
    fun getNews(after: String, limit: String): Call<RedditNewsResponse>
}