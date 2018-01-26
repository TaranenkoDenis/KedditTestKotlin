package example.projects.kotlin.keddittestkotlin.net

/**
 * Created by Denis Taranenko on 08.12.2017 - 15:44.
 */
class RedditDataResponse (
        val children: List<RedditChildrenResponse>,
        val after: String?,
        val before: String?
)