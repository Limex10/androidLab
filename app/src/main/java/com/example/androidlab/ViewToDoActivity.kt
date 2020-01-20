package com.example.androidlab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ViewToDoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_to_do)

        val textViewTitle = this.findViewById<TextView>(R.id.text_View_title)
        val textViewContent = this.findViewById<TextView>(R.id.text_View_content)
        val id = intent.getIntExtra("listId", 0)
        val toDoItem = toDoRepository.getToDoById(id)
        textViewTitle.text = toDoItem?.title
        textViewContent.text = toDoItem?.content

        val buttonDelete = this.findViewById<Button>(R.id.button_delete)

        buttonDelete.setOnClickListener{
            toDoRepository.deleteToDoById(id)
        }

    }

}
