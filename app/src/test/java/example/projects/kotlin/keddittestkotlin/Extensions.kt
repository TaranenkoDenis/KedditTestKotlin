package example.projects.kotlin.keddittestkotlin

import org.mockito.Mockito

/**
 * Created by Denis Taranenko on 23.01.2018 - 8:34.
 */
inline fun <reified T : Any> mock(): T = Mockito.mock(T::class.java)