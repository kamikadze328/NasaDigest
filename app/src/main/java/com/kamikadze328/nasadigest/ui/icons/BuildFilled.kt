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
val BuildFilled: ImageVector
    get() {
        if (_build != null) {
            return _build!!
        }
        _build =
            ImageVector.Builder(
                name = "build",
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
                        moveTo(17.15f, 20.7f)
                        lineTo(11.1f, 14.6f)
                        quadToRelative(-0.5f, 0.2f, -1.01f, 0.3f)
                        quadTo(9.58f, 15f, 9f, 15f)
                        quadTo(6.5f, 15f, 4.75f, 13.25f)
                        reflectiveQuadTo(3f, 9f)
                        quadTo(3f, 8.1f, 3.25f, 7.29f)
                        reflectiveQuadTo(3.95f, 5.75f)
                        lineTo(7.6f, 9.4f)
                        lineTo(9.4f, 7.6f)
                        lineTo(5.75f, 3.95f)
                        quadTo(6.48f, 3.5f, 7.29f, 3.25f)
                        reflectiveQuadTo(9f, 3f)
                        quadToRelative(2.5f, 0f, 4.25f, 1.75f)
                        reflectiveQuadTo(15f, 9f)
                        quadToRelative(0f, 0.57f, -0.1f, 1.09f)
                        reflectiveQuadTo(14.6f, 11.1f)
                        lineToRelative(6.1f, 6.05f)
                        quadToRelative(0.3f, 0.3f, 0.3f, 0.73f)
                        reflectiveQuadTo(20.7f, 18.6f)
                        lineToRelative(-2.1f, 2.1f)
                        quadTo(18.3f, 21f, 17.88f, 21f)
                        reflectiveQuadTo(17.15f, 20.7f)
                        close()
                    }
                }
                .build()
        return _build!!
    }

private var _build: ImageVector? = null


@Preview
@Composable
private fun Preview() {
    Box {
        Icon(
            imageVector = BuildFilled,
            contentDescription = null,
            tint = Color.Black,
        )
    }
}