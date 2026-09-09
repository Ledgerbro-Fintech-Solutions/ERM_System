package com.example.emiyo.core.design

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.cos
import kotlin.math.sin

/**
 * Minimalist Face Shape Icon for analysis badge
 */
@Composable
fun FaceShapeIcon(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier.size(13.dp, 15.dp)) {
        val w = size.width
        val h = size.height
        // Delicate oval contour
        drawOval(
            color = WardrobeCharcoal,
            topLeft = Offset(0f, 0f),
            size = Size(w, h),
            style = Stroke(width = 1.1.dp.toPx())
        )
        // Subtle smile / chin guide arc
        val arcPath = Path().apply {
            moveTo(w * 0.32f, h * 0.63f)
            quadraticTo(w * 0.5f, h * 0.77f, w * 0.68f, h * 0.63f)
        }
        drawPath(arcPath, color = WardrobeCharcoal, style = Stroke(width = 1.dp.toPx(), cap = StrokeCap.Round))
    }
}

/**
 * Minimalist Skin Tone Sun Icon with rays for analysis badge
 */
@Composable
fun SkinToneIcon(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier.size(14.dp)) {
        val center = Offset(size.width / 2f, size.height / 2f)
        val radius = size.width * 0.28f
        drawCircle(color = WardrobeCharcoal, radius = radius, center = center, style = Stroke(width = 1.1.dp.toPx()))

        // 8 subtle outer rays
        val rayLength = size.width * 0.12f
        val rayDistance = size.width * 0.38f
        for (i in 0 until 8) {
            val angle = (i * 45f) * (Math.PI / 180f)
            val startX = center.x + cos(angle).toFloat() * rayDistance
            val startY = center.y + sin(angle).toFloat() * rayDistance
            val endX = center.x + cos(angle).toFloat() * (rayDistance + rayLength)
            val endY = center.y + sin(angle).toFloat() * (rayDistance + rayLength)
            drawLine(
                color = WardrobeCharcoal,
                start = Offset(startX, startY),
                end = Offset(endX, endY),
                strokeWidth = 1.dp.toPx(),
                cap = StrokeCap.Round
            )
        }
    }
}

/**
 * Minimalist Undertone Radial Dot Ring Icon for analysis badge
 */
@Composable
fun UndertoneIcon(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier.size(14.dp)) {
        val center = Offset(size.width / 2f, size.height / 2f)
        val radius = size.width * 0.38f
        for (i in 0 until 8) {
            val angle = (i * 45f) * (Math.PI / 180f)
            val x = center.x + cos(angle).toFloat() * radius
            val y = center.y + sin(angle).toFloat() * radius
            drawCircle(color = WardrobeCharcoal, radius = 1.0.dp.toPx(), center = Offset(x, y))
        }
    }
}

/**
 * 3 Overlapping Color Swatch Discs for Best Colors badge
 */
@Composable
fun ColorSwatchesIcon(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.height(16.dp),
        horizontalArrangement = Arrangement.spacedBy((-5).dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(12.dp)
                .clip(CircleShape)
                .background(Color(0xFFC06C54)) // Terracotta
                .border(0.7.dp, Color.White, CircleShape)
        )
        Box(
            modifier = Modifier
                .size(12.dp)
                .clip(CircleShape)
                .background(Color(0xFF7D8B72)) // Sage
                .border(0.7.dp, Color.White, CircleShape)
        )
        Box(
            modifier = Modifier
                .size(12.dp)
                .clip(CircleShape)
                .background(Color(0xFFC49A6C)) // Warm earth beige
                .border(0.7.dp, Color.White, CircleShape)
        )
    }
}

/**
 * Floating Face Analysis Card matching WARDROBE luxury editorial specification.
 *
 * Sizing:
 * - Width: ~128dp
 * - Height: 44dp (single line) or ~58dp (multiline)
 * - Background: rgba(247, 244, 238, 0.82)
 * - 1px white border + subtle 12dp rounded corners
 */
