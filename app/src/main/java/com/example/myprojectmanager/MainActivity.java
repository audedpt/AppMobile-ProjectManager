package com.example.myprojectmanager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    TextView textView;
    TextView txtload;
    DataBaseHelper myDB;

//    private static final String DB_NAME = "projectManager.db";
//    private static final String TABLE_NAME = "tasks"; //date, durrée, actions réalisées
//    private SQLiteDatabase db;
//    private StringBuilder t_debug;

    //   String sql= "CREATE TABLE IF NOT EXISTS "
    //           + TABLE_NAME
    //           + " (id INTEGER PRIMARY KEY AUTOINCREMENT," +
    //           " date DATE, duration TIME, task TEXT);";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //      db = openOrCreateDatabase(DB_NAME, Context.MODE_PRIVATE, null);
        //    db.execSQL("CREATE TABLE IF NOT EXISTS "
        //              + TABLE_NAME
        //              + " (id INTEGER PRIMARY KEY AUTOINCREMENT," +
        //              " date TEXT, duration TEXT, task TEXT);"); //date DATE, duration TIME, task TEXT
        //      db.close();
        //      Log.i("db ", "cliqueBouton: " + sql);
//        t_debug.append("DB is CREATED. ").append("Path: ").append(DB_NAME).append(" Table: ").append(TABLE_NAME).append("\n");


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView= findViewById(R.id.textView);
        Button addbtn =(Button) findViewById(R.id.addbtn);
        Button listbtn =(Button) findViewById(R.id.listbtn);
        myDB = new DataBaseHelper(this);


    }

    //   @Override
    //   public void onActivityResult(int code,int result,Intent Data) {
    //       super.onActivityResult(code, result, Data);
//
//        Toast.makeText(MainActivity.this, "whah", Toast.LENGTH_LONG);
//        if (code == 1 && result == Activity.RESULT_OK) {
//            String EXTRA1 = "EXTRA1";
//            String oui = Data.getStringExtra(EXTRA1);
//            txtload.setText(oui);
    //Toast.makeText(MainActivity.this,"whah",Toast.LENGTH_LONG);
//        }
//    }

    public void addView (View view){
        Intent Intent1 = new Intent(MainActivity.this,addView.class);
        startActivityForResult (Intent1, 1);
    }


    public void listView (View view){
        Intent Intent4 = new Intent(MainActivity.this,listView.class);
        startActivityForResult (Intent4, 4);
    }
}
