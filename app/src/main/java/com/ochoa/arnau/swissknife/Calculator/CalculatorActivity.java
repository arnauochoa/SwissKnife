package com.ochoa.arnau.swissknife.Calculator;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ochoa.arnau.swissknife.R;

public class CalculatorActivity extends AppCompatActivity implements View.OnClickListener {

    Button button_1, button_2, button_3, button_4, button_5, button_6, button_7, button_8, button_9, button_0, button_point, button_equals;

    String x, t; //prova girar pantalla

    String operation;

    SharedPreferences settings;

    float a = 0, b = 0, res = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        settings = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        button_1 = ((Button) findViewById(R.id.button_1));
        button_1.setOnClickListener(this);

        button_2 = ((Button) findViewById(R.id.button_2));
        button_2.setOnClickListener(this);

        button_3 = ((Button) findViewById(R.id.button_3));
        button_3.setOnClickListener(this);

        button_4 = ((Button) findViewById(R.id.button_4));
        button_4.setOnClickListener(this);

        button_5 = ((Button) findViewById(R.id.button_5));
        button_5.setOnClickListener(this);

        button_6 = ((Button) findViewById(R.id.button_6));
        button_6.setOnClickListener(this);

        button_7 = ((Button) findViewById(R.id.button_7));
        button_7.setOnClickListener(this);

        button_8 = ((Button) findViewById(R.id.button_8));
        button_8.setOnClickListener(this);

        button_9 = ((Button) findViewById(R.id.button_9));
        button_9.setOnClickListener(this);

        button_0 = ((Button) findViewById(R.id.button_0));
        button_0.setOnClickListener(this);

        button_point = ((Button) findViewById(R.id.button_point));
        button_point.setOnClickListener(this);

        button_equals = ((Button) findViewById(R.id.button_equals));
        button_equals.setOnClickListener(this);

        button_equals = ((Button) findViewById(R.id.button_CE));
        button_equals.setOnClickListener(this);

        button_equals = ((Button) findViewById(R.id.button_div));
        button_equals.setOnClickListener(this);

        button_equals = ((Button) findViewById(R.id.button_prod));
        button_equals.setOnClickListener(this);

        button_equals = ((Button) findViewById(R.id.button_minus));
        button_equals.setOnClickListener(this);

        button_equals = ((Button) findViewById(R.id.button_sum));
        button_equals.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.calculator_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        SharedPreferences.Editor editor = settings.edit();
        switch (item.getItemId()) {
            case R.id.call:
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:123456789"));
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
//                       public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                                                              int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                }
                startActivity(intent);
                return true;
            case R.id.toast:
                editor.putString("notifications", "toast");
                editor.apply();
                return true;
            case R.id.status_bar:
                editor.putString("notifications", "toast");
                editor.apply();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        TextView text = (TextView) findViewById(R.id.textView);
        switch (v.getId()){
            case R.id.button_1:
                b=b*10+1;
                text.setText(String.valueOf(b));
                break;
            case R.id.button_2:
                b=b*10+2;
                text.setText(String.valueOf(b));
                break;
            case R.id.button_3:
                b=b*10+3;
                text.setText(String.valueOf(b));
                break;
            case R.id.button_4:
                b=b*10+4;
                text.setText(String.valueOf(b));
                break;
            case R.id.button_5:
                b=b*10+5;
                text.setText(String.valueOf(b));
                break;
            case R.id.button_6:
                b=b*10+6;
                text.setText(String.valueOf(b));
                break;
            case R.id.button_7:
                b=b*10+7;
                text.setText(String.valueOf(b));
                break;
            case R.id.button_8:
                b=b*10+8;
                text.setText(String.valueOf(b));
                break;
            case R.id.button_9:
                b=b*10+9;
                text.setText(String.valueOf(b));
                break;
            case R.id.button_0:
                b=b*10;
                text.setText(String.valueOf(b));
                break;
            case R.id.button_point:
                //TODO ver como hacer decimales
                break;
            case R.id.button_equals:
                switch (operation){
                    case "div":
                        res=a/b;
                        break;
                    case "prod":
                        res=a*b;
                        break;
                    case "minus":
                        res=a-b;
                        break;
                    case "sum":
                        res=a+b;
                }
                a=b=0;
                text.setText(String.valueOf(res));
                break;
            case R.id.button_CE:
                a=b=0;
                text.setText("");
                //TODO split para separar entre coma
                break;
            case R.id.button_div:
                a=b;
                b=0;
                operation= "div";
                break;
            case R.id.button_prod:
                a=b;
                b=0;
                operation="prod";
                break;
            case R.id.button_minus:
                a=b;
                b=0;
                operation="minus";
                break;
            case R.id.button_sum:
                a=b;
                b=0;
                operation="sum";
                break;
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("test", x);
        Log.v("Test","onSaveInstanceState: " + x);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        t = savedInstanceState.getString("test");
        Log.v("Test","onRestoreInstanceState: " + t);
    }
}
