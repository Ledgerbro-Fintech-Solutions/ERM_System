# WARDROBE --- AI Personal Stylist & Intelligent Digital Wardrobe

## 1. Project Overview

**WARDROBE** is an Android-first AI-powered personal styling
application.

Core idea:

> **WARDROBE understands the person first, then understands their
> clothes, and finally explains why a particular outfit works for
> them.**

The app analyzes facial characteristics, skin tone, undertone, color
harmony, silhouette, proportions, style preferences, wardrobe items,
occasion, and weather to generate personalized outfit recommendations.

The product should feel like:

**Luxury fashion editorial × premium personal stylist × modern AI
technology**

------------------------------------------------------------------------

## 2. Product Goal

The primary question WARDROBE should answer is:

**"What looks best on me?"**

The product has three layers:

1.  **Understand Me** --- face, skin tone, undertone, contrast,
    silhouette, proportions, shoulder line, fit and style.
2.  **Understand My Clothes** --- detect and organize clothing already
    owned by the user.
3.  **Style Me** --- generate explainable outfits based on personal
    characteristics, wardrobe, occasion and weather.

------------------------------------------------------------------------

## 3. Platform and Technology

### Android

-   Kotlin
-   Jetpack Compose
-   Android Studio
-   CameraX
-   Room
-   Kotlin Coroutines
-   Hilt
-   Coil
-   ML Kit / MediaPipe where appropriate
-   LiteRT and/or ONNX Runtime Mobile

### AI/ML

-   Python
-   PyTorch
-   Lightweight object detection/segmentation
-   Computer vision
-   Color analysis
-   Clothing classification
-   Recommendation/ranking models

### Optional Backend

-   FastAPI
-   PostgreSQL
-   Object storage
-   Authentication
-   Cloud inference only where local inference is impractical

Prefer on-device processing wherever technically feasible.

------------------------------------------------------------------------

# 4. Privacy

Face and body images are sensitive visual data.

Architecture should prefer:

**On-device processing \> cloud processing**

Do not upload photographs unnecessarily.

Provide clear controls for:

-   What is analyzed
-   Why it is required
-   Where it is processed
-   What is stored
-   How the user can delete it

------------------------------------------------------------------------

# 5. Opening Screen

Full-screen mobile application.

Target:

**390 × 844**

No:

-   black outer border
-   phone mockup
-   browser frame
-   device frame
-   fake status bar
-   fake home indicator
-   presentation background

Visual hierarchy:

1.  WARDROBE wordmark
2.  "Your style, smarter."
3.  Short supporting description
4.  Large editorial group photograph
5.  Get Started button
6.  Existing-account link
7.  Minimal progress indicator

Opening copy:

**Your style, smarter.**

AI-powered style recommendations\
for your unique wardrobe.

Primary CTA:

**Get Started →**

Secondary:

**I ALREADY HAVE AN ACCOUNT**

Hero image: seven fictional adults with distinct but sophisticated
fashion styles, photographed as a premium fashion campaign.

Palette:

-   ivory
-   cream
-   beige
-   camel
-   olive
-   terracotta
-   brown
-   charcoal

------------------------------------------------------------------------

# 6. Onboarding / Discovery

Onboarding should be visual and editorial, not form-heavy.

Avoid:

-   excessive text
-   conventional SaaS cards
-   generic AI graphics
-   neon gradients
-   excessive icons
-   feature-menu appearance

Use:

-   large fashion imagery
-   editorial typography
-   generous whitespace
-   subtle motion
-   horizontal swipe navigation

## Screen: Your Style

Headline:

**Style begins with you.**

Introduce personalized styling based on the person.

## Screen: See the Difference

Show one fictional person in two styling/color states.

Demonstrate:

-   color harmony
-   contrast balance
-   perceived facial radiance
-   visual cohesion

Do not alter identity or artificially beautify the face.

The comparison should support a draggable horizontal divider.

## Screen: Your Closet, Understood

Show AI recognizing:

-   shirts
-   trousers
-   shoes
-   jackets
-   colors
-   textures
-   patterns

