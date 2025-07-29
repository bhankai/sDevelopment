package com.ex.project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WorkoutAdapter extends RecyclerView.Adapter<WorkoutAdapter.WorkoutViewHolder> {
    private List<Workout> workoutList;

    public WorkoutAdapter(List<Workout> workoutList) {
        this.workoutList = workoutList;
    }

    public static class WorkoutViewHolder extends RecyclerView.ViewHolder {
        public TextView txtType, txtDate, txtDuration, txtNotes;

        public WorkoutViewHolder(View itemView) {
            super(itemView);
            txtType = itemView.findViewById(R.id.txtType);
            txtDate = itemView.findViewById(R.id.txtDate);
            txtDuration = itemView.findViewById(R.id.txtDuration);
            txtNotes = itemView.findViewById(R.id.txtNotes);
        }
    }

    @Override
    public WorkoutViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_workout, parent, false);
        return new WorkoutViewHolder(itemView);
    }



    @Override
    public void onBindViewHolder(WorkoutViewHolder holder, int position) {
        Workout workout = workoutList.get(position);
        holder.txtType.setText(workout.getType());
        holder.txtDate.setText(workout.getDate());
        holder.txtDuration.setText(workout.getDuration() + " min");
        holder.txtNotes.setText(workout.getNotes());
    }

    @Override
    public int getItemCount() {
        return workoutList.size();
    }
}
