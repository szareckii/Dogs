package com.zareckii.dogs.domain.usecase

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.zareckii.dogs.data.network.model.ErrorMessage
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import com.zareckii.dogs.data.network.Result
import retrofit2.HttpException
import java.io.IOException


abstract class UseCase<in P, R>(private val coroutineDispatcher: CoroutineDispatcher) {
    suspend operator fun invoke(parameters: P): Result<R> {
        return try {
            withContext(coroutineDispatcher) {
                execute(parameters).let {
                    Result.Success(it)
                }
            }
        } catch (e: Exception) {
            when (e) {
                is HttpException -> {
                    try {
                        val errorResponse: ErrorMessage? = Gson().fromJson(
                            e.response()?.errorBody()?.charStream(),
                            object : TypeToken<ErrorMessage>() {}.type
                        )
                        Result.ErrorResponse(
                            ErrorMessage(
                                httpException = e.code(),
                                code = errorResponse?.code ?: 0,
                                message = errorResponse?.message ?: ""
                            )
                        )
                    } catch (e: java.lang.Exception) {
                        Result.Error(e)
                    }
                }

                is IOException -> {
                    Result.Error(e)
                }

                else -> {
                    Result.Error(e)
                }
            }
        }
    }

    @Throws(RuntimeException::class)
    protected abstract suspend fun execute(parameters: P): R
}
