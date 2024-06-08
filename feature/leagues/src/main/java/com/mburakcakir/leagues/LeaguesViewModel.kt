package com.mburakcakir.leagues

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mburakcakir.common.extensions.collectAsState
import com.mburakcakir.common.extensions.notNullOrEmpty
import com.mburakcakir.domain.usecase.LeaguesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LeaguesViewModel @Inject constructor(
    private val leaguesUseCase: LeaguesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(LeaguesUIState())
    val uiState: StateFlow<LeaguesUIState> = _uiState.asStateFlow()

    init {
        getLeagues()
    }

    private fun getLeagues() {
        viewModelScope.launch(Dispatchers.IO) {
            leaguesUseCase().collectAsState(
                onLoading = { _uiState.update { it.copy(isLoading = true) } },
                onSuccess = { data ->
                    data.leagues.notNullOrEmpty {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                leagues = data
                            )
                        }
                    }
                }
            )
        }
    }
}