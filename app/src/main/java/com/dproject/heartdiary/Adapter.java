package com.dproject.heartdiary;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    LayoutInflater inflater;
    List<Note> notes;

    Adapter(Context context, List<Note> notes){
        this.inflater = LayoutInflater.from(context);
        this.notes = this.notes;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.custom_list_view,viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder viewHolder, int i) {
        String content = notes.get(i).getContent();
        String date = notes.get(i).getDate();
        String time = notes.get(i).getTime();
        //Log.d("Title", "onBindViewHolder: Title -> " +title);
       // viewHolder.nTitle.setText(title);
        viewHolder.nContent.setText(content);
        viewHolder.nDate.setText(date);
        viewHolder.nTime.setText(time);

    }

    @Override
    public int getItemCount() {
        //return notes.size();
        return 0;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView nContent,nDate,nTime;

        public ViewHolder(@NonNull View itemView) {
            super (itemView);
            //nTitle = itemView.findViewById(R.id.nTitle);
            nContent = itemView.findViewById(R.id.nContent);
            nDate = itemView.findViewById(R.id.nDate);
            nTime = itemView.findViewById(R.id.nTime);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(v.getContext(), "ItemClicked", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(v.getContext(), Details.class);
                    i.putExtra("ID", notes.get (getAdapterPosition()).getId());
                    v.getContext().startActivity(i);

                }
            });
        }
    }
}