## Screen: Dress for the Moment

Show:

-   Work
-   Casual
-   Wedding
-   Night Out

Core message:

**Same person. Different moment.**

## Screen: What Should I Wear Today?

Show a complete outfit built from wardrobe items.

Use:

-   personal palette
-   silhouette
-   style
-   weather
-   occasion

## Screen: Make It Yours

Allow preferences such as:

-   Minimal
-   Classic
-   Oversized
-   Streetwear
-   Smart casual
-   Formal
-   Relaxed
-   Contemporary

## Screen: Your Wardrobe Companion

Introduce daily personalized recommendations.

## Screen: Try This

Introduce shopping assistance.

User photographs a garment and receives:

-   color match
-   style match
-   wardrobe compatibility
-   occasion versatility
-   later: virtual try-on

------------------------------------------------------------------------

# 7. Authentication

Screen:

**Welcome back.**

Actions:

-   Continue with Google
-   Continue with Email
-   Sign up

Keep visual treatment minimal.

Do not put large amounts of text over hero imagery.

------------------------------------------------------------------------

# 8. Face Analysis

Introductory screen before photos are collected.

Explain that WARDROBE can analyze:

-   facial structure
-   skin tone
-   undertone
-   contrast
-   color harmony
-   best colors

Example result labels:

-   Face shape: Oval
-   Skin tone: Warm Ivory
-   Undertone: Warm
-   Best colors: Earth Tones

Do not imply that real analysis has already happened before input is
supplied.

------------------------------------------------------------------------

# 9. Face Capture

Keep this screen simple.

Required:

-   Camera capture
-   Gallery upload
-   Front photo
-   Left angle
-   Right angle
-   Optional additional photo

Show:

-   large capture area
-   minimal instruction
-   photo thumbnails
-   continue button

Suggested instruction:

**Capture your face from a few clear angles.**

Validate:

-   face detected
-   face visible
-   adequate sharpness
-   adequate lighting
-   reasonable framing

------------------------------------------------------------------------

# 10. Silhouette Analysis

Introductory screen.

Explain analysis of:

-   body proportions
-   silhouette
-   shoulder line
-   fit
-   clothing proportions

Example labels:

-   Silhouette: Balanced
-   Shoulder line: Soft Structure
-   Proportion: Balanced
-   Best fit: Relaxed Tailoring

------------------------------------------------------------------------

# 11. Full Body Capture

Support:

-   Front
-   Left
-   Back
-   Right
-   Camera
-   Gallery

Validate:

-   full body visible
-   sufficient lighting
-   minimal obstruction
-   reasonable camera distance
-   clear separation from background

Keep the UI visually clean.

------------------------------------------------------------------------

# 12. Style Profile

Example profile:

## WARM NEUTRAL

Attributes:

-   Balanced contrast
-   Relaxed tailoring
-   Warm undertone
-   Balanced silhouette

Recommended palette:

-   Warm Ivory
-   Camel
-   Terracotta
-   Olive
-   Chocolate

This profile becomes the foundation for recommendation ranking.

------------------------------------------------------------------------

# 13. Main Dashboard

The dashboard is the primary product screen.

It must feel like a:

**Personalized fashion discovery feed**

not a feature menu.

Primary question:

**What looks best on me?**

------------------------------------------------------------------------

## Dashboard Header

-   WARDROBE wordmark
-   optional profile/avatar icon

Keep minimal.

------------------------------------------------------------------------

## Editorial Discovery Slider

At the top.

No conventional carousel dots.

Cards:

### Your best colors.

"Warm, muted tones bring your natural features into harmony."

CTA:

**EXPLORE YOUR PALETTE →**

### Combinations that work.

Show personalized shirt + pants + shoes.

Example:

**92% MATCH**

CTA:

**SEE MORE COMBINATIONS →**

### This look suits you.

Show a complete outfit.

Explain:

-   Balanced tones
-   Relaxed tailoring
-   High wardrobe compatibility

CTA:

**SEE THE LOOK →**

------------------------------------------------------------------------

