package com.example.emiyo.feature.onboarding

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.example.emiyo.core.design.WardrobeSurface
import com.example.emiyo.feature.opening.OpeningScreen
import kotlinx.coroutines.launch

/**
 * WARDROBE Complete Interactive Onboarding Flow with Horizontal Pager
 *
 * Coordinates:
 * - Screen 01: OpeningScreen ("DRESS WITH PURPOSE")
 * - Screen 02: OnboardingScreen2 ("Smarter choices. Better looks.")
 * - Screen 03: OnboardingScreen3 ("From your wardrobe to your best look.")
 *
 * Features:
 * - 1:1 finger slide/swipe gestures between screens 1, 2, and 3
 * - Functional bottom progress slider reflecting current active screen
 * - Clickable indicator segments that smoothly scroll to selected screen
 * - Animated Swipe-Up indicator and gesture on Screen 3 transitioning to Screen 4 (Create Account)
 * - Hardware back press handling across pages
 */
@Composable
fun OnboardingFlowScreen(
    initialPage: Int = 0,
    onCreateAccountClick: () -> Unit = {},
    onSignInClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState(
        initialPage = initialPage.coerceIn(0, 2),
        pageCount = { 3 }
    )
    val coroutineScope = rememberCoroutineScope()

    // Hardware back navigation between onboarding pages
    BackHandler(enabled = pagerState.currentPage > 0) {
        coroutineScope.launch {
            pagerState.animateScrollToPage(pagerState.currentPage - 1)
        }
    }

    HorizontalPager(
        state = pagerState,
        modifier = modifier
            .fillMaxSize()
            .background(WardrobeSurface)
    ) { page ->
        when (page) {
            0 -> {
                OpeningScreen(
                    onGetStartedClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(1)
                        }
                    },
                    onAlreadyHaveAccountClick = onSignInClick
                )
            }
            1 -> {
                OnboardingScreen2(
                    onContinue = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(2)
                        }
                    },
                    onNavigateToPrevious = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(0)
                        }
                    },
                    onStepClick = { targetPage ->
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(targetPage)
                        }
                    }
                )
            }
            2 -> {
                OnboardingScreen3(
                    onContinue = onCreateAccountClick,
                    onNavigateToPrevious = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(1)
                        }
                    },
                    onStepClick = { targetPage ->
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(targetPage)
                        }
                    }
                )
            }
        }
    }
}
