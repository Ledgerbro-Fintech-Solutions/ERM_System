package com.example.emiyo.feature.home

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.emiyo.R
import com.example.emiyo.core.design.BodoniModaFontFamily
import com.example.emiyo.core.design.InterFontFamily
import com.example.emiyo.core.design.WardrobeBorder
import com.example.emiyo.core.design.WardrobeCharcoal
import com.example.emiyo.core.design.WardrobeMuted
import com.example.emiyo.core.design.WardrobeSurface
import com.example.emiyo.core.design.WardrobeWordmarkStyle

// ─────────────────────────────────────────────────────────────────────────────
// Data Models
// ─────────────────────────────────────────────────────────────────────────────

data class StyleProfile(
    val title: String = "WARM NEUTRAL",
    val traits: List<String> = listOf("Balanced contrast", "Relaxed tailoring"),
    val swatches: List<Color> = listOf(
        Color(0xFFEADFD0),
        Color(0xFFC29868),
        Color(0xFFB86548),
        Color(0xFF646F58),
        Color(0xFF483B32)
    ),
    val portraitRes: Int = R.drawable.wardrobe_understand_face_hero
)

data class TodaysLook(
    val weather: String = "28°C · Partly cloudy",
    val weatherNote: String = "Lightweight layers work best today.",
    val headline: String = "Effortless layers for a warm day.",
    val outfitRes: Int = R.drawable.beige_blazer
)

data class WardrobeSummary(
    val headline: String = "Your closet starts here.",
    val subtitle: String = "Add pieces as you scan or shop.",
    val thumbnails: List<Int> = listOf(
        R.drawable.beige_blazer,
        R.drawable.ivory_tank,
        R.drawable.ivory_wide_trousers,
        R.drawable.brown_tote
    )
)

data class ForYouItem(
    val title: String,
    val imageRes: Int
)

// ─────────────────────────────────────────────────────────────────────────────
// Root Screen
// ─────────────────────────────────────────────────────────────────────────────

