package com.example.emiyo.feature.opening

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.emiyo.R
import com.example.emiyo.core.design.AccountLink
import com.example.emiyo.core.design.EditorialHeadline
import com.example.emiyo.core.design.OnboardingIndicator
import com.example.emiyo.core.design.PrimaryCTA
import com.example.emiyo.core.design.SupportingText
import com.example.emiyo.core.design.WardrobeIvory
import com.example.emiyo.core.design.WardrobeTheme


/**
 * WARDROBE Opening Screen
 *
 * Full-bleed luxury fashion photography with warm ivory atmospheric scrims,
 * editorial Bodoni Moda typography, and responsive interactive controls.
 */
@Composable
fun OpeningScreen(
    onGetStartedClick: () -> Unit = {},
    onAlreadyHaveAccountClick: () -> Unit = {},
    onStepClick: ((Int) -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    BoxWithConstraints(
        modifier = modifier
            .fillMaxSize()
            .background(WardrobeIvory)
    ) {
        val screenHeight = maxHeight
        val isCompact = screenHeight < 820.dp

        // Layer 1: Full-Bleed Clean Hero Photography
        // Shifting vertically by +48.dp creates ample editorial whitespace below the typography,
        // ensuring the headline & supporting copy never overlap with the subjects' heads while
        // maintaining the prominent framing of the central woman.
        Image(
            painter = painterResource(id = R.drawable.wardrobe_opening_hero),
            contentDescription = "WARDROBE editorial campaign group wearing luxury neutral styling",
            contentScale = ContentScale.Crop,
            alignment = Alignment.TopCenter,
            modifier = Modifier
                .fillMaxSize()
                .offset(y = 48.dp)
        )

        // Layer 2: Top Atmospheric Warm Ivory Scrim
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(if (isCompact) 240.dp else 270.dp)
                .align(Alignment.TopCenter)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            WardrobeIvory.copy(alpha = 0.98f),
                            WardrobeIvory.copy(alpha = 0.88f),
                            WardrobeIvory.copy(alpha = 0.45f),
                            Color.Transparent
                        )
                    )
                )
        )

        // Layer 3: Bottom Atmospheric Warm Ivory Scrim
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(if (isCompact) 230.dp else 250.dp)
                .align(Alignment.BottomCenter)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            WardrobeIvory.copy(alpha = 0.70f),
                            WardrobeIvory.copy(alpha = 0.96f)
                        )
                    )
                )
        )

        // Layer 4: Foreground Content (Safe-Area Aware & Responsive)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .navigationBarsPadding()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(if (isCompact) 6.dp else 10.dp))

            // Editorial Headline
            EditorialHeadline(
                line1 = "Your style,",
                line2Italic = "smarter."
            )

            // Supporting Description
            Spacer(modifier = Modifier.height(if (isCompact) 8.dp else 12.dp))
            SupportingText(
                text = "AI-powered style recommendations\nfor your unique style."
            )

            // Flexible breathing space to showcase fashion photography
            Spacer(modifier = Modifier.weight(1f))

            // Bottom Interactive Section
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = if (isCompact) 20.dp else 28.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(if (isCompact) 12.dp else 15.dp)
            ) {
                // Primary CTA Button
                PrimaryCTA(
                    text = "Get Started",
                    onClick = onGetStartedClick
                )

                // Secondary Account Link
                AccountLink(
                    text = "I ALREADY HAVE AN ACCOUNT",
                    onClick = onAlreadyHaveAccountClick
                )

                // 3-step progress indicator (Step 1 active)
                OnboardingIndicator(
                    totalSteps = 3,
                    activeStep = 0,
                    onStepClick = onStepClick ?: { step ->
                        if (step > 0) onGetStartedClick()
                    },
                    modifier = Modifier.padding(top = 2.dp)
                )
            }
        }
    }
}

@Preview(name = "Standard 390x844", widthDp = 390, heightDp = 844, showBackground = true)
@Composable
private fun OpeningScreenStandardPreview() {
    WardrobeTheme {
        OpeningScreen()
    }
}

@Preview(name = "Compact 360x800", widthDp = 360, heightDp = 800, showBackground = true)
@Composable
private fun OpeningScreenCompactPreview() {
    WardrobeTheme {
        OpeningScreen()
    }
}

@Preview(name = "Large 412x915", widthDp = 412, heightDp = 915, showBackground = true)
@Composable
private fun OpeningScreenLargePreview() {
    WardrobeTheme {
        OpeningScreen()
    }
}
