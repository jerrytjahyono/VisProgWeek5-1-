package com.example.tugasweek51.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.tugasweek51.model.IpkUiState
import com.example.tugasweek51.model.MataKuliah
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class IpkViewModel:ViewModel(
) {
    private val _uiState = MutableStateFlow(IpkUiState())
    val uiState: StateFlow<IpkUiState> = _uiState.asStateFlow()

    var inputNama by mutableStateOf("")
        private set

    var inputSks by mutableStateOf("")
        private set

    var inputScore by mutableStateOf("")
        private set

    fun menerimaInputanDariNama(nama: String){
        inputNama = nama
    }
    fun menerimaInputanDariSks(sks: String){
        inputSks = sks
    }

    fun buttonAddDiKlik (){
        tambahMataKuliah()
    }

    fun buttonDeleteDiKlik(index: String){
        deleteMataKuliah(index.toInt())
    }
    fun menerimaInputanDariScore(score: String){
        inputScore = score
    }
    private fun tambahMataKuliah () {
        var mataKuliahSementara = MataKuliah(nama = inputNama, sks = inputSks.toInt(),score = inputScore.toDouble())

        var sementaraListMataKuliah = uiState.value.listMataKuliah
        sementaraListMataKuliah.add(mataKuliahSementara)
        _uiState.update { currentState ->
            currentState.copy(
                listMataKuliah = sementaraListMataKuliah
            )
        }
        calculateIpkAndSks()
    }

    private fun totalSks (){
        var sksTerbaru = 0
        uiState.value.listMataKuliah.forEach {
            sksTerbaru += it.sks
        }
        _uiState.update { stateTerkini ->
            stateTerkini.copy(
                totalSks = sksTerbaru
            )
        }

    }

    private fun totalIpk (){
        var sementaraTotalIpk = 0.00
       if (uiState.value.listMataKuliah.isNotEmpty()){
           uiState.value.listMataKuliah.forEach {
               sementaraTotalIpk += (it.score * it.sks)
           }
           _uiState.update {
               it.copy(
                   ipk = (sementaraTotalIpk / uiState.value.totalSks)
               )
           }
       }else{
           _uiState.update {
               it.copy(
                   ipk = sementaraTotalIpk
               )
           }
       }

    }

    fun deleteMataKuliah(index: Int){
        var arraySementaraAkhiratSelamanya = uiState.value.listMataKuliah
        arraySementaraAkhiratSelamanya.removeAt(index)

        _uiState.update {
            it.copy(listMataKuliah = arraySementaraAkhiratSelamanya)
        }
        calculateIpkAndSks()
    }

    fun start(){
        _uiState.value = IpkUiState(0,0.00, arrayListOf())
    }

    private  fun calculateIpkAndSks(){
        totalSks()
        totalIpk()
    }
    init {
        start()
    }

}