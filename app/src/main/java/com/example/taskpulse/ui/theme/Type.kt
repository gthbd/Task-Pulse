package com.taskpulse.app.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Một họ font (mặc định hệ thống), phân biệt bằng weight/size thay vì đổi font
// — tránh mix nhiều typeface không cần thiết cho một app cá nhân.
val TaskPulseTypography = Typography(
    headlineMedium = TextStyle( // dùng cho lời chào / tiêu đề màn hình
        fontWeight = FontWeight.SemiBold,
        fontSize = 26.sp,
        lineHeight = 32.sp
    ),
    titleMedium = TextStyle( // tiêu đề task trong card
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 22.sp
    ),
    bodyMedium = TextStyle( // mô tả, nội dung phụ
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp
    ),
    labelSmall = TextStyle( // category, timestamp
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 16.sp
    )
)