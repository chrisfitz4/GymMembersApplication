package com.example.gymmembersapplication.util;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.gymmembersapplication.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MemberRecyclerView extends RecyclerView.Adapter<MemberRecyclerView.ViewHolder> {

    private ArrayList<GymMember> gymMembers;

    private GymMemberAdapterDelegate gymMemberAdapterDelegate;

    public interface GymMemberAdapterDelegate{
        void gymMemberSelected(GymMember selectedGymMember);
    }

    //constructors
    public MemberRecyclerView(ArrayList<GymMember> gymMembers, GymMemberAdapterDelegate gymMemberAdapterDelegate) {
        this.gymMemberAdapterDelegate = gymMemberAdapterDelegate;
        this.gymMembers = gymMembers;
    }
    public MemberRecyclerView(){
        this.gymMembers = new ArrayList<>();
    }



    @NonNull
    @Override
    public MemberRecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.members_list_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MemberRecyclerView.ViewHolder holder, final int position) {
        final GymMember gymMember = gymMembers.get(position);
        holder.textViewMemberId.setText(gymMember.getId());
        holder.textViewMemberName.setText(gymMember.getName());
        Glide.with(holder.imageViewMemberPic.getContext())
                .load(R.drawable.ic_directions_run)
                .into(holder.imageViewMemberPic);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gymMemberAdapterDelegate.gymMemberSelected(gymMembers.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return gymMembers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textViewMemberId;
        TextView textViewMemberName;
        ImageView imageViewMemberPic;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewMemberId = itemView.findViewById(R.id.memberIDTextView);
            textViewMemberName = itemView.findViewById(R.id.memberNameTextView);
            imageViewMemberPic = itemView.findViewById(R.id.memberPhotoImageView);
        }
    }
}
