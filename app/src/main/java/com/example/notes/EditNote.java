package com.example.notes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class EditNote extends AppCompatActivity {
    EditText editNoteTitle, editNoteContent;
    Intent data;
    FirebaseFirestore fStore;
    ProgressBar spinner;

@Override
    protected  void onCreate(Bundle savedInstances) {
    super.onCreate(savedInstances);
    setContentView(R.layout.activity_edit_note);
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    fStore.getInstance();

    data = getIntent();

    editNoteTitle = findViewById(R.id.editNoteTitle);
    editNoteContent = findViewById(R.id.editNoteContent);
    spinner = findViewById(R.id.progressBar2);

    String noteTitle = data.getStringExtra("title");
    String noteContent = data.getStringExtra("content");

    editNoteTitle.setText(noteTitle);
    editNoteContent.setText(noteContent);

    FloatingActionButton fab = findViewById(R.id.saveEditedNote);
    fab.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            String nTitle = editNoteTitle.getText().toString();
            String nContent = editNoteContent.getText().toString();

            if (nTitle.isEmpty() || nContent.isEmpty()){
                Toast.makeText(EditNote.this, "Field is Empty", Toast.LENGTH_SHORT).show();
                return;
            }
            spinner.setVisibility(View.VISIBLE);

            DocumentReference docref = fStore.collection("notes").document(data.getStringExtra("noteId"));
            Map<String,Object> note = new HashMap<>();
            note.put("title",nTitle);
            note.put("content",nContent);
            docref.update(note).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(EditNote.this, "Note Saved Successfully.", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(EditNote.this, "Error, Try Again", Toast.LENGTH_SHORT).show();
                    spinner.setVisibility(View.VISIBLE);
                }
            });
        }
    });
}
}
