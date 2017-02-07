package com.ochoa.arnau.swissknife.Calculator;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ochoa.arnau.swissknife.R;

public class CalculatorActivity extends AppCompatActivity implements View.OnClickListener {

    Button button_1, button_2, button_3, button_4, button_5, button_6, button_7, button_8, button_9, button_0, button_point, button_equals;
    TextView operationsText;

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

        operationsText = (TextView) findViewById(R.id.textView);

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
        Intent intent;
        switch (item.getItemId()) {
            case R.id.call:
                intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:123456789"));
                startActivity(intent);
                return true;
            case R.id.explorer:
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://sites.google.com/jediupc.com/andqt2017tardes"));
            case R.id.toast:
                editor.putString("notifications", "toast");
                editor.apply();
                return true;
            case R.id.status_bar:
                editor.putString("notifications", "status");
                editor.apply();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_1:
                b=b*10+1;
                operationsText.setText(String.valueOf(b));
                break;
            case R.id.button_2:
                b=b*10+2;
                operationsText.setText(String.valueOf(b));
                break;
            case R.id.button_3:
                b=b*10+3;
                operationsText.setText(String.valueOf(b));
                break;
            case R.id.button_4:
                b=b*10+4;
                operationsText.setText(String.valueOf(b));
                break;
            case R.id.button_5:
                b=b*10+5;
                operationsText.setText(String.valueOf(b));
                break;
            case R.id.button_6:
                b=b*10+6;
                operationsText.setText(String.valueOf(b));
                break;
            case R.id.button_7:
                b=b*10+7;
                operationsText.setText(String.valueOf(b));
                break;
            case R.id.button_8:
                b=b*10+8;
                operationsText.setText(String.valueOf(b));
                break;
            case R.id.button_9:
                b=b*10+9;
                operationsText.setText(String.valueOf(b));
                break;
            case R.id.button_0:
                b=b*10;
                operationsText.setText(String.valueOf(b));
                break;
            case R.id.button_point:
                break;
            case R.id.button_equals:
                switch (operation){
                    case "div":
                        if(b == 0){
                            if (a==0){
                                notification(getString(R.string.division_0_by_0));
                            }else{
                                notification(getString(R.string.division_0 ));
                            }
                        }else{
                            res=a/b;
                        }
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
                a = 0;
                b = res;
                operationsText.setText(String.valueOf(res));
                break;
            case R.id.button_CE:
                a=b=0;
                operationsText.setText("");
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

    private void notification(String string) {
        String notifications = settings.getString("notifications", "error");

        if (notifications.equals("toast")){

            Toast.makeText(getApplicationContext(), string, Toast.LENGTH_SHORT).show();

        }else if (notifications.equals("status")){

            int mId = 2;
            NotificationManager mNotificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            NotificationCompat.Builder mBuilder =
                    new NotificationCompat.Builder(getApplicationContext())
                            .setSmallIcon(R.drawable.app_logo)
                            .setContentTitle(getString(R.string.app_name))
                            .setContentText(string);

            Intent resultIntent = new Intent(getApplicationContext(), CalculatorActivity.class);

            TaskStackBuilder stackBuilder2 = TaskStackBuilder.create(getApplicationContext());
            stackBuilder2.addParentStack(CalculatorActivity.class);
            stackBuilder2.addNextIntent(resultIntent);

            PendingIntent resultPendingIntent =
                    stackBuilder2.getPendingIntent(
                            1,
                            PendingIntent.FLAG_UPDATE_CURRENT
                    );
            mBuilder.setContentIntent(resultPendingIntent);

            Notification noti = mBuilder.build();

            long [] vibrate = {500,110,500,110,450,110,200,110,170,40,450,110,200,110,170,40,500};

            noti.vibrate = vibrate;

            noti.flags |= Notification.FLAG_SHOW_LIGHTS;
            mNotificationManager.notify(mId, noti);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("operations", operationsText.getText().toString());
        outState.putString("operation", operation);
        outState.putFloat("a", a);
        outState.putFloat("b", b);
        outState.putFloat("res", res);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        operationsText.setText(savedInstanceState.getString("operations"));
        operation = savedInstanceState.getString("operation");
        a = savedInstanceState.getFloat("a");
        b = savedInstanceState.getFloat("b");
        res = savedInstanceState.getFloat("res");

    }

//    @Override
//    protected void onStop() {
//        super.onStop();
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        this.finish();
//    }
}
