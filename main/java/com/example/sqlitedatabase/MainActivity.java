package com.example.sqlitedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText age, Name;
    Button add, display;

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch switchOff;
    ListView listView;
    ArrayList<StudentModel> arrayList;
    DBopenHelper db;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        age = findViewById(R.id.age);
        Name = findViewById(R.id.name);
        add = findViewById(R.id.add);
        display = findViewById(R.id.display);
        listView = findViewById(R.id.listView);
        db = new DBopenHelper(this);
        arrayList = new ArrayList<>();


        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    LoadDataOnlistView();

            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (!Name.getText().toString().trim().isEmpty() && !age.getText().toString().trim().isEmpty()) {

                    if (Integer.parseInt(age.getText().toString()) <= 0) {
                        Toast.makeText(MainActivity.this, "age should be greater tha 0", Toast.LENGTH_SHORT).show();
                    } else {
                        boolean insert = db.insertData(Name.getText().toString(), Integer.parseInt(age.getText().toString()));

                        if (insert) {
                            Toast.makeText(MainActivity.this, "inserted", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "not success", Toast.LENGTH_SHORT).show();
                        }
                    }

                } else {
                    Toast.makeText(MainActivity.this, "all fields are required", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void LoadDataOnlistView() {
       arrayList= db.getAllData();
        adapter= new MyAdapter(this,arrayList);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


//    private void LoadDataOnlistView() throws ClassNotFoundException, SQLException {
//
//        String url = "jdbc:mysql://localhost:3307/atm_project";
//        String uname = "root";
//        String pass = "";
//        String query = "select fristname from user_register where pin=1111";
//
//
//        Class.forName("com.mysql.jdbc.Driver");
//        Connection connection = DriverManager.getConnection(url, uname, pass);
//        Statement statement = connection.createStatement();
//        ResultSet resultSet = statement.executeQuery(query);
//
//        resultSet.next();
//        String fname = resultSet.getString("fristname");
//        textView.setText(fname);
//
//
//    }

}