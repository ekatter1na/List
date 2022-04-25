package com.example.dogs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;


public class MainActivity2 extends AppCompatActivity {
    int dog;
    ArrayList<String> countries = new ArrayList<String>();
    ArrayList<String> Texts= new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        dog = intent.getIntExtra("selectedItem",dog);
        setContentView(R.layout.activity_main);
        TextView text = findViewById(R.id.textView);
        TextView namedtext = findViewById(R.id.namedText);
        try {
            InputStream inputreader = getAssets().open("nameText.txt");
            InputStream inputreaderTexts = getAssets().open("Text.txt");
            Scanner scanner = new Scanner(inputreader);
            Scanner scanner2 = new Scanner(inputreaderTexts);

            while(scanner.hasNextLine())
            {
                countries.add(scanner.nextLine());
                Texts.add(scanner2.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String selectedItem = (String) countries.get(dog);
        String selectedTexts = (String) Texts.get(dog);
        namedtext.setText(selectedItem);
        text.setText(selectedTexts);

    }
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        TextView mainView = findViewById(R.id.textView);
        outState.putString("mainText", String.valueOf(mainView.getText()));
    }
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null && savedInstanceState.containsKey("mainText")) {
            dog = savedInstanceState.getInt("selectedItem");
        }
    }
}