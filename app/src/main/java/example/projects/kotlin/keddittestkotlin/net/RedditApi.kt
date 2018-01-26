package example.projects.kotlin.keddittestkotlin.net

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Denis Taranenko on 08.12.2017 - 15:46.
 */
interface RedditApi {
    @GET("/top.json")
    fun getTop(
            @Query("after") after: String,
            @Query("limit") limit: String
    ): Call<RedditNewsResponse>
}