package com.example.androidlab

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class CreateToDoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_to_do)

        val saveButton = this.findViewById<Button>(R.id.button_Save)

        saveButton.setOnClickListener{

            val newTitle = this.findViewById<EditText>(R.id.edit_Text_Title).editableText.toString()
            val newContent = this.findViewById<EditText>(R.id.edit_Text_Content).editableText.toString()

            toDoRepository.addToDo(newTitle,newContent)
            val allToDos = toDoRepository.getAllToDos()
            val lastId = allToDos.last().id
            val intent = Intent(this, ViewToDoActivity::class.java)
            intent.putExtra("listId", lastId)
            startActivity(intent)
            finish()
        }
    }
}
