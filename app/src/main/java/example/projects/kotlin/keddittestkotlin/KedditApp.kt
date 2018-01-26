package example.projects.kotlin.keddittestkotlin

import android.app.Application

/**
 * Created by Denis Taranenko on 24.01.2018 - 8:27.
 */
class KedditApp : Application() {

    companion object {
        lateinit var newsComponent: NewsComponent
    }

    override fun onCreate() {
        super.onCreate()

        newsComponent = DaggerNewsComponent.builder()
                .appModule(AppModule(this))
                //.newsModule(NewsModule()) Module with empty constructor is implicitly created by dagger.
                .build()

    }
}