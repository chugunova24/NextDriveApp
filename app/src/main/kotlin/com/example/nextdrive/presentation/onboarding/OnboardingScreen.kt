package com.example.nextdrive.presentation.onboarding

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

import com.example.nextdrive.R


data class OnboardingPage(
    val imageRes: Int,
    val title: String,
    val description: String
)


val onboardingPages = listOf(
    OnboardingPage(
        imageRes = R.drawable.onboarding1,
        title = "Аренда автомобилей",
        description = "Открой для себя удобный и доступный способ передвижения"
    ),
    OnboardingPage(
        imageRes = R.drawable.onboarding2,
        title = "Безопасно и удобно",
        description = "Арендуй автомобиль и наслаждайся его удобством"
    ),
    OnboardingPage(
        imageRes = R.drawable.onboarding3,
        title = "Лучшие предложения",
        description = "Выбирай понравившееся среди сотен доступных автомобилей"
    )
)


@Composable
fun PageIndicator(
    pageCount: Int,
    currentPage: Int
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        repeat(pageCount) { index ->
            val color = if (index == currentPage) Color.Blue else Color.Gray
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .padding(4.dp)
                    .background(color, shape = CircleShape)
            )
        }
    }
}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardingScreen(onFinish: () -> Unit) {
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

    Box(modifier = Modifier.fillMaxSize()) {
        HorizontalPager(
            count = onboardingPages.size,
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            OnboardingPageContent(onboardingPage = onboardingPages[page])
        }

        // Индикатор страниц
        PageIndicator(
            pageCount = onboardingPages.size,
            currentPage = pagerState.currentPage
        )


        // Кнопка "Пропустить"
        TextButton(
            onClick = onFinish,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp)
        ) {
            Text(
                text = "Пропустить",
                color = Color(0xFF2A1246)
            )
        }

        // Кнопки "Далее" и "Поехали"
        Button(
            onClick = {
                if (pagerState.currentPage == onboardingPages.size - 1) {
                    onFinish()
                } else {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF2A1246)),
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Text(
                text = if (pagerState.currentPage == onboardingPages.size - 1) "Поехали" else "Далее",
                color = Color.White
            )
        }
    }
}


@Composable
fun OnboardingPageContent(onboardingPage: OnboardingPage) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = onboardingPage.imageRes),
            contentDescription = null,
            modifier = Modifier.size(200.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = onboardingPage.title,
            style = MaterialTheme.typography.h5,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = onboardingPage.description,
            style = MaterialTheme.typography.body1,
            textAlign = TextAlign.Center
        )
    }
}

fun saveOnboardingCompleted(context: Context) {
    val sharedPreferences = context.getSharedPreferences("app_preferences", Context.MODE_PRIVATE)
    sharedPreferences.edit().putBoolean("onboarding_completed", true).apply()
}

fun isOnboardingCompleted(context: Context): Boolean {
    val sharedPreferences = context.getSharedPreferences("app_preferences", Context.MODE_PRIVATE)
    return sharedPreferences.getBoolean("onboarding_completed", false)
}




@Preview(showBackground = true)
@Composable
fun PreviewOnboardingScreen() {
    OnboardingScreen({})
}