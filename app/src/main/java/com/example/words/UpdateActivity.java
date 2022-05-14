package com.example.words;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class UpdateActivity extends AppCompatActivity {

    EditText word_input, translation_input, notes_input;
    Button update_button, delete_button;

    String id, word, translation, notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        word_input = findViewById(R.id.word_input2);
        translation_input = findViewById(R.id.translation_input2);
        notes_input = findViewById(R.id.notes_input2);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);

        //First we call this
        getAndSetIntentData();

        //Set actionbar title after getAndSetIntentData method
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(word);
        }

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //And only then we call this
                com.example.words.DBHelper dbHelper = new com.example.words.DBHelper(UpdateActivity.this);
                word = word_input.getText().toString().trim();
                translation = translation_input.getText().toString().trim();
                notes = notes_input.getText().toString().trim();
                dbHelper.updateData(id, word, translation, notes);
            }
        });
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });
    }

    void getAndSetIntentData() {
        if (getIntent().hasExtra("id") && getIntent().hasExtra("word") &&
                getIntent().hasExtra("translation") && getIntent().hasExtra("notes")) {
            //Getting data from Intent
            id = getIntent().getStringExtra("id");
            word = getIntent().getStringExtra("word");
            translation = getIntent().getStringExtra("translation");
            notes = getIntent().getStringExtra("notes");

            //Setting Intent Data
            word_input.setText(word);
            translation_input.setText(translation);
            notes_input.setText(notes);
        } else {
            Toast.makeText(this, "No data...", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + word + " ?");
        builder.setMessage("Are you sure you want to delete " + word + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                com.example.words.DBHelper dbHelper = new com.example.words.DBHelper(UpdateActivity.this);
                dbHelper.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}