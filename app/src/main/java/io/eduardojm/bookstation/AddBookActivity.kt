package io.eduardojm.bookstation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class AddBookActivity : AppCompatActivity() {
    lateinit var btnCancel: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_book)

        btnCancel = findViewById(R.id.btn_cancel)
        btnCancel.setOnClickListener {
            finish();
        }
    }
}