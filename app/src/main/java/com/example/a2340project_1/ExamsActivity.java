package com.example.a2340project_1;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ExamsActivity extends AppCompatActivity {

    private EditText editTextCourseName, editTextDate, editTextTime, editTextLocation;
    private Button buttonAddExam;
    private Button buttonBackExam;

    private ListView listViewExams;
    private List<Exam> examList;
    private ArrayAdapter<Exam> examAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exams);


        editTextCourseName = findViewById(R.id.editTextCourse);
        editTextDate = findViewById(R.id.editTextDate);
        editTextTime = findViewById(R.id.editTextTime);
        editTextLocation = findViewById(R.id.editTextLocation);
        buttonAddExam = findViewById(R.id.buttonAddExam);
        buttonBackExam = findViewById(R.id.backButtonExam);
        listViewExams = findViewById(R.id.listViewExams);

        examList = new ArrayList<>();
        examAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, examList);
        listViewExams.setAdapter(examAdapter);


        listViewExams.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showPopupMenu(view, position);
            }
        });

        buttonBackExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    public void addExam(View view) {
        String courseName = editTextCourseName.getText().toString().trim();
        String date = editTextDate.getText().toString().trim();
        String time = editTextTime.getText().toString().trim();
        String location = editTextLocation.getText().toString().trim();

        if (!courseName.isEmpty() && !date.isEmpty() && !time.isEmpty() && !location.isEmpty()) {
            Exam exam = new Exam (courseName, date, time, location);

            examList.add(exam);
            examAdapter.notifyDataSetChanged();

            editTextCourseName.getText().clear();
            editTextDate.getText().clear();
            editTextTime.getText().clear();
            editTextLocation.getText().clear();
        }
    }


    private void showPopupMenu(View view, final int position) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(android.view.MenuItem item) {
                if (item.getItemId() == R.id.menuEdit) {
                    editExam(position);
                    return true;
                } else if (item.getItemId() == R.id.menuDelete) {
                    deleteExam(position);
                    return true;
                } else {
                    return false;
                }
            }

        });

        popupMenu.show();
    }

    private void editExam(final int position) {
        final Exam exam = examList.get(position);
        editTextCourseName.setText(exam.getCourse());
        editTextDate.setText(exam.getDate());
        editTextTime.setText(exam.getTime());
        editTextLocation.setText(exam.getLocation());

        buttonAddExam.setText("Save Changes");

        buttonAddExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String updatedCourseName = editTextCourseName.getText().toString().trim();
                String updatedDate = editTextDate.getText().toString().trim();
                String updatedTime = editTextTime.getText().toString().trim();
                String updatedLocation = editTextLocation.getText().toString().trim();

                if (!updatedCourseName.isEmpty() && !updatedDate.isEmpty() && !updatedTime.isEmpty() && !updatedLocation.isEmpty()) {
                    exam.setCourse(updatedCourseName);
                    exam.setDate(updatedDate);
                    exam.setTime(updatedTime);
                    exam.setLocation(updatedLocation);

                    examAdapter.notifyDataSetChanged();

                    editTextCourseName.getText().clear();
                    editTextDate.getText().clear();
                    editTextTime.getText().clear();
                    editTextLocation.getText().clear();
                    buttonAddExam.setText("Add Exam");
                }
            }
        });
    }



    private void deleteExam(int position) {
        examList.remove(position);
        examAdapter.notifyDataSetChanged();
    }
}