# 14. Dashboard Feed

## FOR YOU

Headline:

**What works on you.**

Show the strongest current recommendation.

Ranking inputs:

-   palette
-   silhouette
-   style
-   wardrobe
-   occasion
-   weather

## FROM YOUR PALETTE

Interactive swatches:

-   Warm Ivory
-   Camel
-   Terracotta
-   Olive
-   Chocolate

Selecting a color updates outfit recommendations.

## BEST COMBINATIONS

Show:

-   shirt + trousers
-   shirt + jeans
-   top + skirt
-   jacket + trousers

Show subtle match indicators.

## TODAY

Weather is contextual, not the hero.

Example:

**28°C · Partly cloudy**

**Lightweight layers work best today.**

## TRY THIS

**Scan a clothing item**

"See how a piece suits you before you buy."

CTA:

**SCAN NOW**

## MY WARDROBE

Empty state:

**Your closet starts here.**

"Add pieces as you scan or shop."

When populated, show actual wardrobe items.

## YOUR STYLIST

**"What should I wear?"**

"Tell me where you're going, and I'll build a look around you."

CTA:

**ASK MY STYLIST →**

------------------------------------------------------------------------

# 15. Bottom Navigation

Four destinations:

1.  HOME
2.  WARDROBE
3.  TRY
4.  STYLIST

Home active on dashboard.

------------------------------------------------------------------------

# 16. Digital Wardrobe

Categories:

-   All
-   Tops
-   Bottoms
-   Outerwear
-   Shoes
-   Dresses
-   Accessories

Each item should store:

-   category
-   subcategory
-   primary color
-   secondary colors
-   pattern
-   texture
-   fit
-   style
-   season
-   formality
-   occasions

Example:

``` text
Beige Linen Blazer
Category: Outerwear
Color: Warm Beige
Pattern: Solid
Style: Minimal / Smart Casual
Fit: Relaxed
Season: Spring / Summer / Autumn
Occasions: Work / Casual / Dinner
```

------------------------------------------------------------------------

# 17. Clothing Add / Scan

Input methods:

-   Camera
-   Gallery
-   Shopping / Try This

Pipeline:

``` text
Image
 ↓
Garment Detection
 ↓
Segmentation
 ↓
Category Classification
 ↓
Color Extraction
 ↓
Pattern Detection
 ↓
Texture / Material Estimation
 ↓
Style Classification
 ↓
Fit Estimation
 ↓
Wardrobe Metadata
 ↓
Room Database
```

Possible technologies:

-   lightweight YOLO-family model
-   segmentation model
-   OpenCV
-   PyTorch
-   LiteRT
-   ONNX Runtime Mobile

Optimize for mobile latency.

------------------------------------------------------------------------

# 18. Outfit Recommendation Engine

Do NOT use an LLM as the core recommendation engine.

Use:

**Rules + ML ranking + preferences + context**

Pipeline:

``` text
User Profile
+
Wardrobe
+
Color Compatibility
+
Silhouette Compatibility
+
Style Preferences
+
Occasion
+
Weather
+
Feedback
        ↓
Candidate Outfit Generation
        ↓
Rule-Based Filtering
        ↓
ML Ranking
        ↓
Explanation Generation
        ↓
Recommendation
```

Initial scoring can be configurable:

``` text
25% Color Compatibility
20% Silhouette/Fit
20% Style
15% Wardrobe Compatibility
10% Occasion
10% Weather
```

Later replace fixed weights with a learned ranking model.

------------------------------------------------------------------------

# 19. Explainability

Every recommendation should explain why it works.

Example:

**Why this works**

-   Warm neutral tones complement your undertone.
-   Relaxed tailoring works with your silhouette.
-   Olive trousers create balanced contrast.
-   Every piece is already in your wardrobe.

Never rely on vague statements such as "AI thinks this looks good."

------------------------------------------------------------------------

# 20. Try This --- Shopping Assistant

User photographs a garment while shopping.

Show:

### Color Match

96%

### Style Match

91%

### Wardrobe Compatibility

94%

### Occasion Versatility

