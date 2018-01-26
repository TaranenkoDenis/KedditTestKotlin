package example.projects.kotlin.keddittestkotlin.commons

import android.util.Log
import example.projects.kotlin.keddittestkotlin.api.NewsAPI
import example.projects.kotlin.keddittestkotlin.api.NewsRestApi
import rx.Observable
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Denis Taranenko on 08.12.2017 - 12:27.
 */
@Singleton
class NewsManager @Inject constructor(private val api: NewsAPI){
//class NewsManager(private val API: NewsAPI = NewsRestApi()){

    fun getNews(after: String, limit: String = "10"): Observable<RedditNews>{

        Log.d("MyTag", "NewsManager -> getNews")
        return Observable.create { subscriber ->

            val callResponse = api.getNews(after, limit)
            val response = callResponse.execute()

            if (response.isSuccessful) {
                val dataResponse = response.body()?.data

                if(dataResponse != null) {
                    val news = dataResponse.children.map {
                        val item = it.data
                        RedditNewsItem(item.author, item.title, item.num_comments,
                                item.created, item.thumbnail, item.url)
                    }
                    val redditNews = RedditNews(
                            dataResponse.after ?: "",
                            dataResponse.before ?: "",
                            news
                    )
                    subscriber.onNext(redditNews)
                    subscriber.onCompleted()
                }
                else Log.i("MyTag", "NewsManager -> getNews: dataResponse can't be null !!!")
            } else {
                subscriber.onError(Throwable(response.message()))
            }
        }
    }
}