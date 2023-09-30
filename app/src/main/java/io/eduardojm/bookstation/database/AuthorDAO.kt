package io.eduardojm.bookstation.database

import android.content.ContentValues
import android.content.Context
import io.eduardojm.bookstation.model.Author
import java.lang.Exception
import java.sql.SQLException

class AuthorDAO(context: Context) {
    private val writable = DatabaseHelper(context).writableDatabase;
    private val readable = DatabaseHelper(context).readableDatabase;

    private fun getAuthorIdByName(title: String): Int? {
        val sql = "SELECT id FROM authors WHERE title=?"
        val args = arrayOf(title)

        val cursor = readable.rawQuery(sql, args)
        if (!cursor.moveToFirst()) {
            return null
        }

        val idIndex = cursor.getColumnIndex("id")
        val id = cursor.getInt(idIndex)

        cursor.close()

        return id
    }

    fun createAuthor(author: Author): Int {
        val id = getAuthorIdByName(author.title)
        if (id != null) {
            return id
        }

        val values = ContentValues()
        values.put("title", author.title);
        values.put("photo", author.photo);
        writable.insert("authors", null, values)

        return getAuthorIdByName(author.title) ?: throw SQLException()
    }
}