package com.solu_lab.test.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.solu_lab.test.R
import com.solu_lab.test.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CoinActivity : AppCompatActivity() {
    private val viewModel: CoinViewModel by lazy {
        ViewModelProvider(this)[CoinViewModel::class.java]
    }
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setting up ui
        installSplashScreen()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        //sending events to viewModel
        viewModel.getCoinList()

        //observing ui state
        observeLoading()
        observeCoinList()
    }

    private fun observeLoading() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.map {
                    it.isLoading
                }.collect { isLoading ->
                    binding.progressBar.isVisible = isLoading
                }
            }
        }
    }

    private fun observeCoinList() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.map {
                    it.coinList
                }.collect { coins ->
                    Log.d("myApplication", coins.toString())
                }
            }
        }
    }
}