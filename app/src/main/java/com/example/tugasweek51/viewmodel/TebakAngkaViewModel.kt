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

    var inputUser by mutableStateOf("")
        private set

    fun updateInputUser(inputUserView: String){
        inputUser = inputUserView
    }

    private fun generateAngkaRandom(): Int{
        return Random.nextInt(1, 11)
    }

    fun randomAngkaTebakan(){
        _uiState.update { kokontol ->
            kokontol.copy(
                angkaRandom = generateAngkaRandom()
            )
        }
    }
    fun pengecekInputanUser(){
        if (inputUser.toInt() == uiState.value.angkaRandom){
            val scoreTerbaru = _uiState.value.score.plus(1)
            val banyakTebakanUserTerbaru = _uiState.value.banyakTebakanUser.plus(1)
            gameBerakhir(
                scoreTerbaru =  scoreTerbaru,
                banyakTebakanUserTerbaru =  banyakTebakanUserTerbaru)
            randomAngkaTebakan()
        } else {
            val banyakTebakanUserTerbaru = _uiState.value.banyakTebakanUser.plus(1)
            gameBerakhir(banyakTebakanUserTerbaru =   banyakTebakanUserTerbaru)
        }
    }

    private fun gameBerakhir (
        banyakTebakanUserTerbaru: Int,
        scoreTerbaru: Int = uiState.value.score){
        if (banyakTebakanUserTerbaru >= 3){
            _uiState.update { currentState ->
                currentState.copy(
                    score = scoreTerbaru,
                    gameBerakhir = true
                )
            }

        } else {
            _uiState.update { currentState ->
                currentState.copy(
                    score = scoreTerbaru,
                    banyakTebakanUser = banyakTebakanUserTerbaru,
                )
            }
        }
    }

    fun bermainLagi() {
        _uiState.value = TebakAngkaUiState(angkaRandom = generateAngkaRandom())
    }

    init {
        bermainLagi()
    }



}

