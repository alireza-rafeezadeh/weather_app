package com.app.core.data.repository

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

open class BaseDataSource {

    suspend fun <T : Any> flowOnIO(api: suspend () -> Response<T>) =
        flow<ResultWrapper<T>> {
            try {
                val response = api.invoke()
//                val body = handleResponse(response)

                if (response.isSuccessful) {

//                    response.body()

                    response.body()?.let {
                        emit(ResultWrapper.Success(it))
                    }
                } else {
                    if (response.code() == 400) {
                        emit(ResultWrapper.ErrorString(response.message()))
                    }
                }

//                body?.let { emit(it) }
            } catch (e: Exception) {
                Log.i("flow_tag", "flowOnIO: ${e.message}")
                ResultWrapper.Success("Unknown Error occurred!")
            }
        }.flowOn(Dispatchers.IO)


//    private fun <T> handleResponse(response: Response<T>): Result {
//        if (response.code() == 200) {
//
//            return ResultWrapper.Success(response.message())
//        }
//        return response.body()
//    }


}


sealed class ResultWrapper<out T : Any> {
    data class Success<out T : Any>(val data: T) : ResultWrapper<T>()
    data class Error(val exception : Exception) : ResultWrapper<Nothing>()
    data class ErrorString(val exception : String) : ResultWrapper<Nothing>()
    object InProgress : ResultWrapper<Nothing>()
}