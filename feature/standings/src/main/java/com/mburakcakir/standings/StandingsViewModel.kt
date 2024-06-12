package com.mburakcakir.standings

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mburakcakir.common.destination.TableDestination
import com.mburakcakir.common.extensions.collectAsState
import com.mburakcakir.common.extensions.isNotNullAndEmpty
import com.mburakcakir.common.extensions.notNull
import com.mburakcakir.common.extensions.notNullOrEmpty
import com.mburakcakir.domain.usecase.SeasonsUseCase
import com.mburakcakir.domain.usecase.StandingsUseCase
import com.mburakcakir.model.standings.Season
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
    private val seasonsUseCase: SeasonsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val leagueId = savedStateHandle.get<String>(TableDestination.Standings.arguments[0])
    private val leagueIcon = savedStateHandle.get<String>(TableDestination.Standings.arguments[1])

    private val headerList = mutableListOf("OM", "G", "B", "M", "AG", "YG", "A", "P")

    private val _uiState =
        MutableStateFlow(
            StandingsUiState(
                headerList = headerList,
                leagueId = leagueId,
                leagueIcon = leagueIcon
            )
        )
    val uiState: StateFlow<StandingsUiState> = _uiState.asStateFlow()

    init {
        leagueId.notNullOrEmpty {
            getSeasons(it)
        }
    }

    fun getStandings(season: Season?, sort: String = "asc") {
        if (leagueId.isNotNullAndEmpty() && season?.year != null)
            viewModelScope.launch(Dispatchers.IO) {
                standingsUseCase(leagueId!!, season.year!!, sort).collectAsState(
                    onLoading = { _uiState.update { it.copy(isLoading = true) } },
                    onSuccess = { data ->
                        if (data.standings != null) {
                            _uiState.update {
                                it.copy(
                                    isLoading = false,
                                    standings = data,
                                    selectedSeason = season
                                )
                            }
                        }
                    }
                )
            }
    }

    private fun getSeasons(leagueId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            seasonsUseCase(leagueId).collectAsState(
                onLoading = { _uiState.update { it.copy(isLoading = true) } },
                onSuccess = { data ->
                    data.seasonInfo.notNull { seasonInfo ->
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                seasons = seasonInfo
                            )
                        }
                        seasonInfo.seasons?.first()?.notNull {
                            getStandings(
                                season = it
                            )
                        }
                    }
                }
            )
        }
    }
}