@Composable
fun FaceAnalysisCard(
    label: String,
    value: String,
    icon: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .shadow(
                elevation = 3.dp,
                shape = RoundedCornerShape(12.dp),
                ambientColor = Color(0x0D1A1715),
                spotColor = Color(0x0D1A1715)
            )
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xD2F7F4EE))
            .border(1.dp, Color(0xB3FFFFFF), RoundedCornerShape(12.dp))
            .clickable { onClick() }
            .padding(horizontal = 10.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Icon badge with translucent white background
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
                    .background(Color(0x99FFFFFF)),
                contentAlignment = Alignment.Center
            ) {
                icon()
            }

            // Label & Value
            Column(
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = label,
                    fontFamily = InterFontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 7.5.sp,
                    lineHeight = 11.sp,
                    letterSpacing = 0.5.sp,
                    color = WardrobeMuted
                )
                Text(
                    text = value,
                    fontFamily = InterFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 11.sp,
                    lineHeight = 13.5.sp,
                    color = WardrobeCharcoal
                )
            }
        }

        // Understated right chevron icon
        Canvas(modifier = Modifier.size(width = 5.dp, height = 8.dp)) {
            val path = Path().apply {
                moveTo(0f, 0f)
                lineTo(size.width, size.height / 2f)
                lineTo(0f, size.height)
            }
            drawPath(
                path = path,
                color = Color(0xFFA8A29E),
                style = Stroke(width = 1.2.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round)
            )
        }
    }
}

/**
 * Facial Framing System Overlay
 *
 * Features:
 * - 148dp × 178dp bounding frame centered over the subject's face
 * - 4 delicate white corner brackets (16dp × 16dp, 1px solid rgba(255, 255, 255, 0.9), 3dp corner radius)
 * - Center horizontal & vertical dashed axis lines in rgba(255, 255, 255, 0.5)
 */
@Composable
fun FacialFramingOverlay(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(width = 148.dp, height = 178.dp)
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val w = size.width
            val h = size.height
            val bracketLength = 16.dp.toPx()
            val cornerRadius = 3.dp.toPx()
            val bracketStroke = 1.dp.toPx()
            val bracketColor = Color(0xE6FFFFFF) // rgba(255, 255, 255, 0.9)
            val dashColor = Color(0x80FFFFFF)    // rgba(255, 255, 255, 0.5)

            // Center Horizontal Dashed Axis
            val centerY = h / 2f
            val dashWidth = 3.dp.toPx()
            val dashGap = 3.dp.toPx()
            var currentX = 0f
            while (currentX < w) {
                drawLine(
                    color = dashColor,
                    start = Offset(currentX, centerY),
                    end = Offset(minOf(currentX + dashWidth, w), centerY),
                    strokeWidth = 0.8.dp.toPx()
                )
                currentX += dashWidth + dashGap
            }

            // Center Vertical Dashed Axis
            val centerX = w / 2f
            var currentY = 0f
            while (currentY < h) {
                drawLine(
                    color = dashColor,
                    start = Offset(centerX, currentY),
                    end = Offset(centerX, minOf(currentY + dashWidth, h)),
                    strokeWidth = 0.8.dp.toPx()
                )
                currentY += dashWidth + dashGap
            }

            // Top-Left Bracket
            val tlPath = Path().apply {
                moveTo(0f, bracketLength)
                lineTo(0f, cornerRadius)
                quadraticTo(0f, 0f, cornerRadius, 0f)
                lineTo(bracketLength, 0f)
            }
            drawPath(tlPath, color = bracketColor, style = Stroke(width = bracketStroke, cap = StrokeCap.Round))

            // Top-Right Bracket
            val trPath = Path().apply {
                moveTo(w - bracketLength, 0f)
                lineTo(w - cornerRadius, 0f)
                quadraticTo(w, 0f, w, cornerRadius)
                lineTo(w, bracketLength)
            }
            drawPath(trPath, color = bracketColor, style = Stroke(width = bracketStroke, cap = StrokeCap.Round))

            // Bottom-Left Bracket
            val blPath = Path().apply {
                moveTo(0f, h - bracketLength)
                lineTo(0f, h - cornerRadius)
                quadraticTo(0f, h, cornerRadius, h)
                lineTo(bracketLength, h)
            }
            drawPath(blPath, color = bracketColor, style = Stroke(width = bracketStroke, cap = StrokeCap.Round))

            // Bottom-Right Bracket
            val brPath = Path().apply {
                moveTo(w - bracketLength, h)
                lineTo(w - cornerRadius, h)
                quadraticTo(w, h, w, h - cornerRadius)
                lineTo(w, h - bracketLength)
            }
            drawPath(brPath, color = bracketColor, style = Stroke(width = bracketStroke, cap = StrokeCap.Round))
        }
    }
}

/**
 * Circular Face Analysis Emblem with Scanning Animation
 *
 * Features:
 * - 48dp circular badge with warm beige border (rgba(214, 206, 190, 0.6))
 * - Dynamic pulsating radar/scanner aura expanding outward in sage (#7D8B72)
 * - 4 crosshair tick marks extending 4dp outward at 12, 3, 6, and 9 o'clock (#D6CEBE)
 * - Minimalist face silhouette contour line-art in #7D8B72 (Sage)
 * - Animated horizontal scanning beam traversing vertically through the face
 * - Clickable interaction that exclusively redirects to Face Capture
 */
