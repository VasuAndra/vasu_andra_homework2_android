package com.example.tema2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private StudentRepository studentRepository;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        studentRepository = new StudentRepository(getApplicationContext());

        setContentView(R.layout.activity_main);

        Button Btn1 = (Button) findViewById(R.id.Btn1);
        Button Btn2 = (Button) findViewById(R.id.Btn2);



        final EditText Edt1 = (EditText) findViewById(R.id.Edt1);
        final EditText Edt2 = (EditText) findViewById(R.id.Edt2);

        Btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Edt1.getText().toString().isEmpty() || Edt2.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this, "Add a name and a mark!", Toast.LENGTH_LONG).show();
                    return;
                }

                final Student student = new Student();

                student.name = Edt1.getText().toString();
                student.mark = Integer.parseInt(Edt2.getText().toString());



                studentRepository.insertStudentAsync(student, new InsertStudentListener() {
                    @Override
                    public void actionSuccess() {
                        Toast.makeText(MainActivity.this, "Added", Toast.LENGTH_SHORT).show();
                        getData();
                    }

                    @Override
                    public void actionFailed(){
                        Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        Btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = Edt1.getText().toString();
                studentRepository.deleteStudentAsync(name, new DeleteListener(){
                    @Override
                    public void deleteResult (Boolean status) {
                        if(status){
                            Toast.makeText(MainActivity.this, "Removed" , Toast.LENGTH_SHORT).show();
                            getData();
                        }
                        else

                        Toast.makeText(MainActivity.this, "Can't find", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        initRecyclerView();
    }

    private void getData(){
        final MainActivity oldThis = this;
        studentRepository.getStudentsAsync(new GetStudentsListener() {
            @Override
            public void actionSuccess(List<Student> students) {
                StudentAdapter adapter = new StudentAdapter(oldThis, students);
                recyclerView.setAdapter(adapter);

                adapter.notifyDataSetChanged();
            }
        });
    }

    private void initRecyclerView(){
        recyclerView = findViewById(R.id.RV);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        getData();
    }
}
