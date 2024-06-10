package com.mburakcakir.teamdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mburakcakir.common.destination.TableDestination
import com.mburakcakir.common.extensions.collectAsState
import com.mburakcakir.common.extensions.getGsonFromJson
import com.mburakcakir.common.extensions.isNotNullAndEmpty
import com.mburakcakir.common.extensions.notNull
import com.mburakcakir.domain.model.teamdetail.TeamDetail
import com.mburakcakir.domain.usecase.StandingsUseCase
import com.mburakcakir.network.model.Season
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeamDetailViewModel @Inject constructor(
    private val standingsUseCase: StandingsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val headerList = mutableListOf("OM", "G", "B", "M", "AG", "YG", "A", "P", "S")
    private val teamDetail =
        savedStateHandle.get<String>(TableDestination.TeamDetail.arguments.first())!!
            .getGsonFromJson<TeamDetail>()
    private val _uiState =
        MutableStateFlow(
            TeamDetailUiState(
                isLoading = true,
                teamDetail = teamDetail,
                headerList = headerList
            )
        )
    val uiState: StateFlow<TeamDetailUiState> = _uiState.asStateFlow()

    init {
        setSeasons()
    }

    private fun setSeasons() {
        viewModelScope.launch {
            val seasonStart = uiState.value.teamDetail?.seasons?.last()?.year.toString()
            val seasonEnd = uiState.value.teamDetail?.seasons?.first()?.year?.plus(1).toString()
            _uiState.update {
                it.copy(
                    isLoading = false,
                    seasonStart = seasonStart,
                    seasonEnd = seasonEnd,
                    selectedStandings = mutableListOf(
                        StandingSeason(
                            standings = teamDetail?.standings?.selectedStanding,
                            season = teamDetail?.standings?.selectedSeason
                        )
                    ),
                )
            }
        }
    }

    fun getStandings(season: Season?, sort: String = "asc") {
        val leagueId = _uiState.value.teamDetail?.standings?.leagueId
        if (leagueId.isNotNullAndEmpty() && season?.year != null) {
            viewModelScope.launch(Dispatchers.IO) {
                standingsUseCase(leagueId!!, season.year!!, sort).collectAsState(
                    onLoading = { _uiState.update { it.copy(isLoading = true) } },
                    onSuccess = { data ->
                        data.standings.notNull { standings ->
                            val teamName =
                                _uiState.value.teamDetail?.standings?.selectedStanding?.teamName
                            val filteredStandings = standings.filter { it.teamName == teamName }
                            val selectedSeasons = filteredStandings.map { season }
                            val standingSeason =
                                StandingSeason(filteredStandings.first(), selectedSeasons.first())
                            _uiState.update {
                                it.copy(
                                    isLoading = false,
                                    selectedStandings = ((it.selectedStandings)?.plus(
                                        standingSeason
                                    ))?.sortedByDescending { it?.season?.year }?.toMutableList(),
                                )
                            }
                        }
                    }
                )
            }
        }
    }
}
