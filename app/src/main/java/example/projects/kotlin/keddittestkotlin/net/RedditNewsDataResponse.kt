package example.projects.kotlin.keddittestkotlin.net

/**
 * Created by Denis Taranenko on 08.12.2017 - 15:45.
 */
class RedditNewsDataResponse (
        val author: String,
        val title: String,
        val num_comments: Int,
        val created: Long,
        val thumbnail: String,
        val url: String
)