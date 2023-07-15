package com.example.notesapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class AddNoteActivity extends AppCompatActivity {

    Button btnSave, btnCancel;
    TextInputEditText etTitle, etDesc;
    TextInputLayout tilTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        tilTitle = findViewById(R.id.tilTitle);
        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);
        etDesc = findViewById(R.id.etDesc);
        etTitle = findViewById(R.id.etTitle);

        etTitle.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

                tilTitle.setHintTextColor(ColorStateList.valueOf(getResources().getColor(R.color.red)));

            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String desc = etDesc.getText().toString().trim();
                String title = etTitle.getText().toString().trim();

                if(!desc.isEmpty() && !title.isEmpty())
                {
                    HashMap<String, Object> data = new HashMap<>();
                    data.put("titleKey", title);
                    data.put("descKey", desc);
                    java.util.Date date = new java.util.Date();
                    data.put("dateKey", date.toString());

                    FirebaseDatabase
                            .getInstance()
                            .getReference()
                            .child("Notes")
                            .child(date.toString())
                            .updateChildren(data)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(AddNoteActivity.this, "Note Added Successfully.", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(AddNoteActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(AddNoteActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });



                }

            }
        });


    }
}