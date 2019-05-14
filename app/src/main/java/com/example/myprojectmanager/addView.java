package com.example.myprojectmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class addView extends AppCompatActivity {
    TextView addtxt;
    EditText dateinp;
    EditText durationinp;
    EditText taskinp;
    DataBaseHelper myDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addview);

        addtxt= findViewById(R.id.addtxt);
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
                    taskinp.setText(""); //clean editText
                    dateinp.setText("");
                    durationinp.setText("");
                } else {
                    Toast.makeText(addView.this, "You must put something in the text fields!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void AddData(String newdate, String newduration, String newtask) {
        boolean insertData = myDB.addData(newdate, newduration, newtask);

        if (insertData == true) {

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