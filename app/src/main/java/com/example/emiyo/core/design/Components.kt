package com.example.emiyo.core.design

import androidx.annotation.DrawableRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.emiyo.R

/**
 * WARDROBE Luxury Brand Wordmark
 */
@Composable
fun WardrobeWordmark(
    modifier: Modifier = Modifier
) {
    Text(
        text = "WARDROBE",
        style = WardrobeWordmarkStyle,
        textAlign = TextAlign.Center,
        modifier = modifier
    )
}

/**
 * Editorial Headline with high-fashion Bodoni Moda styling
 * and authentic italic second line.
 */
@Composable
fun EditorialHeadline(
    line1: String = "Your style,",
    line2Italic: String = "smarter.",
    modifier: Modifier = Modifier
) {
    val annotatedText = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                fontFamily = BodoniModaFontFamily,
                fontStyle = FontStyle.Normal,
                fontSize = 38.sp,
                color = WardrobeCharcoal
            )
        ) {
            append(line1)
            append("\n")
        }
        withStyle(
            style = SpanStyle(
                fontFamily = BodoniModaFontFamily,
                fontStyle = FontStyle.Italic,
                fontSize = 38.sp,
                color = WardrobeCharcoal
            )
        ) {
            append(line2Italic)
        }
    }

    Text(
        text = annotatedText,
        lineHeight = 41.sp,
        textAlign = TextAlign.Center,
        modifier = modifier
    )
}

/**
 * Supporting description text for WARDROBE
 */
@Composable
fun SupportingText(
    text: String = "AI-powered style recommendations\nfor your unique wardrobe.",
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        style = WardrobeSupportingCopyStyle.copy(
            fontSize = 13.sp,
            lineHeight = 18.sp
        ),
        textAlign = TextAlign.Center,
        modifier = modifier.widthIn(max = 260.dp)
    )
}

/**
 * High-fashion Arrow Right Vector Icon
 */
@Composable
fun ArrowRightIcon(
    modifier: Modifier = Modifier,
    color: Color = WardrobeWhite
) {
    Canvas(modifier = modifier.size(16.dp)) {
        val strokeWidth = 1.8.dp.toPx()
        val w = size.width
        val h = size.height
        val midY = h / 2f

        // Horizontal line: (3,12) to (21,12) in 24x24 scale
        drawLine(
            color = color,
            start = Offset(w * (3f / 24f), midY),
            end = Offset(w * (21f / 24f), midY),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round
        )

        // Arrow top head: (13.5, 4.5) to (21, 12)
        drawLine(
            color = color,
            start = Offset(w * (13.5f / 24f), h * (4.5f / 24f)),
            end = Offset(w * (21f / 24f), midY),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round
        )

        // Arrow bottom head: (13.5, 19.5) to (21, 12)
        drawLine(
            color = color,
            start = Offset(w * (13.5f / 24f), h * (19.5f / 24f)),
            end = Offset(w * (21f / 24f), midY),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round
        )
    }
}

/**
 * Primary CTA Button with refined pill shape, subtle scale-on-press, and arrow icon.
 */
@Composable
fun PrimaryCTA(
    text: String = "Get Started",
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.982f else 1.0f,
        animationSpec = tween(durationMillis = 150),
        label = "ctaScale"
    )

    Box(
        modifier = modifier
            .widthIn(max = 280.dp)
            .fillMaxWidth()
            .height(52.dp)
            .scale(scale)
            .shadow(
                elevation = 8.dp,
                shape = CircleShape,
                ambientColor = WardrobeCharcoal.copy(alpha = 0.15f),
                spotColor = WardrobeCharcoal.copy(alpha = 0.20f)
            )
            .clip(CircleShape)
            .background(WardrobeOlive)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            ),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = text,
                style = WardrobePrimaryCtaStyle
            )
            Spacer(modifier = Modifier.width(10.dp))
            ArrowRightIcon(color = WardrobeWhite)
        }
    }
}

/**
 * Secondary Action Link for existing accounts
 */
