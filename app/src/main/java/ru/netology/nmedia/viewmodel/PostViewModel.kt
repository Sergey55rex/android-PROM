package ru.netology.nmedia.viewmodel

import android.app.Application
import androidx.lifecycle.*
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.model.FeedModel
import ru.netology.nmedia.repository.*
import ru.netology.nmedia.util.SingleLiveEvent
import java.io.IOException

private val empty = Post(
    id = 0,
    content = "",
    author = "",
    authorAvatar = "" ,
    likedByMe = false,
    likes = 0,
    published = ""
)

class PostViewModel(application: Application) : AndroidViewModel(application) {
    // упрощённый вариант
    private val repository: PostRepository = PostRepositoryImpl()
    private val _data = MutableLiveData(FeedModel())
    val data: LiveData<FeedModel>
        get() = _data
    val edited = MutableLiveData(empty)
    private val _postCreated = SingleLiveEvent<Unit>()
    val postCreated: LiveData<Unit>
        get() = _postCreated

    init {
        loadPosts()
    }

    fun loadPosts() {
        _data.value = FeedModel(loading = true)
        repository.getAllAsync(object : PostRepository.Callback<List<Post>> {
            override fun onSuccess(posts: List<Post>) {
                _data.postValue(FeedModel(posts = posts, empty = posts.isEmpty()))
            }

            override fun onError(e: Exception) {
                _data.postValue(FeedModel(error = true))
            }
        })
    }

    fun save() {
        edited.value?.let {

            repository.saveAsync(it, object : PostRepository.Callback<Post> {
                override fun onSuccess(post: Post) {
                }
                override fun onError(e: Exception) {}
            })
            _postCreated.postValue(Unit)

        }
        edited.value = empty
    }

    fun edit(post: Post) {
        edited.value = post
    }

    fun changeContent(content: String) {
        val text = content.trim()
        if (edited.value?.content == text) {
            return
        }
        edited.value = edited.value?.copy(content = text)
    }

    fun likeById(id: Long) {
        repository.likeByIdAsync(id, object : PostRepository.Callback<Post> {
            override fun onSuccess(post: Post) {
            }
            override fun onError(e: Exception) {}
        })
    }

    fun removeById(id: Long) {

        // Оптимистичная модель
        val old = _data.value?.posts.orEmpty()
        _data.postValue(
                _data.value?.copy(posts = _data.value?.posts.orEmpty()
                        .filter { it.id != id }
                )
        )
        try {
            repository.removeByIdAsync(id, object : PostRepository.Callback<Unit>{
                override fun onSuccess(post: Unit) {
                }
                override fun onError(e: Exception) {}
            })
        } catch (e: IOException) {
            _data.postValue(_data.value?.copy(posts = old))
        }
    }
}
