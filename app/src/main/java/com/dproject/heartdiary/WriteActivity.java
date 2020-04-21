package com.dproject.heartdiary;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

//import com.dproject.heartdiary.adapters.NotesAdapter;
//import com.dproject.heartdiary.NotesDao;
import com.dproject.heartdiary.Note;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class WriteActivity extends AppCompatActivity {

   /* private EditText inputNote;
    private NotesAdapter adapter;
    private ArrayList<Note> notes;
    private NotesDao dao;*/


    EditText noteDetails;
    Calendar c;
    String todayDate;
    String currentTime;

    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);


        toolbar = (Toolbar)findViewById(R.id.myToolBar);
        setSupportActionBar(toolbar);

        // Remove default title text
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        // Get access to the custom title view
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);

        noteDetails = findViewById(R.id.noteDetails);

        c = Calendar.getInstance();
        todayDate = c.get(Calendar.YEAR)+"/"+(c.get(Calendar.MONTH)+1)+"/"+c.get(Calendar.DAY_OF_MONTH);
        currentTime = pad(c.get(Calendar.HOUR))+":"+pad(c.get(Calendar.MINUTE));

        Log.d("calendar", "Date and Time: " + todayDate +" and " + currentTime);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    // 뒤로가기 버튼 클릭시 메인으로 이동
    @Override
    public boolean onSupportNavigateUp()
    {
        finish();
        return true;
    }



    private String pad(int time) {
        if(time<10)
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
        if(item.getItemId() == R.id.save){
            // Intent i = new Intent(this, AddNote.class);
            // startActivity(i);
           Note note = new Note(noteDetails.getText().toString(),todayDate,currentTime);
           NoteDatabase db = new NoteDatabase(this);
           db.addNote(note);
           Toast.makeText(this, "Save btn is Clicked", Toast.LENGTH_SHORT).show();
          // goToMain();
            //onSaveNote();
            finish();

            /*long id = db.addNote(note);
            Note check = db.getNote(id);
            Log.d("inserted", "Note: "+ id +" Date: "+ check.getDate());
            onBackPressed();
            Toast.makeText(this, "Note Saved.", Toast.LENGTH_SHORT).show();*/



        }
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
   /*private void onSaveNote() {
        String text = inputNote.getText().toString();
        if (!text.isEmpty()) {
            long date = new Date().getTime(); // get Current system time
            Note note = new Note(text, date); // Create new Note
            dao.insertNote(note); //insert and save note to database
            finish(); // return to MainActivity
        }

   }*/

   /* private void onAddNewNote() {
       startActivity(new Intent (this, EditNoteActivity.class));
    }*/
}

