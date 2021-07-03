package com.app.core.data.repository

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

open class BaseDataSource {

    suspend fun <T> flowOnIO(api: suspend () -> Response<T>) =
        flow<T> {
            try {
                val response = api.invoke()
                val body = handleResponse(response)
                body?.let { emit(it) }
            } catch (e: Exception) {
                Log.i("flow_tag", "flowOnIO: ${e.message}")
            }
        }.flowOn(Dispatchers.IO)


    private fun <T> handleResponse(response: Response<T>): T? {
        if (response.code() == 200) {
            return response.body()
        }
        return response.body()
    }


}