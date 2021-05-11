package ru.netology.nmedia.repository

import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.netology.nmedia.api.PostsApi
import ru.netology.nmedia.dto.Post

class PostRepositoryImpl: PostRepository {
    override fun getAllAsync(callback: PostRepository.Callback<List<Post>>) {
        PostsApi.retrofitService.getAll().enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (!response.isSuccessful) {
                    when (response.code()) {
                        in 400..499 -> {
                            callback.onError(RuntimeException(response.message()))
                            return
                        }
                        in 500..599 -> {
                            callback.onError(RuntimeException(response.message()))
                            return
                        }
                    }
                }
                callback.onSuccess(response.body() ?: throw RuntimeException("body is null"))
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                callback.onError(RuntimeException(t))
            }
        })
    }

    override fun saveAsync(post: Post, callback: PostRepository.Callback<Post>) {
        PostsApi.retrofitService.save(post).enqueue(object : Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                if (!response.isSuccessful) {
                    when (response.code()) {
                        in 400..499 -> {
                            callback.onError(RuntimeException(response.message()))
                            return
                        }
                        in 500..599 -> {
                            callback.onError(RuntimeException(response.message()))
                            return
                        }
                    }
                }
                callback.onSuccess(response.body() ?: throw RuntimeException("body is null"))
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                callback.onError(RuntimeException(t))
            }
        })
    }

    override fun removeByIdAsync(id: Long, callback: PostRepository.Callback<Unit>) {
        PostsApi.retrofitService.removeById(id).enqueue(object : Callback<Unit> {
            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                if (!response.isSuccessful) {
                    when (response.code()) {
                        in 400..499 -> {
                            callback.onError(RuntimeException(response.message()))
                            return
                        }
                        in 500..599 -> {
                            callback.onError(RuntimeException(response.message()))
                            return
                        }
                    }
                }
            }

            override fun onFailure(call: Call<Unit>, t: Throwable) {
                callback.onError(RuntimeException(t))
            }
        })
    }

    override fun likeByIdAsync(id: Long, callback: PostRepository.Callback<Post>) {
        PostsApi.retrofitService.likeById(id).enqueue(object : Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                if (!response.isSuccessful) {
                    when (response.code()) {
                        in 400..499 -> {
                            callback.onError(RuntimeException(response.message()))
                            return
                        }
                        in 500..599 -> {
                            callback.onError(RuntimeException(response.message()))
                            return
                        }
                    }
                }
                callback.onSuccess(response.body() ?: throw RuntimeException("body is null"))
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                callback.onError(RuntimeException(t))
            }
        })
    }
}

