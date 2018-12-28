package com.example.shubham.todoapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class additems extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemLongClickListener {


    private EditText itemet;
    private Button   btn;
    private ListView itemslist;
    private ArrayList<String> items;
    private ArrayAdapter<String> adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additems);

        itemet=(EditText)findViewById(R.id.item);
        btn=(Button)findViewById(R.id.addbutton);
        itemslist=(ListView)findViewById(R.id.listitem);

        items=filehelper.readdata(this);
        adapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,items);
        itemslist.setAdapter(adapter);
        itemslist.setOnItemLongClickListener(this);


        btn.setOnClickListener(this);






    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.addbutton:
                String input= itemet.getText().toString();
                if(input!=" "){
                    adapter.add(input);
                    itemet.setText("");
                    filehelper.writedata(items,this);
                    Toast.makeText(this,"item added",Toast.LENGTH_SHORT).show();
                    break;
                }
                else
                {
                    Toast.makeText(this,"please enter item",Toast.LENGTH_SHORT).show();
                    break;
                }


        }

    }



    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        items.remove(position);
        adapter.notifyDataSetChanged();
        filehelper.writedata(items, this);//to make changes after removing so that while exiting the app the list is updated again
        Toast.makeText(this," item deleted",Toast.LENGTH_LONG).show();
        return false;
    }
}
