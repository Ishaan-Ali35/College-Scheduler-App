//package com.example.a2340project_1;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.ItemTouchHelper;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.content.DialogInterface;
//import android.os.Bundle;
//import android.view.View;
//
//import com.example.a2340project_1.Adapter.ToDoAdapter;
//import com.example.a2340project_1.Model.ToDoModel;
//import com.example.a2340project_1.Utils.DatabaseHandler;
//import com.google.android.material.floatingactionbutton.FloatingActionButton;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//
//public class toDoActivity extends AppCompatActivity implements DialogCloseListener {
//
//    public RecyclerView tasksRecyclerView;
//    private ToDoAdapter tasksAdapter;
//
//    private List<ToDoModel> taskList;
//    public DatabaseHandler db;
//    private FloatingActionButton fab;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_to_do);
//
//        db = new DatabaseHandler(this);
//        db.openDatabase();
//
//        taskList = new ArrayList<>();
//
//        tasksRecyclerView = findViewById(R.id.tasksRecyclerView);
//        tasksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        tasksAdapter = new ToDoAdapter(db, this);
//        tasksRecyclerView.setAdapter(tasksAdapter);
//
//        fab = findViewById(R.id.fab);
//
//        ItemTouchHelper itemTouchHelper = new
//                ItemTouchHelper(new RecyclerItemTouchHelper(tasksAdapter));
//        itemTouchHelper.attachToRecyclerView(tasksRecyclerView);
//
//        taskList = db.getAllTasks();
//        Collections.reverse(taskList);
//        tasksAdapter.setTasks(taskList);
//
//        fab. setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                 AddNewTask.newInstance().show(getSupportFragmentManager(), AddNewTask.TAG);
//            }
//        });
//    }
//    @Override
//    public void handleDialogClose(DialogInterface dialog) {
//        taskList = db.getAllTasks();
//        Collections.reverse(taskList);
//        tasksAdapter.setTasks(taskList);
//        tasksAdapter.notifyDataSetChanged();
//    }
//}

// MainActivity.java
package com.example.a2340project_1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import java.util.ArrayList;

public class toDoActivity extends AppCompatActivity {

    private EditText editTextTask;
    private Button buttonAdd;
    private Button buttonBackToDo;
    private ListView listViewTasks;

    private ArrayList<String> taskList;
    private ArrayAdapter<String> taskAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do);

        editTextTask = findViewById(R.id.editTextTask);
        buttonAdd = findViewById(R.id.buttonAdd);
        listViewTasks = findViewById(R.id.listViewTasks);
        buttonBackToDo = findViewById(R.id.buttonBackToDo);

        taskList = new ArrayList<>();
        taskAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, taskList);
        listViewTasks.setAdapter(taskAdapter);

        listViewTasks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showPopupMenu(view, position);
            }
        });

        buttonBackToDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
    }

    private void showPopupMenu(View view, final int position) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
        final int x = R.id.menuDelete;

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(android.view.MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.menuEdit) {
                    editTask(position);
                    return true;
                } else if (itemId == R.id.menuDelete) {
                    deleteTask(position);
                    return true;
                } else {
                    return false;
                }
            }
        });

        popupMenu.show();
    }

    private void editTask(final int position) {
        String task = taskList.get(position);
        editTextTask.setText(task);
        taskList.remove(position);
        taskAdapter.notifyDataSetChanged();
        buttonAdd.setText("Save Changes");

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String updatedTask = editTextTask.getText().toString().trim();
                if (!updatedTask.isEmpty()) {
                    taskList.add(position, updatedTask);
                    taskAdapter.notifyDataSetChanged();
                    editTextTask.getText().clear();
                    buttonAdd.setText("Add Task");
                }
            }
        });
    }

    private void deleteTask(int position) {
        taskList.remove(position);
        taskAdapter.notifyDataSetChanged();
    }

    public void addTask(View view) {
        if (buttonAdd.getText().toString().equals("Add Task")) {
            String task = editTextTask.getText().toString().trim();
            if (!task.isEmpty()) {
                taskList.add(task);
                taskAdapter.notifyDataSetChanged();
                editTextTask.getText().clear();
            }
        } else if (buttonAdd.getText().toString().equals("Save Changes")) {
        }
    }
}