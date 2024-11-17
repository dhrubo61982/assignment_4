package com.cscorner.project;

import android.os.Bundle;
import android.widget.ExpandableListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ExpandableListView expandableListView;
    private ExpandableListAdapter expandableListAdapter;
    private List<String> listGroup;
    private HashMap<String, List<String>> listItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        expandableListView = findViewById(R.id.expandableListView);
        listGroup = new ArrayList<>();
        listItem = new HashMap<>();
        expandableListAdapter = new ExpandableListAdapter(this, listGroup, listItem);
        expandableListView.setAdapter(expandableListAdapter);

        initData();

        expandableListView.setOnGroupExpandListener(groupPosition ->
                // Handle group expand event
                System.out.println("Group Expanded: " + listGroup.get(groupPosition))
        );

        expandableListView.setOnChildClickListener((parent, v, groupPosition, childPosition, id) -> {
            // Handle child click event
            System.out.println("Child Clicked: " + listItem.get(listGroup.get(groupPosition)).get(childPosition));
            return true;
        });
    }

    private void initData() {
        // Adding group data
        listGroup.add("Fruits");
        listGroup.add("Vegetables");
        listGroup.add("Dairy");

        // Adding child data
        List<String> fruits = new ArrayList<>();
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Mango");

        List<String> vegetables = new ArrayList<>();
        vegetables.add("Carrot");
        vegetables.add("Broccoli");
        vegetables.add("Spinach");

        List<String> dairy = new ArrayList<>();
        dairy.add("Milk");
        dairy.add("Cheese");
        dairy.add("Yogurt");

        listItem.put(listGroup.get(0), fruits);
        listItem.put(listGroup.get(1), vegetables);
        listItem.put(listGroup.get(2), dairy);

        expandableListAdapter.notifyDataSetChanged();
    }
}