@Composable
fun AccountLink(
    text: String = "I ALREADY HAVE AN ACCOUNT",
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val interactionSource = remember { MutableInteractionSource() }
    Box(
        modifier = modifier
            .clip(CircleShape)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            )
            .padding(horizontal = 12.dp, vertical = 6.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = WardrobeAccountLinkStyle,
            textAlign = TextAlign.Center
        )
    }
}

/**
 * 3-step Onboarding Progress Indicator with animated horizontal pill segments.
 * Supports smooth animated transitions and interactive click-to-navigate.
 */
@Composable
fun OnboardingIndicator(
    totalSteps: Int = 3,
    activeStep: Int = 0,
    onStepClick: ((Int) -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (i in 0 until totalSteps) {
            val isActive = i == activeStep
            val targetWidth = if (isActive) 34.dp else 22.dp
            val width by animateDpAsState(
                targetValue = targetWidth,
                animationSpec = tween(300, easing = FastOutSlowInEasing),
                label = "IndicatorWidth_$i"
            )
            val targetColor = if (isActive) WardrobeOlive else WardrobeBorder
            val color by animateColorAsState(
                targetValue = targetColor,
                animationSpec = tween(300),
                label = "IndicatorColor_$i"
            )

            val clickModifier = if (onStepClick != null) {
                Modifier.clickable { onStepClick(i) }
            } else {
                Modifier
            }

            Box(
                modifier = Modifier
                    .then(clickModifier)
                    .padding(vertical = 8.dp)
                    .size(width = width, height = 4.dp)
                    .clip(CircleShape)
                    .background(color)
            )
        }
    }
}

/**
 * Animated "Swipe Up" Indicator for WARDROBE Onboarding Screen 03
 * Features a gently floating upward chevron with breathing glow and luxury tracked typography.
 */
@Composable
fun SwipeUpIndicator(
    onSwipeUpClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition(label = "SwipeUpTransition")
    val bounceOffset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = -7f,
        animationSpec = infiniteRepeatable(
            animation = tween(850, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "SwipeUpBounce"
    )
    val glowAlpha by infiniteTransition.animateFloat(
        initialValue = 0.65f,
        targetValue = 1.0f,
        animationSpec = infiniteRepeatable(
            animation = tween(850, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "SwipeUpGlow"
    )

    Column(
        modifier = modifier
            .clickable { onSwipeUpClick() }
            .padding(horizontal = 16.dp, vertical = 6.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        // Animated upward chevron
        Box(
            modifier = Modifier
                .offset(y = bounceOffset.dp)
                .alpha(glowAlpha)
        ) {
            Canvas(modifier = Modifier.size(width = 16.dp, height = 8.dp)) {
                val strokeWidth = 2.dp.toPx()
                val w = size.width
                val h = size.height
                val path = Path().apply {
                    moveTo(0f, h)
                    lineTo(w / 2f, 0f)
                    lineTo(w, h)
                }
                drawPath(
                    path = path,
                    color = WardrobeOlive,
                    style = Stroke(
                        width = strokeWidth,
                        cap = StrokeCap.Round,
                        join = StrokeJoin.Round
                    )
                )
            }
        }

        Text(
            text = "SWIPE UP TO BEGIN",
            fontFamily = InterFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 11.sp,
            letterSpacing = 1.6.sp,
            color = WardrobeOlive.copy(alpha = 0.90f),
            textAlign = TextAlign.Center
        )
    }
}

/**
 * Checkmark icon for analysis cards
 */
@Composable
fun CheckmarkIcon(
    modifier: Modifier = Modifier,
    color: Color = WardrobeOlive
) {
    Canvas(modifier = modifier.size(10.dp)) {
        val strokeWidth = 1.3.dp.toPx()
        val w = size.width
        val h = size.height

        // Checkmark paths: (0.2 * w, 0.5 * h) -> (0.42 * w, 0.75 * h) -> (0.85 * w, 0.25 * h)
        drawLine(
            color = color,
            start = Offset(w * 0.20f, h * 0.50f),
            end = Offset(w * 0.42f, h * 0.75f),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round
        )
        drawLine(
            color = color,
            start = Offset(w * 0.42f, h * 0.75f),
            end = Offset(w * 0.85f, h * 0.25f),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round
        )
    }
}

/**
 * YOUR COLORS Overlay Card
 *
 * Displays the user's luxury personal palette:
 * Warm Ivory, Camel, Terracotta, Olive, Chocolate
 */
@Composable
fun YourColorsCard(
    modifier: Modifier = Modifier
) {
    val swatches = listOf(
        Color(0xFFEDE5D8), // Warm Ivory
        Color(0xFFC79D74), // Camel
        Color(0xFFB86851), // Terracotta
        Color(0xFF6E7B65), // Olive
        Color(0xFF46362C)  // Chocolate
    )

    Box(
        modifier = modifier
            .width(158.dp)
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(16.dp),
                ambientColor = WardrobeCharcoal.copy(alpha = 0.08f),
                spotColor = WardrobeCharcoal.copy(alpha = 0.08f)
            )
            .clip(RoundedCornerShape(16.dp))
            .background(WardrobeSurface.copy(alpha = 0.75f))
            .border(
                width = 1.dp,
                color = Color.White.copy(alpha = 0.65f),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(12.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Header: Title + Terracotta accent dot
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "YOUR COLORS",
                    fontFamily = InterFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 9.sp,
                    letterSpacing = 0.2.em,
                    color = WardrobeCharcoal.copy(alpha = 0.8f)
                )
                Box(
                    modifier = Modifier
                        .size(6.dp)
                        .clip(CircleShape)
                        .background(WardrobeTerracotta)
                )
            }

            // Swatches Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(7.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                swatches.forEach { color ->
                    Box(
                        modifier = Modifier
                            .size(20.dp)
                            .shadow(1.dp, CircleShape)
                            .clip(CircleShape)
                            .background(color)
                            .border(
                                width = 1.dp,
                                color = Color.White.copy(alpha = 0.85f),
                                shape = CircleShape
                            )
                    )
                }
            }
        }
    }
}

