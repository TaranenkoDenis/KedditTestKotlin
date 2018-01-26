package example.projects.kotlin.keddittestkotlin

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Denis Taranenko on 24.01.2018 - 8:32.
 */
@Module
class AppModule(val app: KedditApp){

    @Provides
    @Singleton
    fun provideContext(): Context {
        return app
    }

    @Provides
    @Singleton
    fun provideApplication(): KedditApp{
        return app
    }
}