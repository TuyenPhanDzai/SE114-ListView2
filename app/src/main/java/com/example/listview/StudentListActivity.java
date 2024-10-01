package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class StudentListActivity extends AppCompatActivity {

    private ListView lvStudents;
    private ArrayList<Student> studentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);

        lvStudents = findViewById(R.id.lvStudents);

        // Lấy id của lớp học từ Intent
        String classId = getIntent().getStringExtra("classId");
        String className = getIntent().getStringExtra("className");

        // Tạo danh sách sinh viên ảo cho mỗi lớp (dựa trên classId)
        studentList = getStudentsByClassId(classId);

        // Tạo adapter cho ListView
        ArrayAdapter<Student> adapter = new ArrayAdapter<Student>(this, android.R.layout.simple_list_item_2, android.R.id.text1, studentList) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text1 = view.findViewById(android.R.id.text1);
                TextView text2 = view.findViewById(android.R.id.text2);

                Student student = studentList.get(position);
                text1.setText(student.getName());
                text2.setText(student.getDob());

                return view;
            }
        };
        lvStudents.setAdapter(adapter);
    }

    // Hàm tạo danh sách sinh viên ảo dựa trên classId
    private ArrayList<Student> getStudentsByClassId(String classId) {
        ArrayList<Student> students = new ArrayList<>();
        if (classId.equals("it001")) {
            students.add(new Student("Alice", "01/01/2000"));
            students.add(new Student("Bob", "02/02/2001"));
        } else if (classId.equals("it002")) {
            students.add(new Student("Charlie", "03/03/2002"));
            students.add(new Student("David", "04/04/2003"));
        } else if (classId.equals("it003")) {
            students.add(new Student("Eve", "05/05/2004"));
            students.add(new Student("Frank", "06/06/2005"));
        }
        return students;
    }

    // Lớp chứa thông tin sinh viên
    class Student {
        private String name;
        private String dob;

        public Student(String name, String dob) {
            this.name = name;
            this.dob = dob;
        }

        public String getName() {
            return name;
        }

        public String getDob() {
            return dob;
        }

        @Override
        public String toString() {
            return name + " - " + dob;
        }
    }
}
