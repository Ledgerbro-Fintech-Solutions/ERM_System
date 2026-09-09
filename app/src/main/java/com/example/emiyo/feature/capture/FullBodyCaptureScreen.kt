package com.example.emiyo.feature.capture

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.example.emiyo.R
import com.example.emiyo.core.design.BodoniModaFontFamily
import com.example.emiyo.core.design.CameraFramingOverlay
import com.example.emiyo.core.design.CapturedFacePhoto
import com.example.emiyo.core.design.GuidedAngle
import com.example.emiyo.core.design.InterFontFamily
import com.example.emiyo.core.design.PhotoThumbnailStrip
import com.example.emiyo.core.design.QualityHintRow
import com.example.emiyo.core.design.StepAngleBadge
import com.example.emiyo.core.design.WardrobeCharcoal
import com.example.emiyo.core.design.WardrobeMuted
import com.example.emiyo.core.design.WardrobeSurface
import com.example.emiyo.core.design.WardrobeTheme
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import com.example.emiyo.core.design.InAppCameraPreview
import com.example.emiyo.core.design.takePhoto

/**
 * WARDROBE Screen 12 — "Face Capture / Upload"
 *
 * Visual Source of Truth: screen(2).png / style(4).css / code(2).html
 *
 * Features:
 * - Clean editorial header: W A R D R O B E wordmark, FACE CAPTURE label with terracotta accent line
 * - Bodoni Moda headline: "Let’s capture\nyour face."
 * - Large 340dp camera preview with default demo image (face_capture_demo)
 * - Dynamic guided angle sequence: 1/3 FRONT -> 2/3 LEFT -> 3/3 RIGHT -> 3/3 COMPLETE
 * - Framing overlay with corner brackets, dashed face contour, and center alignment markers
 * - Quality hint row: Good lighting, Face visible, Look straight
 * - Primary "TAKE PHOTO" action launching native Android camera
 * - Secondary "Upload from gallery" supporting multi-photo selection
 * - Interactive horizontal photo strip reviewing captured photos with add & remove options
 * - "CONTINUE ->" enabled once at least FRONT, LEFT, and RIGHT views are captured
 */
