package ru.netology.nmedia.repository

import androidx.lifecycle.LiveData
import ru.netology.nmedia.dto.Post

interface PostRepository {
    fun getAllAsync(callback: GetAllCallback)
    fun likeByIdAsync(callback: PostCallback)
    fun saveAsync(callback: PostCallback)
    fun removeByIdAsync(callback: PostCallback)

    interface GetAllCallback {
        fun onSuccess(posts: List<Post>) {}
        fun onError(e: Exception) {}
    }

    interface PostCallback {
        fun onSuccess(post: Post) {}
        fun onError(e: Exception) {}
    }
}
