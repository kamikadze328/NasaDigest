package com.kamikadze328.nasadigest.ui.icons

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Suppress("CheckReturnValue")
val SkullFilled: ImageVector
    get() {
        if (_SkullFilled != null) {
            return _SkullFilled!!
        }
        _SkullFilled =
            ImageVector.Builder(
                name = "skull",
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
                        moveTo(10.5f, 15.75f)
                        horizontalLineToRelative(3f)
                        lineToRelative(-1.5f, -3f)
                        lineToRelative(-1.5f, 3f)
                        close()
                        moveTo(8.5f, 13f)
                        quadToRelative(0.83f, 0f, 1.41f, -0.59f)
                        reflectiveQuadTo(10.5f, 11f)
                        quadToRelative(0f, -0.83f, -0.59f, -1.41f)
                        reflectiveQuadTo(8.5f, 9f)
                        quadTo(7.68f, 9f, 7.09f, 9.59f)
                        reflectiveQuadTo(6.5f, 11f)
                        reflectiveQuadToRelative(0.59f, 1.41f)
                        reflectiveQuadTo(8.5f, 13f)
                        close()
                        moveToRelative(7f, 0f)
                        quadToRelative(0.82f, 0f, 1.41f, -0.59f)
                        reflectiveQuadTo(17.5f, 11f)
                        quadToRelative(0f, -0.83f, -0.59f, -1.41f)
                        reflectiveQuadTo(15.5f, 9f)
                        reflectiveQuadTo(14.09f, 9.59f)
                        reflectiveQuadTo(13.5f, 11f)
                        reflectiveQuadToRelative(0.59f, 1.41f)
                        reflectiveQuadTo(15.5f, 13f)
                        close()
                        moveTo(6f, 22f)
                        verticalLineTo(17.75f)
                        quadTo(5.03f, 17.33f, 4.29f, 16.61f)
                        reflectiveQuadTo(3.04f, 15f)
                        reflectiveQuadTo(2.26f, 13.08f)
                        reflectiveQuadTo(2f, 11f)
                        quadTo(2f, 7.05f, 4.8f, 4.52f)
                        reflectiveQuadTo(12f, 2f)
                        reflectiveQuadToRelative(7.2f, 2.52f)
                        reflectiveQuadTo(22f, 11f)
                        quadToRelative(0f, 1.05f, -0.26f, 2.07f)
                        reflectiveQuadTo(20.96f, 15f)
                        reflectiveQuadToRelative(-1.25f, 1.61f)
                        reflectiveQuadTo(18f, 17.75f)
                        verticalLineTo(22f)
                        horizontalLineTo(15f)
                        verticalLineTo(20f)
                        horizontalLineTo(13f)
                        verticalLineToRelative(2f)
                        horizontalLineTo(11f)
                        verticalLineTo(20f)
                        horizontalLineTo(9f)
                        verticalLineToRelative(2f)
                        horizontalLineTo(6f)
                        close()
                    }
                }
                .build()
        return _SkullFilled!!
    }

private var _SkullFilled: ImageVector? = null

@Preview
@Composable
private fun PreviewSkull() {
    Box {
        Icon(
            imageVector = SkullFilled,
            contentDescription = null,
            tint = Color.Black,
        )
    }
}