package io.eduardojm.bookstation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import io.eduardojm.bookstation.database.AuthorDAO
import io.eduardojm.bookstation.database.DatabaseHelper
import io.eduardojm.bookstation.databinding.ActivityAddBookBinding
import java.sql.SQLException

class AddBookActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityAddBookBinding.inflate(layoutInflater)
    }

    private val db by lazy {
        DatabaseHelper(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding) {
            btnSave.setOnClickListener{
                saveBook()
            }
            btnCancel.setOnClickListener{
                finish()
            }
        }
    }

    private fun saveBook () {
        val authorName = binding.inputAuthor.text.toString()
        val bookName = binding.inputTitle.text.toString()
        // val sql = "INSERT OR IGNORE INTO author(title) VALUES ('$authorName')"

        try {
            val id = AuthorDAO(this).createAuthor(authorName)
            Log.i("testeeee", "Criou o autor com o id '${id}'")

            /*
            db.writableDatabase.execSQL(
                "INSERT INTO author (title) VALUES ('Main Test');"
            )
            db.readableDatabase.rawQuery("SELECT id FROM author WHERE title=''", null)
            Log.i("database", "Success inserting author");
             */
        } catch (e: SQLException) {
            Log.i("database", "Error inserting author");
        }
    }
}