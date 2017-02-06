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

public class HardMemoryActivity extends AppCompatActivity implements View.OnClickListener{

    int pairs = 32;
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
        setContentView(R.layout.activity_hard_memory);

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
        cards[36] = (ImageView) findViewById(R.id.card36);
        cards[37] = (ImageView) findViewById(R.id.card37);
        cards[38] = (ImageView) findViewById(R.id.card38);
        cards[39] = (ImageView) findViewById(R.id.card39);
        cards[40] = (ImageView) findViewById(R.id.card40);
        cards[41] = (ImageView) findViewById(R.id.card41);
        cards[42] = (ImageView) findViewById(R.id.card42);
        cards[43] = (ImageView) findViewById(R.id.card43);
        cards[44] = (ImageView) findViewById(R.id.card44);
        cards[45] = (ImageView) findViewById(R.id.card45);
        cards[46] = (ImageView) findViewById(R.id.card46);
        cards[47] = (ImageView) findViewById(R.id.card47);
        cards[48] = (ImageView) findViewById(R.id.card48);
        cards[49] = (ImageView) findViewById(R.id.card49);
        cards[50] = (ImageView) findViewById(R.id.card50);
        cards[51] = (ImageView) findViewById(R.id.card51);
        cards[52] = (ImageView) findViewById(R.id.card52);
        cards[53] = (ImageView) findViewById(R.id.card53);
        cards[54] = (ImageView) findViewById(R.id.card54);
        cards[55] = (ImageView) findViewById(R.id.card55);
        cards[56] = (ImageView) findViewById(R.id.card56);
        cards[57] = (ImageView) findViewById(R.id.card57);
        cards[58] = (ImageView) findViewById(R.id.card58);
        cards[59] = (ImageView) findViewById(R.id.card59);
        cards[60] = (ImageView) findViewById(R.id.card60);
        cards[61] = (ImageView) findViewById(R.id.card61);
        cards[62] = (ImageView) findViewById(R.id.card62);
        cards[63] = (ImageView) findViewById(R.id.card63);

        for (ImageView cardView : cards){
            cardView.setOnClickListener(this);
        }

        cardBack = R.drawable.ic_black_jack;

        drawables[0] = R.drawable.ic_adb_black_24dp;
        drawables[1] = R.drawable.ic_android_black_24dp;
        drawables[2] = R.drawable.ic_cake_black_24dp;
        drawables[3] = R.drawable.ic_diamonf;
        drawables[4] = R.drawable.ic_directions_boat_black_24dp;
        drawables[5] = R.drawable.ic_gambler;
        drawables[6] = R.drawable.ic_mood_black_24dp;
        drawables[7] = R.drawable.ic_playing_cards;
        drawables[8] = R.drawable.ic_assistant_photo_black_24dp;
        drawables[9] = R.drawable.ic_attach_file_black_24dp;
        drawables[10] = R.drawable.ic_audiotrack_black_24dp;
        drawables[11] = R.drawable.ic_check_box_black_24dp;
        drawables[12] = R.drawable.ic_functions_black_24dp;
        drawables[13] = R.drawable.ic_indeterminate_check_box_black_24dp;
        drawables[14] = R.drawable.ic_local_taxi_black_24dp;
        drawables[15] = R.drawable.ic_photo_size_select_actual_black_24dp;
        drawables[16] = R.drawable.ic_star_border_black_24dp;
        drawables[17] = R.drawable.ic_public_black_24dp;
        drawables[18] = R.drawable.ic_assistant_black_24dp;
        drawables[19] = R.drawable.ic_attach_money_black_24dp;
        drawables[20] = R.drawable.ic_backspace_black_24dp;
        drawables[21] = R.drawable.ic_backup_black_24dp;
        drawables[22] = R.drawable.ic_bluetooth_black_24dp;
        drawables[23] = R.drawable.ic_exposure_black_24dp;
        drawables[24] = R.drawable.ic_landscape_black_24dp;
        drawables[25] = R.drawable.ic_looks_3_black_24dp;
        drawables[26] = R.drawable.ic_looks_4_black_24dp;
        drawables[27] = R.drawable.ic_looks_5_black_24dp;
        drawables[28] = R.drawable.ic_looks_6_black_24dp;
        drawables[29] = R.drawable.ic_looks_one_black_24dp;
        drawables[30] = R.drawable.ic_looks_two_black_24dp;
        drawables[31] = R.drawable.ic_person_pin_circle_black_24dp;


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
            case R.id.card36:
                win = board.select(v,36);
                break;
            case R.id.card37:
                win = board.select(v,37);
                break;
            case R.id.card38:
                win = board.select(v,38);
                break;
            case R.id.card39:
                win = board.select(v,39);
                break;
            case R.id.card40:
                win = board.select(v,40);
                break;
            case R.id.card41:
                win = board.select(v,41);
                break;
            case R.id.card42:
                win = board.select(v,42);
                break;
            case R.id.card43:
                win = board.select(v,43);
                break;
            case R.id.card44:
                win = board.select(v,44);
                break;
            case R.id.card45:
                win = board.select(v,45);
                break;
            case R.id.card46:
                win = board.select(v,46);
                break;
            case R.id.card47:
                win = board.select(v, 47);
                break;
            case R.id.card48:
                win = board.select(v, 48);
                break;
            case R.id.card49:
                win = board.select(v, 49);
                break;
            case R.id.card50:
                win = board.select(v, 50);
                break;
            case R.id.card51:
                win = board.select(v, 51);
                break;
            case R.id.card52:
                win = board.select(v, 52);
                break;
            case R.id.card53:
                win = board.select(v, 53);
                break;
            case R.id.card54:
                win = board.select(v, 54);
                break;
            case R.id.card55:
                win = board.select(v, 55);
                break;
            case R.id.card56:
                win = board.select(v, 56);
                break;
            case R.id.card57:
                win = board.select(v, 57);
                break;
            case R.id.card58:
                win = board.select(v, 58);
                break;
            case R.id.card59:
                win = board.select(v, 59);
                break;
            case R.id.card60:
                win = board.select(v, 60);
                break;
            case R.id.card61:
                win = board.select(v, 61);
                break;
            case R.id.card62:
                win = board.select(v, 62);
                break;
            case R.id.card63:
                win = board.select(v, 63);
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
        valuesToStore.put("level", "hard");
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