@Composable
fun FaceAnalysisEmblem(
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition(label = "FaceScanAnimation")

    // Pulsing radar ripple expanding from circle outward
    val rippleScale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.36f,
        animationSpec = infiniteRepeatable(
            animation = tween(2200, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "rippleScale"
    )
    val rippleAlpha by infiniteTransition.animateFloat(
        initialValue = 0.5f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(2200, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "rippleAlpha"
    )

    // Vertical scanning beam progress traversing up and down
    val scanProgress by infiniteTransition.animateFloat(
        initialValue = 0.15f,
        targetValue = 0.85f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "scanProgress"
    )

    Box(
        modifier = modifier
            .size(62.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(bounded = false, radius = 32.dp, color = Color(0xFF7D8B72))
            ) { onClick() },
        contentAlignment = Alignment.Center
    ) {
        // Outer pulsing scanner ring & crosshair ticks
        Canvas(modifier = Modifier.fillMaxSize()) {
            val center = Offset(size.width / 2f, size.height / 2f)
            val baseRadius = 24.dp.toPx()

            // Dynamic Scanning Ripple Aura
            drawCircle(
                color = Color(0xFF7D8B72).copy(alpha = rippleAlpha),
                radius = baseRadius * rippleScale,
                center = center,
                style = Stroke(width = 1.2.dp.toPx())
            )

            val tickLength = 5.dp.toPx()
            val circleRadius = 24.dp.toPx()
            val tickColor = Color(0xFFD6CEBE)
            val strokeWidth = 1.dp.toPx()

            // Top tick (12 o'clock)
            drawLine(tickColor, Offset(center.x, center.y - circleRadius - tickLength), Offset(center.x, center.y - circleRadius), strokeWidth)
            // Bottom tick (6 o'clock)
            drawLine(tickColor, Offset(center.x, center.y + circleRadius), Offset(center.x, center.y + circleRadius + tickLength), strokeWidth)
            // Left tick (9 o'clock)
            drawLine(tickColor, Offset(center.x - circleRadius - tickLength, center.y), Offset(center.x - circleRadius, center.y), strokeWidth)
            // Right tick (3 o'clock)
            drawLine(tickColor, Offset(center.x + circleRadius, center.y), Offset(center.x + circleRadius + tickLength, center.y), strokeWidth)
        }

        // Central 48dp circular badge
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(Color(0x4DFFFFFF))
                .border(1.dp, Color(0x99D6CEBE), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            // Minimalist line-art face contour + animated scanning beam
            Canvas(modifier = Modifier.size(18.dp, 22.dp)) {
                val w = size.width
                val h = size.height
                val sageColor = Color(0xFF7D8B72)
                val strokeWidth = 1.2.dp.toPx()

                val faceContour = Path().apply {
                    moveTo(w * 0.5f, h)
                    cubicTo(w * 0.15f, h * 0.72f, 0f, h * 0.45f, 0f, h * 0.25f)
                    arcTo(
                        rect = Rect(0f, 0f, w, h * 0.5f),
                        startAngleDegrees = 180f,
                        sweepAngleDegrees = 180f,
                        forceMoveTo = false
                    )
                    cubicTo(w, h * 0.45f, w * 0.85f, h * 0.72f, w * 0.5f, h)
                    close()
                }
                drawPath(faceContour, color = sageColor, style = Stroke(width = strokeWidth, cap = StrokeCap.Round, join = StrokeJoin.Round))

                // Delicate eye dots
                drawCircle(color = sageColor, radius = 0.8.dp.toPx(), center = Offset(w * 0.36f, h * 0.38f))
                drawCircle(color = sageColor, radius = 0.8.dp.toPx(), center = Offset(w * 0.64f, h * 0.38f))

                // Active horizontal scanning beam laser passing through the face
                val beamY = h * scanProgress
                val beamHalfWidth = (w * 0.45f) * (1f - (beamY / h - 0.5f) * (beamY / h - 0.5f))
                drawLine(
                    color = Color(0xFF7D8B72).copy(alpha = 0.85f),
                    start = Offset(w * 0.5f - beamHalfWidth, beamY),
                    end = Offset(w * 0.5f + beamHalfWidth, beamY),
                    strokeWidth = 1.3.dp.toPx(),
                    cap = StrokeCap.Round
                )
            }
        }
    }
}

/**
 * Bottom Editorial Section Tracked Label
 */
@Composable
fun EditorialBottomLabel(
    text: String = "FACE ANALYSIS",
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        fontFamily = InterFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 9.sp,
        lineHeight = 13.sp,
        letterSpacing = 2.2.sp,
        color = Color(0xFF7D8B72),
        textAlign = TextAlign.Center,
        modifier = modifier
    )
}
