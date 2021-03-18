package com.amitsalunke.clevertapsdkdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.amitsalunke.clevertapsdkdemo.databinding.ActivityMainBinding
import com.clevertap.android.sdk.CleverTapAPI
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collect

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainActivityViewModel

    private lateinit var bindingMainActivity: ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMainActivity = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingMainActivity.root)

        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        with(bindingMainActivity) {
            btnProductView.setOnClickListener {
                val profileUpdate = HashMap<String, Any>()
                profileUpdate["Email"] = "amitsalunke01@gmail.com"
                val prodViewedAction = mapOf(
                    "Product ID" to 1,
                    "Product Image" to "https://d35fo82fjcw0y8.cloudfront.net/2018/07/26020307/customer-success-clevertap.jpg",
                    "Product Name" to "CleverTap",
                )
                viewModel.sendProductViewAndProfile(
                    productViewdMap = prodViewedAction,
                    profileMap = profileUpdate
                )
            }
        }
        clickEventFlow()
    }

    private fun clickEventFlow() {
        lifecycleScope.launchWhenStarted {
            viewModel.eventFlow.collect { event ->

                when (event) {
                    is MainActivityViewModel.ButtonClickEvents.SuccessEvent -> {
                        Snackbar.make(bindingMainActivity.root, event.msg, Snackbar.LENGTH_SHORT)
                            .show()
                    }

                    is MainActivityViewModel.ButtonClickEvents.ErrorEvent -> {
                        Snackbar.make(bindingMainActivity.root, event.msg, Snackbar.LENGTH_SHORT)
                            .show()
                    }
                }

            }
        }


    }
}