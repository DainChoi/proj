package com.dproject.heartdiary;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

//import com.dproject.heartdiary.db.NotesDatabase;
//import com.dproject.heartdiary.db.NotesDao;

public class Frag2 extends Fragment {

    private View view;
    //private NotesDao dao;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag2, container, false);

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.myToolBar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
        //dao = NotesDatabase.getInstance(getActivity()).notesDao();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.menu2, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    /*@Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.save){
            // Intent i = new Intent(this, AddNote.class);
            // startActivity(i);
            //Toast.makeText(getActivity(), "Add btn is Clicked", Toast.LENGTH_SHORT).show();
       
            onSaveNote();
        }
        return super.onOptionsItemSelected(item);
    }*/


}
