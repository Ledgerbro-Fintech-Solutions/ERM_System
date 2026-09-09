package com.example.emiyo.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.emiyo.core.design.WardrobeSurface
import com.example.emiyo.feature.auth.CreateAccountScreen
import com.example.emiyo.feature.auth.EmailSignInScreen
import com.example.emiyo.feature.auth.EmailSignUpScreen
import com.example.emiyo.feature.auth.SignInScreen
import com.example.emiyo.feature.capture.FaceCaptureScreen
import com.example.emiyo.feature.capture.FullBodyCaptureScreen
import com.example.emiyo.feature.home.WardrobeHomeScreen
import com.example.emiyo.feature.onboarding.OnboardingFlowScreen
import com.example.emiyo.feature.onboarding.UnderstandYourFaceScreen

/**
 * WARDROBE App Destinations
 */
sealed interface WardrobeScreen {
    data class Onboarding(val initialPage: Int = 0) : WardrobeScreen
    data object CreateAccount : WardrobeScreen
    data object SignIn : WardrobeScreen
    data object EmailSignUp : WardrobeScreen
    data object EmailSignIn : WardrobeScreen
    data object UnderstandYourFace : WardrobeScreen
    data object FaceCapture : WardrobeScreen
    data object FullBodyCapture : WardrobeScreen
    data object Home : WardrobeScreen
}

/**
 * Centralized Type-Safe Navigation Host for WARDROBE
 *
 * Coordinates:
 * 1. Onboarding Flow (Horizontal Pager across Screens 01, 02, 03)
 *    - 1:1 finger swipe/slide gestures between screens
 *    - Functional, animated bottom progress slider indicating current active screen
 *    - Clickable indicator pills to jump/scroll to any screen
 *    - Animated Swipe-Up indicator and gesture on Screen 03 to reach Screen 04
 * 2. Screen 04: CreateAccountScreen ("Create your account." with Google/Email)
 * 3. Screen 05: SignInScreen ("Welcome back." with Google/Email)
 * 4. Screen 06: EmailSignUpScreen ("Create your account with email")
 * 5. Screen 07: EmailSignInScreen ("Sign in with email & password")
 * 6. Screen 10: UnderstandYourFaceScreen ("Understand your face." - Editorial Face Analysis)
 * 7. Screen 12: FaceCaptureScreen ("Let's capture your face." - Guided Camera & Gallery Capture)
 */
@Composable
fun WardrobeApp(
    startDestination: WardrobeScreen = WardrobeScreen.UnderstandYourFace,
    modifier: Modifier = Modifier
) {
    var backStack by remember {
        mutableStateOf(
            when (startDestination) {
                is WardrobeScreen.FaceCapture -> listOf(
                    WardrobeScreen.Onboarding(0),
                    WardrobeScreen.UnderstandYourFace,
                    startDestination
                )
                is WardrobeScreen.UnderstandYourFace -> listOf(
                    WardrobeScreen.Onboarding(0),
                    startDestination
                )
                else -> listOf(startDestination)
            }
        )
    }
    val currentScreen = backStack.lastOrNull() ?: startDestination

    fun navigateTo(screen: WardrobeScreen) {
        if (backStack.lastOrNull() != screen) {
            backStack = backStack + screen
        }
    }

    fun navigateBack() {
        if (backStack.size > 1) {
            backStack = backStack.dropLast(1)
        }
    }

    fun replaceWith(screen: WardrobeScreen) {
        backStack = if (backStack.isNotEmpty()) {
            backStack.dropLast(1) + screen
        } else {
            listOf(screen)
        }
    }

    // Hardware/gesture system back support across screens
    BackHandler(enabled = backStack.size > 1) {
        navigateBack()
    }

    AnimatedContent(
        targetState = currentScreen,
        transitionSpec = {
            fadeIn(animationSpec = tween(350)) togetherWith fadeOut(animationSpec = tween(350))
        },
        label = "WardrobeScreenTransition",
        modifier = modifier
            .fillMaxSize()
            .background(WardrobeSurface)
    ) { screen ->
        when (screen) {
            is WardrobeScreen.Onboarding -> {
                OnboardingFlowScreen(
                    initialPage = screen.initialPage,
                    onCreateAccountClick = {
                        navigateTo(WardrobeScreen.CreateAccount)
                    },
                    onSignInClick = {
                        navigateTo(WardrobeScreen.SignIn)
                    }
                )
            }

            is WardrobeScreen.CreateAccount -> {
                CreateAccountScreen(
                    onContinueWithGoogle = {
                        navigateTo(WardrobeScreen.UnderstandYourFace)
                    },
                    onContinueWithEmail = {
                        navigateTo(WardrobeScreen.EmailSignUp)
                    },
                    onSignInClick = {
                        replaceWith(WardrobeScreen.SignIn)
                    }
                )
            }

            is WardrobeScreen.SignIn -> {
                SignInScreen(
                    onContinueWithGoogle = {
                        navigateTo(WardrobeScreen.UnderstandYourFace)
                    },
                    onContinueWithEmail = {
                        navigateTo(WardrobeScreen.EmailSignIn)
                    },
                    onSignUpClick = {
                        replaceWith(WardrobeScreen.CreateAccount)
                    }
                )
            }

            is WardrobeScreen.EmailSignUp -> {
                EmailSignUpScreen(
                    onCreateAccount = { _, _ ->
                        navigateTo(WardrobeScreen.UnderstandYourFace)
                    },
                    onSignInClick = {
                        replaceWith(WardrobeScreen.EmailSignIn)
                    },
                    onBackClick = {
                        navigateBack()
                    }
                )
            }

            is WardrobeScreen.EmailSignIn -> {
                EmailSignInScreen(
                    onSignIn = { _, _ ->
                        navigateTo(WardrobeScreen.UnderstandYourFace)
                    },
                    onForgotPasswordClick = {
                        // Hook for forgot password flow
                    },
                    onSignUpClick = {
                        replaceWith(WardrobeScreen.EmailSignUp)
                    },
                    onBackClick = {
                        navigateBack()
                    }
                )
            }

            is WardrobeScreen.UnderstandYourFace -> {
                UnderstandYourFaceScreen(
                    onContinue = {
                        navigateTo(WardrobeScreen.FaceCapture)
                    },
                    onBackClick = {
                        navigateBack()
                    }
                )
            }

            is WardrobeScreen.FaceCapture -> {
                FaceCaptureScreen(
                    onContinue = {
                        navigateTo(WardrobeScreen.FullBodyCapture)
                    },
                    onBackClick = {
                        navigateBack()
                    }
                )
            }

            is WardrobeScreen.FullBodyCapture -> {
                FullBodyCaptureScreen(
                    onContinue = {
                        navigateTo(WardrobeScreen.Home)
                    },
                    onBackClick = {
                        navigateBack()
                    }
                )
            }

            is WardrobeScreen.Home -> {
                WardrobeHomeScreen(
                    onViewAnalysis = {},
                    onSeeTheLook = {},
                    onScanNow = {},
                    onViewWardrobe = {},
                    onAskStylist = {},
                    onExploreMore = {},
                    onWardrobeTab = {},
                    onTryTab = {},
                    onStylistTab = {}
                )
            }
        }
    }
}
