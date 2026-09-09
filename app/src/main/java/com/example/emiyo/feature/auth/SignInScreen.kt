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
import com.example.emiyo.core.design.AuthDivider
import com.example.emiyo.core.design.BodoniModaFontFamily
import com.example.emiyo.core.design.EmailAuthButton
import com.example.emiyo.core.design.GoogleAuthButton
import com.example.emiyo.core.design.InterFontFamily
import com.example.emiyo.core.design.SignUpPrompt
import com.example.emiyo.core.design.WardrobeCharcoal
import com.example.emiyo.core.design.WardrobeMuted
import com.example.emiyo.core.design.WardrobeSurface
import com.example.emiyo.core.design.WardrobeTheme

/**
 * WARDROBE "Sign In / Welcome Back" Screen (Screen 05)
 *
 * Visual Source of Truth: image.png / style.css
 *
 * Features:
 * - High-fashion editorial photography of seated model in warm ivory linen suit
 * - Subtle multi-stop gradient transition from photography into solid ivory background (#FBF9F5)
 * - Major editorial Bodoni Moda headline: "Welcome back." (single line)
 * - Supporting narrative copy in Inter: "Sign in to continue your style journey with\nWARDROBE."
 * - Primary authentication action: Continue with Google (#1A1715 + official Google G logo, 50dp height, 10dp radius)
 * - Secondary authentication action: Continue with Email (subtle border #E5E0D6 + mail outline, 50dp height, 10dp radius)
 * - Minimal "OR" divider with flanking horizontal lines (#E5E0D6)
 * - Restrained sign-up prompt: "Don’t have an account? Sign up"
 * - Responsive edge-to-edge layout supporting 360x800, 390x844, 412x915+
 */
@Composable
fun SignInScreen(
    onContinueWithGoogle: () -> Unit = {},
    onContinueWithEmail: () -> Unit = {},
    onSignUpClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    BoxWithConstraints(
        modifier = modifier
            .fillMaxSize()
            .background(WardrobeSurface)
    ) {
        val screenHeight = maxHeight
        val isCompact = screenHeight < 820.dp
        val heroHeight = if (isCompact) screenHeight * 0.49f else screenHeight * 0.52f

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
                // Editorial Headline: "Welcome back."
                Text(
                    text = "Welcome back.",
                    fontFamily = BodoniModaFontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = if (isCompact) 34.sp else 38.sp,
                    lineHeight = if (isCompact) 38.sp else 41.sp,
                    letterSpacing = (-0.95).sp,
                    color = WardrobeCharcoal,
                    textAlign = TextAlign.Start
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Explanatory Subtitle
                Text(
                    text = "Sign in to continue your style journey.",
                    fontFamily = InterFontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    lineHeight = 23.sp,
                    color = WardrobeMuted,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.widthIn(max = 334.dp)
                )

                Spacer(modifier = Modifier.height(if (isCompact) 14.dp else 18.dp))

                // Primary Action: Continue with Google
                GoogleAuthButton(
                    onClick = onContinueWithGoogle,
                    height = 50.dp,
                    cornerRadius = 10.dp,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Secondary Action: Continue with Email
                EmailAuthButton(
                    onClick = onContinueWithEmail,
                    height = 50.dp,
                    cornerRadius = 10.dp,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(if (isCompact) 12.dp else 16.dp))

                // Minimal OR Divider
                AuthDivider(
                    text = "OR",
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(if (isCompact) 12.dp else 16.dp))

                // Sign-Up Prompt Footer
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    SignUpPrompt(
                        onSignUpClick = onSignUpClick
                    )
                }
            }
        }
    }
}

@Preview(name = "Standard 390x844 - image.png Reference", widthDp = 390, heightDp = 844, showBackground = true)
@Composable
private fun SignInStandardPreview() {
    WardrobeTheme {
        SignInScreen()
    }
}

@Preview(name = "Compact 360x800", widthDp = 360, heightDp = 800, showBackground = true)
@Composable
private fun SignInCompactPreview() {
    WardrobeTheme {
        SignInScreen()
    }
}

@Preview(name = "Large 412x915", widthDp = 412, heightDp = 915, showBackground = true)
@Composable
private fun SignInLargePreview() {
    WardrobeTheme {
        SignInScreen()
    }
}
