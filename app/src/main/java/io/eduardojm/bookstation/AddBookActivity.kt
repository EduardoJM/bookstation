package io.eduardojm.bookstation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import io.eduardojm.bookstation.database.AuthorDAO
import io.eduardojm.bookstation.database.BookDAO
import io.eduardojm.bookstation.database.DatabaseHelper
import io.eduardojm.bookstation.databinding.ActivityAddBookBinding
import io.eduardojm.bookstation.model.Author
import io.eduardojm.bookstation.model.Book
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

        try {
            val id = AuthorDAO(this).createAuthor(Author(null, authorName, null))
            val book = Book(null, bookName, null, 0, 0.0f, null, id)
            BookDAO(this).createBook(book)

            Toast.makeText(this, "Salvo com sucesso!", Toast.LENGTH_SHORT).show()
            finish()
        } catch (e: SQLException) {
            Log.i("database", "Error inserting author");
        }
    }
}