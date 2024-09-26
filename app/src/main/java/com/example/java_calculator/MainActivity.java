package com.example.java_calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView ResultTxt, SolutionTxt;
    MaterialButton btnC, btnOpenBracket, btnCloseBracket;
    MaterialButton btnAC, btnDot, btnAdd, btnSub, btnMul, btnDiv, btnEquals;
    MaterialButton btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ResultTxt = findViewById(R.id.result);
        SolutionTxt = findViewById(R.id.solutiontxt);

        btnC = btnId(R.id._button_c);
        btnOpenBracket = btnId(R.id._button_openbracket);
        btnCloseBracket = btnId(R.id._button_closebracket);
        btn0 = btnId(R.id._button_0);
        btn1 = btnId(R.id._button_1);
        btn2 = btnId(R.id._button_2);
        btn3 = btnId(R.id._button_3);
        btn4 = btnId(R.id._button_4);
        btn5 = btnId(R.id._button_5);
        btn6 = btnId(R.id._button_6);
        btn7 = btnId(R.id._button_7);
        btn8 = btnId(R.id._button_8);
        btn9 = btnId(R.id._button_9);
        btnAC = btnId(R.id._button_clear);
        btnDot = btnId(R.id._button_dot);
        btnAdd = btnId(R.id._button_add);
        btnSub = btnId(R.id._button_subtract);
        btnMul = btnId(R.id._button_multiple);
        btnDiv = btnId(R.id._button_divide);
        btnEquals = btnId(R.id._button_equals);
    }

    MaterialButton btnId(int id) {
        MaterialButton btn = findViewById(id);
        btn.setOnClickListener(this);
        return btn;
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonTxt = button.getText().toString();
        String dataConcat = SolutionTxt.getText().toString();

        if (buttonTxt.equals("AC")) {
            SolutionTxt.setText("");
            ResultTxt.setText("0");
            return;
        } else if (buttonTxt.equals("=")) {
            String result = getResult(dataConcat);
            SolutionTxt.setText(result);
            ResultTxt.setText(result);
            return;
        } else if (buttonTxt.equals("C")) {
            if (dataConcat.length() > 0) {
                dataConcat = dataConcat.substring(0, dataConcat.length() - 1);
            }
        } else {
            dataConcat = dataConcat + buttonTxt;
        }

        SolutionTxt.setText(dataConcat);

        String result = getResult(dataConcat);
        if(!result.equals("Error")){
            ResultTxt.setText(result);
        }
    }

    String getResult(String data) {
        try {

            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initSafeStandardObjects();

            Object result = context.evaluateString(scriptable, data, "JavaScript", 1, null).toString();

            if (((String) result).endsWith(".0")) {
                result = ((String) result).replace(".0", "");
            }


            return Context.toString(result);
        } catch (Exception e) {
            return "Error";
        }
    }
}
