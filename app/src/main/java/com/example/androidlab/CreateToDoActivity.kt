package com.example.androidlab

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class CreateToDoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_to_do)
        var buttonTitleConfirm = true
        var buttonContentConfirm = true
        val saveButton = this.findViewById<Button>(R.id.button_Save)
        val newTitle = this.findViewById<EditText>(R.id.edit_Text_Title)
        val newContent = this.findViewById<EditText>(R.id.edit_Text_Content)
        val textViewTitleVal = this.findViewById<TextView>(R.id.textView_Create_Val_Title)
        val textViewContentVal = this.findViewById<TextView>(R.id.textView_Create_Val_Content)


        newTitle.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                when {
                    s!!.length < 3 -> {
                        textViewTitleVal.text = getString(R.string.validate_Title_Min)
                        buttonTitleConfirm = false
                    }
                    s.length >= 15 -> {
                        textViewTitleVal.text = getString(R.string.validate_Title_Max)
                        buttonTitleConfirm = false
                    }
                    else -> {
                        textViewTitleVal.text = ""
                        buttonTitleConfirm = true
                    }
                }
            }
        })

        newContent.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                when{
                    s!!.length < 6 ->{
                        textViewContentVal.text = getString(R.string.validate_Content_Min)
                        buttonContentConfirm = false
                    }
                    s.length >= 51 -> {
                        textViewContentVal.text = getString(R.string.validate_Content_max)
                        buttonContentConfirm = false
                    }
                    else -> {
                        textViewContentVal.text = ""
                        buttonContentConfirm = true
                    }
                }
            }

        })

        saveButton.setOnClickListener{

            if(buttonTitleConfirm && buttonContentConfirm) {
                
                toDoRepository.addToDo(
                    newTitle.editableText.toString(),
                    newContent.editableText.toString()
                )
                val allToDos = toDoRepository.getAllToDos()
                val lastId = allToDos.last().id

                val intent = Intent(this, ViewToDoActivity::class.java)
                intent.putExtra("listId", lastId)
                startActivity(intent)
                finish()
            }
        }
    }
}
