package com.example.emiyo.core.design

import android.graphics.Bitmap
import android.net.Uri
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.UUID

/**
 * Recommended guided face angles for WARDROBE analysis
 */
enum class GuidedAngle(val stepNumber: Int, val label: String) {
    FRONT(1, "FRONT"),
    LEFT(2, "LEFT"),
    RIGHT(3, "RIGHT"),
    COMPLETE(3, "COMPLETE")
}

/**
 * Data model for a captured face photo
 */
data class CapturedFacePhoto(
    val id: String = UUID.randomUUID().toString(),
    val uri: Uri? = null,
    val bitmap: Bitmap? = null,
    val angle: GuidedAngle = GuidedAngle.FRONT
)

/**
 * Camera Viewport Framing Overlay with delicate corner brackets,
 * dotted oval face contour, and minimal center alignment crosshair ticks.
 */
@Composable
fun CameraFramingOverlay(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        // Center Target Frame
        Box(
            modifier = Modifier
                .width(260.dp)
                .height(320.dp),
            contentAlignment = Alignment.Center
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                val w = size.width
                val h = size.height
                val bracketSize = 24.dp.toPx()
                val cornerRadius = 3.dp.toPx()
                val bracketStroke = 2.dp.toPx()
                val bracketColor = Color(0xCCFFFFFF) // rgba(255, 255, 255, 0.8)
                val guideColor = Color(0x99FFFFFF)   // rgba(255, 255, 255, 0.6)

                // Top-Left Bracket
                val tlPath = Path().apply {
                    moveTo(0f, bracketSize)
                    lineTo(0f, cornerRadius)
                    quadraticTo(0f, 0f, cornerRadius, 0f)
                    lineTo(bracketSize, 0f)
                }
                drawPath(tlPath, color = bracketColor, style = Stroke(width = bracketStroke, cap = StrokeCap.Round))

                // Top-Right Bracket
                val trPath = Path().apply {
                    moveTo(w - bracketSize, 0f)
                    lineTo(w - cornerRadius, 0f)
                    quadraticTo(w, 0f, w, cornerRadius)
                    lineTo(w, bracketSize)
                }
                drawPath(trPath, color = bracketColor, style = Stroke(width = bracketStroke, cap = StrokeCap.Round))

                // Bottom-Left Bracket
                val blPath = Path().apply {
                    moveTo(0f, h - bracketSize)
                    lineTo(0f, h - cornerRadius)
                    quadraticTo(0f, h, cornerRadius, h)
                    lineTo(bracketSize, h)
                }
                drawPath(blPath, color = bracketColor, style = Stroke(width = bracketStroke, cap = StrokeCap.Round))

                // Bottom-Right Bracket
                val brPath = Path().apply {
                    moveTo(w - bracketSize, h)
                    lineTo(w - cornerRadius, h)
                    quadraticTo(w, h, w, h - cornerRadius)
                    lineTo(w, h - bracketSize)
                }
                drawPath(brPath, color = bracketColor, style = Stroke(width = bracketStroke, cap = StrokeCap.Round))

                // Subtle Dotted Oval Face Contour Guide (190dp x 255dp)
                val ovalWidth = 190.dp.toPx()
                val ovalHeight = 255.dp.toPx()
                val ovalLeft = (w - ovalWidth) / 2f
                val ovalTop = (h - ovalHeight) / 2f
                val dashIntervals = floatArrayOf(8.dp.toPx(), 6.dp.toPx())

                drawOval(
                    color = guideColor,
                    topLeft = Offset(ovalLeft, ovalTop),
                    size = Size(ovalWidth, ovalHeight),
                    style = Stroke(
                        width = 1.2.dp.toPx(),
                        pathEffect = PathEffect.dashPathEffect(dashIntervals, 0f)
                    )
                )

                // Minimal Center Crosshairs (small tick markers)
                val tickLength = 10.dp.toPx()
                val tickStroke = 1.dp.toPx()

                // Left Tick
                drawLine(guideColor, Offset(0f, h / 2f), Offset(tickLength, h / 2f), tickStroke)
                // Right Tick
                drawLine(guideColor, Offset(w - tickLength, h / 2f), Offset(w, h / 2f), tickStroke)
                // Top Tick
                drawLine(guideColor, Offset(w / 2f, 0f), Offset(w / 2f, tickLength), tickStroke)
                // Bottom Tick
                drawLine(guideColor, Offset(w / 2f, h - tickLength), Offset(w / 2f, h), tickStroke)
            }
        }
    }
}

/**
 * Dynamic Step & Angle Badge in the top right of camera preview
 * Example: "1 / 3 · FRONT", "2 / 3 · LEFT", "3 / 3 · RIGHT", "3 / 3 · COMPLETE"
 */