@Composable
fun FullBodyCaptureScreen(
    onContinue: () -> Unit = {},
    onBackClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var capturedPhotos by remember { mutableStateOf(listOf<CapturedFacePhoto>()) }
    var latestBitmap by remember { mutableStateOf<Bitmap?>(null) }

    // Dynamic guided angle based on captured count
    val currentAngle = when (capturedPhotos.size) {
        0 -> GuidedAngle.FRONT
        1 -> GuidedAngle.LEFT
        2 -> GuidedAngle.RIGHT
        else -> GuidedAngle.COMPLETE
    }

    val stepNumber = (capturedPhotos.size + 1).coerceAtMost(3)
    val hasSufficientCoverage = capturedPhotos.size >= 3

    val imageCapture = remember { ImageCapture.Builder().build() }
    val mainExecutor = ContextCompat.getMainExecutor(context)

    val coroutineScope = rememberCoroutineScope()
    var isFlashing by remember { mutableStateOf(false) }

    var hasCameraPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
        )
    }

    val cameraPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        hasCameraPermission = isGranted
        if (isGranted) {
            takePhoto(
                imageCapture = imageCapture,
                executor = mainExecutor,
                isFrontCamera = true,
                onImageCaptured = { bitmap ->
                    val newPhoto = CapturedFacePhoto(bitmap = bitmap, angle = currentAngle)
                    capturedPhotos = capturedPhotos + newPhoto
                    latestBitmap = bitmap
                    coroutineScope.launch {
                        isFlashing = true
                        delay(120)
                        isFlashing = false
                    }
                },
                onError = { exc ->
                    Toast.makeText(context, "Photo capture failed: ${exc.message}", Toast.LENGTH_SHORT).show()
                }
            )
        } else {
            Toast.makeText(context, "Camera permission is needed to take a photo", Toast.LENGTH_SHORT).show()
        }
    }

    // Gallery Multi-Photo Picker Launcher
    val galleryPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickMultipleVisualMedia()
    ) { uris: List<Uri> ->
        if (uris.isNotEmpty()) {
            val newPhotos = uris.mapIndexed { index, uri ->
                val angle = when ((capturedPhotos.size + index) % 3) {
                    0 -> GuidedAngle.FRONT
                    1 -> GuidedAngle.LEFT
                    else -> GuidedAngle.RIGHT
                }
                val bitmap: Bitmap? = try {
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
                        val source = android.graphics.ImageDecoder.createSource(context.contentResolver, uri)
                        android.graphics.ImageDecoder.decodeBitmap(source) { decoder, _, _ ->
                            decoder.setTargetSampleSize(4)
                        }
                    } else {
                        @Suppress("DEPRECATION")
                        android.provider.MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
                    }
                } catch (_: Exception) {
                    null
                }
                CapturedFacePhoto(uri = uri, bitmap = bitmap, angle = angle)
            }
            capturedPhotos = capturedPhotos + newPhotos
            newPhotos.lastOrNull { it.bitmap != null }?.bitmap?.let { latestBitmap = it }
        }
    }

    fun launchCamera() {
        if (hasCameraPermission) {
            takePhoto(
                imageCapture = imageCapture,
                executor = mainExecutor,
                isFrontCamera = true,
                onImageCaptured = { bitmap ->
                    val newPhoto = CapturedFacePhoto(bitmap = bitmap, angle = currentAngle)
                    capturedPhotos = capturedPhotos + newPhoto
                    latestBitmap = bitmap
                    coroutineScope.launch {
                        isFlashing = true
                        delay(120)
                        isFlashing = false
                    }
                },
                onError = { exc ->
                    Toast.makeText(context, "Photo capture failed: ${exc.message}", Toast.LENGTH_SHORT).show()
                }
            )
        } else {
            cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }

    BackHandler(onBack = onBackClick)

    BoxWithConstraints(
        modifier = modifier
            .fillMaxSize()
            .background(WardrobeSurface)
    ) {
        val isCompact = maxHeight < 820.dp

        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .navigationBarsPadding()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp)
                .padding(top = 4.dp, bottom = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Section Label + Terracotta Line
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "FULL BODY CAPTURE",
                    fontFamily = InterFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 11.sp,
                    letterSpacing = 1.1.sp,
                    color = WardrobeMuted
                )
                Spacer(modifier = Modifier.height(3.dp))
                Box(
                    modifier = Modifier
                        .size(width = 28.dp, height = 2.dp)
                        .clip(RoundedCornerShape(9999.dp))
                        .background(Color(0xFFC06C54)) // Terracotta
                )
            }

            // Editorial Headline: "Let’s capture\nyour face."
            Text(
                text = "Let’s capture\nyour silhouette.",
                fontFamily = BodoniModaFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = if (isCompact) 29.sp else 32.sp,
                lineHeight = if (isCompact) 33.sp else 37.sp,
                letterSpacing = (-0.32).sp,
                color = WardrobeCharcoal,
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(6.dp))

            // Subtitle Description
            Text(
                text = "Take a few full-body photos from different angles so we can understand your proportions, silhouette and fit.",
                fontFamily = InterFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 13.sp,
                lineHeight = 19.sp,
                color = WardrobeMuted,
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(if (isCompact) 12.dp else 16.dp))

            // Camera Preview Viewport
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(if (isCompact) 360.dp else 400.dp)
                    .shadow(4.dp, RoundedCornerShape(16.dp), ambientColor = Color(0x14000000))
                    .clip(RoundedCornerShape(16.dp))
                    .border(1.dp, Color(0xFFE5E0D6), RoundedCornerShape(16.dp))
                    .background(Color(0xFFF5F2EA))
            ) {
                if (hasCameraPermission) {
                    InAppCameraPreview(
                        imageCapture = imageCapture,
                        lensFacing = CameraSelector.LENS_FACING_FRONT
                    )
                } else if (latestBitmap != null) {
                    Image(
                        bitmap = latestBitmap!!.asImageBitmap(),
                        contentDescription = "Uploaded photo preview",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.full_body_capture_demo),
                        contentDescription = "Default camera demo preview",
                        contentScale = ContentScale.Crop,
                        alignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    )
                }

                if (isFlashing) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.White.copy(alpha = 0.7f))
                    )
                }

                // Step & Angle Indicator Badge (top right)
                StepAngleBadge(
                    stepNumber = stepNumber,
                    totalSteps = 3,
                    angleLabel = currentAngle.label,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(14.dp)
                )

                // Face Framing Guides (Corner Brackets, Dotted Oval, Crosshairs)
                CameraFramingOverlay()
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Guidance Hints Row
            QualityHintRow()

            Spacer(modifier = Modifier.height(16.dp))

            // Primary Shutter Button: TAKE PHOTO
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .shadow(3.dp, RoundedCornerShape(12.dp), ambientColor = Color(0x1F7D8B72))
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFF7D8B72)) // Sage
                    .clickable { launchCamera() },
                contentAlignment = Alignment.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    // Camera Outline Icon
                    Canvas(modifier = Modifier.size(18.dp)) {
                        val w = size.width
                        val h = size.height
                        val stroke = 1.8.dp.toPx()
                        // Body
                        val body = Path().apply {
                            moveTo(w * 0.15f, h * 0.35f)
                            lineTo(w * 0.3f, h * 0.35f)
                            lineTo(w * 0.38f, h * 0.2f)
                            lineTo(w * 0.62f, h * 0.2f)
                            lineTo(w * 0.7f, h * 0.35f)
                            lineTo(w * 0.85f, h * 0.35f)
                            quadraticTo(w, h * 0.35f, w, h * 0.5f)
                            lineTo(w, h * 0.85f)
                            quadraticTo(w, h, w * 0.85f, h)
                            lineTo(w * 0.15f, h)
                            quadraticTo(0f, h, 0f, h * 0.85f)
                            lineTo(0f, h * 0.5f)
                            quadraticTo(0f, h * 0.35f, w * 0.15f, h * 0.35f)
                        }
                        drawPath(body, Color.White, style = Stroke(stroke, cap = StrokeCap.Round, join = StrokeJoin.Round))
                        // Lens
                        drawCircle(Color.White, radius = w * 0.22f, center = Offset(w * 0.5f, h * 0.65f), style = Stroke(stroke))
                    }

                    Text(
                        text = "TAKE PHOTO",
                        fontFamily = InterFontFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 13.sp,
                        letterSpacing = 1.2.sp,
                        color = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Secondary Action: Upload from gallery
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(44.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .clickable {
                        galleryPickerLauncher.launch(
                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                        )
                    },
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Gallery Icon
                Canvas(modifier = Modifier.size(16.dp)) {
                    val w = size.width
                    val h = size.height
                    val stroke = 1.5.dp.toPx()
                    // Frame
                    drawRoundRect(
                        color = Color(0xFF57534E),
                        size = Size(w, h),
                        cornerRadius = androidx.compose.ui.geometry.CornerRadius(2.dp.toPx()),
                        style = Stroke(stroke)
                    )
                    // Mountain peaks
                    val mtn = Path().apply {
                        moveTo(w * 0.15f, h * 0.75f)
                        lineTo(w * 0.45f, h * 0.45f)
                        lineTo(w * 0.65f, h * 0.65f)
                        lineTo(w * 0.85f, h * 0.45f)
                    }
                    drawPath(mtn, Color(0xFF57534E), style = Stroke(stroke, cap = StrokeCap.Round))
                }

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = "Upload from gallery",
                    fontFamily = InterFontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 13.sp,
                    color = WardrobeCharcoal
                )
            }

            // Photo Strip Section (when photos are captured or selected)
            if (capturedPhotos.isNotEmpty()) {
                Spacer(modifier = Modifier.height(14.dp))
                PhotoThumbnailStrip(
                    photos = capturedPhotos,
                    onRemovePhoto = { photoToRemove ->
                        val remaining = capturedPhotos.filter { it.id != photoToRemove.id }
                        capturedPhotos = remaining
                        if (latestBitmap == photoToRemove.bitmap) {
                            latestBitmap = remaining.lastOrNull { it.bitmap != null }?.bitmap
                        }
                    },
                    onAddPhotoClick = {
                        launchCamera()
                    }
                )
            }

            // Continue Button (Enabled once minimum 3 views are captured)
            if (hasSufficientCoverage) {
                Spacer(modifier = Modifier.height(16.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                        .shadow(3.dp, RoundedCornerShape(9999.dp), ambientColor = Color(0x1F7D8B72))
                        .clip(RoundedCornerShape(9999.dp))
                        .background(Color(0xFF7D8B72))
                        .clickable { onContinue() },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "CONTINUE →",
                        fontFamily = InterFontFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 13.5.sp,
                        letterSpacing = 1.3.sp,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Preview(name = "Standard 390x844", widthDp = 390, heightDp = 844, showBackground = true)
@Composable
private fun FullBodyCaptureStandardPreview() {
    WardrobeTheme {
        FullBodyCaptureScreen()
    }
}

@Preview(name = "Compact 360x800", widthDp = 360, heightDp = 800, showBackground = true)
@Composable
private fun FaceCaptureCompactPreview() {
    WardrobeTheme {
        FullBodyCaptureScreen()
    }
}

@Preview(name = "Large 412x915", widthDp = 412, heightDp = 915, showBackground = true)
@Composable
private fun FaceCaptureLargePreview() {
    WardrobeTheme {
        FullBodyCaptureScreen()
    }
}
