package com.example.myweather.presentation.ui.home.components

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val LogoutIcon: ImageVector
    get() {
        if (_logoutIcon != null) {
            return _logoutIcon!!
        }
        _logoutIcon = ImageVector.Builder(
            name = "LogOut",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                fill = null,
                stroke = SolidColor(Color(0xFF000000)),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(9f, 21f)
                horizontalLineTo(5f)
                arcToRelative(2f, 2f, 0.0F, false, false, -2f, -2f)
                verticalLineTo(5f)
                arcToRelative(2f, 2f, 0.0F, false, false, 2f, -2f)
                horizontalLineToRelative(4f)
            }
            path(
                fill = null,
                stroke = SolidColor(Color(0xFF000000)),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(16f, 17f)
                lineTo(21f, 12f)
                lineTo(16f, 7f)
            }
            path(
                fill = null,
                stroke = SolidColor(Color(0xFF000000)),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(21f, 12f)
                lineTo(9f, 12f)
            }
        }.build()
        return _logoutIcon!!
    }

private var _logoutIcon: ImageVector? = null
