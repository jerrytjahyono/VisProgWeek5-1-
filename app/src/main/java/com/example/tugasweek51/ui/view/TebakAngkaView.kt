package com.example.tugasweek51.ui.view

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tugasweek51.ui.theme.TugasWeek51Theme
import com.example.tugasweek51.viewmodel.TebakAngkaViewModel


@Composable
fun TebakAngkaView( tebakAngkaViewModel: TebakAngkaViewModel = viewModel()){
    val tebakAngkaUiState by tebakAngkaViewModel.uiState.collectAsState()

    Column (
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(10.dp, 10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "Guest The Number",
            modifier = Modifier
                .padding(10.dp,10.dp,10.dp,10.dp),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )


        Column (
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(10.dp, 10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){

            Text(
                text = "Guest The Number",
                modifier = Modifier
                    .padding(10.dp,10.dp,10.dp,10.dp),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Card (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp, 10.dp, 10.dp, 10.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp, 10.dp, 10.dp, 10.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Column(
                        modifier = Modifier
                            .background(color = Color.DarkGray, shape = RoundedCornerShape(10.dp))
                            .align(Alignment.End)
                            .padding(7.dp, 7.dp, 7.dp, 7.dp)

                    ) {
                        Text(
                            text = "Number of Guesses: ${tebakAngkaUiState.banyakTebakanUser}",
                            modifier = Modifier,
                            color = Color.White,
                        )
                    }

                    Text(
                        text = "${tebakAngkaUiState.angkaRandom}",
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(10.dp, 10.dp),
                        fontSize = 40.sp
                    )
                    Text(
                        text = "From 1 to 10 Guess The Number",
                        modifier = Modifier
                            .padding(10.dp, 10.dp),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Score: ${tebakAngkaUiState.score}",
                        fontWeight = FontWeight.Bold,
                    )

                    CustomAnswerField(
                        value = tebakAngkaViewModel.InputUser.toString(),
                        onValueChanged = { tebakAngkaViewModel.InputUser },
                        text = "Enter Your Word",
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Done
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                }
            }

            Button(
                onClick = {
                    tebakAngkaViewModel.pengecekInputanUser()
                },
                modifier = Modifier
                    .fillMaxWidth(),
                enabled = true
            ){
                Text(text = "Submit")
            }

            if (tebakAngkaUiState.gameBerakhir) {
                dialogMuncul(
                    score = tebakAngkaUiState.score,
                    bermainLagi = { tebakAngkaViewModel.bermainLagi() }
                )
            }

        }

    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomAnswerField(
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
        modifier = modifier,
        shape = RoundedCornerShape(20.dp),
    )
}

@Composable
fun dialogMuncul(
    score: Int ,
    bermainLagi: () -> Unit
){

    val activity = (LocalContext.current as Activity)

    AlertDialog(
        onDismissRequest =
        {},

        title =
        {
            Text(text = "Welp!")
        },

        text =
        {
            "Your Score: $score"
        },
        dismissButton = {
            TextButton(
                onClick = {
                    activity.finish()
                }
            ) {
                Text(text = "Exit")
            }
        },
        confirmButton = {
            TextButton(onClick = bermainLagi ) {
                Text(text = "Play Again")
            }
        }
    )
}



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TebakAngkaPreview() {
    TugasWeek51Theme{
        TebakAngkaView()
    }
}