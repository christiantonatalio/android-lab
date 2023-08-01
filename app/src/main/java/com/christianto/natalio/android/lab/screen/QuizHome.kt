package com.christianto.natalio.android.lab.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.christianto.natalio.android.lab.component.QuestionDisplay
import com.christianto.natalio.android.lab.util.AppColors

@Composable
fun QuizHome(viewModel: QuestionViewModel = hiltViewModel()) {
    val questions = viewModel.data.value.data?.toMutableList()
    var index by rememberSaveable {
        mutableStateOf(0)
    }
    var optionChosen by rememberSaveable {
        mutableStateOf<Int?>(null)
    }
    var isCorrectChoice by rememberSaveable {
        mutableStateOf<Boolean?>(null)
    }
    var score by rememberSaveable {
        mutableStateOf(0.0)
    }

    Surface(
        modifier = Modifier.fillMaxSize(), color = AppColors.mDarkPurple
    ) {
        if (viewModel.data.value.loading == true) {
            Box(
                modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            questions?.getOrNull(index)?.let { question ->
                Column {
                    ShowProgress(score)
                    QuestionDisplay(
                        question = question,
                        questionIndex = index + 1,
                        totalQuestions = questions.size,
                        optionChosen = optionChosen,
                        isCorrectChoice = isCorrectChoice,
                        onAnswered = {
                            optionChosen = it
                            question.choices.getOrNull(it)?.let { option ->
                                isCorrectChoice = option == question.answer
                            }
                        }
                    ) {
                        if (isCorrectChoice == true) {
                            score += eachScoreCounter(index.toDouble(), questions.size.toDouble())
                        }
                        optionChosen = null
                        index += 1
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun ShowProgress(score: Double = 0.0) {
    val gradient = Brush.linearGradient(listOf(Color(0xFFF95075), Color(0xFFBE6BE5)))
    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(40.dp)
            .border(
                width = 2.dp,
                brush = Brush.verticalGradient(
                    colors = listOf(
                        AppColors.mLightPurple,
                        AppColors.mLightPurple
                    )
                ),
                shape = RoundedCornerShape(34.dp)
            )
            .clip(RoundedCornerShape(34.dp))
            .background(Color.Transparent),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.CenterStart
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth(score.toFloat() * 0.01f)
                    .fillMaxHeight()
                    .background(brush = gradient),
                elevation = 0.dp,
                contentColor = Color.Transparent,
                backgroundColor = Color.Transparent
            ) {}
            val formattedScore = "%.2f".format(score)
            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = formattedScore,
                color = Color.White
            )
        }
    }
}

fun eachScoreCounter(index: Double, size: Double) = (index + 1) * 100 / size