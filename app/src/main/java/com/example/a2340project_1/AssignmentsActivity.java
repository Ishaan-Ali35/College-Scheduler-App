package com.example.a2340project_1;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AssignmentsActivity extends AppCompatActivity {

    private EditText editTextAssignmentTitle, editTextDueDate, editTextCourse;
    private Button buttonAddAssignment;
    private Button buttonBackAssignment;
    private Spinner spinnerSort;
    private ListView listViewAssignments;
    private List<Assignment> assignmentList;
    private ArrayAdapter<Assignment> assignmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignments);


        editTextAssignmentTitle = findViewById(R.id.editTextAssignmentTitle);
        editTextDueDate = findViewById(R.id.editTextDueDate);
        editTextCourse = findViewById(R.id.editTextCourse);
        buttonAddAssignment = findViewById(R.id.buttonAddAssignment);
        buttonBackAssignment = findViewById(R.id.backButtonAssignment);
        spinnerSort = findViewById(R.id.spinnerSort);
        listViewAssignments = findViewById(R.id.listViewAssignments);

        assignmentList = new ArrayList<>();
        assignmentAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, assignmentList);
        listViewAssignments.setAdapter(assignmentAdapter);

        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(
                this, R.array.sorting_options, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSort.setAdapter(spinnerAdapter);

        spinnerSort.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                sortAssignments(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });

        listViewAssignments.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showPopupMenu(view, position);
            }
        });

        buttonBackAssignment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public void addAssignment(View view) {
        String title = editTextAssignmentTitle.getText().toString().trim();
        String dueDate = editTextDueDate.getText().toString().trim();
        String course = editTextCourse.getText().toString().trim();

        if (!title.isEmpty() && !dueDate.isEmpty() && !course.isEmpty()) {
            Assignment assignment = new Assignment(title, dueDate, course);

            assignmentList.add(assignment);
            assignmentAdapter.notifyDataSetChanged();

            editTextAssignmentTitle.getText().clear();
            editTextDueDate.getText().clear();
            editTextCourse.getText().clear();
        }
    }

    private void sortAssignments(int sortingOption) {
        switch (sortingOption) {
            case 0:
                Collections.sort(assignmentList, new Comparator<Assignment>() {
                    @Override
                    public int compare(Assignment a1, Assignment a2) {
                        return a1.getDueDate().compareTo(a2.getDueDate());
                    }
                });
                break;
            case 1:
                Collections.sort(assignmentList, new Comparator<Assignment>() {
                    @Override
                    public int compare(Assignment a1, Assignment a2) {
                        return a1.getCourse().compareTo(a2.getCourse());
                    }
                });
                break;

            default:
                break;
        }

        assignmentAdapter.notifyDataSetChanged();
    }

    private void showPopupMenu(View view, final int position) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(android.view.MenuItem item) {
                if (item.getItemId() == R.id.menuEdit) {
                    editAssignment(position);
                    return true;
                } else if (item.getItemId() == R.id.menuDelete) {
                    deleteAssignment(position);
                    return true;
                } else {
                    return false;
                }
            }

        });

        popupMenu.show();
    }

    private void editAssignment(final int position) {
        final Assignment assignment = assignmentList.get(position);
        editTextAssignmentTitle.setText(assignment.getTitle());
        editTextDueDate.setText(assignment.getDueDate());
        editTextCourse.setText(assignment.getCourse());

        buttonAddAssignment.setText("Save Changes");

        buttonAddAssignment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String updatedTitle = editTextAssignmentTitle.getText().toString().trim();
                String updatedDueDate = editTextDueDate.getText().toString().trim();
                String updatedCourse = editTextCourse.getText().toString().trim();

                if (!updatedTitle.isEmpty() && !updatedDueDate.isEmpty() && !updatedCourse.isEmpty()) {
                    assignment.setTitle(updatedTitle);
                    assignment.setDueDate(updatedDueDate);
                    assignment.setCourse(updatedCourse);

                    assignmentAdapter.notifyDataSetChanged();

                    editTextAssignmentTitle.getText().clear();
                    editTextDueDate.getText().clear();
                    editTextCourse.getText().clear();
                    buttonAddAssignment.setText("Add Assignment");
                }
            }
        });
    }



    private void deleteAssignment(int position) {
        assignmentList.remove(position);
        assignmentAdapter.notifyDataSetChanged();
    }
}
