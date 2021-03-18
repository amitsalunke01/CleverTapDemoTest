package com.amitsalunke.clevertapsdkdemo

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {

    sealed class ButtonClickEvents {
        data class ErrorEvent(val msg: String) : ButtonClickEvents()
        data class SuccessEvent(val msg: String) : ButtonClickEvents()
    }

    private val eventChannel = Channel<ButtonClickEvents>()
    val eventFlow = eventChannel.receiveAsFlow()

    fun sendProductViewAndProfile(
        productViewdMap: Map<String, Any>,
        profileMap: HashMap<String, Any>
    ) = viewModelScope.launch {

        CleverTapSDKApplication.cleverTapDefaultInstance?.onUserLogin(profileMap)
        CleverTapSDKApplication.cleverTapDefaultInstance?.pushEvent(
            "Product viewed",
            productViewdMap
        )
        Log.d("VM", "data sent")
        eventChannel.send(ButtonClickEvents.SuccessEvent("sent successfully"))
    }
}