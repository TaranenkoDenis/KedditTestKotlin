package example.projects.kotlin.keddittestkotlin


import example.projects.kotlin.keddittestkotlin.api.NewsAPI
import example.projects.kotlin.keddittestkotlin.commons.NewsManager
import example.projects.kotlin.keddittestkotlin.commons.RedditNews
import example.projects.kotlin.keddittestkotlin.net.RedditChildrenResponse
import example.projects.kotlin.keddittestkotlin.net.RedditDataResponse
import example.projects.kotlin.keddittestkotlin.net.RedditNewsDataResponse
import example.projects.kotlin.keddittestkotlin.net.RedditNewsResponse
import junit.framework.Assert.assertNotNull
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.mockito.ArgumentMatchers.anyString
import java.util.*

/**
 * Created by Denis Taranenko on 23.01.2018 - 8:35.
 */
class NewsManagerSpekTest : Spek({

//    given("a NewsManager") {
//        var redditNews: RedditNews? = null
//        var apiMock = mock<NewsAPI>()
//
//        beforeEachTest {
//            redditNews = null
//            apiMock = mock<NewsAPI>()
//        }
//
//        on("service returns something") {
//            beforeGroup {
//                // prepare
//                val redditNewsResponse = RedditNewsResponse(RedditDataResponse(listOf(), null, null))
//                val callMock = MockedCall<RedditNewsResponse>(redditNewsResponse)
//                whenever(apiMock.getNews(anyString(), anyString())).thenReturn(callMock)
//
//                // call
//                val newsManager = NewsManager(apiMock)
//                runBlockinzg {
//                    redditNews = newsManager.getNews("")
//                }
//            }
//
//            it("should receive something and no errors") {
//                assertNotNull(redditNews)
//            }
//        }
//
//        on("service returns just one news") {
//            val newsData = RedditNewsDataResponse(
//                    "author",
//                    "title",
//                    10,
//                    Date().time,
//                    "thumbnail",
//                    "url"
//            )
//            beforeGroup {
//                // prepare
//                val newsResponse = RedditChildrenResponse(newsData)
//                val redditNewsResponse = RedditNewsResponse(RedditDataResponse(listOf(newsResponse), null, null))
//                val callMock = MockedCall<RedditNewsResponse>(redditNewsResponse)
//                whenever(apiMock.getNews(anyString(), anyString())).thenReturn(callMock)
//
//                // call
//                val newsManager = NewsManager(apiMock)
//                runBlocking {
//                    redditNews = newsManager.getNews("")
//                }
//            }
//
//            it("should process only one news successfully") {
//                assertNotNull(redditNews)
//                assert(redditNews!!.news[0].author == newsData.author)
//                assert(redditNews!!.news[0].title == newsData.title)
//            }
//        }
//
//        on("service returns a 500 error") {
//            var newsManager: NewsManager? = null
//
//            beforeGroup {
//                // prepare
//                val callMock = MockedCall<RedditNewsResponse>(exception = Throwable())
//                whenever(apiMock.getNews(anyString(), anyString())).thenReturn(callMock)
//
//                // call
//                newsManager = NewsManager(apiMock)
//            }
//
//            it("should receive an exception") {
//                assertFailsWith<Throwable> {
//                    runBlocking {
//                        redditNews = newsManager!!.getNews("")
//                    }
//                }
//            }
//        }
//    }
})