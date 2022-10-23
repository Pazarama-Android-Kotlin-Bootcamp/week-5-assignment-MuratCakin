package com.muratcakin.userposts.ui.comments.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.muratcakin.userposts.data.local.database.entity.CommentEntity
import com.muratcakin.userposts.data.model.*
import com.muratcakin.userposts.data.repository.CommentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class CommentsViewModel @Inject constructor(
    private val commentRepository: CommentRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private var _commentLiveData = MutableLiveData<DataState<List<CommentDTO>?>>()
    val commentLiveData: LiveData<DataState<List<CommentDTO>?>>
        get() = _commentLiveData

    private val _eventStateLiveData = MutableLiveData<CommentsViewModel>()
    val eventStateLiveData: LiveData<CommentsViewModel>
        get() = _eventStateLiveData

    init {
        savedStateHandle.get<Int>("userId")?.let {
            Log.d("DENEME2", "MODEL GELEN ${it}")

        }
        val postId = savedStateHandle.get<Int>("userId")
        getComments(postId)


    }

    private fun getComments(postId: Int?) {
        _commentLiveData.postValue(DataState.Loading())
        if (postId != null) {
            commentRepository.getCommentById(postId).enqueue(object : Callback<Comment> {
                override fun onResponse(call: Call<Comment>, response: Response<Comment>) {
                    if (response.isSuccessful) {
                        response.body()?.let {

                            _commentLiveData.postValue(DataState.Success(listOf(CommentDTO(
                                id = it.id,
                                name = it.name,
                                email = it.email,
                                postId = it.postId,
                                body = it.body,
                                isFavorite = isExists(it.id)
                            ))))

                        } ?: kotlin.run {
                            _commentLiveData.postValue(DataState.Error("Data Empty"))
                        }
                    } else {
                        _commentLiveData.postValue(DataState.Error(response.message()))
                    }
                }

                override fun onFailure(call: Call<Comment>, t: Throwable) {
                    _commentLiveData.postValue(t.message?.let { DataState.Error(it) })
                }
            })
        }
    }
    private fun isExists(postId: Int?): Boolean {
        postId?.let {
            commentRepository.getCommentById(it)?.let {
                return true
            }
        }
        return false
    }
    fun onFavoriteComment(comment: CommentDTO) {
        commentRepository.getCommentByIdForFavorite(comment.id ?: 0)?.let {
            commentRepository.deleteFavoriteComment(
                comment.id.toString()
            )
            updateFavoriteState( comment.id,false)
        } ?: kotlin.run {
            commentRepository.insertFavoriteComment(
                CommentEntity(
                    commentId = comment.id.toString(),
                    commentEmail = comment.email,
                    commentBody = comment.body,
                )
            )
            updateFavoriteState( comment.id,true)
        }
    }
    private fun updateFavoriteState(id:Int?, isFavorite:Boolean){
        when(val current= _commentLiveData.value){
            is DataState.Success -> {
                val currentList= current.data?.map {
                    if (it.id==id){
                        it.copy(isFavorite = isFavorite)

                    }
                    else it
                }
                _commentLiveData.value=DataState.Success(currentList)
            }
            is DataState.Error -> {}
            is DataState.Loading -> {}

            null -> {}
        }
    }


}