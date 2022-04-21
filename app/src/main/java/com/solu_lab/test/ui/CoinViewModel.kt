package com.solu_lab.test.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solu_lab.test.data.model.CoinItem
import com.solu_lab.test.data.repo.CoinRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinViewModel @Inject constructor(
    private val coinRepository: CoinRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(CoinUiState())
    val uiState: Flow<CoinUiState>
        get() = _uiState

    fun getCoinList() {
        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            try {
                val coins = coinRepository.getCoins()
                _uiState.update {
                    it.copy(isLoading = false, coinList = coins)
                }
            } catch (th: Throwable) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = "Something went Wrong, please try again later"
                    )
                }
                Log.d("myApplication", th.localizedMessage, th)
            }
        }
    }
}

data class CoinUiState(
    val isLoading: Boolean = false,
    val coinList: List<CoinItem> = emptyList(),
    val errorMessage: String? = null
)