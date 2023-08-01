package com.christianto.natalio.android.lab.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.christianto.natalio.android.lab.model.Question
import com.christianto.natalio.android.lab.util.AppColors

@Preview
@Composable
fun QuestionDisplay(
    question: Question = Question(
        question = "LFD2 was banned in Australia.",
        answer = "True",
        category = "world",
        choices = listOf("False", "True")
    ),
    questionIndex: Int = 10,
    totalQuestions: Int = 100,
    optionChosen: Int? = null,
    isCorrectChoice: Boolean? = null,
    onAnswered: (Int) -> Unit = {},
    onNextClicked: () -> Unit = {}
) {
    val pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
    Column(
        modifier = Modifier.padding(24.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        QuestionTracker(counter = questionIndex, outOf = totalQuestions)
        DrawDottedLine(pathEffect = pathEffect)
        Text(
            modifier = Modifier
                .fillMaxHeight(0.3f)
                .padding(8.dp),
            text = question.question,
            color = AppColors.mOffWhite,
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 22.sp
        )
        Column(
            modifier = Modifier.padding(8.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            question.choices.forEachIndexed { index, answerText ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .clip(RoundedCornerShape(15.dp))
                        .clickable {
                            onAnswered(index)
                        },
                    border = BorderStroke(2.dp, AppColors.mOffDarkPurple),
                    backgroundColor = getOptionBackgroundColor(
                        isCorrectChoice, index, optionChosen
                    ),
                    shape = RoundedCornerShape(15.dp),
                    elevation = 0.dp
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(40.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = answerText
                        )
                    }
                }
            }
            Button(onClick = {
                onNextClicked()
            }) {
                Text(text = "Next")
            }
        }
    }
}

@Composable
fun QuestionTracker(counter: Int, outOf: Int) {
    Text(modifier = Modifier.padding(8.dp), text = buildAnnotatedString {
        withStyle(style = ParagraphStyle(textIndent = TextIndent.None)) {
            withStyle(
                style = SpanStyle(
                    color = AppColors.mLightGray, fontWeight = FontWeight.Bold, fontSize = 27.sp
                )
            ) {
                append("Question $counter/")
            }
            withStyle(
                style = SpanStyle(
                    color = AppColors.mLightGray,
                    fontWeight = FontWeight.Light,
                    fontSize = 14.sp
                )
            ) {
                append("$outOf")
            }
        }

    })
}

@Composable
fun DrawDottedLine(pathEffect: PathEffect) {
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
    ) {
        drawLine(
            color = AppColors.mLightGray,
            start = Offset(0f, 0f),
            end = Offset(size.width, 0f),
            pathEffect = pathEffect
        )
    }
}

fun getOptionBackgroundColor(
    isCorrectChoice: Boolean?, index: Int, optionChosen: Int?
): Color {
    return when {
        isCorrectChoice == true && index == optionChosen -> Color.Green
        isCorrectChoice == false && index == optionChosen -> Color.Red
        else -> Color.Transparent
    }
}