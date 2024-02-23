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

public class ScheduleActivity extends AppCompatActivity {

    private EditText editTextCourseName, editTextStartTime, editTextEndTime, editTextInstructor;
    private Button buttonAddCourse;
    private Button buttonBackSchedule;
    private ListView listViewCourses;
    private List<Schedule> courseList;
    private ArrayAdapter<Schedule> courseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);


        editTextCourseName = findViewById(R.id.editTextCourseName);
        editTextStartTime = findViewById(R.id.editTextStartTime);
        editTextEndTime = findViewById(R.id.editTextEndTime);
        editTextInstructor = findViewById(R.id.editTextInstructor);
        buttonAddCourse = findViewById(R.id.buttonAddCourse);
        buttonBackSchedule = findViewById(R.id.buttonBackSchedule);

        listViewCourses = findViewById(R.id.listViewCourses);

        courseList = new ArrayList<>();
        courseAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, courseList);
        listViewCourses.setAdapter(courseAdapter);


        listViewCourses.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showPopupMenu(view, position);
            }
        });
        buttonBackSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });


    }

    public void addCourse(View view) {
        String courseName = editTextCourseName.getText().toString().trim();
        String startTime = editTextStartTime.getText().toString().trim();
        String endTime = editTextEndTime.getText().toString().trim();
        String instructor = editTextInstructor.getText().toString().trim();

        if (!courseName.isEmpty() && !startTime.isEmpty() && !endTime.isEmpty() && !instructor.isEmpty()) {
            Schedule course = new Schedule(courseName, startTime, endTime, instructor);

            courseList.add(course);
            courseAdapter.notifyDataSetChanged();

            editTextCourseName.getText().clear();
            editTextStartTime.getText().clear();
            editTextEndTime.getText().clear();
            editTextInstructor.getText().clear();
        }
    }


    private void showPopupMenu(View view, final int position) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(android.view.MenuItem item) {
                if (item.getItemId() == R.id.menuEdit) {
                    editCourse(position);
                    return true;
                } else if (item.getItemId() == R.id.menuDelete) {
                    deleteCourse(position);
                    return true;
                } else {
                    return false;
                }
            }

        });

        popupMenu.show();
    }

    private void editCourse(final int position) {
        final Schedule course = courseList.get(position);
        editTextCourseName.setText(course.getCourse());
        editTextStartTime.setText(course.getStartTime());
        editTextEndTime.setText(course.getEndTime());
        editTextInstructor.setText(course.getInstructor());

        buttonAddCourse.setText("Save Changes");

        buttonAddCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String updatedCourseName = editTextCourseName.getText().toString().trim();
                String updatedStartTime = editTextStartTime.getText().toString().trim();
                String updatedEndTime = editTextEndTime.getText().toString().trim();
                String updatedInstructor = editTextInstructor.getText().toString().trim();

                if (!updatedCourseName.isEmpty() && !updatedStartTime.isEmpty() && !updatedEndTime.isEmpty() && !updatedInstructor.isEmpty()) {
                    course.setCourse(updatedCourseName);
                    course.setStartTime(updatedStartTime);
                    course.setEndTime(updatedEndTime);
                    course.setInstructor(updatedInstructor);

                    courseAdapter.notifyDataSetChanged();

                    editTextCourseName.getText().clear();
                    editTextStartTime.getText().clear();
                    editTextEndTime.getText().clear();
                    editTextInstructor.getText().clear();
                    buttonAddCourse.setText("Add Course");
                }
            }
        });
    }



    private void deleteCourse(int position) {
        courseList.remove(position);
        courseAdapter.notifyDataSetChanged();
    }
}
