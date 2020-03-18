package com.example.tema2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {
    private List<Student> students;
    private Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        RelativeLayout parentLayout;

        public ViewHolder(View v) {
            super(v);

            textView = v.findViewById(R.id.textViewRV);
            parentLayout = v.findViewById(R.id.itemRV);

        }
    }

    public StudentAdapter(Context pContext, List<Student> myDataset) {

        context = pContext;
        students = myDataset;
    }

    @Override
    public StudentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_stud, parent, false);

        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.textView.setText(students.get(position).name + " - " +  students.get(position).mark);
    }

    @Override
    public int getItemCount() {
        return students.size();
    }
}
