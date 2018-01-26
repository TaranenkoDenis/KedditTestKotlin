package example.projects.kotlin.keddittestkotlin.commons.adapter

import android.support.v4.util.SparseArrayCompat
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.ViewGroup
import example.projects.kotlin.keddittestkotlin.commons.RedditNewsItem

/**
 * Created by Denis Taranenko on 08.12.2017 - 10:49.
 */
class NewsAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val delegateAdapters =
            SparseArrayCompat<ViewTypeDelegateAdapter>()
    private var items: ArrayList<ViewType>

    private val loadingItem = object: ViewType{
        override fun getViewType() = AdapterConstants.LOADING
    }

    // init is the reserved word in Kotlin for initializations in constructor of a class
    init {
        delegateAdapters.put(AdapterConstants.LOADING, LoadingDelegateAdapter())
        delegateAdapters.put(AdapterConstants.NEWS, NewsDelegateAdapter())
        items = ArrayList()
        items.add(loadingItem)

        Log.d("MyTag", "NewsAdapter -> init: size of items = ${items.size}")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.d("MyTag", "NewsAdapter -> onBindViewHolder")
        delegateAdapters.get(getItemViewType(position)).onBindViewHolder(holder, this.items[position])
    }

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return delegateAdapters.get(viewType).onCreateViewHolder(parent)
    }

    override fun getItemViewType(position: Int): Int {
        return this.items[position].getViewType()
    }

    fun addNews(news: List<RedditNewsItem>) {
        // first remove loading and notify
        val initPosition = items.size - 1
        items.removeAt(initPosition)
        notifyItemRemoved(initPosition)

        // insert news and the loading at the end of the list
        items.addAll(news)
        items.add(loadingItem)
        notifyItemRangeChanged(initPosition, items.size + 1 /* plus loading item */)
        Log.d("MyTag", "NewsAdapter -> addNews")
    }

    fun getNews(): List<RedditNewsItem>{
        return items
                .filter { it.getViewType() == AdapterConstants.NEWS }
                .map { it as RedditNewsItem }
    }

    private fun getLastPosition() = if (items.lastIndex == -1) 0 else items.lastIndex

    fun clearAndAddNews(news: List<RedditNewsItem>) {

    }
}