package example.projects.kotlin.keddittestkotlin.commons.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import example.projects.kotlin.keddittestkotlin.commons.RedditNewsItem

/**
 * Created by Denis Taranenko on 08.12.2017 - 11:34.
 */
class NewsDelegateAdapter : ViewTypeDelegateAdapter {
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return TurnsViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        holder as TurnsViewHolder
        holder.bind(item as RedditNewsItem)
    }


}