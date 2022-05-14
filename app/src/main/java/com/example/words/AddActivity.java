package com.example.words;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {

    EditText word_input, translation_input, notes_input;
    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        word_input = findViewById(R.id.word_input);
        translation_input = findViewById(R.id.translation_input);
        notes_input = findViewById(R.id.notes_input);

        add_button = findViewById(R.id.add_button);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbHelper = new DBHelper(AddActivity.this);
                dbHelper.addWord(word_input.getText().toString().trim(),
                        translation_input.getText().toString().trim(),
                        notes_input.getText().toString().trim());
                //Refresh activity
                Intent intent = new Intent(getParentActivityIntent());
                startActivity(intent);
                finish();
            }
        });
    }
}