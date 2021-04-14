package ru.netology.nmedia.repository

import androidx.lifecycle.LiveData
import ru.netology.nmedia.dto.Post

interface PostRepository {
    fun getAll(): List<Post>
    fun likeById(id: Long)
    fun save(post: Post)
    fun removeById(id: Long)

    fun getAllAsync(callback: GetAllCallback)

    fun likeByIdAsync(callback: GetAllCallback, id: Long)
    fun saveAsync(callback: GetAllCallback, posts: Post)
    fun removeByIdAsync(callback: GetAllCallback, id: Long)

    interface GetAllCallback {
        fun onSuccess(posts: List<Post>) {}
        fun onError(e: Exception) {}
    }

}
