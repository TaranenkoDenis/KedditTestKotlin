package example.projects.kotlin.keddittestkotlin

import android.support.v4.app.Fragment
import rx.subscriptions.CompositeSubscription

/**
 * Created by Denis Taranenko on 08.12.2017 - 12:50.
 */
open class RxBaseFragment() : Fragment() {
    protected var subscriptions = CompositeSubscription()

    override fun onResume() {
        super.onResume()
        subscriptions = CompositeSubscription()
    }

    override fun onPause() {
        super.onPause()
        if (!subscriptions.isUnsubscribed) {
            subscriptions.unsubscribe()
        }
        subscriptions.clear()
    }
}