package com.example.gymmembersapplication.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.TestLooperManager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gymmembersapplication.R;
import com.example.gymmembersapplication.util.Constants;
import com.example.gymmembersapplication.util.GymMember;
import com.example.gymmembersapplication.util.MemberRecyclerView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MemberRecyclerView.GymMemberAdapterDelegate{

    File file;
    @BindView(R.id.addMembers)
    Button addMembersButton;
    @BindView(R.id.membersRecyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.gymTitle)
    TextView textView;
    ArrayList<GymMember> members;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        members = new ArrayList<GymMember>();

        file = new File(Environment.getRootDirectory(),Constants.FILE_NAME);
        drawRecyclerView();



        addMembersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddMembersActivity.class);
                intent.putExtra(Constants.SEND_MEMBER_COUNT,members.size());
                startActivityForResult(intent, Constants.REQUEST_ADD_MEMBERS);
            }
        });

    }

    //read the file and set the arraylist to the members
    private void getMembersFromFile(){
        //file = new File(Environment.getRootDirectory(),Constants.FILE_NAME);
        try{
            FileInputStream fileInputStream = openFileInput(Constants.FILE_NAME);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String text = bufferedReader.readLine();
            String[] membersText = text.split("\\*\\*\\*");
            for(int i = 0; i<membersText.length; i++){
                members.add(new GymMember(membersText[i]));
            }
            bufferedReader.close();
            inputStreamReader.close();
            fileInputStream.close();
        }catch (IOException io){
            io.printStackTrace();
        }
    }

    private void deleteFile(){
        try{
            FileWriter fileWriter = new FileWriter(file,false);
            PrintWriter printWriter = new PrintWriter(fileWriter,false);
            printWriter.flush();
            printWriter.close();
            fileWriter.flush();
            fileWriter.close();
        }catch (IOException io){
            io.printStackTrace();
        }
    }

    private void drawRecyclerView(){
        getMembersFromFile();
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this);
        MemberRecyclerView adapter = new MemberRecyclerView(members, this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==Constants.REQUEST_ADD_MEMBERS){
            if(resultCode==RESULT_OK){
                drawRecyclerView();
            }else if(resultCode==RESULT_CANCELED){
                Toast.makeText(this,"Member was not added",Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void gymMemberSelected(GymMember selectedGymMember) {
        Intent intent = new Intent(this,ViewMemberActivity.class);
        intent.putExtra(Constants.SEND_GYM_MEMBER,selectedGymMember);
        startActivity(intent);
    }
}
