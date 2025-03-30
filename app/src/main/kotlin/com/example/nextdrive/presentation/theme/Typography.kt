//@file: Suppress("Filename")
//
//package my.zukoap.tasky.core.uikit.theme
//
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.Immutable
//import androidx.compose.runtime.staticCompositionLocalOf
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.text.ExperimentalTextApi
//import androidx.compose.ui.text.PlatformTextStyle
//import androidx.compose.ui.text.TextStyle
//import androidx.compose.ui.text.font.Font
//import androidx.compose.ui.text.font.FontFamily
//import androidx.compose.ui.text.font.FontVariation
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import my.zukoap.tasky.core.uikit.R
//
///**
// * Variable font for sdk 26+
// * Weight is applied in TextStyle, no need to set it here with FontVariation
// * (if applied in both places it stacks and leads to extremely bold letters)
// */
//@OptIn(ExperimentalTextApi::class)
//private val fontFamily = FontFamily(
//    Font(
//        R.font.inter_varaible,
//        weight = FontWeight.W400,
//        variationSettings = FontVariation.Settings(FontVariation.weight(400))
//    ),
//    Font(
//        R.font.inter_varaible,
//        weight = FontWeight.W500,
//        variationSettings = FontVariation.Settings(FontVariation.weight(500))
//    ),
//    Font(
//        R.font.inter_varaible,
//        weight = FontWeight.W600,
//        variationSettings = FontVariation.Settings(FontVariation.weight(600))
//    ),
//    Font(
//        R.font.inter_varaible,
//        weight = FontWeight.W700,
//        variationSettings = FontVariation.Settings(FontVariation.weight(700))
//    )
//)
//
//@Immutable
//data class TaskyTypography(
//    val headline1: TextStyle,
//    val headline2: TextStyle,
//    val headline3: TextStyle,
//    val headline4: TextStyle,
//    val headline5: TextStyle,
//    val title1: TextStyle,
//    val title2: TextStyle,
//    val title3: TextStyle,
//    val title4: TextStyle,
//    val title5: TextStyle,
//    val body1: TextStyle,
//    val body2: TextStyle,
//    val body3: TextStyle,
//    val body4: TextStyle,
//    val body5: TextStyle,
//    val label1: TextStyle,
//    val label2: TextStyle,
//    val label3: TextStyle,
//    val label4: TextStyle,
//) {
//    companion object {
//        val defaultTypography = TaskyTypography(
//            headline1 = TextStyle(
//                fontFamily = fontFamily,
//                fontSize = 28.sp,
//                fontWeight = FontWeight.W700,
//                lineHeight = 30.sp,
//                platformStyle = PlatformTextStyle(includeFontPadding = false)
//            ),
//            headline2 = TextStyle(
//                fontFamily = fontFamily,
//                fontSize = 26.sp,
//                fontWeight = FontWeight.W700,
//                lineHeight = 25.sp,
//                platformStyle = PlatformTextStyle(includeFontPadding = false)
//            ),
//            headline3 = TextStyle(
//                fontFamily = fontFamily,
//                fontSize = 20.sp,
//                fontWeight = FontWeight.W700,
//                lineHeight = 24.sp,
//                platformStyle = PlatformTextStyle(includeFontPadding = false)
//            ),
//            headline4 = TextStyle(
//                fontFamily = fontFamily,
//                fontSize = 20.sp,
//                fontWeight = FontWeight.W600,
//                lineHeight = 18.sp,
//                platformStyle = PlatformTextStyle(includeFontPadding = false)
//            ),
//            headline5 = TextStyle(
//                fontFamily = fontFamily,
//                fontSize = 16.sp,
//                fontWeight = FontWeight.W700,
//                lineHeight = 30.sp,
//                platformStyle = PlatformTextStyle(includeFontPadding = false)
//            ),
//            title1 = TextStyle(
//                fontFamily = fontFamily,
//                fontSize = 20.sp,
//                fontWeight = FontWeight.W700,
//                lineHeight = 16.sp,
//                platformStyle = PlatformTextStyle(includeFontPadding = false)
//            ),
//            title2 = TextStyle(
//                fontFamily = fontFamily,
//                fontSize = 18.sp,
//                fontWeight = FontWeight.W600,
//                lineHeight = 12.sp,
//                platformStyle = PlatformTextStyle(includeFontPadding = false)
//            ),
//            title3 = TextStyle(
//                fontFamily = fontFamily,
//                fontSize = 16.sp,
//                fontWeight = FontWeight.W600,
//                lineHeight = 30.sp,
//                platformStyle = PlatformTextStyle(includeFontPadding = false)
//            ),
//            title4 = TextStyle(
//                fontFamily = fontFamily,
//                fontSize = 16.sp,
//                fontWeight = FontWeight.W400,
//                lineHeight = 20.sp,
//                platformStyle = PlatformTextStyle(includeFontPadding = false)
//            ),
//            title5 = TextStyle(
//                fontFamily = fontFamily,
//                fontSize = 14.sp,
//                fontWeight = FontWeight.W500,
//                lineHeight = 30.sp,
//                letterSpacing = 0.7.sp,
//                platformStyle = PlatformTextStyle(includeFontPadding = false)
//            ),
//            body1 = TextStyle(
//                fontFamily = fontFamily,
//                fontSize = 16.sp,
//                fontWeight = FontWeight.W700,
//                lineHeight = 19.2.sp,
//                platformStyle = PlatformTextStyle(includeFontPadding = false)
//            ),
//            body2 = TextStyle(
//                fontFamily = fontFamily,
//                fontSize = 16.sp,
//                fontWeight = FontWeight.W600,
//                lineHeight = 12.sp,
//                platformStyle = PlatformTextStyle(includeFontPadding = false)
//            ),
//            body3 = TextStyle(
//                fontFamily = fontFamily,
//                fontSize = 16.sp,
//                fontWeight = FontWeight.W400,
//                lineHeight = 25.sp,
//                platformStyle = PlatformTextStyle(includeFontPadding = false),
//            ),
//            body4 = TextStyle(
//                fontFamily = fontFamily,
//                fontSize = 16.sp,
//                fontWeight = FontWeight.W400,
//                lineHeight = 15.sp,
//                platformStyle = PlatformTextStyle(includeFontPadding = false),
//            ),
//            body5 = TextStyle(
//                fontFamily = fontFamily,
//                fontSize = 14.sp,
//                fontWeight = FontWeight.W400,
//                lineHeight = 18.sp,
//                platformStyle = PlatformTextStyle(includeFontPadding = false),
//            ),
//            label1 = TextStyle(
//                fontFamily = fontFamily,
//                fontSize = 14.sp,
//                fontWeight = FontWeight.W500,
//                lineHeight = 15.sp,
//                platformStyle = PlatformTextStyle(includeFontPadding = false)
//            ),
//            label2 = TextStyle(
//                fontFamily = fontFamily,
//                fontSize = 12.sp,
//                fontWeight = FontWeight.W600,
//                lineHeight = 14.4.sp,
//                platformStyle = PlatformTextStyle(includeFontPadding = false)
//            ),
//            label3 = TextStyle(
//                fontFamily = fontFamily,
//                fontSize = 13.sp,
//                fontWeight = FontWeight.W600,
//                lineHeight = 15.6.sp,
//                platformStyle = PlatformTextStyle(includeFontPadding = false)
//            ),
//            label4 = TextStyle(
//                fontFamily = fontFamily,
//                fontSize = 11.sp,
//                fontWeight = FontWeight.W700,
//                lineHeight = 13.2.sp,
//                platformStyle = PlatformTextStyle(includeFontPadding = false)
//            )
//        )
//    }
//}
//
//val LocalTaskyTypography = staticCompositionLocalOf {
//    TaskyTypography.defaultTypography
//}
//
//@Preview(showBackground = true)
//@Composable
//private fun TaskyTypographyPreview() {
//    ProjectTheme {
//        Column(
//            modifier = Modifier.padding(all = 8.dp),
//            verticalArrangement = Arrangement.spacedBy(8.dp)
//        ) {
//            TypographyPreviewText(name = "headline1", style = ProjectTheme.typography.headline1)
//            TypographyPreviewText(name = "headline2", style = ProjectTheme.typography.headline2)
//            TypographyPreviewText(name = "headline3", style = ProjectTheme.typography.headline3)
//            TypographyPreviewText(name = "headline4", style = ProjectTheme.typography.headline4)
//            TypographyPreviewText(name = "headline5", style = ProjectTheme.typography.headline5)
//            TypographyPreviewText(name = "title1", style = ProjectTheme.typography.title1)
//            TypographyPreviewText(name = "title2", style = ProjectTheme.typography.title2)
//            TypographyPreviewText(name = "title3", style = ProjectTheme.typography.title3)
//            TypographyPreviewText(name = "title4", style = ProjectTheme.typography.title4)
//            TypographyPreviewText(name = "title5", style = ProjectTheme.typography.title5)
//            TypographyPreviewText(name = "body1", style = ProjectTheme.typography.body1)
//            TypographyPreviewText(name = "body2", style = ProjectTheme.typography.body2)
//            TypographyPreviewText(name = "body3", style = ProjectTheme.typography.body3)
//            TypographyPreviewText(name = "body4", style = ProjectTheme.typography.body4)
//            TypographyPreviewText(name = "body5", style = ProjectTheme.typography.body5)
//            TypographyPreviewText(name = "label1", style = ProjectTheme.typography.label1)
//            TypographyPreviewText(name = "label2", style = ProjectTheme.typography.label2)
//            TypographyPreviewText(name = "label3", style = ProjectTheme.typography.label3)
//            TypographyPreviewText(name = "label4", style = ProjectTheme.typography.label4)
//        }
//    }
//}
//
//@Composable
//private fun TypographyPreviewText(name: String, style: TextStyle) {
//    Text(
//        text = name,
//        style = style
//    )
//}
