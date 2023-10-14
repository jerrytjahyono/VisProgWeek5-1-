package com.example.tugasweek51.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tugasweek51.viewmodel.IpkViewModel

@Composable
fun IpkView(
    ipkViewModel: IpkViewModel = viewModel ()
){
    val ipkUiState by ipkViewModel.uiState.collectAsState()

    LazyColumn(
        modifier = Modifier.
        padding(horizontal = 10.dp, vertical = 10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ){
        item {
            Text(
                text = "Course",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold
            )
        }
        item {
            Text(
                text = "Total SKS: ${ipkUiState.totalSks}",
                fontSize = 20.sp,
            )
        }
        item{
            Text(
                text = "IPK: ${ipkUiState.ipk}",
                fontSize = 20.sp,
            )
        }
        item { 
            Row (
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ){
                CustomAnswer(
                    value = ipkViewModel.inputSks,
                    onValueChanged = { ipkViewModel.menerimaInputanDariSks(it) },
                    text = "Sks",
                    keyboardOptions =
                        KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next
                        ),
                    modifier = Modifier
                        .weight(1f)
                )

                CustomAnswer(
                    value = ipkViewModel.inputScore,
                    onValueChanged = { ipkViewModel.menerimaInputanDariScore(it) },
                    text = "Score",
                    keyboardOptions =
                    KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    modifier = Modifier
                        .weight(1f)
                )
            }
        }
        item {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                CustomAnswer(
                    value = ipkViewModel.inputNama,
                    onValueChanged = { ipkViewModel.menerimaInputanDariNama(it) },
                    text = "Name",
                    keyboardOptions =
                    KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    )
                )

                Button(
                    onClick = {
                        ipkViewModel.buttonAddDiKlik()
                    },
                    modifier = Modifier
                        .fillMaxSize(1f)
                )
                {
                    Icon(imageVector = Icons.Filled.Add, contentDescription = "Kontol")
                }
            }
        }
        itemsIndexed(ipkUiState.listMataKuliah){
                index,matkul ->
            Card (
                modifier = Modifier
                    .fillMaxWidth(),
            ){
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Column (
                        verticalArrangement = Arrangement.spacedBy(5.dp)
                    ){
                        Text(
                            text = "Name: ${matkul.nama}",
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp
                        )
                        Text(
                            text = "SKS: ${matkul.sks}",
                            fontSize = 10.sp
                        )
                        Text(
                            text = "Score: ${matkul.score}",
                            fontSize = 10.sp
                        )
                    }
                    Button(
                        onClick = { ipkViewModel.buttonDeleteDiKlik(index.toString()) },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent
                        )
                    ) {
                        Icon(imageVector = Icons.Filled.Delete,
                            contentDescription = "Delete cok",
                            tint = Color.Red
                        )
                    }
                }
            }

        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomAnswer(
    value: String,
    onValueChanged: (String) -> Unit,
    text: String,
    keyboardOptions: KeyboardOptions,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChanged,
        label = { Text(text = text) },
        keyboardOptions = keyboardOptions,
        modifier = modifier
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun IpkPriview(){
    IpkView()
}