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
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.emiyo.R
import com.example.emiyo.core.design.BodoniModaFontFamily
import com.example.emiyo.core.design.EmailAuthButton
import com.example.emiyo.core.design.GoogleAuthButton
import com.example.emiyo.core.design.InterFontFamily
import com.example.emiyo.core.design.SignInPrompt
import com.example.emiyo.core.design.WardrobeCharcoal
import com.example.emiyo.core.design.WardrobeSubtitleText
import com.example.emiyo.core.design.WardrobeSurface
import com.example.emiyo.core.design.WardrobeTheme

/**
 * WARDROBE "Create Your Account" Screen (Screen 04)
 *
 * Visual Source of Truth: screen.png / code.html / style.css
 *
 * Features:
 * - High-fashion editorial photography of seated model in warm ivory linen suit
 * - Seamless soft atmospheric gradient fade from photograph to solid ivory background (#FBF9F5)
 * - Major editorial Bodoni Moda headline: "Create your\naccount."
 * - Supporting narrative copy in Inter: personalization explanation
 * - Primary authentication action: Continue with Google (#1E1C1A + official Google G logo)
 * - Secondary authentication action: Continue with Email (subtle border + mail outline)
 * - Restrained sign-in link with sage accent underline: "Already have an account? Sign in"
 * - Responsive edge-to-edge layout supporting 360x800, 390x844, 412x915+
 */
@Composable
fun CreateAccountScreen(
    onContinueWithGoogle: () -> Unit = {},
    onContinueWithEmail: () -> Unit = {},
    onSignInClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
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
                .verticalScroll(rememberScrollState())
        ) {
            // Layer 1: Editorial Hero Photography Section
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(heroHeight)
            ) {
                // High-resolution local fashion model photo
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

                // Soft gradient overlay: fades photograph seamlessly into ivory content section
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                0.0f to WardrobeSurface.copy(alpha = 0.25f),
                                0.40f to Color.Transparent,
                                0.75f to WardrobeSurface.copy(alpha = 0.55f),
                                0.92f to WardrobeSurface.copy(alpha = 0.90f),
                                1.0f to WardrobeSurface
                            )
                        )
                )
            }

            // Layer 2: Authentication Content & Actions Section
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(WardrobeSurface)
                    .padding(horizontal = 28.dp)
                    .padding(top = 4.dp, bottom = 24.dp)
                    .navigationBarsPadding(),
                horizontalAlignment = Alignment.Start
            ) {
                // Editorial Headline
                Text(
                    text = "Create your\naccount.",
                    fontFamily = BodoniModaFontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = if (isCompact) 36.sp else 40.sp,
                    lineHeight = if (isCompact) 39.sp else 43.sp,
                    letterSpacing = (-1).sp,
                    color = WardrobeCharcoal,
                    textAlign = TextAlign.Start
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Explanatory Subtitle
                Text(
                    text = "It only takes a minute. We’ll use this information to personalize your experience and recommendations.",
                    fontFamily = InterFontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    color = WardrobeSubtitleText,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.widthIn(max = 310.dp)
                )

                Spacer(modifier = Modifier.height(if (isCompact) 20.dp else 28.dp))

                // Primary Action: Continue with Google
                GoogleAuthButton(
                    onClick = onContinueWithGoogle,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Secondary Action: Continue with Email
                EmailAuthButton(
                    onClick = onContinueWithEmail,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(if (isCompact) 18.dp else 24.dp))

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

@Preview(name = "Standard 390x844 - screen.png Reference", widthDp = 390, heightDp = 844, showBackground = true)
@Composable
private fun CreateAccountStandardPreview() {
    WardrobeTheme {
        CreateAccountScreen()
    }
}

@Preview(name = "Compact 360x800", widthDp = 360, heightDp = 800, showBackground = true)
@Composable
private fun CreateAccountCompactPreview() {
    WardrobeTheme {
        CreateAccountScreen()
    }
}

@Preview(name = "Large 412x915", widthDp = 412, heightDp = 915, showBackground = true)
@Composable
private fun CreateAccountLargePreview() {
    WardrobeTheme {
        CreateAccountScreen()
    }
}
