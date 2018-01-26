package example.projects.kotlin.keddittestkotlin.commons.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup


/**
 * Created by Denis Taranenko on 08.12.2017 - 10:33.
 */
class LoadingDelegateAdapter : ViewTypeDelegateAdapter {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType){
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder = TurnsViewHolder(parent)
}