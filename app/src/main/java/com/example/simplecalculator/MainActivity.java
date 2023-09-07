package com.example.simplecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;// ??
import org.mozilla.javascript.Scriptable;// ??

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView result_tv, solution_tv;
    MaterialButton buttonC;
    MaterialButton buttonplusminus, buttonparcentage, buttondivide, buttonmultiply, buttonminus, buttonplus, buttonequals;
    MaterialButton button0, button1, button2, button3, button4, button5, button6, button7, button8, button9, buttonAC;

    MaterialButton buttonpoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // assigning id to result_textView
        result_tv = findViewById(R.id.result_textView);
        solution_tv = findViewById(R.id.solution_textView);


        assignId(buttonC, R.id.button_c);// ??
        assignId(buttonplusminus, R.id.button_plusminus);
        assignId(buttonparcentage, R.id.button_percentage);
        assignId(buttondivide, R.id.button_divide);
        assignId(button7, R.id.button_7);
        assignId(button8, R.id.button_8);
        assignId(button9, R.id.button_9);
        assignId(buttonmultiply, R.id.button_multiply);
        assignId(button4, R.id.button_4);
        assignId(button5, R.id.button_5);
        assignId(button6, R.id.button_6);
        assignId(buttonminus, R.id.button_minus);
        assignId(button1, R.id.button_1);
        assignId(button2, R.id.button_2);
        assignId(button3, R.id.button_3);
        assignId(buttonplus, R.id.button_plus);
        assignId(buttonAC,R.id.button_ac);
        assignId(button0, R.id.button_zero);
        assignId(buttonpoint, R.id.button_point);
        assignId(buttonequals, R.id.button_equals);
    }


    void assignId(MaterialButton btn, int id) {
        btn = findViewById(id);
        btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view){

    // we will take the button first
    MaterialButton button = (MaterialButton) view;// syntax ??
    // extracting the text first.it will get the button whatever the button is clicked
    String buttonText = button.getText().toString();// same
        //result_tv.setText(buttonText);// if we click a button,it will show on the screen
    String dataToCalculate = solution_tv.getText().toString();// link things together: 9 + 3 =

        if(buttonText.equals("AC")) {
        solution_tv.setText("");
        result_tv.setText("0");
        return;
    }

        if(buttonText.equals("=")){ // ?
        solution_tv.setText(result_tv.getText());
        return;
    }

        if(buttonText.equals("C")) {
            dataToCalculate = dataToCalculate.substring(0,dataToCalculate.length()-1);
        }

        else {
        dataToCalculate = dataToCalculate + buttonText;

    }

        solution_tv.setText(dataToCalculate);

        String finalResult = getResult(dataToCalculate);

        if(!finalResult.equals("ERROR"))
        {
            result_tv.setText(finalResult);
        }
}
    String getResult(String data)
    {
        try {
            Context contex = Context.enter();
            contex.setOptimizationLevel(-1);
            Scriptable scriptable = contex.initStandardObjects();
            String finalResult = contex.evaluateString(scriptable, data, "Javascript", 1, null).toString();

            if(finalResult.endsWith(".0"))
            {
                finalResult = finalResult.replace(".0","");
            }
            return finalResult;
        }
        catch(Exception e)
        {
            return "ERROR";
        }




    }

}







