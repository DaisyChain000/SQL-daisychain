package com.eyes.sqlitedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.util.Patterns;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.eyes.sqlitedatabase.databinding.ActivityMainBinding;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        DataBaseHelper dataBaseHelper = new DataBaseHelper(this);
        AwesomeValidation awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(this, R.id.mobile, "[0-9]{2}[0-9]{9}$", R.string.invalid_mobile);
        awesomeValidation.addValidation(this, R.id.email, Patterns.EMAIL_ADDRESS, R.string.invalid_email);
        awesomeValidation.addValidation(this, R.id.name, Pattern.compile("[a-zA-Z]{30}"), R.string.invalid_name);
        awesomeValidation.addValidation(this, R.id.id, "[0-9]{2}$", R.string.invalid_id);


        binding.button.setOnClickListener(view -> {
            //if(binding.id.equals("")|binding.name.equals("")|binding.email.equals("")|binding.mobile.equals("")) {
            if (awesomeValidation.validate()) {
                if (dataBaseHelper.saveData(binding.id.getText().toString(), binding.name.getText().toString(), binding.email.getText().toString(), binding.mobile.getText().toString()) == -1) {
                    Toast.makeText(this, "Please Check Error", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(this, "Saved Successfully", Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(this, "For Validation Successful", Toast.LENGTH_SHORT).show();
                // Toast.makeText(this, "Please Fill in the blank", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Validation failed", Toast.LENGTH_SHORT).show();
//                if (dataBaseHelper.saveData(binding.id.getText().toString(), binding.name.getText().toString(), binding.email.getText().toString(), binding.mobile.getText().toString()) == -1) {
//                    Toast.makeText(this, "Please Check Error", Toast.LENGTH_SHORT).show();
//
//                } else {
//                    Toast.makeText(this, "Saved Successfully", Toast.LENGTH_SHORT).show();
//                }


            }
        });


    }
}