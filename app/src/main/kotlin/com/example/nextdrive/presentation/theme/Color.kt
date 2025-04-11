@file: Suppress("Filename")

package com.example.nextdrive.presentation.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import java.util.Locale

@Immutable
data class ProjectColors(
    val black: Color,
//    val darkGray: Color,
//    val mediumGray: Color,
//    val gray: Color,
//    val darkGreen: Color,
//    val darkerGreen: Color,
//    val green: Color,
//    val lightGreen: Color,
//    val red: Color,
//    val lightBlue: Color,
//    val light: Color,
//    val light2: Color,
//    val white: Color,
//    val brown: Color,
//    val orange: Color,
//    val lightViolet: Color
) {
    companion object {
        val defaultColors = ProjectColors(
            black = Color(0xFF16161C),
//            darkGray = Color(0xFF5C5D5A),
//            mediumGray = Color(0xFFA1A4B2),
//            gray = Color(0xFFAAB4BE),
//            darkGreen = Color(0xFF16582B),
//            darkerGreen = Color(0xFF279F70),
//            green = Color(0xFF3BA071),
//            lightGreen = Color(0xFFCBF14C),
//            red = Color.Red,
//            lightBlue = Color(0xFFB9C6DE),
//            light = Color(0xFFEFF6FF),
//            light2 = Color(0xFFF2F3F7),
//            white = Color(0xFFFFFFFF),
//            brown = Color(0xFF40492E),
//            orange = Color(0xFFFBF0A9),
//            lightViolet = Color(0xFF9295FC)
        )
    }
}

val LocalProjectColors = staticCompositionLocalOf {
    ProjectColors.defaultColors
}

//private fun Color.toHexString(): String {
//    val alpha = (this.alpha * 255).toInt().toString(16)
//    val red = (this.red * 255).toInt().toString(16)
//    val green = (this.green * 255).toInt().toString(16)
//    val blue = (this.blue * 255).toInt().toString(16)
//    return "#$alpha$red$green$blue".uppercase(Locale.getDefault())
//}
