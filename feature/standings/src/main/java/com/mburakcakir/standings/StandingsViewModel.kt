package com.mburakcakir.standings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mburakcakir.common.extensions.collectAsState
import com.mburakcakir.common.extensions.notNull
import com.mburakcakir.domain.usecase.SeasonsUseCase
import com.mburakcakir.domain.usecase.StandingsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StandingsViewModel @Inject constructor(
    private val standingsUseCase: StandingsUseCase,
    private val seasonsUseCase: SeasonsUseCase
) : ViewModel() {

    private val headerList = mutableListOf("OM", "G", "B", "M", "AG", "YG", "A", "P")
    private val _uiState = MutableStateFlow(StandingsUiState(headerList = headerList))
    val uiState: StateFlow<StandingsUiState> = _uiState.asStateFlow()

    fun getStandings(leagueId: String, season: Int, sort: String = "asc") {
        viewModelScope.launch(Dispatchers.IO) {
            standingsUseCase(leagueId, season, sort).collectAsState(
                onLoading = { _uiState.update { it.copy(isLoading = true) } },
                onSuccess = { data ->
                    if (data.standings != null) {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                standings = data
                            )
                        }
                    }

                }
            )
        }
    }

    fun getSeasons(league: String) {
        viewModelScope.launch(Dispatchers.IO) {
            seasonsUseCase(league).collectAsState(
                onLoading = { _uiState.update { it.copy(isLoading = true) } },
                onSuccess = { data ->
                    data.seasonInfo.notNull { seasonInfo ->
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                seasons = seasonInfo
                            )
                        }
                        seasonInfo.seasons?.first()?.year.notNull {
                            getStandings(
                                leagueId = league,
                                season = it
                            )
                        }
                    }
                }
            )
        }
    }
}