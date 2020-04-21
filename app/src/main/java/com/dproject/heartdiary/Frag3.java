package com.dproject.heartdiary;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Frag3 extends Fragment {

    private View view;
    RecyclerView recyclerView;
    Adapter adapter;
    List<Note> notes;
  //  private Adapter adapter = null;
    //private List<Note> notes = null;
   // private NotesAdapter adapter;
    //private ArrayList<Note> notes;
    //private NotesDao dao;
   //private List<Note> notes = new ArrayList<>();
    NoteDatabase noteDatabase;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag3, container, false);

        //View v = inflater.inflate(R.layout.fragment_report, container, false);

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.myToolBar);
       ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        // Remove default title text
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);

       // recyclerView = (RecyclerView) view.findViewById(R.id.notes_list);
        //NoteDatabase db = new NoteDatabase(getActivity());
       // notes = db.getNotes();

        noteDatabase = new NoteDatabase(getActivity());
        List<Note> allNotes = noteDatabase.getNotes();

        recyclerView = view.findViewById(R.id.listOfNotes);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new Adapter(getActivity(),notes);
        recyclerView.setAdapter(adapter);
        //recyclerView.setAdapter(adapter);

        //loadNotes();






        setHasOptionsMenu(true);

        return view;
    }

    /*@Override
    protected void onPostExecute(List<Note> notes) {

        if(notes != null && !notes.isEmpty())
        {
            super.onPostExecute(notes);
            List<Note> noteList = notes;
            adapter.notifyDataSetChanged();
        }

    }*/

    /*private void loadNotes() {
       //ArrayList<Note> notes = new ArrayList<>();
        this.notes = new ArrayList<>();

        List<Note> list = dao.getNotes();// get All notes from DataBase

        this.notes.addAll(list);

        this.adapter = new NotesAdapter(getActivity(), this.notes);
        this.recyclerView.setAdapter(adapter);
        //adapter.notifyDataSetChanged();
    }*/
/*
    private void onAddNewNote() {
        if (notes != null)
            notes.add(new Note ("this is new note ", new Date().getTime()));
        if (adapter != null)
            adapter.notifyDataSetChanged();
    }

 */

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.menu3, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.delete){
            // Intent i = new Intent(this, AddNote.class);
            // startActivity(i);
            Toast.makeText(getActivity(), "Delete btn is Clicked", Toast.LENGTH_SHORT).show();
        }

        if(item.getItemId() == R.id.logout){
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivityForResult(intent,100);
        }
        return super.onOptionsItemSelected(item);
    }

   /* @Override
    public void onResume() {
        super.onResume();
        loadNotes();
    }*/
}
