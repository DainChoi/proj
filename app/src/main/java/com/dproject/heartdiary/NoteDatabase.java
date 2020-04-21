package com.dproject.heartdiary;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class NoteDatabase extends SQLiteOpenHelper {

    private  static final int DATABASE_VERSION = 2;
    private  static final String DATABASE_NAME = "NoteDB";
    private static final String TABLE_NAME = "notetables";

    //columns name for database table
    private  static final String KEY_ID = "id";
    //private  static final String KEY_TITLE = "title";
    private  static final String KEY_CONTENT = "content";
    private  static final String KEY_DATE = "date";
    private  static final String KEY_TIME = "time";



    NoteDatabase(Context context){
        super(context, DATABASE_NAME,null, DATABASE_VERSION);


    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        //CREATE TABLE nametame(id INT PRIMARY KEY, title TEXT, content TEXT, date TEXT, tme TEXT);
        String createDb = "CREATE TABLE "+TABLE_NAME+" ("+
                KEY_ID+" INTEGER PRIMARY KEY,"+
                KEY_CONTENT+" TEXT,"+
                KEY_DATE+" TEXT,"+
                KEY_TIME+" TEXT"
                +" )";



        db.execSQL(createDb);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if(oldVersion >= newVersion)
            return;
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public long addNote(Note note){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        //c.put (KEY_TITLE,note.getTitle());
        c.put (KEY_CONTENT,note.getContent());
        c.put (KEY_DATE,note.getDate());
        c.put (KEY_TIME,note.getTime());


        long ID = db.insert(TABLE_NAME, null, c);
       // Log.d("Inserted", "ID -> " + ID);
        return ID;
    }

    public Note getNote(long id){
         //select * from databaseTable where id=1;
        Note note = null;
        SQLiteDatabase db = this.getWritableDatabase();
        String[] query = new String[] {KEY_ID,KEY_CONTENT,KEY_DATE,KEY_TIME};
        //Cursor cursor = db.query(TABLE_NAME,new String[]{KEY_ID,KEY_CONTENT,KEY_DATE,KEY_TIME},KEY_ID+"=?"
        Cursor cursor=  db.query(TABLE_NAME, query, KEY_ID+" =?", new String[]
                {String.valueOf(id)},null,null,null, null);
       // cursor.close();



        if(cursor != null)
        {
            if(cursor.moveToFirst())
            {
                note = new Note(Long.parseLong(cursor.getString(0)),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3));
            }
        }
        db.close();
        return note;
           /* cursor.moveToFirst();

       return new Note(
                Long.parseLong(cursor.getString(0)),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3)); */
    }


        /*if(cursor != null)
            cursor.moveToFirst();

        Note note = new Note (cursor.getLong(0),cursor.getString(1),
                cursor.getString(2),cursor.getString(3));
        return note; // 추가된 부분
    }*/

    public List<Note> getNotes(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<Note> allNotes = new ArrayList<>();
        // select * from databaseName

        String query = "SELECT * FROM "+ TABLE_NAME+" ORDER BY "+KEY_ID+" DESC";

        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            do{
                Note note = new Note();
                note.setID(cursor.getLong(0));
               // note.setTitle(cursor.getString(1));
                note.setContent(cursor.getString(1));
                note.setDate(cursor.getString(2));
                note.setTime(cursor.getString(3));

                allNotes.add(note);


            }while (cursor.moveToNext());
        }

        return allNotes;


    }

    public int editNote(Note note) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        Log.d("Edited", "ID -> "+note.getId());
        c.put (KEY_CONTENT,note.getContent());
        c.put (KEY_DATE,note.getDate());
        c.put (KEY_TIME,note.getTime());
        return db.update(TABLE_NAME, c, KEY_ID+"=?",new String[]{String.valueOf(note.getId())});
    }

    void deleteNote(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ID+"=?", new String[]{String.valueOf(id)});
        db.close();
    }


}