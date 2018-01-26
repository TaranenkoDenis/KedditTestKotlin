package example.projects.kotlin.keddittestkotlin

import example.projects.kotlin.keddittestkotlin.api.NewsAPI
import example.projects.kotlin.keddittestkotlin.commons.NewsManager
import example.projects.kotlin.keddittestkotlin.commons.RedditNews
import example.projects.kotlin.keddittestkotlin.net.RedditChildrenResponse
import example.projects.kotlin.keddittestkotlin.net.RedditDataResponse
import example.projects.kotlin.keddittestkotlin.net.RedditNewsDataResponse
import example.projects.kotlin.keddittestkotlin.net.RedditNewsResponse
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.*
import retrofit2.Call
import retrofit2.Response
import rx.observers.TestSubscriber
import java.util.*

/**
 * Created by Denis Taranenko on 11.01.2018 - 8:45.
 */
class NewsManagerTest {

    var testSub = TestSubscriber<RedditNews>()
    var apiMock = mock<NewsAPI>()
    var callMock = mock<Call<RedditNewsResponse>>()

    @Before
    fun setup(){
        testSub = TestSubscriber<RedditNews>()
        apiMock = mock<NewsAPI>()
        callMock = mock<Call<RedditNewsResponse>>()
        `when`(apiMock.getNews(anyString(), ArgumentMatchers.anyString())).thenReturn(callMock)
    }

    @Test
    fun testSuccess_basic(){
        //prepare
        val redditNewsResponse = RedditNewsResponse(RedditDataResponse(listOf(), null, null))
        val response = Response.success(redditNewsResponse)

        `when`(callMock.execute()).thenReturn(response)

        //call
        val newsManager = NewsManager(apiMock)
        newsManager.getNews("").subscribe(testSub)

        // assert
        testSub.assertNoErrors()
        testSub.assertValueCount(1)
        testSub.assertCompleted()
    }

    @Test
    fun testSuccess_checkOneNews(){
        //prepare
        val newsData = RedditNewsDataResponse(
                "author",
                "title",
                10,
                Date().time,
                "thumbnail",
                "url"
        )

        val newsResponse = RedditChildrenResponse(newsData)
        val redditNewsResponse = RedditNewsResponse(RedditDataResponse(listOf(newsResponse), null, null))
        val response = Response.success(redditNewsResponse)

        `when`(callMock.execute()).thenReturn(response)

        //call
        val newsManager = NewsManager(apiMock)
        newsManager.getNews("").subscribe(testSub)

        //assert
        testSub.assertNoErrors()
        testSub.assertValueCount(1)
        testSub.assertCompleted()

        assert(testSub.onNextEvents[0].news[0].author == newsData.author)
        assert(testSub.onNextEvents[0].news[0].title == newsData.title)
    }

    @Test
    fun testError(){
        //prepare
        val responseError = Response.error<RedditNewsResponse>(500,
                ResponseBody.create(MediaType.parse("application/json"), ""))
        `when`(callMock.execute()).thenReturn(responseError)

        //call
        val newsManager = NewsManager(apiMock)
        newsManager.getNews("").subscribe(testSub)

        //assert
        assert(testSub.onErrorEvents.size == 1)
    }
}