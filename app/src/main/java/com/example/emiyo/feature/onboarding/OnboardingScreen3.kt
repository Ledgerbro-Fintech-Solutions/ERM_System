package com.example.emiyo.feature.onboarding

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
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.ui.input.pointer.pointerInput
import com.example.emiyo.R
import com.example.emiyo.core.design.BodoniModaFontFamily
import com.example.emiyo.core.design.InterFontFamily
import com.example.emiyo.core.design.OccasionCard
import com.example.emiyo.core.design.OccasionConnectors
import com.example.emiyo.core.design.OnboardingIndicator
import com.example.emiyo.core.design.SwipeUpIndicator
import com.example.emiyo.core.design.WardrobeCharcoal
import com.example.emiyo.core.design.WardrobeMuted
import com.example.emiyo.core.design.WardrobeSurface
import com.example.emiyo.core.design.WardrobeTheme

/**
 * WARDROBE Onboarding Screen 03 — "From your wardrobe to your best look."
 *
 * Visual Source of Truth: image.png
 * Features:
 * - Central model in luxury ivory architecture positioned between 4 occasion cards
 * - Four floating frosted occasion cards: WORK, CASUAL, WEDDING, NIGHT OUT
 * - Curved vector connector lines with anchor nodes connecting directly to the model
 * - Bodoni Moda editorial headline: "From your wardrobe to your best look."
 * - Inter narrative subtitle: "AI that understands you."
 * - Animated luxury "SWIPE UP TO BEGIN" indicator
 * - 3-step progress indicator with step 3 active
 * - Supports vertical swipe-up gesture to advance to Screen 04 (Create Account)
 */
