package com.mburakcakir.teamdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mburakcakir.common.destination.TableDestination
import com.mburakcakir.common.extensions.getGsonFromJson
import com.mburakcakir.domain.model.teamdetail.TeamDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeamDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val teamDetail =
        savedStateHandle.get<String>(TableDestination.TeamDetail.arguments.first())!!
            .getGsonFromJson<TeamDetail>()
    private val _uiState =
        MutableStateFlow(TeamDetailUiState(isLoading = true, teamDetail = teamDetail))
    val uiState: StateFlow<TeamDetailUiState> = _uiState.asStateFlow()

    init {
        setSeasons()
    }

    private fun setSeasons() {
        viewModelScope.launch {
            delay(500)
            val seasonStart = uiState.value.teamDetail?.seasons?.last()?.year.toString()
            val seasonEnd = uiState.value.teamDetail?.seasons?.first()?.year?.plus(1).toString()
            _uiState.update {
                it.copy(
                    isLoading = false,
                    seasonStart = seasonStart,
                    seasonEnd = seasonEnd
                )
            }
        }
    }
}
