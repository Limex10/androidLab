package com.example.androidlab

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog

class ViewToDoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_to_do)



        val id = intent.getIntExtra("listId", 0)

        val toDoItem = toDoRepository.getToDoById(id)

        val textViewTitle = this.findViewById<TextView>(R.id.text_View_title)

        val textViewContent = this.findViewById<TextView>(R.id.text_View_content)


        textViewTitle.text = toDoItem?.title
        textViewContent.text = toDoItem?.content

        val buttonDelete = this.findViewById<Button>(R.id.button_delete)
        val buttonUpdate = this.findViewById<Button>(R.id.button_Redirect_Update)

        buttonDelete.setOnClickListener{
            AlertDialog.Builder(this)

                .setTitle(getString(R.string.setTitle_Notify_Delete))
                .setMessage(getString(R.string.setMessage_Notify_Delete))
                .setPositiveButton(
                    getString(R.string.setPositive_Notify_Delete)
                ) { dialog, whichButton ->
                    toDoRepository.deleteToDoById(id)
                    finish()
                }.setNegativeButton(
                    getString(R.string.setNegative_Notify_Delete)
                ) { dialog, whichButton ->
                    // Do not delete it.
                }.show()

        }
        buttonUpdate.setOnClickListener{
            val intent = Intent(this, UpdateToDoActivity::class.java)
            intent.putExtra("updateId", id)
            startActivity(intent)
            finish()

        }

    }

}
