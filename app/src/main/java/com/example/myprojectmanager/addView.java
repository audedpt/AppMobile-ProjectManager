package com.example.myprojectmanager;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class addView extends AppCompatActivity {
    TextView addtxt;
    //  Intent MyIntent;
    EditText dateinp;
    EditText durationinp;
    EditText taskinp;
    //  private SQLiteDatabase db;
    DataBaseHelper myDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addview);

        addtxt= findViewById(R.id.addtxt);
        // MyIntent = getIntent();
        dateinp = findViewById(R.id.dateinp);
        durationinp = findViewById(R.id.durationinp);
        taskinp = findViewById(R.id.taskinp);
        Button added =(Button) findViewById(R.id.added);
        Button back =(Button) findViewById(R.id.backbtn);
        myDB = new DataBaseHelper(this);


        added.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newdate = dateinp.getText().toString();
                String newduration = durationinp.getText().toString();
                String newtask = taskinp.getText().toString();

                if (dateinp.length() != 0 && durationinp.length() !=0 && taskinp.length() !=0) {
                    AddData(newdate, newduration, newtask);
                    dateinp.setText("");
                } else {
                    Toast.makeText(addView.this, "You must put something in the text fields!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void AddData(String newdate, String newduration, String newtask) {
        boolean insertData = myDB.addData(newdate, newduration, newtask);

        if (insertData == true) {
            taskinp.setText("");
            dateinp.setText("");
            durationinp.setText("");
            Toast.makeText(addView.this, "Data successfully inserted!", Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(addView.this, "Something went wrong :( ", Toast.LENGTH_LONG).show();
        }
    }
    public void back (View view){
        Intent Intent = new Intent(addView.this,listView.class);
        startActivityForResult (Intent, 1);
    }



}