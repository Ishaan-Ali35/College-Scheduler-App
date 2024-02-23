package com.example.a2340project_1;


import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener {

    public CardView scheduleCard, examsCard, assignmentCard, toDoCard;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        scheduleCard = (CardView) findViewById(R.id.schedule);
        examsCard = (CardView) findViewById(R.id.exams);
        assignmentCard = (CardView) findViewById(R.id.assignments);
        toDoCard = (CardView) findViewById(R.id.toDo);

        scheduleCard.setOnClickListener(this);
        examsCard.setOnClickListener(this);
        assignmentCard.setOnClickListener(this);
        toDoCard.setOnClickListener(this);



    }


    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getTag().toString()) {
            case "schedule" :
                i = new Intent(this, ScheduleActivity.class);
                startActivity(i);
                break;
                
            case "exams" :
                i = new Intent(this, ExamsActivity.class);
                startActivity(i);
                break;
                
            case "assignments" :
                i = new Intent(this, AssignmentsActivity.class);
                startActivity(i);
                break;
            case "toDo" :
                i = new Intent(this, toDoActivity.class);
                startActivity(i);
                break;
        }
    }
}