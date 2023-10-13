package com.example.tugasweek51.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.tugasweek51.model.TebakAngkaUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.random.Random

class TebakAngkaViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(TebakAngkaUiState())
    val uiState: StateFlow<TebakAngkaUiState> = _uiState.asStateFlow()

    var InputUser by mutableStateOf(0)
        private set

    private fun merandomAngka(): Int{
        val angkaRandom = Random.nextInt(1, 11)
        _uiState.update { currentState ->
            currentState.copy(
                angkaRandom = angkaRandom
            )
        }
        return angkaRandom
    }

    fun pengecekInputanUser(){
        if (InputUser == uiState.value.angkaRandom){
            val scoreTerbaru = _uiState.value.score.plus(1)
            val banyakTebakanUserTerbaru = _uiState.value.banyakTebakanUser.plus(1)
            gameBerakhir(scoreTerbaru,banyakTebakanUserTerbaru)
        } else {
            val banyakTebakanUserTerbaru = _uiState.value.banyakTebakanUser.plus(1)
            gameBerakhir(_uiState.value.score,banyakTebakanUserTerbaru)
        }
    }

    private fun gameBerakhir (banyakTebakanUserTerbaru: Int, scoreTerbaru: Int){
        if (banyakTebakanUserTerbaru >= 3){
            _uiState.update { currentState ->
                currentState.copy(
                    score = scoreTerbaru,
                    gameBerakhir = true
                )
            }
            merandomAngka()
        } else {
            _uiState.update { currentState ->
                currentState.copy(
                    score = scoreTerbaru,
                    banyakTebakanUser = banyakTebakanUserTerbaru,
                    gameBerakhir = false
                )
            }
            merandomAngka()
        }
    }

    fun bermainLagi() {
        _uiState.value = TebakAngkaUiState(merandomAngka())
    }

    init {
        bermainLagi()
    }



}

