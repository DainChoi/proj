package com.dproject.heartdiary;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Details extends AppCompatActivity {
   //TextView mDetails;
   // NoteDatabase db;
    //Note note;
    long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TextView details = findViewById(R.id.detailsOfNote);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //mDetails = findViewById(R.id.detailsOfNote);

        Intent i = getIntent();
       // Long id = i.getLongExtra("ID", 0);
        id = i.getLongExtra("ID", 0);

        NoteDatabase db = new NoteDatabase(this);
        Note note = db.getNote(id);
       // getSupportActionBar().setTitle(note.getTitle());

        if(details != null) {
            details.setText(note.getContent());

        details.setMovementMethod(new ScrollingMovementMethod());}


        //Toast.makeText(this, "Details -> " + note.getContent(), Toast.LENGTH_SHORT).show();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //db.deleteNote(note.getId());
                NoteDatabase db = new NoteDatabase(getApplicationContext());
                db.deleteNote(id);
                Toast.makeText(getApplicationContext(), "Note Deleted", Toast.LENGTH_SHORT).show();
                finish();
                //startActivity(new Intent (getApplicationContext(), Frag3.class)); // context 객체를 frag3에 전달
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.edit_menu, menu);
        return super.onCreateOptionsMenu(menu);
        //return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == R.id.editNote){
            // send user to edit activity
            //Toast.makeText(this, "Edit Note", Toast.LENGTH_SHORT).show();
            Intent i = new Intent (this, Edit.class);
            i.putExtra("ID", id); //id라는 정보 전달
            startActivity(i);

        }
        return super.onOptionsItemSelected(item);
    }

}
