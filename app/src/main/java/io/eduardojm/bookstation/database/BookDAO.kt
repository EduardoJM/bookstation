package io.eduardojm.bookstation.database

import android.content.ContentValues
import android.content.Context
import io.eduardojm.bookstation.model.Book

class BookDAO(context: Context) {
    private val writable = DatabaseHelper(context).writableDatabase;
    private val readable = DatabaseHelper(context).readableDatabase;

    fun createBook(book: Book) {
        val values = ContentValues()
        values.put("title", book.title)
        values.put("description", book.description)
        values.put("clicks", book.clicks)
        values.put("rating", book.rating)
        values.put("cover", book.cover)
        values.put("author_id", book.authorId)

        writable.insert("books", null, values)
    }
}