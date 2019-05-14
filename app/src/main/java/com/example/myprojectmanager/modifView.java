package com.example.myprojectmanager;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



public class modifView extends AppCompatActivity {
    TextView modiftxt, datetxt, durationtxt, tasktxt;
    EditText dateinp, durationinp, taskinp;
    DataBaseHelper myDB;
    private String selectedtask;
    private String selecteddate;
    private String selectedduration;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modifview);

        modiftxt= findViewById(R.id.modiftxt);
        datetxt= findViewById(R.id.datetxt);
        durationtxt= findViewById(R.id.durationtxt);
        tasktxt= findViewById(R.id.tasktxt);
        dateinp =(EditText) findViewById(R.id.dateinp) ;
        durationinp=(EditText) findViewById(R.id.durationinp) ;
        taskinp =(EditText) findViewById(R.id.taskinp) ;
        Button modify =(Button) findViewById(R.id.modify);
        Button delete =(Button) findViewById(R.id.deleted);
        Button back =(Button) findViewById(R.id.backbtn);
        myDB = new DataBaseHelper(this);


        //get the intent extra from the listDataACtivity
        Intent receivedIntent = getIntent();
        selectedtask=receivedIntent.getStringExtra("task");
        selecteddate=receivedIntent.getStringExtra("date");
        selectedduration=receivedIntent.getStringExtra("duration");

        //set text to show the current selected item
        dateinp.setText(selecteddate);
        durationinp.setText(selectedduration);
        taskinp.setText(selectedtask);

        //check that the new value isn't null
        modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newtask = taskinp.getText().toString();
                String newdate = dateinp.getText().toString();
                String newduration = durationinp.getText().toString();

                if (!newtask.equals("") && !newdate.equals("") && !newduration.equals("")){
                     myDB.updateDate(selectedtask, selectedduration, selecteddate, newdate);
                     myDB.updateDuration(selectedtask, selectedduration, selecteddate, newduration);
                     myDB.updateTask(selectedtask, selectedduration, selecteddate, newtask, newduration, newdate);

                    Toast.makeText(modifView.this, "Data successfully modified!", Toast.LENGTH_LONG).show();

                }else{
                    Toast.makeText(modifView.this, "You must complete the fields! ", Toast.LENGTH_LONG).show();
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDB.deleteTask(selectedtask,selecteddate, selectedduration);
                taskinp.setText("");
                dateinp.setText("");
                durationinp.setText("");
                Toast.makeText(modifView.this, "Removed from database! ", Toast.LENGTH_LONG).show();

            }
        });

    }
    public void back (View view){
        Intent Intent = new Intent(modifView.this,listView.class);
        startActivityForResult (Intent, 1);
    }


}