@Composable
fun WardrobeHomeScreen(
    onViewAnalysis: () -> Unit = {},
    onSeeTheLook: () -> Unit = {},
    onScanNow: () -> Unit = {},
    onViewWardrobe: () -> Unit = {},
    onAskStylist: () -> Unit = {},
    onExploreMore: () -> Unit = {},
    onWardrobeTab: () -> Unit = {},
    onTryTab: () -> Unit = {},
    onStylistTab: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val styleProfile = StyleProfile()
    val todaysLook = TodaysLook()
    val wardrobeSummary = WardrobeSummary()
    val forYouItems = listOf(
        ForYouItem("Your best combinations", R.drawable.beige_blazer),
        ForYouItem("Looks for your color palette", R.drawable.wardrobe_understand_face_hero),
        ForYouItem("Trending styles that suit you", R.drawable.olive_sweater)
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(WardrobeSurface)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(bottom = 80.dp)
        ) {
            // Spacer for sticky header
            Spacer(modifier = Modifier.height(60.dp))
            GreetingSection()
            StyleProfileCarouselSection(profile = styleProfile, onViewAnalysis = onViewAnalysis)
            Spacer(modifier = Modifier.height(28.dp))
            TodaysLookSection(look = todaysLook, onSeeTheLook = onSeeTheLook)
            Spacer(modifier = Modifier.height(24.dp))
            TryThisBannerSection(onScanNow = onScanNow)
            Spacer(modifier = Modifier.height(24.dp))
            WardrobeStylistRow(
                summary = wardrobeSummary,
                onViewWardrobe = onViewWardrobe,
                onAskStylist = onAskStylist
            )
            Spacer(modifier = Modifier.height(24.dp))
            ForYouRailSection(items = forYouItems, onExploreMore = onExploreMore)
            Spacer(modifier = Modifier.height(16.dp))
        }

        HomeHeader(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
                .zIndex(10f)
        )

        HomeBottomNavigation(
            activeTab = HomeTab.Home,
            onTabSelected = { tab ->
                when (tab) {
                    HomeTab.Wardrobe -> onWardrobeTab()
                    HomeTab.Try -> onTryTab()
                    HomeTab.Stylist -> onStylistTab()
                    else -> {}
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .zIndex(10f)
        )
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// Home Header
// ─────────────────────────────────────────────────────────────────────────────

@Composable
fun HomeHeader(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(WardrobeSurface.copy(alpha = 0.97f), WardrobeSurface.copy(alpha = 0.92f))
                )
            )
            .statusBarsPadding()
            .height(56.dp)
            .padding(horizontal = 24.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier.width(28.dp))

        Text(
            text = "W A R D R O B E",
            style = WardrobeWordmarkStyle.copy(fontSize = 10.5.sp, letterSpacing = 0.36.sp),
            color = WardrobeCharcoal
        )

        Box(
            modifier = Modifier
                .size(28.dp)
                .clickable(indication = null, interactionSource = remember { MutableInteractionSource() }) {},
            contentAlignment = Alignment.Center
        ) {
            Canvas(modifier = Modifier.size(20.dp)) {
                val sw = Stroke(1.4.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round)
                val col = Color(0xFF1A1715)
                val p = Path()
                p.moveTo(size.width * 0.1f, size.height * 0.88f)
                p.lineTo(size.width * 0.9f, size.height * 0.88f)
                p.lineTo(size.width * 0.5f, size.height * 0.35f)
                p.close()
                drawPath(p, color = col, style = sw)
                drawArc(col, 200f, 140f, false,
                    topLeft = Offset(size.width * 0.28f, 0f),
                    size = Size(size.width * 0.44f, size.height * 0.5f), style = sw)
                drawLine(col,
                    Offset(size.width * 0.1f, size.height * 0.88f),
                    Offset(size.width * 0.9f, size.height * 0.88f),
                    1.4.dp.toPx(), cap = StrokeCap.Round)
            }
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// Greeting Section
// ─────────────────────────────────────────────────────────────────────────────

@Composable
fun GreetingSection(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(horizontal = 24.dp)
            .padding(top = 12.dp, bottom = 20.dp)
    ) {
        Column(horizontalAlignment = Alignment.Start) {
            Text(
                text = "GOOD AFTERNOON",
                fontFamily = InterFontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 10.sp,
                letterSpacing = 1.4.sp,
                color = WardrobeCharcoal
            )
            Spacer(modifier = Modifier.height(3.dp))
            Box(
                modifier = Modifier
                    .size(width = 28.dp, height = 1.5.dp)
                    .clip(RoundedCornerShape(9999.dp))
                    .background(Color(0xFFBA664C))
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Your style,\nunderstood.",
            fontFamily = BodoniModaFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 38.sp,
            lineHeight = 42.sp,
            letterSpacing = (-0.5).sp,
            color = WardrobeCharcoal
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "We've started building a style profile around you.",
            fontFamily = InterFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 13.sp,
            lineHeight = 18.sp,
            color = WardrobeMuted
        )
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// Style Profile Carousel
// ─────────────────────────────────────────────────────────────────────────────

@Composable
fun StyleProfileCarouselSection(
    profile: StyleProfile,
    onViewAnalysis: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .horizontalScroll(rememberScrollState())
            .padding(start = 24.dp, end = 12.dp)
    ) {
        // Main card
        Row(
            modifier = Modifier
                .width(350.dp)
                .shadow(2.dp, RoundedCornerShape(20.dp), ambientColor = Color(0x0A000000))
                .clip(RoundedCornerShape(20.dp))
                .background(Color(0xFFF6F3EC))
                .border(1.dp, WardrobeBorder.copy(alpha = 0.6f), RoundedCornerShape(20.dp))
                .padding(12.dp)
        ) {
            // Portrait + badge
            Box(
                modifier = Modifier
                    .width(148.dp)
                    .height(184.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(WardrobeSurface)
            ) {
                Image(
                    painter = painterResource(id = profile.portraitRes),
                    contentDescription = "Style profile portrait",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(10.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(Color(0x57000000))
                        .padding(horizontal = 6.dp, vertical = 3.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        Text("1/3", fontFamily = InterFontFamily, fontWeight = FontWeight.Medium, fontSize = 10.sp, color = Color.White, letterSpacing = 0.6.sp)
                        Box(modifier = Modifier.size(width = 24.dp, height = 1.5.dp).clip(RoundedCornerShape(9999.dp)).background(Color.White.copy(alpha = 0.7f)))
                    }
                }
            }
            Spacer(modifier = Modifier.width(12.dp))
            // Content
            Column(
                modifier = Modifier.weight(1f).fillMaxHeight().padding(vertical = 4.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text("YOUR STYLE PROFILE", fontFamily = InterFontFamily, fontWeight = FontWeight.SemiBold, fontSize = 8.5.sp, letterSpacing = 0.8.sp, color = WardrobeMuted, maxLines = 1)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(profile.title, fontFamily = BodoniModaFontFamily, fontWeight = FontWeight.Normal, fontSize = 19.sp, letterSpacing = 1.sp, color = WardrobeCharcoal, lineHeight = 22.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    profile.traits.forEach { trait ->
                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp), modifier = Modifier.padding(bottom = 2.dp)) {
                            Box(modifier = Modifier.size(6.dp).clip(CircleShape).background(Color(0xFFBA9873)))
                            Text(trait, fontFamily = InterFontFamily, fontWeight = FontWeight.Light, fontSize = 11.sp, color = WardrobeCharcoal.copy(alpha = 0.8f))
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                        profile.swatches.forEach { swatchColor ->
                            Box(modifier = Modifier.size(16.dp).clip(CircleShape).background(swatchColor).border(0.5.dp, Color.Black.copy(alpha = 0.08f), CircleShape))
                        }
                    }
                }
                Column(
                    modifier = Modifier
                        .clickable(indication = null, interactionSource = remember { MutableInteractionSource() }) { onViewAnalysis() }
                        .padding(top = 6.dp)
                ) {
                    Text("VIEW MY ANALYSIS →", fontFamily = InterFontFamily, fontWeight = FontWeight.SemiBold, fontSize = 9.5.sp, letterSpacing = 0.7.sp, color = WardrobeCharcoal)
                    Spacer(modifier = Modifier.height(1.dp))
                    Box(modifier = Modifier.fillMaxWidth(0.9f).height(0.7.dp).background(WardrobeCharcoal))
                }
            }
        }

        Spacer(modifier = Modifier.width(10.dp))

        // Peek card
        Box(
            modifier = Modifier
                .width(64.dp)
                .height(190.dp)
                .clip(RoundedCornerShape(topStart = 16.dp, bottomStart = 16.dp))
                .background(Color(0xFFF0EDE6))
                .border(1.dp, WardrobeBorder.copy(alpha = 0.6f), RoundedCornerShape(topStart = 16.dp, bottomStart = 16.dp))
        ) {
            Image(
                painter = painterResource(id = R.drawable.wardrobe_understand_face_hero),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize().clip(RoundedCornerShape(topStart = 16.dp, bottomStart = 16.dp)),
                alpha = 0.7f
            )
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// Section Label Helper
// ─────────────────────────────────────────────────────────────────────────────

@Composable
private fun SectionLabel(text: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(bottom = 10.dp), horizontalAlignment = Alignment.Start) {
        Text(text, fontFamily = InterFontFamily, fontWeight = FontWeight.SemiBold, fontSize = 10.sp, letterSpacing = 1.5.sp, color = WardrobeCharcoal)
        Spacer(modifier = Modifier.height(3.dp))
        Box(modifier = Modifier.size(width = 28.dp, height = 1.5.dp).clip(RoundedCornerShape(9999.dp)).background(Color(0xFFBA664C)))
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// Today's Look
// ─────────────────────────────────────────────────────────────────────────────

@Composable
fun TodaysLookSection(
    look: TodaysLook,
    onSeeTheLook: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(horizontal = 24.dp)) {
        SectionLabel("TODAY'S LOOK")
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(2.dp, RoundedCornerShape(20.dp), ambientColor = Color(0x0A000000))
                .clip(RoundedCornerShape(20.dp))
                .background(Color(0xFFF6F3EC))
                .border(1.dp, WardrobeBorder.copy(alpha = 0.6f), RoundedCornerShape(20.dp))
                .padding(12.dp)
        ) {
            Box(
                modifier = Modifier.width(148.dp).height(228.dp).clip(RoundedCornerShape(12.dp)).background(WardrobeSurface)
            ) {
                Image(
                    painter = painterResource(id = look.outfitRes),
                    contentDescription = "Today's outfit",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column(
                modifier = Modifier.weight(1f).fillMaxHeight().padding(vertical = 4.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    // Weather row
                    Row(horizontalArrangement = Arrangement.spacedBy(6.dp), verticalAlignment = Alignment.Top) {
                        Canvas(modifier = Modifier.size(14.dp).padding(top = 1.dp)) {
                            val sw = Stroke(1.3.dp.toPx(), cap = StrokeCap.Round)
                            val col = Color(0xFF7A736B)
                            drawCircle(col, size.width * 0.22f, Offset(size.width * 0.6f, size.height * 0.35f), style = sw)
                            drawArc(col, 200f, 140f, false, Offset(0f, size.height * 0.4f), Size(size.width * 0.65f, size.height * 0.5f), style = sw)
                        }
                        Column {
                            Text(look.weather, fontFamily = InterFontFamily, fontWeight = FontWeight.Medium, fontSize = 10.5.sp, color = WardrobeCharcoal)
                            Text(look.weatherNote, fontFamily = InterFontFamily, fontWeight = FontWeight.Normal, fontSize = 10.sp, color = WardrobeMuted, lineHeight = 14.sp)
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Box(modifier = Modifier.fillMaxWidth().height(0.7.dp).background(WardrobeBorder.copy(alpha = 0.7f)))
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(look.headline, fontFamily = BodoniModaFontFamily, fontWeight = FontWeight.Normal, fontSize = 22.sp, lineHeight = 26.sp, color = WardrobeCharcoal, modifier = Modifier.padding(end = 4.dp))
                }
                Column(
                    modifier = Modifier
                        .clickable(indication = null, interactionSource = remember { MutableInteractionSource() }) { onSeeTheLook() }
                        .padding(top = 8.dp)
                ) {
                    Text("SEE THE LOOK →", fontFamily = InterFontFamily, fontWeight = FontWeight.SemiBold, fontSize = 9.5.sp, letterSpacing = 0.7.sp, color = WardrobeCharcoal)
                    Spacer(modifier = Modifier.height(1.dp))
                    Box(modifier = Modifier.fillMaxWidth(0.88f).height(0.7.dp).background(WardrobeCharcoal))
                }
            }
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// Try This Banner
// ─────────────────────────────────────────────────────────────────────────────

@Composable
fun TryThisBannerSection(
    onScanNow: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .padding(horizontal = 24.dp)
            .fillMaxWidth()
            .shadow(2.dp, RoundedCornerShape(20.dp), ambientColor = Color(0x0A000000))
            .clip(RoundedCornerShape(20.dp))
            .background(Brush.horizontalGradient(listOf(Color(0xFFF6F3EC), Color(0xFFF4EDE2), Color(0xFFECE1D0))))
            .border(1.dp, WardrobeBorder.copy(alpha = 0.6f), RoundedCornerShape(20.dp))
            .padding(16.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            // Left
            Column(modifier = Modifier.weight(0.55f)) {
                Column(horizontalAlignment = Alignment.Start) {
                    Text("TRY THIS", fontFamily = InterFontFamily, fontWeight = FontWeight.SemiBold, fontSize = 9.sp, letterSpacing = 1.4.sp, color = WardrobeCharcoal)
                    Spacer(modifier = Modifier.height(3.dp))
                    Box(modifier = Modifier.size(width = 20.dp, height = 1.5.dp).clip(RoundedCornerShape(9999.dp)).background(Color(0xFFBA664C)))
                }
                Spacer(modifier = Modifier.height(6.dp))
                Text("Scan a clothing item", fontFamily = BodoniModaFontFamily, fontWeight = FontWeight.Normal, fontSize = 18.sp, lineHeight = 22.sp, color = WardrobeCharcoal)
                Spacer(modifier = Modifier.height(4.dp))
                Text("See how a piece suits you before you buy.", fontFamily = InterFontFamily, fontWeight = FontWeight.Normal, fontSize = 10.5.sp, lineHeight = 14.sp, color = WardrobeMuted)
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    modifier = Modifier.clip(RoundedCornerShape(8.dp)).background(Color(0xFF5E6852)).clickable { onScanNow() }.padding(horizontal = 12.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Canvas(modifier = Modifier.size(12.dp)) {
                        val sw = Stroke(1.3.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round)
                        drawRoundRect(Color.White, Offset(0f, size.height * 0.3f), Size(size.width, size.height * 0.7f), CornerRadius(2.dp.toPx()), style = sw)
                        drawCircle(Color.White, size.width * 0.2f, Offset(size.width * 0.5f, size.height * 0.65f), style = sw)
                    }
                    Text("SCAN NOW", fontFamily = InterFontFamily, fontWeight = FontWeight.SemiBold, fontSize = 10.sp, letterSpacing = 0.8.sp, color = Color.White)
                }
            }

            // Right — product collage
            Box(modifier = Modifier.width(120.dp).height(110.dp)) {
                Column(verticalArrangement = Arrangement.spacedBy(1.dp)) {
                    Row(modifier = Modifier.fillMaxWidth().height(54.dp), horizontalArrangement = Arrangement.spacedBy(1.dp)) {
                        Box(modifier = Modifier.weight(1f).fillMaxHeight().clip(RoundedCornerShape(topStart = 10.dp, topEnd = 0.dp)).background(Color(0xFFF2EFE9))) {
                            Image(painterResource(id = R.drawable.ivory_wide_trousers), null, contentScale = ContentScale.Crop, modifier = Modifier.fillMaxSize())
                        }
                        Box(modifier = Modifier.weight(1f).fillMaxHeight().clip(RoundedCornerShape(topEnd = 10.dp)).background(Color(0xFFF2EFE9))) {
                            Image(painterResource(id = R.drawable.beige_blazer), null, contentScale = ContentScale.Crop, modifier = Modifier.fillMaxSize())
                        }
                    }
                    Row(modifier = Modifier.fillMaxWidth().height(55.dp), horizontalArrangement = Arrangement.spacedBy(1.dp)) {
                        Box(modifier = Modifier.weight(1f).fillMaxHeight().clip(RoundedCornerShape(bottomStart = 10.dp)).background(Color(0xFFF2EFE9))) {
                            Image(painterResource(id = R.drawable.olive_sweater), null, contentScale = ContentScale.Crop, modifier = Modifier.fillMaxSize())
                        }
                        Box(modifier = Modifier.weight(1f).fillMaxHeight().clip(RoundedCornerShape(bottomEnd = 10.dp)).background(Color(0xFFF2EFE9))) {
                            Image(painterResource(id = R.drawable.cognac_loafers), null, contentScale = ContentScale.Crop, modifier = Modifier.fillMaxSize())
                        }
                    }
                }
            }
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// Wardrobe + Stylist 2-col Row
// ─────────────────────────────────────────────────────────────────────────────

@Composable
fun WardrobeStylistRow(
    summary: WardrobeSummary,
    onViewWardrobe: () -> Unit,
    onAskStylist: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.padding(horizontal = 24.dp).fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // My Wardrobe
        Column(
            modifier = Modifier.weight(1f)
                .shadow(2.dp, RoundedCornerShape(20.dp), ambientColor = Color(0x0A000000))
                .clip(RoundedCornerShape(20.dp))
                .background(Color(0xFFF6F3EC))
                .border(1.dp, WardrobeBorder.copy(alpha = 0.6f), RoundedCornerShape(20.dp))
                .padding(14.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    Canvas(modifier = Modifier.size(12.dp)) {
                        val sw = Stroke(1.2.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round)
                        val c = Color(0xFF1A1715)
                        val p = Path(); p.moveTo(size.width * 0.1f, size.height * 0.9f); p.lineTo(size.width * 0.9f, size.height * 0.9f); p.lineTo(size.width * 0.5f, size.height * 0.35f); p.close()
                        drawPath(p, c, style = sw)
                        drawArc(c, 200f, 140f, false, Offset(size.width * 0.28f, 0f), Size(size.width * 0.44f, size.height * 0.5f), style = sw)
                    }
                    Text("MY WARDROBE", fontFamily = InterFontFamily, fontWeight = FontWeight.SemiBold, fontSize = 8.5.sp, letterSpacing = 0.7.sp, color = WardrobeCharcoal)
                }
                Spacer(modifier = Modifier.height(6.dp))
                Text(summary.headline, fontFamily = InterFontFamily, fontWeight = FontWeight.Normal, fontSize = 11.sp, lineHeight = 15.sp, color = WardrobeCharcoal)
                Text(summary.subtitle, fontFamily = InterFontFamily, fontWeight = FontWeight.Light, fontSize = 9.5.sp, lineHeight = 13.sp, color = WardrobeMuted, modifier = Modifier.padding(top = 2.dp))
                Spacer(modifier = Modifier.height(10.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    summary.thumbnails.forEach { resId ->
                        Box(modifier = Modifier.size(width = 28.dp, height = 32.dp).clip(RoundedCornerShape(4.dp)).background(Color.White).border(0.5.dp, Color.Black.copy(alpha = 0.06f), RoundedCornerShape(4.dp)), contentAlignment = Alignment.Center) {
                            Image(painterResource(id = resId), null, contentScale = ContentScale.Fit, modifier = Modifier.fillMaxSize().padding(2.dp))
                        }
                    }
                    Box(modifier = Modifier.size(width = 28.dp, height = 32.dp).clip(RoundedCornerShape(4.dp)).background(WardrobeSurface).border(0.5.dp, WardrobeBorder, RoundedCornerShape(4.dp)).clickable { onViewWardrobe() }, contentAlignment = Alignment.Center) {
                        Text("+", fontFamily = InterFontFamily, fontWeight = FontWeight.Light, fontSize = 14.sp, color = WardrobeMuted)
                    }
                }
            }
            Column(
                modifier = Modifier.clickable(indication = null, interactionSource = remember { MutableInteractionSource() }) { onViewWardrobe() }.padding(top = 10.dp)
            ) {
                Text("VIEW WARDROBE →", fontFamily = InterFontFamily, fontWeight = FontWeight.SemiBold, fontSize = 9.sp, letterSpacing = 0.6.sp, color = WardrobeCharcoal)
                Box(modifier = Modifier.fillMaxWidth(0.85f).height(0.7.dp).background(WardrobeCharcoal))
            }
        }

        // Your Stylist
        Column(
            modifier = Modifier.weight(1f)
                .shadow(2.dp, RoundedCornerShape(20.dp), ambientColor = Color(0x0A000000))
                .clip(RoundedCornerShape(20.dp))
                .background(Color(0xFFF6F3EC))
                .border(1.dp, WardrobeBorder.copy(alpha = 0.6f), RoundedCornerShape(20.dp))
                .padding(14.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    Canvas(modifier = Modifier.size(12.dp)) {
                        val sw = Stroke(1.2.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round)
                        val c = Color(0xFF1A1715)
                        drawRoundRect(c, Offset(0f, 0f), Size(size.width, size.height * 0.75f), CornerRadius(2.dp.toPx()), style = sw)
                        val t = Path(); t.moveTo(size.width * 0.15f, size.height * 0.75f); t.lineTo(size.width * 0.1f, size.height); t.lineTo(size.width * 0.35f, size.height * 0.75f); t.close()
                        drawPath(t, c, style = sw)
                    }
                    Text("YOUR STYLIST", fontFamily = InterFontFamily, fontWeight = FontWeight.SemiBold, fontSize = 8.5.sp, letterSpacing = 0.7.sp, color = WardrobeCharcoal)
                }
                Spacer(modifier = Modifier.height(6.dp))
                Text("\u201cWhat should I wear?\u201d", fontFamily = BodoniModaFontFamily, fontWeight = FontWeight.Normal, fontSize = 14.sp, lineHeight = 18.sp, color = WardrobeCharcoal)
                Spacer(modifier = Modifier.height(6.dp))
                Text("Tell me where you're going, and I'll build a look around you.", fontFamily = InterFontFamily, fontWeight = FontWeight.Light, fontSize = 9.5.sp, lineHeight = 13.sp, color = WardrobeMuted)
            }
            Column(
                modifier = Modifier.clickable(indication = null, interactionSource = remember { MutableInteractionSource() }) { onAskStylist() }.padding(top = 16.dp)
            ) {
                Text("ASK MY STYLIST →", fontFamily = InterFontFamily, fontWeight = FontWeight.SemiBold, fontSize = 9.sp, letterSpacing = 0.6.sp, color = WardrobeCharcoal)
                Box(modifier = Modifier.fillMaxWidth(0.9f).height(0.7.dp).background(WardrobeCharcoal))
            }
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// For You Rail
// ─────────────────────────────────────────────────────────────────────────────

@Composable
fun ForYouRailSection(
    items: List<ForYouItem>,
    onExploreMore: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp).padding(bottom = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("FOR YOU", fontFamily = InterFontFamily, fontWeight = FontWeight.SemiBold, fontSize = 10.sp, letterSpacing = 1.5.sp, color = WardrobeCharcoal)
            Text(
                text = "EXPLORE MORE →",
                fontFamily = InterFontFamily, fontWeight = FontWeight.SemiBold, fontSize = 9.5.sp, letterSpacing = 0.7.sp, color = WardrobeMuted,
                modifier = Modifier.clickable(indication = null, interactionSource = remember { MutableInteractionSource() }) { onExploreMore() }
            )
        }
        Row(
            modifier = Modifier.horizontalScroll(rememberScrollState()).padding(start = 24.dp, end = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items.forEach { item ->
                Box(
                    modifier = Modifier.size(width = 112.dp, height = 78.dp)
                        .shadow(1.dp, RoundedCornerShape(12.dp), ambientColor = Color(0x08000000))
                        .clip(RoundedCornerShape(12.dp))
                        .clickable {}
                ) {
                    Image(painterResource(id = item.imageRes), item.title, contentScale = ContentScale.Crop, modifier = Modifier.fillMaxSize())
                    Box(modifier = Modifier.fillMaxSize().background(Brush.verticalGradient(listOf(Color.Transparent, Color(0x33000000), Color(0xBF000000)))))
                    Text(item.title, fontFamily = InterFontFamily, fontWeight = FontWeight.Medium, fontSize = 9.5.sp, lineHeight = 13.sp, color = Color.White,
                        modifier = Modifier.align(Alignment.BottomStart).padding(8.dp), maxLines = 2, overflow = TextOverflow.Ellipsis)
                }
            }
            // More tile
            Box(
                modifier = Modifier.size(width = 82.dp, height = 78.dp)
                    .shadow(1.dp, RoundedCornerShape(12.dp), ambientColor = Color(0x08000000))
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFFF6F3EC))
                    .border(1.dp, WardrobeBorder.copy(alpha = 0.6f), RoundedCornerShape(12.dp))
                    .clickable { onExploreMore() },
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Canvas(modifier = Modifier.size(18.dp)) {
                        val sw = Stroke(1.2.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round)
                        val c = Color(0xFF8C7A6B)
                        val p = Path()
                        val cx = size.width / 2f; val cy = size.height / 2f
                        val outer = size.width * 0.45f; val inner = size.width * 0.2f
                        for (i in 0 until 10) {
                            val r = if (i % 2 == 0) outer else inner
                            val angle = Math.PI * i / 5 - Math.PI / 2
                            val x = cx + (r * Math.cos(angle)).toFloat()
                            val y = cy + (r * Math.sin(angle)).toFloat()
                            if (i == 0) p.moveTo(x, y) else p.lineTo(x, y)
                        }
                        p.close()
                        drawPath(p, c, style = sw)
                    }
                    Text("More\nfor you", fontFamily = InterFontFamily, fontWeight = FontWeight.Medium, fontSize = 8.5.sp, color = WardrobeMuted, textAlign = TextAlign.Center, lineHeight = 12.sp)
                }
            }
            Spacer(modifier = Modifier.width(12.dp))
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// Bottom Navigation
// ─────────────────────────────────────────────────────────────────────────────

enum class HomeTab { Home, Wardrobe, Try, Stylist }

@Composable
fun HomeBottomNavigation(
    activeTab: HomeTab,
    onTabSelected: (HomeTab) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(Color(0xF7FAF8F4))
            .border(0.7.dp, WardrobeBorder.copy(alpha = 0.7f), RoundedCornerShape(0.dp))
            .navigationBarsPadding()
            .padding(horizontal = 24.dp, vertical = 10.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround, verticalAlignment = Alignment.CenterVertically) {
            // HOME
            NavItem("HOME", activeTab == HomeTab.Home, {
                Canvas(modifier = Modifier.size(20.dp)) {
                    val col = if (activeTab == HomeTab.Home) Color(0xFF1A1715) else Color(0xFF7A736B)
                    val p = Path()
                    p.moveTo(size.width * 0.08f, size.height * 0.5f); p.lineTo(size.width * 0.5f, size.height * 0.12f); p.lineTo(size.width * 0.92f, size.height * 0.5f)
                    p.lineTo(size.width * 0.78f, size.height * 0.5f); p.lineTo(size.width * 0.78f, size.height * 0.88f); p.lineTo(size.width * 0.62f, size.height * 0.88f)
                    p.lineTo(size.width * 0.62f, size.height * 0.65f); p.lineTo(size.width * 0.38f, size.height * 0.65f); p.lineTo(size.width * 0.38f, size.height * 0.88f)
                    p.lineTo(size.width * 0.22f, size.height * 0.88f); p.lineTo(size.width * 0.22f, size.height * 0.5f); p.close()
                    drawPath(p, col)
                }
            }, { onTabSelected(HomeTab.Home) })

            // WARDROBE
            NavItem("WARDROBE", activeTab == HomeTab.Wardrobe, {
                val col = if (activeTab == HomeTab.Wardrobe) Color(0xFF1A1715) else Color(0xFF7A736B)
                Canvas(modifier = Modifier.size(20.dp)) {
                    val sw = Stroke(1.5.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round)
                    val p = Path(); p.moveTo(size.width * 0.12f, size.height * 0.88f); p.lineTo(size.width * 0.88f, size.height * 0.88f); p.lineTo(size.width * 0.5f, size.height * 0.38f); p.close()
                    drawPath(p, col, style = sw)
                    drawArc(col, 200f, 140f, false, Offset(size.width * 0.28f, 0f), Size(size.width * 0.44f, size.height * 0.5f), style = sw)
                }
            }, { onTabSelected(HomeTab.Wardrobe) })

            // TRY
            NavItem("TRY", activeTab == HomeTab.Try, {
                val col = if (activeTab == HomeTab.Try) Color(0xFF1A1715) else Color(0xFF7A736B)
                Canvas(modifier = Modifier.size(20.dp)) {
                    val sw = Stroke(1.5.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round)
                    drawRoundRect(col, Offset(0f, size.height * 0.35f), Size(size.width, size.height * 0.65f), CornerRadius(2.dp.toPx()), style = sw)
                    val n = Path(); n.moveTo(size.width * 0.35f, size.height * 0.35f); n.lineTo(size.width * 0.45f, size.height * 0.1f); n.lineTo(size.width * 0.55f, size.height * 0.1f); n.lineTo(size.width * 0.65f, size.height * 0.35f)
                    drawPath(n, col, style = sw)
                    drawCircle(col, size.width * 0.18f, Offset(size.width * 0.5f, size.height * 0.67f), style = sw)
                }
            }, { onTabSelected(HomeTab.Try) })

            // STYLIST
            NavItem("STYLIST", activeTab == HomeTab.Stylist, {
                val col = if (activeTab == HomeTab.Stylist) Color(0xFF1A1715) else Color(0xFF7A736B)
                Canvas(modifier = Modifier.size(20.dp)) {
                    val sw = Stroke(1.5.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round)
                    drawRoundRect(col, Offset(0f, 0f), Size(size.width, size.height * 0.75f), CornerRadius(2.dp.toPx()), style = sw)
                    val t = Path(); t.moveTo(size.width * 0.15f, size.height * 0.75f); t.lineTo(size.width * 0.08f, size.height); t.lineTo(size.width * 0.4f, size.height * 0.75f)
                    drawPath(t, col, style = sw)
                }
            }, { onTabSelected(HomeTab.Stylist) })
        }
    }
}

@Composable
private fun NavItem(label: String, isActive: Boolean, icon: @Composable () -> Unit, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .clickable(indication = null, interactionSource = remember { MutableInteractionSource() }) { onClick() }
            .padding(horizontal = 8.dp, vertical = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(3.dp)
    ) {
        icon()
        Text(
            text = label,
            fontFamily = InterFontFamily,
            fontWeight = if (isActive) FontWeight.Bold else FontWeight.Medium,
            fontSize = 8.5.sp,
            letterSpacing = 1.sp,
            color = if (isActive) WardrobeCharcoal else WardrobeMuted
        )
    }
}
