package com.example.emiyo.feature.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.emiyo.R
import com.example.emiyo.core.design.BodoniModaFontFamily
import com.example.emiyo.core.design.InterFontFamily
import com.example.emiyo.core.design.PrimaryAuthButton
import com.example.emiyo.core.design.SignInPrompt
import com.example.emiyo.core.design.WardrobeCharcoal
import com.example.emiyo.core.design.WardrobeMuted
import com.example.emiyo.core.design.WardrobeSurface
import com.example.emiyo.core.design.WardrobeTextField
import com.example.emiyo.core.design.WardrobeTheme

/**
 * WARDROBE "Continue with Email / Create Your Account with Email" Screen
 *
 * Screen Family: WARDROBE Authentication (Screen 04/05/06)
 *
 * Features:
 * - Identical seated fashion model photography in ivory architectural setting
 * - Seamless soft atmospheric gradient fade to solid #FBF9F5
 * - Bodoni Moda editorial headline: "Create your\naccount."
 * - Concise supporting copy: "Create your account to save your style profile and get personalized recommendations."
 * - Refined custom WARDROBE text inputs for Email, Password, and Confirm Password with subtle sage focus (#7D8B72)
 * - Text-based SHOW / HIDE password visibility toggle
 * - Gentle, quiet luxury validation feedback
 * - Signature full-width pill CTA button: "CREATE ACCOUNT →" (#7D8B72)
 * - Centered "Already have an account? Sign in" prompt
 * - Full edge-to-edge support with soft keyboard scroll accommodation (imePadding)
 */
@Composable
fun EmailSignUpScreen(
    onCreateAccount: (String, String) -> Unit = { _, _ -> },
    onSignInClick: () -> Unit = {},
    onBackClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val focusManager = LocalFocusManager.current

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    var emailError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }
    var confirmPasswordError by remember { mutableStateOf<String?>(null) }

    fun validateAndSubmit() {
        var isValid = true

        if (email.isBlank() || !email.contains("@") || !email.contains(".")) {
            emailError = "Enter a valid email address"
            isValid = false
        } else {
            emailError = null
        }

        if (password.length < 6) {
            passwordError = "Use at least 6 characters"
            isValid = false
        } else {
            passwordError = null
        }

        if (confirmPassword != password) {
            confirmPasswordError = "Passwords don't match"
            isValid = false
        } else {
            confirmPasswordError = null
        }

        if (isValid) {
            focusManager.clearFocus()
            onCreateAccount(email, password)
        }
    }

    BoxWithConstraints(
        modifier = modifier
            .fillMaxSize()
            .background(WardrobeSurface)
    ) {
        val screenHeight = maxHeight
        val isCompact = screenHeight < 820.dp
        val heroHeight = if (isCompact) screenHeight * 0.50f else screenHeight * 0.53f

        Column(
            modifier = Modifier
                .fillMaxSize()
                .imePadding()
                .verticalScroll(rememberScrollState())
        ) {
            // Layer 1: Editorial Hero Photography Section (Identical to Create Account screen)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(heroHeight)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.wardrobe_create_account_hero),
                    contentDescription = "Editorial fashion model seated in luxury linen suit",
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.TopCenter,
                    modifier = Modifier.fillMaxSize()
                )

                // Atmospheric top scrim for status bar clarity
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    WardrobeSurface.copy(alpha = 0.35f),
                                    Color.Transparent
                                )
                            )
                        )
                )

                // Soft atmospheric gradient overlay fading seamlessly into ivory content section
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                0.0f to WardrobeSurface.copy(alpha = 0.20f),
                                0.40f to Color.Transparent,
                                0.72f to WardrobeSurface.copy(alpha = 0.50f),
                                0.90f to WardrobeSurface.copy(alpha = 0.88f),
                                1.0f to WardrobeSurface
                            )
                        )
                )
            }

            // Layer 2: Authentication Content & Form Section
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(WardrobeSurface)
                    .padding(horizontal = 28.dp)
                    .padding(top = 2.dp, bottom = 24.dp)
                    .navigationBarsPadding(),
                horizontalAlignment = Alignment.Start
            ) {
                // Editorial Headline
                Text(
                    text = "Create your\naccount.",
                    fontFamily = BodoniModaFontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = if (isCompact) 34.sp else 38.sp,
                    lineHeight = if (isCompact) 38.sp else 42.sp,
                    letterSpacing = (-1).sp,
                    color = WardrobeCharcoal,
                    textAlign = TextAlign.Start
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Concise Supporting Copy
                Text(
                    text = "Create your account to save your style profile and get personalized recommendations.",
                    fontFamily = InterFontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    color = WardrobeMuted,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.widthIn(max = 320.dp)
                )

                Spacer(modifier = Modifier.height(if (isCompact) 18.dp else 24.dp))

                // Field 1: Email Address
                WardrobeTextField(
                    value = email,
                    onValueChange = {
                        email = it
                        if (emailError != null) emailError = null
                    },
                    label = "EMAIL ADDRESS",
                    placeholder = "Enter your email",
                    errorMessage = emailError,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = { focusManager.moveFocus(FocusDirection.Down) }
                    )
                )

                Spacer(modifier = Modifier.height(14.dp))

                // Field 2: Password
                WardrobeTextField(
                    value = password,
                    onValueChange = {
                        password = it
                        if (passwordError != null) passwordError = null
                    },
                    label = "PASSWORD",
                    placeholder = "Create a password",
                    isPassword = true,
                    errorMessage = passwordError,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = { focusManager.moveFocus(FocusDirection.Down) }
                    )
                )

                Spacer(modifier = Modifier.height(14.dp))

                // Field 3: Confirm Password
                WardrobeTextField(
                    value = confirmPassword,
                    onValueChange = {
                        confirmPassword = it
                        if (confirmPasswordError != null) confirmPasswordError = null
                    },
                    label = "CONFIRM PASSWORD",
                    placeholder = "Re-enter your password",
                    isPassword = true,
                    errorMessage = confirmPasswordError,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = { validateAndSubmit() }
                    )
                )

                Spacer(modifier = Modifier.height(if (isCompact) 20.dp else 26.dp))

                // Primary CTA: CREATE ACCOUNT →
                PrimaryAuthButton(
                    text = "CREATE ACCOUNT",
                    onClick = { validateAndSubmit() },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(if (isCompact) 16.dp else 22.dp))

                // Sign-In Prompt Footer
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    SignInPrompt(
                        onSignInClick = onSignInClick
                    )
                }
            }
        }
    }
}

@Preview(name = "Standard 390x844", widthDp = 390, heightDp = 844, showBackground = true)
@Composable
private fun EmailSignUpStandardPreview() {
    WardrobeTheme {
        EmailSignUpScreen()
    }
}

@Preview(name = "Compact 360x800", widthDp = 360, heightDp = 800, showBackground = true)
@Composable
private fun EmailSignUpCompactPreview() {
    WardrobeTheme {
        EmailSignUpScreen()
    }
}

@Preview(name = "Large 412x915", widthDp = 412, heightDp = 915, showBackground = true)
@Composable
private fun EmailSignUpLargePreview() {
    WardrobeTheme {
        EmailSignUpScreen()
    }
}
