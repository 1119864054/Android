package com.example.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private static double num1;
    private static double num2;
    private static char operator;

    double calculate(double num1, double num2, char operator) {
        switch (operator) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
            case '/':
                return num1 / num2;
            case '%':
                return num1 / (num2 * 100);
        }
        ;
        return 0;
    }

    public void click(View v) {
        // TODO Auto-generated method stub
        TextView result = (TextView) findViewById(R.id.result);
        TextView log = (TextView) findViewById(R.id.log);
        log.setMovementMethod(new ScrollingMovementMethod());
        String t, n1, n2, l;
        int id = v.getId();
        switch (id) {
            case R.id.b1:
                t = result.getText().toString();
                result.setText(t + "1");
                break;
            case R.id.b2:
                t = result.getText().toString();
                result.setText(t + "2");
                break;
            case R.id.b3:
                t = result.getText().toString();
                result.setText(t + "3");
                break;
            case R.id.b4:
                t = result.getText().toString();
                result.setText(t + "4");
                break;
            case R.id.b5:
                t = result.getText().toString();
                result.setText(t + "5");
                break;
            case R.id.b6:
                t = result.getText().toString();
                result.setText(t + "6");
                break;
            case R.id.b7:
                t = result.getText().toString();
                result.setText(t + "7");
                break;
            case R.id.b8:
                t = result.getText().toString();
                result.setText(t + "8");
                break;
            case R.id.b9:
                t = result.getText().toString();
                result.setText(t + "9");
                break;
            case R.id.b0:
                t = result.getText().toString();
                result.setText(t + "0");
                break;
            case R.id.b00:
                t = result.getText().toString();
                result.setText(t + "00");
                break;
            case R.id.point:
                t = result.getText().toString();
                result.setText(t + ".");
                break;
            case R.id.C:
                result.setText("");
                log.setText("");
                break;
            case R.id.DEL:
                t = result.getText().toString();
                int len = t.length();
                char chars[] = t.toCharArray();
                result.setText("");
                for (int i = 0; i < len - 1; i++) {
                    t = result.getText().toString();
                    result.setText(t + chars[i]);
                }
                break;
            case R.id.add:
                l = log.getText().toString();
                t = result.getText().toString();
                num1 = Double.parseDouble(t);
                operator = '+';
                log.setText(l + t + "+");
                result.setText("");
                break;
            case R.id.plus:
                l = log.getText().toString();
                t = result.getText().toString();
                num1 = Double.parseDouble(t);
                operator = '*';
                log.setText(l + t + "*");
                result.setText("");
                break;
            case R.id.divide:
                l = log.getText().toString();
                t = result.getText().toString();
                num1 = Double.parseDouble(t);
                operator = '/';
                log.setText(l + t + "/");
                result.setText("");
                break;
            case R.id.sub:
                l = log.getText().toString();
                t = result.getText().toString();
                num1 = Double.parseDouble(t);
                operator = '-';
                log.setText(l + t + "-");
                result.setText("");
                break;
            case R.id.percent:
                l = log.getText().toString();
                t = result.getText().toString();
                num1 = Double.parseDouble(t);
                operator = '%';
                log.setText(l + t + "%");
                result.setText("");
                break;
            case R.id.equal:
                l = log.getText().toString();
                t = result.getText().toString();
                if(t!="")
                    num2 = Double.parseDouble(t);
                else
                    num2 = 1.0;
                log.setText("");

//                t = log.getText().toString();
//                int logLen = t.length();
//                char logChars[] = t.toCharArray();

                result.setText(String.valueOf(calculate(num1, num2, operator)));
                break;
            default:
                break;
        }
    }
}
