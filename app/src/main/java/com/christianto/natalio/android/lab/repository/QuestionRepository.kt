package com.christianto.natalio.android.lab.repository

import com.christianto.natalio.android.lab.data.DataOrException
import com.christianto.natalio.android.lab.model.Question
import com.christianto.natalio.android.lab.network.QuestionApi
import javax.inject.Inject

class QuestionRepository @Inject constructor(
    private val api: QuestionApi
) {
    private val dataOrException = DataOrException<ArrayList<Question>, Boolean, Exception>()

    suspend fun getAllQuestion(): DataOrException<ArrayList<Question>, Boolean, Exception> {
        try {
            dataOrException.apply {
                loading = true
                data = api.getAllQuestions()
                if (dataOrException.data.toString().isNotEmpty()) loading = false
            }
        } catch (exception: Exception) {
            dataOrException.e = exception
        }
        return dataOrException
    }
}