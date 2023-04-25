package com.example.algorytmy_1_0_e_aj;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText inputText;
    private TextView outputText;
    private Button calculateButton;
    private DecimalFormat decimalFormat = new DecimalFormat("#.###");

    private List<String> units = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputText = findViewById(R.id.input_text);
        outputText = findViewById(R.id.output_text);
        calculateButton = findViewById(R.id.calculate_button);

        units.add("b");
        units.add("B");
        units.add("KB");
        units.add("MB");
        units.add("GB");
        units.add("TB");
        units.add("PB");
        units.add("EB");
        units.add("ZB");
        units.add("YB");

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = inputText.getText().toString();

                if (!TextUtils.isEmpty(input)) {
                    double value = Double.parseDouble(input);
                    int inputUnitIndex = ((Spinner) findViewById(R.id.input_spinner)).getSelectedItemPosition();
                    int outputUnitIndex = ((Spinner) findViewById(R.id.output_spinner)).getSelectedItemPosition();
                    int exponent = outputUnitIndex - inputUnitIndex;

                    if (exponent >= 0) {
                        while (exponent > 0) {
                            value /= 1024;
                            exponent--;
                        }
                    } else {
                        while (exponent < 0) {
                            value *= 1024;
                            exponent++;
                        }
                    }

                    String output = decimalFormat.format(value) + " " + units.get(outputUnitIndex);

                    outputText.setText(output);
                } else {
                    outputText.setText("");
                }
            }
        });
    }
}