89%

Then:

**Should I buy it?**

Example:

"Strong match. The warm terracotta tone fits your palette and works with
three trousers already in your wardrobe."

Show compatible existing wardrobe combinations.

------------------------------------------------------------------------

# 21. Virtual Try-On

Treat this as a later phase.

### Phase 1

Personal compatibility analysis.

### Phase 2

Static outfit visualization.

### Phase 3

Image-based virtual try-on.

### Phase 4

Advanced pose-aware garment transfer.

Potential technologies:

-   human parsing
-   pose estimation
-   garment segmentation
-   garment warping
-   diffusion-based virtual try-on
-   identity preservation

Keep the feature isolated so the rest of the app does not depend on it.

------------------------------------------------------------------------

# 22. AI Stylist

The stylist is conversational but grounded in structured wardrobe data.

Example:

User:

"What should I wear to a wedding?"

Assistant:

"Based on your warm-neutral palette and relaxed tailoring preference,
start with your terracotta shirt, cream trousers and brown loafers."

User:

"Make it more formal."

Assistant:

"Replace the shirt with your beige blazer and keep the cream trousers.
Add your brown leather shoes."

The LLM may interpret intent and explain recommendations, but it must
not invent wardrobe items that do not exist.

------------------------------------------------------------------------

# 23. Weather

Weather inputs:

-   temperature
-   humidity
-   rain probability
-   wind
-   condition

Use them to influence:

-   fabric weight
-   layering
-   outerwear
-   footwear
-   seasonal styling
-   outfit suitability

Weather remains secondary to personal styling.

------------------------------------------------------------------------

# 24. Compare Outfits

Compare:

-   color harmony
-   fit
-   style
-   occasion
-   weather
-   wardrobe compatibility

Example:

``` text
LOOK A
Color: 94
Fit: 88
Style: 91
Occasion: 95

LOOK B
Color: 88
Fit: 95
Style: 89
Occasion: 92
```

Explain the tradeoff.

------------------------------------------------------------------------

# 25. Share Look

"Share Look" is contextual.

Expose it from:

-   Try This
-   outfit details
-   wardrobe recommendations
-   compare outfits

Generate a clean fashion-card image.

Do not make it a permanent dashboard card.

------------------------------------------------------------------------

# 26. Visual Design System

### Background

`#FBF9F5`

Secondary:

`#F7F4EE`

### Primary text

`#1A1715`

### Secondary text

`#78716C`

### Olive

`#7D8B72`

### Terracotta

`#C06C54`

### Warm beige

`#F2EFE9`

### Border / neutral

`#E5E0D6`

### White

`#FFFFFF`

------------------------------------------------------------------------

# 27. Typography

Display:

**Bodoni Moda**

Use for:

-   hero headlines
-   editorial headings
-   major outfit titles

UI:

**Inter**

Use for:

-   buttons
-   labels
-   navigation
-   descriptions
-   metadata
-   scores

Do not use monospace/developer-style typography in the product UI.

------------------------------------------------------------------------

# 28. Visual Principles

WARDROBE should look:

-   premium
-   editorial
-   warm
-   human
-   minimal
-   sophisticated
-   modern
-   fashion-focused
-   technologically intelligent without looking technical

Avoid:

-   neon colors
-   purple AI gradients
-   excessive glassmorphism
-   excessive rounded cards
-   generic SaaS dashboard appearance
-   generic chatbot appearance
-   excessive icons
-   excessive text
-   clutter

------------------------------------------------------------------------

# 29. Figma → Android Workflow

Figma is the **visual source of truth**.

Use:

``` text
Figma
 ↓
Inspect layout and assets
 ↓
Extract design tokens
 ↓
Create Compose design system
 ↓
Create reusable components
 ↓
Implement screen
 ↓
Run Android emulator
 ↓
Compare with Figma
 ↓
Refine
```

Do not use Figma screenshots as the actual UI background.

Every UI element must be a real Compose component.

------------------------------------------------------------------------

# 30. Compose Design System

Create reusable components:

