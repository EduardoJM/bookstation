package io.eduardojm.bookstation.database

import android.content.ContentValues
import android.content.Context

class AuthorDAO(context: Context) {
    private val writable = DatabaseHelper(context).writableDatabase;
    private val readable = DatabaseHelper(context).readableDatabase;

    fun getAuthorIdByName(title: String): Int? {
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

    fun createAuthor(title: String): Int? {
        val id = getAuthorIdByName(title)
        if (id != null) {
            return id
        }

        val values = ContentValues()
        values.put("title", title);
        writable.insert("authors", null, values)

        return getAuthorIdByName(title)
    }
}