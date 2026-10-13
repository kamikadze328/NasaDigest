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
val PlanetOutlined: ImageVector
    get() {
        if (_planet != null) {
            return _planet!!
        }
        _planet =
            ImageVector.Builder(
                name = "planet",
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
                        moveTo(12.11f, 10.16f)
                        quadTo(12.48f, 9.8f, 12.48f, 9.27f)
                        reflectiveQuadTo(12.11f, 8.39f)
                        reflectiveQuadTo(11.23f, 8.02f)
                        reflectiveQuadTo(10.34f, 8.39f)
                        reflectiveQuadTo(9.98f, 9.27f)
                        reflectiveQuadToRelative(0.36f, 0.89f)
                        quadToRelative(0.36f, 0.36f, 0.89f, 0.36f)
                        reflectiveQuadToRelative(0.89f, -0.36f)
                        close()
                        moveTo(20.55f, 22f)
                        quadTo(19.5f, 22f, 17.73f, 21.13f)
                        reflectiveQuadToRelative(-3.8f, -2.38f)
                        quadToRelative(-0.47f, 0.13f, -0.96f, 0.19f)
                        reflectiveQuadTo(11.98f, 19f)
                        quadTo(9.05f, 19f, 7.03f, 16.98f)
                        reflectiveQuadTo(5f, 12.02f)
                        quadToRelative(0f, -0.5f, 0.08f, -1f)
                        reflectiveQuadToRelative(0.2f, -0.97f)
                        quadTo(3.8f, 8.02f, 2.91f, 6.26f)
                        reflectiveQuadTo(2.03f, 3.45f)
                        quadTo(2.03f, 2.77f, 2.4f, 2.39f)
                        reflectiveQuadTo(3.43f, 2f)
                        reflectiveQuadTo(5.11f, 2.45f)
                        reflectiveQuadTo(7.98f, 3.97f)
                        quadTo(7.45f, 4.25f, 7f, 4.55f)
                        reflectiveQuadTo(6.13f, 5.2f)
                        quadTo(5.65f, 4.93f, 5.2f, 4.72f)
                        reflectiveQuadTo(4.25f, 4.3f)
                        quadTo(4.7f, 5.25f, 5.21f, 6.15f)
                        reflectiveQuadTo(6.3f, 7.93f)
                        quadTo(7.25f, 6.57f, 8.73f, 5.8f)
                        quadTo(10.2f, 5.02f, 11.98f, 5.02f)
                        quadToRelative(2.92f, 0f, 4.96f, 2.04f)
                        reflectiveQuadToRelative(2.04f, 4.96f)
                        quadToRelative(0f, 1.78f, -0.79f, 3.25f)
                        reflectiveQuadTo(16.05f, 17.7f)
                        quadToRelative(0.88f, 0.57f, 1.79f, 1.1f)
                        reflectiveQuadToRelative(1.86f, 0.95f)
                        quadTo(19.5f, 19.27f, 19.29f, 18.83f)
                        reflectiveQuadTo(18.8f, 17.9f)
                        quadTo(19.18f, 17.48f, 19.48f, 17f)
                        reflectiveQuadToRelative(0.55f, -0.98f)
                        quadToRelative(1.15f, 1.95f, 1.56f, 2.91f)
                        reflectiveQuadTo(22f, 20.55f)
                        quadToRelative(0f, 0.72f, -0.4f, 1.09f)
                        reflectiveQuadTo(20.55f, 22f)
                        close()
                        moveTo(14.44f, 14.74f)
                        quadToRelative(0.29f, -0.29f, 0.29f, -0.71f)
                        reflectiveQuadTo(14.44f, 13.31f)
                        quadTo(14.15f, 13.02f, 13.73f, 13.02f)
                        reflectiveQuadToRelative(-0.71f, 0.29f)
                        reflectiveQuadToRelative(-0.29f, 0.71f)
                        reflectiveQuadToRelative(0.29f, 0.71f)
                        quadToRelative(0.29f, 0.29f, 0.71f, 0.29f)
                        reflectiveQuadToRelative(0.71f, -0.29f)
                        close()
                        moveToRelative(1.08f, -3.42f)
                        quadToRelative(0.21f, -0.21f, 0.21f, -0.54f)
                        reflectiveQuadTo(15.51f, 10.24f)
                        reflectiveQuadTo(14.98f, 10.02f)
                        reflectiveQuadToRelative(-0.54f, 0.21f)
                        reflectiveQuadToRelative(-0.21f, 0.54f)
                        reflectiveQuadToRelative(0.21f, 0.54f)
                        quadToRelative(0.21f, 0.21f, 0.54f, 0.21f)
                        reflectiveQuadToRelative(0.54f, -0.21f)
                        close()
                        moveTo(11.7f, 16.98f)
                        quadTo(10.43f, 15.88f, 9.25f, 14.7f)
                        quadTo(8.08f, 13.52f, 7f, 12.25f)
                        quadToRelative(0.05f, 0.95f, 0.43f, 1.79f)
                        reflectiveQuadToRelative(1.03f, 1.49f)
                        reflectiveQuadToRelative(1.47f, 1.03f)
                        reflectiveQuadToRelative(1.78f, 0.43f)
                        close()
                        moveToRelative(2.57f, -0.53f)
                        quadToRelative(1.2f, -0.63f, 1.95f, -1.81f)
                        reflectiveQuadTo(16.98f, 12f)
                        quadToRelative(0f, -2.08f, -1.46f, -3.53f)
                        quadTo(14.05f, 7.02f, 11.98f, 7.02f)
                        quadToRelative(-1.45f, 0f, -2.63f, 0.75f)
                        quadTo(8.18f, 8.52f, 7.55f, 9.73f)
                        quadToRelative(1.42f, 1.9f, 3.13f, 3.6f)
                        quadToRelative(1.7f, 1.7f, 3.6f, 3.13f)
                        close()
                        moveTo(9.35f, 14.63f)
                        close()
                        moveToRelative(2.93f, -2.9f)
                        close()
                    }
                }
                .build()
        return _planet!!
    }

private var _planet: ImageVector? = null

@Preview
@Composable
private fun PreviewSkull() {
    Box {
        Icon(
            imageVector = PlanetOutlined,
            contentDescription = null,
            tint = Color.Black,
        )
    }
}