/**
 * STYLE MATCH Score Overlay Card
 *
 * Displays circular score ring (92%) and style validation checklist.
 */
@Composable
fun StyleMatchCard(
    score: Int = 92,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .width(152.dp)
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(16.dp),
                ambientColor = WardrobeCharcoal.copy(alpha = 0.08f),
                spotColor = WardrobeCharcoal.copy(alpha = 0.08f)
            )
            .clip(RoundedCornerShape(16.dp))
            .background(WardrobeSurface.copy(alpha = 0.75f))
            .border(
                width = 1.dp,
                color = Color.White.copy(alpha = 0.65f),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(12.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Header: Title + Olive accent dot
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "STYLE MATCH",
                    fontFamily = InterFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 9.sp,
                    letterSpacing = 0.2.em,
                    color = WardrobeCharcoal.copy(alpha = 0.8f)
                )
                Box(
                    modifier = Modifier
                        .size(6.dp)
                        .clip(CircleShape)
                        .background(WardrobeOlive)
                )
            }

            // Content: Circular Score Badge + Checklist
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Circular 92% Gauge
                Box(
                    modifier = Modifier.size(44.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Canvas(modifier = Modifier.size(44.dp)) {
                        val stroke = 3.dp.toPx()
                        // Inactive track
                        drawArc(
                            color = Color(0xFFE5E0D6),
                            startAngle = -90f,
                            sweepAngle = 360f,
                            useCenter = false,
                            style = Stroke(
                                width = stroke,
                                cap = StrokeCap.Round
                            )
                        )
                        // Active progress arc (92% = 331.2 deg)
                        drawArc(
                            color = WardrobeOlive,
                            startAngle = -90f,
                            sweepAngle = (score / 100f) * 360f,
                            useCenter = false,
                            style = Stroke(
                                width = stroke,
                                cap = StrokeCap.Round
                            )
                        )
                    }

                    // Score Text inside circle
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "$score",
                            fontFamily = InterFontFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp,
                            color = WardrobeCharcoal,
                            letterSpacing = (-0.3).sp
                        )
                        Text(
                            text = "%",
                            fontFamily = InterFontFamily,
                            fontWeight = FontWeight.Normal,
                            fontSize = 8.sp,
                            color = WardrobeMuted,
                            letterSpacing = (-0.3).sp
                        )
                    }
                }

                // Checklist Items
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier.padding(start = 10.dp)
                ) {
                    listOf("Color", "Fit", "Style").forEach { label ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(5.dp)
                        ) {
                            CheckmarkIcon(color = WardrobeOlive)
                            Text(
                                text = label,
                                fontFamily = InterFontFamily,
                                fontWeight = FontWeight.Medium,
                                fontSize = 9.sp,
                                color = WardrobeCharcoal.copy(alpha = 0.9f)
                            )
                        }
                    }
                }
            }
        }
    }
}

