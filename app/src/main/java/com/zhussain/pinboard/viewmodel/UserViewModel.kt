package com.zhussain.pinboard.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zhussain.pinboard.network.UsersApi
import com.zhussain.pinboard.model.UsersProperty
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


/**
 * The [UserViewModel] that is attached to the [UserFragment].
 */

enum class UsersApiStatus { LOADING, ERROR, DONE }
class UserViewModel : ViewModel() {
    // The internal MutableLiveData String that stores the status of the most recent request
    private val _status = MutableLiveData<UsersApiStatus>()

    // The external immutable LiveData for the request status String
    val status: LiveData<UsersApiStatus>
        get() = _status

    // Internally, we use a MutableLiveData, because we will be updating the List of MarsProperty
    // with new values
    private val _userProperties = MutableLiveData<List<UsersProperty>>()

    // The external LiveData interface to the property is immutable, so only this class can modify
    val userProperties: LiveData<List<UsersProperty>>
        get() = _userProperties


    // Create a Coroutine scope using a job to be able to cancel when needed
    private var userModelJob = Job()

    // the Coroutine runs using the Main (UI) dispatcher
    private val coroutineScope = CoroutineScope(userModelJob + Dispatchers.Main)


    /**
     * Call getUserProperties() on init so we can display status immediately.
     */
    init {
        getUserProperties()
    }

    /**
     * Sets the value of the _userProperties LiveData to the Users API.
     *
     */
    private fun getUserProperties() {

        coroutineScope.launch {
            // Get the Deferred object for our Retrofit request
            var getPropertiesDeferred = UsersApi.retrofitService.getProperties()
            try {
                // Await the completion of our Retrofit request
                _status.value = UsersApiStatus.LOADING
                var listResult = getPropertiesDeferred.await()
                if (listResult.size > 0){
                    _userProperties.value = listResult
                    //_status.value = "Success: ${listResult.size} Mars properties retrieved"
                    _status.value = UsersApiStatus.DONE
                }
            } catch (e: Exception) {
                _status.value = UsersApiStatus.ERROR
                _userProperties.value = ArrayList() // no data..
                //_status.value = "Failure: ${e.message}"
            }
        }
    }

    /**
     * When the [ViewModel] is finished, we cancel our coroutine [viewModelJob], which tells the
     * Retrofit service to stop.
     */
    override fun onCleared() {
        super.onCleared()
        userModelJob.cancel()
    }

}