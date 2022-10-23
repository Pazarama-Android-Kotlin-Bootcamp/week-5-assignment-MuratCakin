package com.muratcakin.userposts.ui.users.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.muratcakin.userposts.data.model.DataState
import com.muratcakin.userposts.data.model.PostDTO
import com.muratcakin.userposts.data.model.Users
import com.muratcakin.userposts.data.model.UsersDTO
import com.muratcakin.userposts.data.repository.UsersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import okhttp3.internal.threadName
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(private val usersRepository: UsersRepository) : ViewModel() {

    private var _usersLiveData = MutableLiveData<DataState<List<UsersDTO>?>>()
    val usersLiveData: LiveData<DataState<List<UsersDTO>?>>
        get() = _usersLiveData

    private val _eventStateLiveData = MutableLiveData<UsersViewEvent>()
    val eventStateLiveData: LiveData<UsersViewEvent>
        get() = _eventStateLiveData

    init {
        getUsers()
    }

    private fun getUsers() {
        _usersLiveData.value = DataState.Loading()
        usersRepository.getUsers().enqueue(object : Callback<List<Users>> {
            override fun onResponse(call: Call<List<Users>>, response: Response<List<Users>>) {
                if (response.isSuccessful) {
                    response.body()?.let {

                        _usersLiveData.value = DataState.Success(it.map { safeUsers -> UsersDTO(
                                id = safeUsers.id,
                                address = safeUsers.address,
                                company = safeUsers.company,
                                email = safeUsers.email,
                                name = safeUsers.name,
                                phone = safeUsers.phone,
                                username = safeUsers.username,
                                website = safeUsers.website
                            )
                        })

                    } ?: kotlin.run {
                        _usersLiveData.value = DataState.Error("Data Empty")
                    }
                } else {
                    _usersLiveData.value = DataState.Error(response.message())
                }
            }

            override fun onFailure(call: Call<List<Users>>, t: Throwable) {
                _usersLiveData.value = DataState.Error(t.message.toString())
                _eventStateLiveData.postValue(UsersViewEvent.ShowMessage(t.message.toString()))
            }
        })
    }
}

sealed class UsersViewEvent {
    object NavigateToDetail : UsersViewEvent()
    class ShowMessage(val message: String?) : UsersViewEvent()
}