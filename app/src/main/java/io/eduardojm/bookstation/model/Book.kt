package io.eduardojm.bookstation.model

data class Book(
    val id: Int?,
    val title: String,
    val description: String?,
    val clicks: Int?,
    val rating: Float?,
    val cover: String?,
    val authorId: Int,
) {
}
