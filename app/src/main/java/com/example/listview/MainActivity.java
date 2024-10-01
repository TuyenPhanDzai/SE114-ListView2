package com.example.listview;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView lvClasses;
    private ArrayList<ClassRoom> classList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvClasses = findViewById(R.id.lvClasses);

        // Tạo danh sách lớp học (id, className)
        classList = new ArrayList<>();
        classList.add(new ClassRoom("it001", "Class 1"));
        classList.add(new ClassRoom("it002", "Class 2"));
        classList.add(new ClassRoom("it003", "Class 3"));

        // Tạo adapter cho ListView
        ArrayAdapter<ClassRoom> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, classList);
        lvClasses.setAdapter(adapter);

        // Bắt sự kiện khi nhấn vào một lớp học
        lvClasses.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Lấy thông tin lớp học được chọn
                ClassRoom selectedClass = classList.get(position);

                // Chuyển sang Activity2, truyền id của lớp học qua Intent
                Intent intent = new Intent(MainActivity.this, StudentListActivity.class);
                intent.putExtra("classId", selectedClass.getId());
                intent.putExtra("className", selectedClass.getClassName());
                startActivity(intent);
            }
        });
    }

    // Lớp chứa thông tin lớp học
    class ClassRoom {
        private String id;
        private String className;

        public ClassRoom(String id, String className) {
            this.id = id;
            this.className = className;
        }

        public String getId() {
            return id;
        }

        public String getClassName() {
            return className;
        }

        @Override
        public String toString() {
            return id + "  :  " +   className;  // Hiển thị tên lớp học trong ListView
        }
    }
}
