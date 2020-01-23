package com.example.androidlab

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class UpdateToDoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_to_do)
        var buttonTitleConfirm = true
        var buttonContentConfirm = true
        val buttonUpdate = this.findViewById<Button>(R.id.button_Update)
        val textViewTitleUpdate = this.findViewById<EditText>(R.id.edit_Text_Title_Update)
        val textViewContentUpdate = this.findViewById<EditText>(R.id.edit_Text_Content_Update)
        val textViewTitleVal = this.findViewById<TextView>(R.id.text_View_Title_Val)
        val textViewContentVal = this.findViewById<TextView>(R.id.text_View_Content_Val)


        val id = intent.getIntExtra("updateId", 0)
        val todo = toDoRepository.getToDoById(id)
        textViewContentUpdate.setText(todo?.content)
        textViewTitleUpdate.setText(todo?.title)

        textViewTitleUpdate.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

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
        textViewContentUpdate.addTextChangedListener(object: TextWatcher{
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
        buttonUpdate.setOnClickListener{
            if(buttonTitleConfirm && buttonContentConfirm){
                val newTitle = this.findViewById<EditText>(R.id.edit_Text_Title_Update).editableText.toString()
                val newContent = this.findViewById<EditText>(R.id.edit_Text_Content_Update).editableText.toString()

                toDoRepository.updateToDoById(id,newTitle,newContent)

                finish()
            }
        }


    }
}