/**
 * Floating Occasion Callout Card
 *
 * Translucent frosted card showcasing an occasion look (WORK, CASUAL, WEDDING, NIGHT OUT)
 */
@Composable
fun OccasionCard(
    label: String,
    @DrawableRes imageRes: Int,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.97f else 1.0f,
        animationSpec = tween(durationMillis = 150),
        label = "cardScale"
    )

    Box(
        modifier = modifier
            .width(106.dp)
            .scale(scale)
            .shadow(
                elevation = 6.dp,
                shape = RoundedCornerShape(16.dp),
                ambientColor = WardrobeCharcoal.copy(alpha = 0.08f),
                spotColor = WardrobeCharcoal.copy(alpha = 0.10f)
            )
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White.copy(alpha = 0.70f))
            .border(
                width = 1.dp,
                color = Color.White.copy(alpha = 0.90f),
                shape = RoundedCornerShape(16.dp)
            )
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            )
            .padding(6.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = label,
                fontFamily = InterFontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 9.sp,
                letterSpacing = 0.14.em,
                color = Color(0xFF524E48),
                modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp)
            )

            Spacer(modifier = Modifier.height(4.dp))

            Image(
                painter = painterResource(id = imageRes),
                contentDescription = label,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(122.dp)
                    .clip(RoundedCornerShape(12.dp))
            )
        }
    }
}

/**
 * Elegant curved connector lines bridging occasion cards to the central fashion model
 */
@Composable
fun OccasionConnectors(
    modifier: Modifier = Modifier
) {
    Canvas(modifier = modifier.fillMaxSize()) {
        val lineColor = Color(0xFFC9C2B7)
        val dotColor = Color(0xFFB2A99D)
        val strokeWidth = 1.dp.toPx()
        val dotRadius = 2.5.dp.toPx()
        val w = size.width
        val h = size.height

        // Connector 1: WORK (upper left -> model's right hair/shoulder)
        val p1Start = Offset(w * 0.313f, h * 0.127f)
        val p1Ctrl = Offset(w * 0.353f, h * 0.168f)
        val p1End = Offset(w * 0.409f, h * 0.200f)
        val path1 = Path().apply {
            moveTo(p1Start.x, p1Start.y)
            quadraticTo(p1Ctrl.x, p1Ctrl.y, p1End.x, p1End.y)
        }
        drawPath(path = path1, color = lineColor, style = Stroke(width = strokeWidth))
        drawCircle(color = dotColor, radius = dotRadius, center = p1End)

        // Connector 2: CASUAL (upper right -> model's left shoulder)
        val p2Start = Offset(w * 0.687f, h * 0.184f)
        val p2Ctrl = Offset(w * 0.654f, h * 0.204f)
        val p2End = Offset(w * 0.626f, h * 0.216f)
        val path2 = Path().apply {
            moveTo(p2Start.x, p2Start.y)
            quadraticTo(p2Ctrl.x, p2Ctrl.y, p2End.x, p2End.y)
        }
        drawPath(path = path2, color = lineColor, style = Stroke(width = strokeWidth))
        drawCircle(color = dotColor, radius = dotRadius, center = p2End)
    }
}

/**
 * Primary WARDROBE Authentication Action: Continue with Google
 *
 * Dark charcoal (#1E1C1A / #1A1715) background with official 4-color Google G icon.
 */