``` text
WardrobeText
WardrobeDisplayText
WardrobeSectionLabel
WardrobePrimaryButton
WardrobeSecondaryButton
WardrobeCard
WardrobeImageCard
WardrobeChip
WardrobeBottomNavigation
WardrobeTopBar
WardrobeColorSwatch
WardrobeMatchScore
WardrobeOutfitCard
WardrobeClothingCard
WardrobeAnalysisCard
WardrobeEmptyState
WardrobeBeforeAfterSlider
```

Centralize:

-   colors
-   typography
-   spacing
-   dimensions
-   shapes
-   component styling

Do not duplicate visual constants across screens.

------------------------------------------------------------------------

# 31. Recommended Android Structure

``` text
app/
└── src/main/java/com/wardrobe/app/
    ├── core/
    │   ├── design/
    │   │   ├── Color.kt
    │   │   ├── Typography.kt
    │   │   ├── Theme.kt
    │   │   ├── Dimensions.kt
    │   │   └── Components.kt
    │   ├── navigation/
    │   ├── database/
    │   ├── storage/
    │   ├── camera/
    │   └── ml/
    ├── feature/
    │   ├── onboarding/
    │   ├── auth/
    │   ├── faceanalysis/
    │   ├── facecapture/
    │   ├── silhouette/
    │   ├── bodycapture/
    │   ├── dashboard/
    │   ├── wardrobe/
    │   ├── trythis/
    │   ├── stylist/
    │   ├── outfit/
    │   └── profile/
    ├── data/
    │   ├── local/
    │   ├── model/
    │   └── repository/
    └── domain/
        ├── model/
        ├── repository/
        └── usecase/
```

------------------------------------------------------------------------

# 32. Architecture

Use:

**MVVM + Clean Architecture**

``` text
Compose UI
   ↓
ViewModel
   ↓
Use Cases
   ↓
Repository
   ↓
Local / Remote Data Sources
```

ML pipeline:

``` text
Camera / Image
      ↓
Preprocessing
      ↓
ML Model
      ↓
Structured Result
      ↓
Repository
      ↓
Recommendation Engine
```

------------------------------------------------------------------------

# 33. Room Database

Core entities:

``` text
UserProfile
FaceAnalysis
SilhouetteAnalysis
StylePreference
WardrobeItem
Outfit
OutfitFeedback
ColorPalette
Recommendation
```

WardrobeItem should contain fields such as:

``` text
id
imageUri
category
subcategory
primaryColor
secondaryColors
pattern
texture
fit
style
season
formality
occasionTags
createdAt
```

------------------------------------------------------------------------

# 34. Navigation

Initial setup:

``` text
Opening
 ↓
Onboarding
 ↓
Authentication
 ↓
Face Analysis
 ↓
Face Capture
 ↓
Silhouette Analysis
 ↓
Full Body Capture
 ↓
Style Preferences
 ↓
Dashboard
```

After setup:

``` text
Dashboard
 ├── Wardrobe
 ├── Try This
 ├── Stylist
 ├── Outfit Details
 ├── Color Palette
 └── Analysis Profile
```

------------------------------------------------------------------------

# 35. Interactive Before/After Slider

The "See the Difference" screen must have a real draggable comparison
slider.

Behavior:

``` text
Touch divider
 ↓
Drag horizontally
 ↓
Divider follows finger
 ↓
Image clipping changes continuously
 ↓
User sees more/less of either styling state
```

Implement natively in Jetpack Compose.

Do not make it a static image.

------------------------------------------------------------------------

# 36. Loading States

Use subtle editorial loading states.

Example:

**Analyzing your features...**

Avoid large generic AI graphics.

Use restrained progress indicators and elegant transitions.

------------------------------------------------------------------------

# 37. Error Handling

Handle:

-   no face detected
-   multiple faces detected
-   poor lighting
-   blurry image
-   body not fully visible
-   unsupported image
-   camera permission denied
-   storage issues
-   ML inference failure
-   empty wardrobe
-   no suitable outfit

Messages should be human and actionable.

Example:

Instead of:

