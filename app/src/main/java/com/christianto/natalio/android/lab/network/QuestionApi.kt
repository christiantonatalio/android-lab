package com.christianto.natalio.android.lab.network

import com.christianto.natalio.android.lab.model.Question
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface QuestionApi {
    @GET("world.json")
    suspend fun getAllQuestions(): ArrayList<Question>
}