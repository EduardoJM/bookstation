package io.eduardojm.bookstation.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import java.sql.SQLException

class DatabaseHelper(context: Context): SQLiteOpenHelper(
    context,
    "books",
    null,
    1
) {
    override fun onCreate(db: SQLiteDatabase?) {
        val createAuthorSql = "CREATE TABLE IF NOT EXISTS authors (" +
                "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "title VARCHAR(150) NOT NULL," +
                "photo TEXT" +
                ");"
        val createBookSql = "CREATE TABLE IF NOT EXISTS books (" +
                "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "title VARCHAR(150) NOT NULL," +
                "description TEXT," +
                "clicks INTEGER not null default 0," +
                "rating REAL," +
                "cover TEXT," +
                "author_id INTEGER NOT NULL," +
                "FOREIGN KEY(author_id) REFERENCES author(id)" +
                ");"

        try {
            db?.execSQL(createAuthorSql);
            db?.execSQL(createBookSql);
            Log.i("database", "Database is successfully created.")
        } catch (e: SQLException) {
            e.printStackTrace();
            Log.i("database", "Error in database creation.")
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

}