@Composable
fun StepAngleBadge(
    stepNumber: Int,
    totalSteps: Int = 3,
    angleLabel: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .shadow(2.dp, RoundedCornerShape(9999.dp), ambientColor = Color(0x1A000000))
            .clip(RoundedCornerShape(9999.dp))
            .background(Color(0xEBFBF9F5)) // #FBF9F5 with 92% opacity
            .border(1.dp, Color(0x66FFFFFF), RoundedCornerShape(9999.dp))
            .padding(horizontal = 10.dp, vertical = 5.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "$stepNumber / $totalSteps · $angleLabel",
            fontFamily = InterFontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 10.sp,
            letterSpacing = 0.8.sp,
            color = WardrobeCharcoal
        )
    }
}

/**
 * Three-item Quality Hint Row below camera preview
 * 1. Good lighting  2. Face visible  3. Look straight
 */
@Composable
fun QualityHintRow(
    isLightingGood: Boolean = true,
    isFaceVisible: Boolean = true,
    isLookingStraight: Boolean = true,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        QualityCheckItem(
            text = "Good lighting",
            isChecked = isLightingGood
        )
        QualityCheckItem(
            text = "Face visible",
            isChecked = isFaceVisible
        )
        QualityCheckItem(
            text = "Look straight",
            isChecked = isLookingStraight
        )
    }
}

@Composable
private fun QualityCheckItem(
    text: String,
    isChecked: Boolean,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        // Circular sage check badge
        Box(
            modifier = Modifier
                .size(16.dp)
                .clip(CircleShape)
                .background(if (isChecked) Color(0x337D8B72) else Color(0x1F78716C)),
            contentAlignment = Alignment.Center
        ) {
            Canvas(modifier = Modifier.size(9.dp)) {
                val strokeWidth = 1.6.dp.toPx()
                val path = Path().apply {
                    moveTo(size.width * 0.2f, size.height * 0.5f)
                    lineTo(size.width * 0.42f, size.height * 0.75f)
                    lineTo(size.width * 0.85f, size.height * 0.25f)
                }
                drawPath(
                    path = path,
                    color = if (isChecked) Color(0xFF5D6B52) else Color(0xFF78716C),
                    style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
                )
            }
        }

        Text(
            text = text,
            fontFamily = InterFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp,
            color = Color(0xFF44403C)
        )
    }
}

/**
 * Horizontal Thumbnail Strip for reviewing captured face photos
 */
@Composable
fun PhotoThumbnailStrip(
    photos: List<CapturedFacePhoto>,
    onRemovePhoto: (CapturedFacePhoto) -> Unit,
    onAddPhotoClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "YOUR PHOTOS",
                fontFamily = InterFontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 10.5.sp,
                letterSpacing = 1.2.sp,
                color = WardrobeMuted
            )

            Text(
                text = "${photos.size} captured",
                fontFamily = InterFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 11.sp,
                color = WardrobeMuted
            )
        }

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            items(photos, key = { it.id }) { photo ->
                PhotoThumbnailCard(
                    photo = photo,
                    onRemove = { onRemovePhoto(photo) }
                )
            }

            item {
                AddPhotoTile(onClick = onAddPhotoClick)
            }
        }
    }
}

@Composable
private fun PhotoThumbnailCard(
    photo: CapturedFacePhoto,
    onRemove: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(width = 68.dp, height = 88.dp)
            .shadow(2.dp, RoundedCornerShape(10.dp), ambientColor = Color(0x14000000))
            .clip(RoundedCornerShape(10.dp))
            .background(Color(0xFFF0ECE3))
            .border(1.dp, Color(0xFFE5E0D6), RoundedCornerShape(10.dp))
    ) {
        if (photo.bitmap != null) {
            Image(
                bitmap = photo.bitmap.asImageBitmap(),
                contentDescription = "Captured ${photo.angle.label} photo",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        } else {
            // Placeholder box
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFE7E3DA)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = photo.angle.label,
                    fontFamily = InterFontFamily,
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Medium,
                    color = WardrobeMuted
                )
            }
        }

        // Angle tag at bottom
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .background(Color(0xB31A1715))
                .padding(vertical = 2.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = photo.angle.label,
                fontFamily = InterFontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 8.sp,
                letterSpacing = 0.5.sp,
                color = Color.White
            )
        }

        // Subtle Remove 'x' button in top right
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(3.dp)
                .size(16.dp)
                .clip(CircleShape)
                .background(Color(0xCC1A1715))
                .clickable { onRemove() },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "×",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center,
                lineHeight = 11.sp
            )
        }
    }
}

@Composable
private fun AddPhotoTile(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(width = 68.dp, height = 88.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color(0x66F7F4EE))
            .border(1.dp, Color(0xFFD6CEBE), RoundedCornerShape(10.dp))
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "+",
                fontFamily = InterFontFamily,
                fontWeight = FontWeight.Light,
                fontSize = 22.sp,
                color = Color(0xFF7D8B72)
            )
            Text(
                text = "ADD",
                fontFamily = InterFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 8.sp,
                letterSpacing = 0.6.sp,
                color = Color(0xFF7D8B72)
            )
        }
    }
}