@Composable
fun OnboardingScreen3(
    onOccasionClick: (String) -> Unit = {},
    onContinue: () -> Unit = {},
    onNavigateToPrevious: () -> Unit = {},
    onStepClick: ((Int) -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    BoxWithConstraints(
        modifier = modifier
            .fillMaxSize()
            .background(WardrobeSurface)
            .pointerInput(Unit) {
                var totalDragX = 0f
                var totalDragY = 0f
                detectDragGestures(
                    onDragStart = {
                        totalDragX = 0f
                        totalDragY = 0f
                    },
                    onDragEnd = {
                        if (totalDragY < -35f) {
                            // Swiped UP -> Advance to Screen 4 (Create Account)
                            onContinue()
                        } else if (totalDragX > 35f) {
                            // Swiped Right -> Back to Screen 2
                            onNavigateToPrevious()
                        }
                    },
                    onDrag = { change, dragAmount ->
                        change.consume()
                        totalDragX += dragAmount.x
                        totalDragY += dragAmount.y
                    }
                )
            }
    ) {
        val screenHeight = maxHeight
        val isCompact = screenHeight < 820.dp

        // Layer 1: Central Hero Model Photograph
        // Offset Y (-108dp) positions the model directly in between the occasion cards
        // so that all four connector nodes touch her hair, shoulder, arm, and lapel.
        Image(
            painter = painterResource(id = R.drawable.wardrobe_onboarding_03_hero),
            contentDescription = "WARDROBE central model in luxury linen suit",
            contentScale = ContentScale.Crop,
            alignment = Alignment.TopCenter,
            modifier = Modifier
                .fillMaxWidth()
                .height(screenHeight + 115.dp)
                .offset(y = (-108).dp)
        )

        // Layer 2: Subtle top atmospheric scrim for status bar clarity
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .align(Alignment.TopCenter)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            WardrobeSurface.copy(alpha = 0.40f),
                            WardrobeSurface.copy(alpha = 0.15f),
                            Color.Transparent
                        )
                    )
                )
        )

        // Layer 3: Delicate curved vector connector lines with anchor nodes
        OccasionConnectors(
            modifier = Modifier.fillMaxSize()
        )

        // Layer 4: Four Floating Editorial Occasion Cards framing the central model
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            // Card 1: WORK (Upper Left)
            OccasionCard(
                label = "WORK",
                imageRes = R.drawable.occasion_work,
                onClick = { onOccasionClick("WORK") },
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .statusBarsPadding()
                    .padding(
                        start = 16.dp,
                        top = if (isCompact) 10.dp else 12.dp
                    )
            )

            // Card 2: CASUAL (Upper Right - slight asymmetrical offset matching image.png)
            OccasionCard(
                label = "CASUAL",
                imageRes = R.drawable.occasion_casual,
                onClick = { onOccasionClick("CASUAL") },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .statusBarsPadding()
                    .padding(
                        end = 16.dp,
                        top = if (isCompact) 18.dp else 22.dp
                    )
            )

            // Card 3: WEDDING (Lower Left)
            OccasionCard(
                label = "WEDDING",
                imageRes = R.drawable.occasion_wedding,
                onClick = { onOccasionClick("WEDDING") },
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(
                        start = 16.dp,
                        top = if (isCompact) 365.dp else 415.dp
                    )
            )

            // Card 4: NIGHT OUT (Lower Right)
            OccasionCard(
                label = "NIGHT OUT",
                imageRes = R.drawable.occasion_night_out,
                onClick = { onOccasionClick("NIGHT OUT") },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(
                        end = 16.dp,
                        top = if (isCompact) 358.dp else 405.dp
                    )
            )
        }

        // Layer 5: Bottom soft atmospheric ivory fade blending hero into typography
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(if (isCompact) 280.dp else 320.dp)
                .align(Alignment.BottomCenter)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            WardrobeSurface.copy(alpha = 0.35f),
                            WardrobeSurface.copy(alpha = 0.85f),
                            WardrobeSurface
                        )
                    )
                )
        )

        // Layer 6: Bottom Editorial Section & Onboarding Indicator (Step 3 of 3 Active)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .navigationBarsPadding()
                .padding(horizontal = 24.dp)
                .padding(bottom = if (isCompact) 16.dp else 22.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(if (isCompact) 6.dp else 8.dp)
        ) {
            // Major Editorial Headline: "From your wardrobe\nto your best look."
            Text(
                text = "From your closet\nto your best look.",
                fontFamily = BodoniModaFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = if (isCompact) 34.sp else 38.sp,
                lineHeight = if (isCompact) 38.sp else 41.sp,
                letterSpacing = (-0.31).sp,
                color = WardrobeCharcoal,
                textAlign = TextAlign.Center,
                modifier = Modifier.widthIn(max = 350.dp)
            )

            // Narrative Subtitle: "AI that understands you."
            Text(
                text = "AI that understands you.",
                fontFamily = InterFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 13.sp,
                lineHeight = 20.sp,
                color = WardrobeMuted,
                textAlign = TextAlign.Center
            )

            // Animated Swipe Up Indicator
            SwipeUpIndicator(
                onSwipeUpClick = onContinue,
                modifier = Modifier.padding(vertical = 2.dp)
            )

            // Step 3 Active Indicator (3 segments: inactive | inactive | ACTIVE in #5D6B52)
            OnboardingIndicator(
                totalSteps = 3,
                activeStep = 2,
                onStepClick = onStepClick ?: { step ->
                    if (step < 2) onNavigateToPrevious()
                }
            )
        }
    }
}

@Preview(name = "Standard 390x844 - image.png Reference", widthDp = 390, heightDp = 844, showBackground = true)
@Composable
private fun OnboardingScreen3StandardPreview() {
    WardrobeTheme {
        OnboardingScreen3()
    }
}

@Preview(name = "Compact 360x800", widthDp = 360, heightDp = 800, showBackground = true)
@Composable
private fun OnboardingScreen3CompactPreview() {
    WardrobeTheme {
        OnboardingScreen3()
    }
}

@Preview(name = "Large 412x915", widthDp = 412, heightDp = 915, showBackground = true)
@Composable
private fun OnboardingScreen3LargePreview() {
    WardrobeTheme {
        OnboardingScreen3()
    }
}
