package example.projects.kotlin.keddittestkotlin.commons.adapter

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.ViewGroup
import example.projects.kotlin.keddittestkotlin.R
import example.projects.kotlin.keddittestkotlin.commons.RedditNewsItem
import example.projects.kotlin.keddittestkotlin.commons.extensions.getFriendlyTime
import example.projects.kotlin.keddittestkotlin.commons.extensions.inflate
import example.projects.kotlin.keddittestkotlin.commons.extensions.loadImage
import kotlinx.android.synthetic.main.news_item.view.*

/**
 * Created by Denis Taranenko on 08.12.2017 - 10:42.
 */
class TurnsViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        parent.inflate(R.layout.news_item)) {

    fun bind(item: RedditNewsItem) = with(itemView) {
        Log.d("MyTag", "TurnsViewHolder -> bind")
        img_thumbnail.loadImage(item.thumbnail)
        description.text = item.title
        author.text = item.author
        comments.text = "${item.numComments} comments"
        time.text = item.created.getFriendlyTime()
    }
}
