package com.example.notesapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class NotesAdapter extends FirebaseRecyclerAdapter<Note, NotesAdapter.MyViewHolder> {


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public NotesAdapter(@NonNull FirebaseRecyclerOptions<Note> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull Note model) {
            holder.tvTitle.setText(model.getTitleKey());
            holder.tvDesc.setText(model.getDescKey().substring(0, model.getDescKey().length()/4));
            holder.tvDate.setText(model.getDateKey());
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_design, parent, false);
        return new MyViewHolder(v);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvTitle, tvDesc, tvDate;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvDate = itemView.findViewById(R.id.tvDate);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDesc = itemView.findViewById(R.id.tvDesc);
        }
    }
}
