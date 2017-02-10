package com.ochoa.arnau.swissknife.Memory;

import android.content.res.Resources;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.material.joanbarroso.flipper.CoolImageFlipper;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by arnau on 05/02/2017.
 */

public class Board {
    private int attempts, matchedPairs;
    private boolean isFirst = true;
    private boolean mustWait = true;
    private boolean canClick = true;

    private int pairs;

    private Card[] cards;
    private Card lastCard = new Card(0);
    private Card thisCard = new Card(0);

    private View lastCardView;
    private View thisCardView;

    private CoolImageFlipper flipper;
    private Resources resources;
    TextView attemptsView;

    private Integer cardBack;
    private Integer[] drawables; //Array with all drawables id
    private Integer[] values; //Drawables after duplicate values and shuffle


    public Board(Resources resources, Integer[] drawables, Integer cardBack,
                 CoolImageFlipper flipper, TextView attemptsView) {
        this.drawables = drawables;
        this.flipper = flipper;
        this.resources = resources;
        this.cardBack = cardBack;
        this.attemptsView = attemptsView;

        attempts = 0;
        attemptsView.setText(String.valueOf(attempts));
        matchedPairs = 0;

        pairs = drawables.length;

        values = new Integer[2*pairs];
        cards = new Card[values.length];
        setCards();
    }

    public int getAttempts() {
        return attempts;
    }

    public void setCards() {
        for(int i = 0; i < drawables.length ; i++){
            values[i*2] = drawables[i];
            values[(i*2)+1] = drawables[i];
        }

        List<Integer> valuesList = Arrays.asList(values);
        Collections.shuffle(valuesList);
        valuesList.toArray(values);

        for (int i = 0; i < 2*pairs; i++){
            cards[i] = new Card(values[i]);
        }

        lastCard.setValue(0);
    }

    public boolean select(View v,int pos){
        boolean win = false;
        if(canClick) {
            thisCard = cards[pos];
            thisCardView = v;
            if (!thisCard.isSelected()) {
                flip(thisCardView, thisCard);
                win = check();
                if (!mustWait) {
                    lastCard = thisCard;
                    lastCardView = thisCardView;
                }
                mustWait = true;
            }
        }
        return win;
    }

    private boolean check() {
        boolean win = false;
        if(!isFirst) {
            canClick = false;
            attempts++;
            attemptsView.setText(String.valueOf(attempts));
            if (lastCard.getValue() == thisCard.getValue()) {
                matchedPairs++;
                canClick = true;
            } else {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        flip(thisCardView, thisCard);
                        flip(lastCardView, lastCard);
                        canClick = true;
                        mustWait = false;
                    }
                }, 1000);
            }
            if (matchedPairs == (values.length/2)){
                win = true;
            }
        }else{
            mustWait = false;
        }
        isFirst = !isFirst;
        return win;
    }

    private void flip(View v,Card card) {
        if(card.isSelected()){
            flipper.flipImage(resources.getDrawable(cardBack), (ImageView) v);
        }else{
            flipper.flipImage(resources.getDrawable(card.getValue()), (ImageView) v);
        }
        card.setSelected(!card.isSelected());
    }

    public void hideCards (View[] cardViews) {
        for (int i = 0; i < pairs * 2; i++) {
            flipper.flipImage(resources.getDrawable(cardBack), (ImageView) cardViews[i]);
        }
    }
}
