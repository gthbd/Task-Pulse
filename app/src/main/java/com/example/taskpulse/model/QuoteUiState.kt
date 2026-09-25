package com.example.taskpulse.model

sealed class QuoteUiState {
    object Loading: QuoteUiState()      //trạng thái không mang theo dữ liệu, sử dụng object
    data class Success(val quote: Quote ): QuoteUiState()   //trạng thái thành công bắt buộc phải mang 1 quote nên sử dụng data class
    data class Error(val message: String): QuoteUiState()   //trạng thái lỗi bắt buộc phải mang theo ít nhất 1 message mô tả lỗi dạng string nên cũng sử dụng data class
}