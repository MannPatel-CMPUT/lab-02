package com.example.listcity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;

    Button Add_City, Delete_City;

    EditText city_input;

    Button Confirm_city;

    int index =-1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        cityList = findViewById(R.id.city_list);
        Add_City = findViewById(R.id.add_city);
        Delete_City = findViewById(R.id.delete_city);
        city_input = findViewById(R.id.input);
        Confirm_city = findViewById(R.id.confirm_city);

        String []cities = {""};

        dataList = new ArrayList<>();

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, R.id.content_view, dataList);
        cityList.setAdapter(cityAdapter);


        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                index = position;
                Toast.makeText(MainActivity.this,
                        "Selected:"+ dataList.get(position),
                        Toast.LENGTH_SHORT).show();
            }


        });



        Add_City.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_Selected_City();

            }
        });
        Delete_City.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete_Selected_City();

            }
        });
        Confirm_city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_Selected_City();

            }
        });



        }
    private void delete_Selected_City(){
        if (index ==-1){
            Toast.makeText(this,
                    "Tap on a city to select it",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        dataList.remove(index);
        cityAdapter.notifyDataSetChanged();
        index = -1;

    }
    private void add_Selected_City(){
        String newCity = city_input.getText().toString().trim();

        if (newCity.isEmpty()){
            Toast.makeText(this,
                    "Please Enter a City Name",
                    Toast.LENGTH_LONG).show();
            return;
        }
        String normalized = newCity.toLowerCase();

        ArrayList<String> normalizedList = new ArrayList<>();
        for (String existing_city: dataList){
            normalizedList.add(existing_city.toLowerCase());
        }
        if (normalizedList.contains(normalized)){
            Toast.makeText(this, "City Already Exist", Toast.LENGTH_SHORT).show();
            city_input.setText("");
            return;

        }

        dataList.add(newCity);
        cityAdapter.notifyDataSetChanged();
        city_input.setText("");

    }
}