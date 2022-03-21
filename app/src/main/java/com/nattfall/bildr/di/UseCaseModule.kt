package com.nattfall.bildr.di

import com.nattfall.bildr.data.repository.PhotosRepository
import com.nattfall.bildr.gallery.PicturesProvides
import com.nattfall.bildr.gallery.PicturesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class UseCaseModule {

    @Singleton
    @Provides
    fun provideGalleryUseCase(photosRepository: PhotosRepository): PicturesUseCase =
        PicturesProvides(photosRepository = photosRepository)

}
