package com.example.shubham.todoapp;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {


    DatabaseHelper mydb;
     EditText aitem;
    Button abtn;
     ListView userlist;
     ArrayList<String> arrlist;
    ArrayAdapter<String> adapt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        arrlist=new ArrayList<String>();



        mydb=new DatabaseHelper(this);
        aitem=(EditText)findViewById(R.id.aitem);
        abtn=(Button)findViewById(R.id.abtn);
        userlist=(ListView) findViewById(R.id.user_list);

         viewdata();

         abtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 String name=aitem.getText().toString();
                 if(!(name ==("")) && mydb.insertdata(name,Main2Activity.this)){
                     Toast.makeText(Main2Activity.this,"data added",Toast.LENGTH_LONG);

                     viewdata();
                     aitem.setText("");
                 }
                 else{
                     Toast.makeText(Main2Activity.this,"data not added",Toast.LENGTH_LONG);
                 }
             }
         });








userlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String text=userlist.getItemAtPosition(1).toString();

    }
});






    }

    private void viewdata() {
        Cursor cursor=mydb.viewdata();
        if(cursor.getCount()==0){
            Toast.makeText(Main2Activity.this,"Empty ",Toast.LENGTH_LONG);

        }else {
            while (cursor.moveToNext()){
                arrlist.add(cursor.getString(1));

            }
            adapt=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1);
            userlist.setAdapter(adapt);
        }

    }

    /**
     * takind ddata as input and passing it
     */





}
