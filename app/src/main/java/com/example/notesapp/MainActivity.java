package com.example.notesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton btnAddNote;
    RecyclerView rvNotes;
    NotesAdapter nAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAddNote = findViewById(R.id.btnAddNote);
        rvNotes = findViewById(R.id.rvNotes);
        rvNotes.setHasFixedSize(true);
        rvNotes.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Note> options
                = new FirebaseRecyclerOptions.Builder<Note>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("Notes"), Note.class)
                .build();
        nAdapter = new NotesAdapter(options);
        rvNotes.setAdapter(nAdapter);

        btnAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddNoteActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        nAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        nAdapter.stopListening();
    }
}