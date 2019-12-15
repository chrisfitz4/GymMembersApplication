package com.example.gymmembersapplication.util;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gymmembersapplication.R;

import java.util.ArrayList;

public class RecyclerViewPlan extends RecyclerView.Adapter<RecyclerViewPlan.ViewHolder> {

    ArrayList<GymPlan> gymPlans;
    GymPlanDelegate gymPlanDelegate;


    public interface GymPlanDelegate{
        void whichPlan(GymPlan gymPlan);
    }

    public RecyclerViewPlan(ArrayList<GymPlan> gymPlans, GymPlanDelegate gymPlanDelegate) {
        this.gymPlans = gymPlans;
        this.gymPlanDelegate = gymPlanDelegate;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.plan_resource,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.textView.setText(gymPlans.get(position).toString());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gymPlanDelegate.whichPlan(gymPlans.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return gymPlans.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textViewPlanLayout);
        }
    }
}
