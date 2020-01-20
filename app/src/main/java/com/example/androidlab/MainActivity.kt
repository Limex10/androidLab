package com.example.androidlab

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView = this.findViewById<ListView>(R.id.list_view)
        listView.adapter = ArrayAdapter<ToDo>(
            this,
            android.R.layout.simple_list_item_1,
            android.R.id.text1,
            toDoRepository.getAllToDos()
        )

        listView.setOnItemClickListener { _, _, position, id ->
            val toDoItem = listView.adapter.getItem(position) as ToDo
            val intent = Intent(this, ViewToDoActivity::class.java)
            intent.putExtra("listId", toDoItem.id)
            startActivity(intent)
        }

        val createButton = this.findViewById<Button>(R.id.create_Button)

        createButton.setOnClickListener{
            val intent = Intent(this, CreateToDoActivity::class.java)

            startActivity(intent)
        }



    }



}
