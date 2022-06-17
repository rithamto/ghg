package com.huynhphuoctruong.crud_student;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.huynhphuoctruong.adapter.StudentAdapter;
import com.huynhphuoctruong.database.StudentDB;
import com.huynhphuoctruong.models.Student;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    StudentDB db;
    ListView lvStudent;
    StudentAdapter adapter;
    ArrayList<Student> students;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linkViews();
        prepareDB();
        loadData();
        addEvents();
    }

    private void addEvents() {
        lvStudent.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                dialogOpen();
                return false;
            }
        });
    }

    private void loadData() {
        students = new ArrayList<Student>();
        Cursor cursor = db.getData("SELECT * FROM " + StudentDB.TB_NAME);
        students.clear();
        while(cursor.moveToNext()){
            students.add(new Student(cursor.getString(0), cursor.getString(1), cursor.getString(2)));
        }

        cursor.close();
        adapter = new StudentAdapter(MainActivity.this, R.layout.item_list_view, students);
        lvStudent.setAdapter(adapter);
    }

    private void prepareDB() {
        db = new StudentDB(this);
        db.generateDB();
    }

    private void linkViews() {
        lvStudent = findViewById(R.id.lvStudent);
    }

    public void dialogOpen(){
        EditText editId, editName, editClass;
        Button btnAdd, btnCancel;

        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.add_student);
        editId = dialog.findViewById(R.id.editId);
        editName = dialog.findViewById(R.id.editName);
        editClass = dialog.findViewById(R.id.editClass);
        btnAdd = dialog.findViewById(R.id.btnAdd);
        btnCancel = dialog.findViewById(R.id.btnCancel);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = editId.getText().toString();
                String name = editName.getText().toString();
                String classs = editClass.getText().toString();

                if(!id.equals("") && !name.equals("") && !classs.equals("")){
                    db.execQuery("INSERT INTO " + StudentDB.TB_NAME + " VALUES('" + id + "', '" + name + "', '" + classs + "')");
                    Toast.makeText(MainActivity.this, "ADD STUDENT SUCCESS", Toast.LENGTH_SHORT).show();

                    editId.setText("");
                    editName.setText("");
                    editClass.setText("");

                    dialog.dismiss();
                    loadData();
                } else{
                    Toast.makeText(MainActivity.this, "ADD STUDENT FAIL", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}