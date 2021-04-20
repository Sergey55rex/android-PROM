package ru.netology.nmedia.repository

import androidx.lifecycle.LiveData
import ru.netology.nmedia.dto.Post

interface PostRepository {
    fun getAll(): List<Post>
    fun likeById(id: Long)
    fun save(post: Post)
    fun removeById(id: Long)

    fun getAllAsync(callback: GetAllCallback)

    fun likeByIdAsync(callback: LikeByCallback)
    fun saveAsync(callback: SaveCallback)
    fun removeByIdAsync(callback: RemoveByIdCallback)

    interface GetAllCallback {
        fun onSuccess(posts: List<Post>) {}
        fun onError(e: Exception) {}
    }

    interface LikeByCallback {
        fun onSuccess(post: Post) {}
        fun onError(e: Exception) {}
    }

    interface SaveCallback {
        fun onSuccess(post: Post) {}
        fun onError(e: Exception) {}
    }

    interface RemoveByIdCallback {
        fun onSuccess(post: Post) {}
        fun onError(e: Exception) {}
    }

}
