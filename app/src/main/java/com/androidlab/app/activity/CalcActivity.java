package com.androidlab.app.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.androidlab.app.R;
import com.androidlab.app.constant.Operation;

import java.util.Arrays;
import java.util.List;

public class CalcActivity extends Activity {
    private TextView resultTextView;

    private static String firstNumber;
    private static String secondNumber;
    private static Operation operation;
    private static boolean operationClicked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.calc_layout);
        resultTextView = (TextView) findViewById(R.id.calcResultTextView);

        operation = Operation.NONE;
        operationClicked = false;
        secondNumber = "";

    }

    public void onNumberClick(View view) {
        if(!secondNumber.equals("")){
            onClrClick(view);
        }

        if(operationClicked){
            resultTextView.setText("");
            operationClicked = false;
        }

        String resultViewText = resultTextView.getText().toString();

        if ("0".equals(resultViewText)) {
            if("0".equals(((Button)view).getText().toString())){
                resultViewText = "0";
            }
            resultViewText = "";
        }

        if (resultViewText.length() < 9) {
            resultTextView.setText(resultViewText + ((Button) view).getText());
        }
    }

    public void onClrClick(View view) {
        resultTextView.setText("0");
        operationClicked = false;
        operation = Operation.NONE;
        secondNumber ="";
        firstNumber ="";
    }

    public void onOperationClick(View view) {
        String resultViewText = resultTextView.getText().toString();

        switch (view.getId()) {
            case R.id.operationDivisionButton:
                operation = Operation.DIV;
                break;
            case R.id.operationMinusButton:
                operation = Operation.MINUS;
                break;
            case R.id.operationPlusButton:
                operation = Operation.PLUS;
                break;
            case R.id.operationMulButton:
                operation = Operation.MUL;
                break;
        }

        colorOperationButtons(Color.BLACK);
        ((Button)view).setTextColor(Color.RED);

        firstNumber = resultViewText;
        secondNumber = "";
        operationClicked = true;
    }

    public void onResultClick(View view){
        if(!operation.equals(Operation.NONE)) {
            calc();
        }
    }

    private void calc(){
        double value;
        double first = Double.valueOf(firstNumber);

        if(secondNumber.isEmpty()) {
            secondNumber = resultTextView.getText().toString();
        }
        double second = Double.valueOf(secondNumber);

        if (operation == Operation.MINUS) {
            value = first - second;
        } else if (operation == Operation.PLUS) {
            value = first + second;
        } else if (operation == Operation.MUL) {
            value = first * second;
        } else {
            value = first / second;
        }

        colorOperationButtons(Color.BLACK);
        firstNumber = String.valueOf(value);
        resultTextView.setText(String.valueOf(value));
    }

    private void colorOperationButtons(int color){
        List<View> operationButtons = Arrays.asList(findViewById(R.id.operationDivisionButton),
                findViewById(R.id.operationMinusButton),
                findViewById(R.id.operationPlusButton),
                findViewById(R.id.operationMulButton));
        for(View view : operationButtons){
            ((Button)view).setTextColor(color);
        }
    }
}