@Composable
fun GoogleAuthButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    height: androidx.compose.ui.unit.Dp = 52.dp,
    cornerRadius: androidx.compose.ui.unit.Dp = 12.dp
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.985f else 1f,
        animationSpec = tween(durationMillis = 100),
        label = "GoogleButtonScale"
    )

    Row(
        modifier = modifier
            .scale(scale)
            .fillMaxWidth()
            .height(height)
            .clip(RoundedCornerShape(cornerRadius))
            .background(WardrobeDarkButton)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            )
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_google_g),
            contentDescription = "Google logo",
            modifier = Modifier.size(18.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = "Continue with Google",
            fontFamily = InterFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            letterSpacing = 0.35.sp,
            color = Color.White
        )
    }
}

/**
 * Secondary WARDROBE Authentication Action: Continue with Email
 *
 * Transparent background with subtle border and mail outline icon.
 */
@Composable
fun EmailAuthButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    height: androidx.compose.ui.unit.Dp = 52.dp,
    cornerRadius: androidx.compose.ui.unit.Dp = 12.dp
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.985f else 1f,
        animationSpec = tween(durationMillis = 100),
        label = "EmailButtonScale"
    )

    Row(
        modifier = modifier
            .scale(scale)
            .fillMaxWidth()
            .height(height)
            .clip(RoundedCornerShape(cornerRadius))
            .border(
                width = 1.dp,
                color = WardrobeBorder,
                shape = RoundedCornerShape(cornerRadius)
            )
            .background(Color.Transparent)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            )
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_mail_outline),
            contentDescription = "Mail outline icon",
            modifier = Modifier.size(18.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = "Continue with Email",
            fontFamily = InterFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            letterSpacing = 0.35.sp,
            color = WardrobeCharcoal
        )
    }
}

/**
 * WARDROBE Sign-In Prompt: "Already have an account? Sign in"
 *
 * Displays subtle muted prompt with deep charcoal underlined link.
 */
@Composable
fun SignInPrompt(
    onSignInClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val annotatedText = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                fontFamily = InterFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 13.sp,
                color = WardrobeSignInMuted
            )
        ) {
            append("Already have an account? ")
        }
        pushStringAnnotation(tag = "SIGN_IN", annotation = "sign_in")
        withStyle(
            style = SpanStyle(
                fontFamily = InterFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 13.sp,
                color = WardrobeCharcoal,
                textDecoration = TextDecoration.Underline
            )
        ) {
            append("Sign in")
        }
        pop()
    }

    Text(
        text = annotatedText,
        textAlign = TextAlign.Center,
        modifier = modifier.clickable { onSignInClick() }
    )
}

/**
 * Subtle WARDROBE Divider: "────── OR ──────"
 *
 * Flanked by 1dp borders (#E5E0D6) with small uppercase Inter 10sp text.
 */
@Composable
fun AuthDivider(
    text: String = "OR",
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .height(1.dp)
                .background(WardrobeBorder)
        )
        Text(
            text = text,
            fontFamily = InterFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 10.sp,
            letterSpacing = 2.5.sp,
            color = Color(0xFFA8A29E),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 12.dp)
        )
        Box(
            modifier = Modifier
                .weight(1f)
                .height(1.dp)
                .background(WardrobeBorder)
        )
    }
}

/**
 * WARDROBE Sign-Up Prompt: "Don’t have an account? Sign up"
 *
 * Displays muted prompt with deep charcoal underlined "Sign up" link.
 */
@Composable
fun SignUpPrompt(
    onSignUpClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val annotatedText = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                fontFamily = InterFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 13.sp,
                color = WardrobeMuted
            )
        ) {
            append("Don’t have an account? ")
        }
        pushStringAnnotation(tag = "SIGN_UP", annotation = "sign_up")
        withStyle(
            style = SpanStyle(
                fontFamily = InterFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 13.sp,
                color = WardrobeCharcoal,
                textDecoration = TextDecoration.Underline
            )
        ) {
            append("Sign up")
        }
        pop()
    }

    Text(
        text = annotatedText,
        textAlign = TextAlign.Center,
        modifier = modifier.clickable { onSignUpClick() }
    )
}

