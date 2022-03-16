package com.nattfall.bildr.di

import com.nattfall.bildr.data.repository.PhotosRepository
import com.nattfall.bildr.networking.flickr.FlickrMediaRetriever
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {
    @Singleton
    @Provides
    fun providePhotosRepository(flickrMediaRetriever: FlickrMediaRetriever): PhotosRepository =
        PhotosRepository(flickrMediaRetriever)

    @Singleton
    @Provides
    fun provideFlickerMediaRetriever(retrofit: Retrofit): FlickrMediaRetriever =
        FlickrMediaRetriever(retrofit)

}
