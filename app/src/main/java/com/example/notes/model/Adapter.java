package com.example.notes.model;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notes.NoteDetails;
import com.example.notes.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    List<String> titles;
    List<String> content;
    public Adapter(List<String> title, List<String> content){
        this.titles = title;
        this.content = content;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notes,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        holder.noteTitle.setText(titles.get(position));
        holder.noteContent.setText(content.get(position));
        holder.cardView.setCardBackgroundColor(holder.view.getResources().getColor(getRandom(),null));

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(), NoteDetails.class);
                intent.putExtra("title", titles.get(position));
                intent.putExtra("content",content.get(position));
                view.getContext().startActivity(intent);


            }
        });
    }
    private int getRandom()
    {
        List<Integer> colorCodes = new ArrayList<>();
        colorCodes.add(R.color.blue);
        colorCodes.add(R.color.skyblue);
        colorCodes.add(R.color.lightPurple);
        colorCodes.add(R.color.gray);
        colorCodes.add(R.color.red);
        colorCodes.add(R.color.notgreen);

        Random randomColor = new Random() ;
        int number = randomColor.nextInt(colorCodes.size());
        return colorCodes.get(number);

    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView noteTitle, noteContent;
        View view;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            noteTitle = itemView.findViewById(R.id.titles);
            noteContent = itemView.findViewById(R.id.content);
            cardView = itemView.findViewById(R.id.noteCard);
            view = itemView;
        }
    }
}
