package com.example.bmiapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.card.MaterialCardView;

public class MainActivity extends AppCompatActivity {

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


        EditText edtWeight = findViewById(R.id.edt_weight);
        EditText edtHeightFt = findViewById(R.id.edt_height_ft);
        EditText edtHeightIn = findViewById(R.id.edt_height_in);
        Button calculate = findViewById(R.id.btn_calculate);
        TextView txtResult = findViewById(R.id.txt_result);
        LinearLayout llMain = findViewById(R.id.main);

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* int wt = Integer.parseInt(edtWeight.getText().toString());
                int heightFt = Integer.parseInt(edtHeightFt.getText().toString());
                int heightIn = Integer.parseInt(edtHeightIn.getText().toString());

                //BMI unit - Kg/m2

                int totalHeight = heightFt * 12 + heightIn;

                double totalCm = totalHeight * 2.54;
                double totalM = totalCm / 100;

                double bmiValue = (wt) / (totalM * totalM);


                if (bmiValue > 25) {
                    txtResult.setText("You're Overweight") ;
                llMain.setBackgroundColor(getResources().getColor(R.color.red));
                }
                else if (bmiValue < 18) {
                    txtResult.setText("You're Underweight");
                    llMain.setBackgroundColor(getResources().getColor(R.color.yellow));
                }
                else {
                    txtResult.setText("You're Healthy");
                    llMain.setBackgroundColor(getResources().getColor(R.color.green));
            }*/

                // Inside your onClick listener
                String weightStr = edtWeight.getText().toString();
                String ftStr = edtHeightFt.getText().toString();
                String inStr = edtHeightIn.getText().toString();

                // Validation: Don't crash if fields are empty!
                if (weightStr.isEmpty() || ftStr.isEmpty() || inStr.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter all values", Toast.LENGTH_SHORT).show();
                    return;
                }

                double wt = Double.parseDouble(weightStr);
                int heightFt = Integer.parseInt(ftStr);
                int heightIn = Integer.parseInt(inStr);

                // Logic remains the same, but update the UI professionally
                int totalHeight = heightFt * 12 + heightIn;
                double totalCm = totalHeight * 2.54;
                double totalM = totalCm / 100;
                double bmiValue = wt / (totalM * totalM);

                MaterialCardView resultCard = findViewById(R.id.result_card);
                resultCard.setVisibility(View.VISIBLE);

                if (bmiValue > 25) {
                    txtResult.setText(String.format("BMI: %.1f\nYou're Overweight", bmiValue));
                    resultCard.setCardBackgroundColor(getResources().getColor(R.color.bmi_overweight));
                } else if (bmiValue < 18.5) {
                    txtResult.setText(String.format("BMI: %.1f\nYou're Underweight", bmiValue));
                    resultCard.setCardBackgroundColor(getResources().getColor(R.color.bmi_underweight));
                } else {
                    txtResult.setText(String.format("BMI: %.1f\nYou're Healthy", bmiValue));
                    resultCard.setCardBackgroundColor(getResources().getColor(R.color.bmi_healthy));
                }

            }
        });



    }
}