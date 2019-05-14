package com.example.myprojectmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.database.Cursor;
import android.widget.Toast;
import java.util.ArrayList;

public class listView extends AppCompatActivity {

    private static final String TAG = "listView";
    TextView listtxt, worktxt, calcultxt;
    DataBaseHelper myDB;
    private ListView myList;
    String durationStr =null;
    int durationInt = 0;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);

        listtxt= findViewById(R.id.listtxt);
        worktxt= findViewById(R.id.worktxt);
        calcultxt= findViewById(R.id.calcultxt);
        myList = (ListView) findViewById(R.id.list_item);
        Button back =(Button) findViewById(R.id.backbtn);
        myDB = new DataBaseHelper( this);

        populateListView();
    }

    private void populateListView(){
        Log.d(TAG, "populateListView: Displaying data in the ListView");

        //get the data and append to a list
        Cursor data = myDB.getData();
        ArrayList<String> dbList = new ArrayList<>();
        if (data.getCount() ==0){
            Toast.makeText(listView.this, "The Database is empty :( ", Toast.LENGTH_LONG).show();
        }else {
            while (data.moveToNext()) {
                //get the value from the database in column 3 (2+1)
                //then add it to the ArrayList
                dbList.add(data.getString(2)); //va juste prendre les task name

                //Count Work Hours
                durationStr=data.getString(1);
                int duration_int=0;
                duration_int = Integer.parseInt(durationStr);
                durationInt = durationInt +duration_int;
                String calculTXT = String.valueOf(durationInt);
                calcultxt.setText(calculTXT);
            }
        }
        //create the list adapter and set the adapter
        ListAdapter listadapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dbList);
        myList.setAdapter(listadapter);

        //set onItemClickListener to the ListView
        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String task = parent.getItemAtPosition(position).toString();
                Log.d(TAG, "onItemClick: You Clicked on " + task);

                //get data associated with that task
                    Cursor date = myDB.getDate(task);
                    String itemdate=null;
                    while(date.moveToNext()) {
                        itemdate = date.getString(0);
                    }
                    Cursor duration = myDB.getDuration(task);
                    String itemduration=null;
                     while ((duration.moveToNext())) {
                        itemduration = duration.getString(0);
                    }
                        Log.d(TAG, "onItemClick: Data found");
                        Intent IntentEdit = new Intent(listView.this,modifView.class);
                        IntentEdit.putExtra("date", itemdate);
                        IntentEdit.putExtra("duration", itemduration);
                        IntentEdit.putExtra("task", task);
                        startActivity(IntentEdit );
            }
        });

    }
    public void back (View view){
        Intent Intent = new Intent(listView.this,MainActivity.class);
        startActivityForResult (Intent, 1);
    }

}

