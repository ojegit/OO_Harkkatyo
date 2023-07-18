package com.example.oo_harjoitustyo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oo_harjoitustyo.lutemons.Black;
import com.example.oo_harjoitustyo.lutemons.Green;
import com.example.oo_harjoitustyo.lutemons.Orange;
import com.example.oo_harjoitustyo.lutemons.Pink;
import com.example.oo_harjoitustyo.lutemons.White;

public class AddLutemon extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lutemon);

        //add button functionality
        findViewById(R.id.btnAddNewLutemon).setOnClickListener(view -> {

            Lutemon lutemon = null;
            RadioGroup rg = findViewById(R.id.rgLutemonColor);
            EditText et = findViewById(R.id.etName);
            String name = String.valueOf(et.getText());

            //check radiobutton
            switch (rg.getCheckedRadioButtonId()) {
                case R.id.rbWhite:
                    lutemon = new White(name);
                    break;
                case R.id.rbGreen:
                    lutemon = new Green(name);
                    break;
                case R.id.rbPink:
                    lutemon = new Pink(name);
                    break;
                case R.id.rbOrange:
                    lutemon = new Orange(name);
                    break;
                case R.id.rbBlack:
                    lutemon = new Black(name);
                    break;
            }

            System.out.println("Lutemon counter: "+ Lutemon.getIdCounter());

            //add new object
            Storage.getInstance().addLutemon(lutemon);


            String msg = lutemon.getColor()+
                    " Lutemon named '"
                    +lutemon.getName()+
                    "' generated.";

            //announce transfer
            Toast.makeText(view.getContext(), msg, Toast.LENGTH_SHORT).show();
            System.out.println(msg);

            //clear edits
            et.setText("");
            rg.clearCheck();
        });
    }




}