`FACE_DETECTION_FAILED`

Use:

**We couldn't clearly see your face. Try a brighter photo with your face
fully visible.**

------------------------------------------------------------------------

# 38. Accessibility

Support:

-   content descriptions
-   font scaling where practical
-   sufficient contrast
-   touch targets around 48dp or larger
-   screen reader labels
-   reduced-motion consideration

Maintain the premium visual hierarchy.

------------------------------------------------------------------------

# 39. Performance

Target mid-range Android devices.

Prioritize:

-   lightweight models
-   quantization
-   lazy lists
-   image caching
-   efficient bitmap handling
-   background inference
-   coroutines
-   avoiding unnecessary recompositions

Never run heavy ML inference on the main thread.

------------------------------------------------------------------------

# 40. Offline-First

Core features should work offline where possible.

Offline:

-   user profile
-   wardrobe
-   image storage
-   basic color analysis
-   basic clothing analysis
-   rule-based recommendations
-   saved outfits

Online features may include:

-   advanced LLM stylist
-   cloud inference
-   synchronization
-   advanced virtual try-on

------------------------------------------------------------------------

# 41. Product Personality

The app should communicate:

**"I understand your style."**

Not:

**"Here is a list of fashion features."**

Every screen should reinforce personalization.

------------------------------------------------------------------------

# 42. MVP

Required MVP:

-   Opening screen
-   Onboarding
-   Authentication
-   Face capture
-   Full body capture
-   Basic profile
-   Style preferences
-   Dashboard
-   Wardrobe
-   Clothing scan/upload
-   Clothing categorization
-   Basic personalized recommendations
-   Outfit generation
-   Weather-aware recommendations
-   Try This compatibility
-   Basic stylist chat

Not required initially:

-   full generative virtual try-on
-   complex cloud infrastructure
-   marketplace
-   payments
-   social network
-   real-time AR clothing overlay

------------------------------------------------------------------------

# 43. Development Milestones

## Milestone 1 --- UI Foundation

Build:

1.  Design system
2.  Opening screen
3.  Onboarding
4.  Authentication
5.  Navigation
6.  Dashboard
7.  Wardrobe
8.  Try This
9.  Stylist

Use mock data.

The entire app must be navigable.

## Milestone 2 --- Local Computer Vision

Add:

-   Camera
-   Gallery
-   Image storage
-   Face detection
-   Body detection
-   Clothing detection
-   Color extraction
-   Wardrobe database

## Milestone 3 --- Recommendation Engine

Start with deterministic rules.

Example:

``` text
IF undertone == warm
AND garmentColor in warmPalette
THEN increaseColorScore

IF silhouette == balanced
AND fit == relaxed
THEN increaseFitScore

IF occasion == formal
AND garmentFormality >= medium
THEN increaseOccasionScore
```

Then introduce ML ranking.

## Milestone 4 --- Context and Intelligence

Add:

-   weather
-   personalized recommendations
-   outfit comparison
-   feedback learning
-   stylist LLM
-   shopping compatibility

## Milestone 5 --- Advanced AI

Investigate:

-   virtual try-on
-   generative outfit visualization
-   advanced personalization

------------------------------------------------------------------------

# 44. Antigravity Instructions

Antigravity is the implementation agent.

Before coding:

1.  Inspect the existing Android project.
2.  Inspect all provided Figma-derived designs and assets.
3.  Understand the design system.
4.  Identify reusable components.
5.  Plan architecture.
6.  Implement incrementally.

Do not:

-   replace the architecture unnecessarily
-   invent new colors
-   invent new typography
-   invent UI not present in the design
-   add unrelated features
-   add unnecessary libraries
-   convert the project to a web application
-   use HTML/CSS instead of native Android UI
-   use screenshots as UI
-   put the entire application in one Compose file

Use:

**Kotlin + Jetpack Compose**

as the primary implementation technology.

------------------------------------------------------------------------

# 45. Visual Fidelity Rules

For every Figma screen:

