package com.example.myprojectmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    TextView textView;
    TextView txtload;
    DataBaseHelper myDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView= findViewById(R.id.textView);
        Button addbtn =(Button) findViewById(R.id.addbtn);
        Button listbtn =(Button) findViewById(R.id.listbtn);
        myDB = new DataBaseHelper(this);


    }

    public void addView (View view){
        Intent Intent1 = new Intent(MainActivity.this,addView.class);
        startActivityForResult (Intent1, 1);
    }


    public void listView (View view){
        Intent Intent4 = new Intent(MainActivity.this,listView.class);
        startActivityForResult (Intent4, 4);
    }
}
