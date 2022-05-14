package com.example.words;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    Activity activity;
    private ArrayList word_id, word_word, word_translation, word_notes;

    Animation translate_anim;

    CustomAdapter(Activity activity, Context context, ArrayList word_id, ArrayList word_word, ArrayList word_translation,
                  ArrayList word_notes) {
        this.activity = activity;
        this.context = context;
        this.word_id = word_id;
        this.word_word = word_word;
        this.word_translation = word_translation;
        this.word_notes = word_notes;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.word_id_txt.setText(String.valueOf(word_id.get(position)));
        holder.word_word_txt.setText(String.valueOf(word_word.get(position)));
        holder.word_translation_txt.setText(String.valueOf(word_translation.get(position)));
        holder.word_notes_txt.setText(String.valueOf(word_notes.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(word_id.get(position)));
                intent.putExtra("word", String.valueOf(word_word.get(position)));
                intent.putExtra("translation", String.valueOf(word_translation.get(position)));
                intent.putExtra("notes", String.valueOf(word_notes.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return word_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView word_id_txt, word_word_txt, word_translation_txt, word_notes_txt;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            word_id_txt = itemView.findViewById(R.id.word_id_txt);
            word_word_txt = itemView.findViewById(R.id.word_word_txt);
            word_translation_txt = itemView.findViewById(R.id.word_translation_txt);
            word_notes_txt = itemView.findViewById(R.id.word_notes_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            //Animate RecyclerView
            translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }
    }
}