-   match hierarchy
-   match typography
-   match font sizes
-   match letter spacing
-   match image crop
-   match padding
-   match margins
-   match spacing
-   match corner radius
-   match button dimensions
-   match icon sizing
-   match alignment
-   match background
-   match visual density

Primary target:

**390 × 844**

Use the Android emulator for visual comparison.

When differences are found, fix the implementation rather than
arbitrarily changing the design.

------------------------------------------------------------------------

# 46. Important Product Rules

1.  Do not force users to populate the entire wardrobe before receiving
    value.
2.  Give users useful personalized recommendations as early as possible.
3.  Wardrobe should be populated progressively.
4.  Recommendations must prefer actual wardrobe items.
5.  The system should explain recommendations.
6.  Weather is contextual, not the main product.
7.  The dashboard is a personalized fashion feed, not a feature menu.
8.  "Share Look" is contextual.
9.  Virtual try-on is a later feature and should not block the MVP.
10. The LLM is a communication/intent layer, not the core recommendation
    engine.

------------------------------------------------------------------------

# 47. Product Differentiation

Do not claim AI fashion recommendations are completely unique.

The stronger positioning is:

> **WARDROBE understands the person, understands their wardrobe, and
> explains the relationship between the two.**

Important differentiators:

1.  Personalized color harmony
2.  Silhouette-aware styling
3.  Recommendations from the actual wardrobe
4.  Explainable outfit decisions
5.  Context-aware styling
6.  Shopping compatibility
7.  Privacy-first/on-device processing
8.  Visual demonstration of styling differences

------------------------------------------------------------------------

# 48. Future Expansion

Potential future features:

-   smart mirror mode
-   AR try-on
-   shopping integrations
-   brand compatibility
-   personalized shopping feed
-   capsule wardrobe generation
-   travel packing assistant
-   seasonal wardrobe rotation
-   clothing usage analytics
-   cost-per-wear analysis
-   sustainability insights
-   outfit calendar
-   laundry-aware wardrobe availability
-   event calendar integration

------------------------------------------------------------------------

# 49. Final Implementation Priority

``` text
Design System
 ↓
Opening Screen
 ↓
Onboarding
 ↓
Navigation
 ↓
Authentication
 ↓
Face Capture
 ↓
Body Capture
 ↓
Style Profile
 ↓
Dashboard
 ↓
Wardrobe
 ↓
Garment Analysis
 ↓
Color + Silhouette Analysis
 ↓
Recommendation Engine
 ↓
Outfit Generation
 ↓
Weather
 ↓
Try This
 ↓
Stylist
 ↓
Compare / Share
 ↓
Virtual Try-On
```

Priority order:

**Visual fidelity → clean architecture → working navigation → local
functionality → ML intelligence → advanced AI**

------------------------------------------------------------------------

# 50. Immediate Task

Do not implement every feature immediately.

First:

1.  Inspect the Android project.
2.  Establish the WARDROBE Compose design system.
3.  Establish typography and colors.
4.  Add the required local assets.
5.  Create reusable components.
6.  Implement the Opening Screen from Figma.
7.  Implement onboarding screens from Figma.
8.  Implement navigation.
9.  Run on the Android emulator.
10. Compare each screen with Figma.
11. Fix visual discrepancies.
12. Then implement functional capture flows.

Build incrementally and keep the project compiling after each major
step.

The goal is a production-quality native Android application where the
supplied Figma designs become the visual foundation and the product
behavior described in this specification becomes the functional
foundation.

**Final product concept:**

``` text
PERSON
 ↓
UNDERSTAND ME
 ↓
COLOR + SILHOUETTE + STYLE
 ↓
UNDERSTAND MY CLOSET
 ↓
MY CLOTHING
 ↓
UNDERSTAND CONTEXT
 ↓
WEATHER + OCCASION
 ↓
GENERATE OUTFITS
 ↓
EXPLAIN WHY THEY WORK
 ↓
HELP ME DECIDE WHAT TO WEAR
 ↓
HELP ME DECIDE WHAT TO BUY
```

WARDROBE should feel like a **personal fashion intelligence system**,
not an AI-generated clothing catalog.
