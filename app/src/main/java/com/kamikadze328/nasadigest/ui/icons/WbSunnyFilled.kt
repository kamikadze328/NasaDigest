package com.kamikadze328.nasadigest.ui.icons

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

@Suppress("CheckReturnValue")
val WbSunnyFilled: ImageVector
    get() {
        if (_icon != null) {
            return _icon!!
        }
        _icon =
            ImageVector.Builder(
                name = "wb_sunny",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 24f,
                viewportHeight = 24f,
            )
                .apply {
                    path(
                        fill = SolidColor(Color.Black),
                        fillAlpha = 1f,
                        stroke = null,
                        strokeAlpha = 1f,
                        strokeLineWidth = 1f,
                        strokeLineCap = StrokeCap.Butt,
                        strokeLineJoin = StrokeJoin.Bevel,
                        strokeLineMiter = 1f,
                        pathFillType = PathFillType.Companion.NonZero,
                    ) {
                        moveTo(11f, 4f)
                        verticalLineTo(1f)
                        horizontalLineToRelative(2f)
                        verticalLineTo(4f)
                        horizontalLineTo(11f)
                        close()
                        moveToRelative(0f, 19f)
                        verticalLineTo(20f)
                        horizontalLineToRelative(2f)
                        verticalLineToRelative(3f)
                        horizontalLineTo(11f)
                        close()
                        moveTo(20f, 13f)
                        verticalLineTo(11f)
                        horizontalLineToRelative(3f)
                        verticalLineToRelative(2f)
                        horizontalLineTo(20f)
                        close()
                        moveTo(1f, 13f)
                        verticalLineTo(11f)
                        horizontalLineTo(4f)
                        verticalLineToRelative(2f)
                        horizontalLineTo(1f)
                        close()
                        moveTo(18.7f, 6.7f)
                        lineTo(17.3f, 5.3f)
                        lineTo(19.05f, 3.5f)
                        lineTo(20.5f, 4.95f)
                        lineTo(18.7f, 6.7f)
                        close()
                        moveTo(4.95f, 20.5f)
                        lineTo(3.5f, 19.05f)
                        lineTo(5.3f, 17.3f)
                        lineToRelative(1.4f, 1.4f)
                        lineTo(4.95f, 20.5f)
                        close()
                        moveToRelative(14.1f, 0f)
                        lineTo(17.3f, 18.7f)
                        lineToRelative(1.4f, -1.4f)
                        lineToRelative(1.8f, 1.75f)
                        lineTo(19.05f, 20.5f)
                        close()
                        moveTo(5.3f, 6.7f)
                        lineTo(3.5f, 4.95f)
                        lineTo(4.95f, 3.5f)
                        lineTo(6.7f, 5.3f)
                        lineTo(5.3f, 6.7f)
                        close()
                        moveToRelative(2.45f, 9.55f)
                        quadTo(6f, 14.5f, 6f, 12f)
                        reflectiveQuadTo(7.75f, 7.75f)
                        reflectiveQuadTo(12f, 6f)
                        reflectiveQuadToRelative(4.25f, 1.75f)
                        reflectiveQuadTo(18f, 12f)
                        reflectiveQuadToRelative(-1.75f, 4.25f)
                        reflectiveQuadTo(12f, 18f)
                        reflectiveQuadTo(7.75f, 16.25f)
                        close()
                    }
                }
                .build()
        return _icon!!
    }

private var _icon: ImageVector? = null

@Preview
@Composable
private fun PreviewWbSunny() {
    Box {
        Icon(
            imageVector = WbSunnyFilled,
            contentDescription = null,
            tint = Color.Black,
        )
    }
}