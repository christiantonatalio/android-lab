package com.christianto.natalio.android.lab.di

import com.christianto.natalio.android.lab.network.QuestionApi
import com.christianto.natalio.android.lab.repository.QuestionRepository
import com.christianto.natalio.android.lab.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideQuestionApi(): QuestionApi = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(QuestionApi::class.java)

    @Singleton
    @Provides
    fun provideQuestionRepository(
        api: QuestionApi
    ) = QuestionRepository(api)
}