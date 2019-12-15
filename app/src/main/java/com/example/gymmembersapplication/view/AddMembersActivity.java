package com.example.gymmembersapplication.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gymmembersapplication.R;
import com.example.gymmembersapplication.util.Constants;
import com.example.gymmembersapplication.util.GymMember;
import com.example.gymmembersapplication.util.GymPlan;
import com.example.gymmembersapplication.util.RecyclerViewPlan;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddMembersActivity extends AppCompatActivity implements RecyclerViewPlan.GymPlanDelegate {


    File file;
    @BindView(R.id.confirmAddMemberButton)
    Button addMemberButton;
    @BindView(R.id.cancelAddMemberButton)
    Button cancelAddMemberButton;
    @BindView(R.id.editTextName)
    EditText editText;
    @BindView(R.id.recylerViewPlan)
    RecyclerView recyclerView;
    @BindView(R.id.planSelected)
    TextView planSelectedTextView;

    int memberCount;
    ArrayList<GymPlan> gymPlans;

    int plan = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_members);

        ButterKnife.bind(this);
        file = new File(Environment.getRootDirectory(), Constants.FILE_NAME);
        Intent intent = getIntent();
        memberCount = intent.getIntExtra(Constants.SEND_MEMBER_COUNT,-1);

        setUpRecyclerView();
        //cancel add members
        cancelAddMemberButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                setResult(RESULT_CANCELED,intent);
                finish();
            }
        });
    }

    public void setUpRecyclerView(){
        gymPlans = new ArrayList<>();
        for(int i = 1; i<4;i++){
            gymPlans.add(new GymPlan(i));
        }
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        final RecyclerView.Adapter adapter = new RecyclerViewPlan(gymPlans,this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public GymMember createMember(){
        String id = Constants.GYM_MEMBER_ID_PREFIX+memberCount;
        String name = editText.getText().toString().trim();
        String planName = planSelectedTextView.getText().toString().trim();
        if(name.equals("")||planName.equals("")){
            return null;
        }
        return new GymMember(id,name,plan);
    }

    //add member
    public void onClick(View v) {
        GymMember gymMember = createMember();
        if(gymMember==null){
            Toast.makeText(this,"Input a valid name and choose a plan",Toast.LENGTH_LONG).show();
        }else {
            try {
                FileOutputStream fileOutputStream = openFileOutput(Constants.FILE_NAME, MODE_APPEND);
                String text = gymMember.toSaveString()+"***";
                fileOutputStream.write(text.getBytes());
                fileOutputStream.flush();
                fileOutputStream.close();
            } catch (IOException io) {
                io.printStackTrace();
            }
            Intent intent = new Intent();
            setResult(RESULT_OK, intent);
            finish(); }
    }

    @Override
    public void whichPlan(GymPlan gymPlan) {
        plan = gymPlan.getId();
        planSelectedTextView.setText((new GymPlan(plan)).getName());
    }
}