/**
 * WARDROBE Custom Refined Input Field
 *
 * Warm ivory background (#FBF9F5), 1dp border (#E5E0D6) with subtle sage focus (#7D8B72),
 * small uppercase tracked label, optional minimal "SHOW" / "HIDE" password toggle,
 * and gentle validation feedback.
 */
@Composable
fun WardrobeTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    modifier: Modifier = Modifier,
    isPassword: Boolean = false,
    errorMessage: String? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default
) {
    var isFocused by remember { mutableStateOf(false) }
    var passwordVisible by remember { mutableStateOf(false) }

    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = label,
            fontFamily = InterFontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 10.5.sp,
            letterSpacing = 0.12.em,
            color = WardrobeCharcoalMuted,
            modifier = Modifier.padding(bottom = 6.dp)
        )

        val borderColor = when {
            errorMessage != null -> WardrobeTerracotta.copy(alpha = 0.7f)
            isFocused -> WardrobeSageAccent
            else -> WardrobeBorder
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(WardrobeSurface)
                .border(width = 1.dp, color = borderColor, shape = RoundedCornerShape(12.dp))
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.CenterStart
                ) {
                    if (value.isEmpty()) {
                        Text(
                            text = placeholder,
                            fontFamily = InterFontFamily,
                            fontWeight = FontWeight.Normal,
                            fontSize = 14.5.sp,
                            color = WardrobeMuted.copy(alpha = 0.6f)
                        )
                    }

                    BasicTextField(
                        value = value,
                        onValueChange = onValueChange,
                        singleLine = true,
                        textStyle = TextStyle(
                            fontFamily = InterFontFamily,
                            fontWeight = FontWeight.Normal,
                            fontSize = 14.5.sp,
                            color = WardrobeCharcoal
                        ),
                        cursorBrush = SolidColor(WardrobeSageAccent),
                        visualTransformation = if (isPassword && !passwordVisible) PasswordVisualTransformation() else VisualTransformation.None,
                        keyboardOptions = keyboardOptions,
                        keyboardActions = keyboardActions,
                        modifier = Modifier
                            .fillMaxWidth()
                            .onFocusChanged { isFocused = it.isFocused }
                    )
                }

                if (isPassword) {
                    Text(
                        text = if (passwordVisible) "HIDE" else "SHOW",
                        fontFamily = InterFontFamily,
                        fontWeight = FontWeight.Medium,
                        fontSize = 11.sp,
                        letterSpacing = 0.08.em,
                        color = WardrobeCharcoalMuted,
                        modifier = Modifier
                            .padding(start = 12.dp)
                            .clickable { passwordVisible = !passwordVisible }
                    )
                }
            }
        }

        if (errorMessage != null) {
            Text(
                text = errorMessage,
                fontFamily = InterFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp,
                color = WardrobeTerracotta,
                modifier = Modifier.padding(top = 4.dp, start = 4.dp)
            )
        }
    }
}

/**
 * Full-width Primary Authentication CTA Button ("CREATE ACCOUNT →")
 *
 * Warm sage (#7D8B72) background, white text, pill shape, and subtle press animation.
 */
@Composable
fun PrimaryAuthButton(
    text: String = "CREATE ACCOUNT",
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.985f else 1f,
        animationSpec = tween(durationMillis = 100),
        label = "PrimaryAuthButtonScale"
    )

    Row(
        modifier = modifier
            .scale(scale)
            .fillMaxWidth()
            .height(52.dp)
            .shadow(
                elevation = 6.dp,
                shape = CircleShape,
                ambientColor = WardrobeCharcoal.copy(alpha = 0.12f),
                spotColor = WardrobeCharcoal.copy(alpha = 0.18f)
            )
            .clip(CircleShape)
            .background(WardrobeSageAccent)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            )
            .padding(horizontal = 24.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            fontFamily = InterFontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            letterSpacing = 0.08.em,
            color = Color.White
        )
        Spacer(modifier = Modifier.width(10.dp))
        ArrowRightIcon(
            color = Color.White,
            modifier = Modifier.size(15.dp)
        )
    }
}




