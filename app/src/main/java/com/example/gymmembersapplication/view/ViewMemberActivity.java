package com.example.gymmembersapplication.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.gymmembersapplication.R;
import com.example.gymmembersapplication.util.Constants;
import com.example.gymmembersapplication.util.GymMember;
import com.example.gymmembersapplication.util.GymPlan;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewMemberActivity extends AppCompatActivity {

    @BindView(R.id.returnButton3)
    Button buttonReturn;
    @BindView(R.id.memberIdTextView3)
    TextView textViewID;
    @BindView(R.id.memberNameTextView3)
    TextView textViewName;
    @BindView(R.id.memberIcon3)
    ImageView memberIcon;
    @BindView(R.id.planTextView3)
    TextView planTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_member);

        ButterKnife.bind(this);

        intialize();
        buttonReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void intialize(){
        Intent intent = getIntent();
        GymMember gymMember = (GymMember)(intent.getParcelableExtra(Constants.SEND_GYM_MEMBER));
        textViewID.setText(gymMember.getId());
        textViewName.setText(gymMember.getName());
        GymPlan gymPlan = new GymPlan(gymMember.getPlan());
        planTextView.setText(gymPlan.toString());
        Glide.with(this).load(R.drawable.ic_directions_run).into(memberIcon);
    }
}
