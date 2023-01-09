package com.example.todolist;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText item;
    Button add;
    ListView listView;
    ArrayList<String> itemList = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        item = findViewById(R.id.editText);
        add = findViewById(R.id.button);
        listView = findViewById(R.id.list);

        itemList = FileHelper.readData(this);

        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1
                , android.R.id.text1, itemList);

        listView.setAdapter(arrayAdapter);

        add.setOnClickListener(view -> {

            String itemName = item.getText().toString();
            itemList.add(itemName);
            item.setText("");
            FileHelper.writeData(itemList, getApplicationContext());
            arrayAdapter.notifyDataSetChanged();

        });

        //TODO, fix the issue, removed item stays on the screen unless user clicks again

        listView.setOnItemClickListener((adapterView, view, position, l) -> {

            new AlertDialogFragment().show(getSupportFragmentManager(), AlertDialogFragment.TAG);

            if (AlertDialogFragment.isRemoved) {
                itemList.remove(position-1);
                arrayAdapter.notifyDataSetChanged();
                FileHelper.writeData(itemList, getApplicationContext());
                AlertDialogFragment.isRemoved = false;
            };

        });

    }
}