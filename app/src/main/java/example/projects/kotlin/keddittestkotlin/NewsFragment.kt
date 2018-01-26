package example.projects.kotlin.keddittestkotlin


import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import example.projects.kotlin.keddittestkotlin.commons.NewsManager
import example.projects.kotlin.keddittestkotlin.commons.RedditNews
import example.projects.kotlin.keddittestkotlin.commons.adapter.NewsAdapter
import example.projects.kotlin.keddittestkotlin.commons.extensions.inflate
import kotlinx.android.synthetic.main.fragment_news.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 */
class NewsFragment : RxBaseFragment() {

    @Inject lateinit var newsManager: NewsManager
    private var redditNews: RedditNews? = null

    companion object {
        private val KEY_REDDIT_NEWS = "redditNews"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        KedditApp.newsComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        Log.d("MyTag", "NewsFragment -> onCreateView")
        return container?.inflate(R.layout.fragment_news)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d("MyTag", "NewsFragment -> onActivityCreated")

        news_list.apply {
            setHasFixedSize(true)
            val linearLayout = LinearLayoutManager(context)
            layoutManager = linearLayout
            news_list.clearOnScrollListeners()
            news_list.addOnScrollListener(InfiniteScrollListener({requestNews()}, linearLayout))
            news_list.adapter = NewsAdapter()
        }

        if(savedInstanceState != null && savedInstanceState.containsKey(KEY_REDDIT_NEWS)){
            redditNews = savedInstanceState.get(KEY_REDDIT_NEWS) as RedditNews
            (news_list.adapter as NewsAdapter).clearAndAddNews(redditNews!!.news)
        } else {
            requestNews()
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

        val news = (news_list.adapter as NewsAdapter).getNews()

        if (redditNews != null && news.size > 0) {
            outState?.putParcelable(KEY_REDDIT_NEWS, redditNews?.copy(news = news))
        }
    }

    private fun requestNews() {

        /**
         * first time will send empty string for after parameter.
         * first time will send empty string for 'after' parameter.
         * Next time we will have redditNews set with the next page to
         * navigate with the after param.
         * navigate with the 'after' param.
         */

        Log.d("MyTag", "NewsFragment -> requestNews")
        val subscription = newsManager.getNews(redditNews?.after ?: "")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { retrievedNews ->

                            Log.d("MyTag", "NewsFragment -> requestNews -> subscribe -> retrievedNews")
                            redditNews = retrievedNews
                            (news_list.adapter as NewsAdapter).addNews(retrievedNews.news)

                        }, {
                    e -> Snackbar.make(news_list, e.message ?: "", Snackbar.LENGTH_LONG).show()
                })

        // from super class
        subscriptions.add(subscription)
    }
}
