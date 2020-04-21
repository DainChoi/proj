package com.dproject.heartdiary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class Edit extends AppCompatActivity {

    Toolbar toolbar;
    EditText noteDetails;
    Calendar c;
    String todayDate;
    String currentTime;
    //NoteDatabase db;
    Note note;
    long nId;
    EditText nContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Intent i = getIntent();
        nId = i.getLongExtra("ID", 0);
        NoteDatabase db = new NoteDatabase(this);
        Note note = db.getNote(nId);
       // if(content != null)
         String content = note.getContent();
        nContent = findViewById(R.id.noteDetails);



        toolbar = (Toolbar) findViewById(R.id.myToolBar);
        //toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(note.getTitle());



        //noteTitle = findViewById(R.id.noteTitle);
        //noteDetails = findViewById(R.id.noteDetails);

        //noteDetails.setText(note.getContent());
        nContent.setText(content);

        /*noteTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() != 0){
                    getSupportActionBar().setTitle(s);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        }*/


        //get current date and time
        c = Calendar.getInstance();
        todayDate = c.get(Calendar.YEAR)+"/"+(c.get(Calendar.MONTH)+1)+"/"+c.get(Calendar.DAY_OF_MONTH);
        currentTime = pad(c.get(Calendar.HOUR))+":"+pad(c.get(Calendar.MINUTE));

        Log.d("calendar", "Date and Time: " + todayDate +" and " + currentTime);
    }

    private String pad(int time) {
        if(time <10)
            return "0"+time;
        return String.valueOf(time);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu2, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == R.id.save) {
            /*if (noteDetails.getText().length() != 0) {
                Note note = new Note(noteDetails.getText().toString(), noteDetails.getText().toString(), todayDate, currentTime);
                NoteDatabase sDB.addNote(note);
                Note check = sDB.getNote(id);
                Log.d("inserted", "Note: " + id + " -> Title: " + check.getContent() + " Date: " + check.getDate());
                onBackPressed();

                Toast.makeText(this, "Note Update.", Toast.LENGTH_SHORT).show();
            } else {
                noteDetails.setError("Details Can not be Blank.");
            }

        }else if(item.getItemId() == R.id.delete) {
            Toast.makeText(this, "Canceled", Toast.LENGTH_SHORT).show();
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);*/
            Note note = new Note(nId,nContent.getText().toString(),todayDate,currentTime);

            NoteDatabase sDB = new NoteDatabase(getApplicationContext());
            long id = sDB.editNote(note);
            Log.d("EDITED", "EDIT: id " + id);
           finish();
            Toast.makeText(this, "Note Edited.", Toast.LENGTH_SHORT).show();
        }else if(item.getItemId() == R.id.delete){
            Toast.makeText(this, "Canceled", Toast.LENGTH_SHORT).show();
            onBackPressed();
        }


            /*if (noteDetails.getText().length() != 0) {
                note.setContent(noteDetails.getText().toString());
                int id = db.editNote(note);
                if(id == note.getId()){
                    Toast.makeText(this, "Note Updated", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Error Updating Note.", Toast.LENGTH_SHORT).show();
                }
                Intent i = new Intent (getApplicationContext(), Details.class);
                i.putExtra("ID", note.getId());
                startActivity(i);

                Toast.makeText(this, "Note Update.", Toast.LENGTH_SHORT).show();
            } else {
                noteDetails.setError("Details Can not be Blank.");
            }

        }else if(item.getItemId() == R.id.delete) {
            Toast.makeText(this, "Canceled", Toast.LENGTH_SHORT).show();
            onBackPressed();
        }*/
        return super.onOptionsItemSelected(item);


    }



   /* private void goToMain() {
        Intent i = new Intent(this, Frag3.class);
        startActivity(i);
    }*/

    @Override
    public void onBackPressed(){
        super.onBackPressed();
    }

}

