package com.example.androidlab

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class UpdateToDoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_to_do)

        val buttonUpdate = this.findViewById<Button>(R.id.button_Update)

        val id = intent.getIntExtra("updateId", 0)



        buttonUpdate.setOnClickListener{
            val newTitle = this.findViewById<EditText>(R.id.edit_Text_Title_Update).editableText.toString()
            val newContent = this.findViewById<EditText>(R.id.edit_Text_Content_Update).editableText.toString()

            toDoRepository.updateToDoById(id,newTitle,newContent)
            val intent = Intent(this, ViewToDoActivity::class.java)
            intent.putExtra("listId", id)

            startActivity(intent)
            finish()
        }

    }
}
