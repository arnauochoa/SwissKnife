package com.ochoa.arnau.swissknife.Memory;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.material.joanbarroso.flipper.CoolImageFlipper;
import com.ochoa.arnau.swissknife.Data.DatabaseHelper;
import com.ochoa.arnau.swissknife.R;

public class MediumMemoryActivity extends AppCompatActivity implements View.OnClickListener{

    int pairs = 18;
    int attempts;

    boolean win = false;

    Integer cardBack;
    Integer[] drawables = new Integer[pairs];

    CoolImageFlipper flipper;

    ImageView[] cards = new ImageView[2*pairs];

    ImageView restart;
    TextView attemptsView;

    Board board;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medium_memory);

        restart = (ImageView)findViewById(R.id.restart);
        restart.setOnClickListener(this);

        attemptsView = (TextView) findViewById(R.id.attempts);

        cards[0] = (ImageView) findViewById(R.id.card0);
        cards[1] = (ImageView) findViewById(R.id.card1);
        cards[2] = (ImageView) findViewById(R.id.card4);
        cards[3] = (ImageView) findViewById(R.id.card5);
        cards[4] = (ImageView) findViewById(R.id.card2);
        cards[5] = (ImageView) findViewById(R.id.card3);
        cards[6] = (ImageView) findViewById(R.id.card6);
        cards[7] = (ImageView) findViewById(R.id.card7);
        cards[8] = (ImageView) findViewById(R.id.card8);
        cards[9] = (ImageView) findViewById(R.id.card9);
        cards[10] = (ImageView) findViewById(R.id.card10);
        cards[11] = (ImageView) findViewById(R.id.card11);
        cards[12] = (ImageView) findViewById(R.id.card12);
        cards[13] = (ImageView) findViewById(R.id.card13);
        cards[14] = (ImageView) findViewById(R.id.card14);
        cards[15] = (ImageView) findViewById(R.id.card15);
        cards[16] = (ImageView) findViewById(R.id.card16);
        cards[17] = (ImageView) findViewById(R.id.card17);
        cards[18] = (ImageView) findViewById(R.id.card18);
        cards[19] = (ImageView) findViewById(R.id.card19);
        cards[20] = (ImageView) findViewById(R.id.card20);
        cards[21] = (ImageView) findViewById(R.id.card21);
        cards[22] = (ImageView) findViewById(R.id.card22);
        cards[23] = (ImageView) findViewById(R.id.card23);
        cards[24] = (ImageView) findViewById(R.id.card24);
        cards[25] = (ImageView) findViewById(R.id.card25);
        cards[26] = (ImageView) findViewById(R.id.card26);
        cards[27] = (ImageView) findViewById(R.id.card27);
        cards[28] = (ImageView) findViewById(R.id.card28);
        cards[29] = (ImageView) findViewById(R.id.card29);
        cards[30] = (ImageView) findViewById(R.id.card30);
        cards[31] = (ImageView) findViewById(R.id.card31);
        cards[32] = (ImageView) findViewById(R.id.card32);
        cards[33] = (ImageView) findViewById(R.id.card33);
        cards[34] = (ImageView) findViewById(R.id.card34);
        cards[35] = (ImageView) findViewById(R.id.card35);

        for (ImageView cardView : cards){
            cardView.setOnClickListener(this);
        }

        cardBack = R.drawable.app_logo;

        drawables[0] = R.drawable.ic_angry;
        drawables[1] = R.drawable.ic_angry_1;
        drawables[2] = R.drawable.ic_bored;
        drawables[3] = R.drawable.ic_bored_1;
        drawables[4] = R.drawable.ic_bored_2;
        drawables[5] = R.drawable.ic_confused;
        drawables[6] = R.drawable.ic_confused_1;
        drawables[7] = R.drawable.ic_crying;
        drawables[8] = R.drawable.ic_crying_1;
        drawables[9] = R.drawable.ic_embarrassed;
        drawables[10] = R.drawable.ic_emoticons;
        drawables[11] = R.drawable.ic_happy;
        drawables[12] = R.drawable.ic_happy_1;
        drawables[13] = R.drawable.ic_happy_2;
        drawables[14] = R.drawable.ic_ill;
        drawables[15] = R.drawable.ic_in_love;
        drawables[16] = R.drawable.ic_kissing;
        drawables[17] = R.drawable.ic_mad;


        flipper = new CoolImageFlipper(this);

        board = new Board(getResources(), drawables, cardBack, flipper, attemptsView);
    }

    public void onClick (View v) {
        switch (v.getId()){
            case R.id.card0:
                win = board.select(v, 0);
                break;
            case R.id.card1:
                win = board.select(v, 1);
                break;
            case R.id.card2:
                win = board.select(v, 2);
                break;
            case R.id.card3:
                win = board.select(v, 3);
                break;
            case R.id.card4:
                win = board.select(v, 4);
                break;
            case R.id.card5:
                win = board.select(v, 5);
                break;
            case R.id.card6:
                win = board.select(v, 6);
                break;
            case R.id.card7:
                win = board.select(v, 7);
                break;
            case R.id.card8:
                win = board.select(v, 8);
                break;
            case R.id.card9:
                win = board.select(v, 9);
                break;
            case R.id.card10:
                win = board.select(v, 10);
                break;
            case R.id.card11:
                win = board.select(v, 11);
                break;
            case R.id.card12:
                win = board.select(v, 12);
                break;
            case R.id.card13:
                win = board.select(v, 13);
                break;
            case R.id.card14:
                win = board.select(v, 14);
                break;
            case R.id.card15:
                win = board.select(v, 15);
                break;
            case R.id.card16:
                win = board.select(v, 16);
                break;
            case R.id.card17:
                win = board.select(v, 17);
                break;
            case R.id.card18:
                win = board.select(v, 18);
                break;
            case R.id.card19:
                win = board.select(v, 19);
                break;
            case R.id.card20:
                win = board.select(v, 20);
                break;
            case R.id.card21:
                win = board.select(v, 21);
                break;
            case R.id.card22:
                win = board.select(v, 22);
                break;
            case R.id.card23:
                win = board.select(v, 23);
                break;
            case R.id.card24:
                win = board.select(v, 24);
                break;
            case R.id.card25:
                win = board.select(v, 25);
                break;
            case R.id.card26:
                win = board.select(v, 26);
                break;
            case R.id.card27:
                win = board.select(v, 27);
                break;
            case R.id.card28:
                win = board.select(v, 28);
                break;
            case R.id.card29:
                win = board.select(v, 29);
                break;
            case R.id.card30:
                win = board.select(v, 30);
                break;
            case R.id.card31:
                win = board.select(v, 31);
                break;
            case R.id.card32:
                win = board.select(v, 32);
                break;
            case R.id.card33:
                win = board.select(v, 33);
                break;
            case R.id.card34:
                win = board.select(v, 34);
                break;
            case R.id.card35:
                win = board.select(v, 35);
                break;
            case R.id.restart:
                restart();
                break;
        }

        if (win){
            attempts = board.getAttempts();
            Toast.makeText(getApplicationContext(), "Congratulations! You did it in " + String.valueOf(attempts) + " attempts", Toast.LENGTH_LONG).show();
            Log.d("WIN","CONGRATULATIONS!!");
            saveScore(attempts);
        }
    }

    private void saveScore(int attempts) {
        DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());

        SharedPreferences settings = getSharedPreferences(String.valueOf(R.string.app_name), Context.MODE_PRIVATE);
        String username = settings.getString("username", "Username not found");

        ContentValues valuesToStore = new ContentValues();
        valuesToStore.put("name", username);
        valuesToStore.put("level", "medium");
        valuesToStore.put("score", attempts);
        databaseHelper.addScore(valuesToStore);
    }

    private void restart() {
        board.hideCards(cards);
        board = new Board(getResources(), drawables, cardBack, flipper, attemptsView);
        win = false;
        attempts = 0;
    }
}
