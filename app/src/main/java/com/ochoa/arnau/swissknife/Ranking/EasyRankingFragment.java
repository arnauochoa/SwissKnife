package com.ochoa.arnau.swissknife.Ranking;


import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ochoa.arnau.swissknife.Data.LoginHelper;
import com.ochoa.arnau.swissknife.Data.MemoryHelper;
import com.ochoa.arnau.swissknife.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class EasyRankingFragment extends Fragment implements View.OnClickListener{

    FloatingActionButton clearFab;

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;

    ArrayList<Player> players;

    MyCustomAdapter adapter;

    MemoryHelper memoryHelper;

    public EasyRankingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_easy_ranking, container, false);

        memoryHelper = new MemoryHelper(getActivity().getApplicationContext());

        players = new ArrayList<>(0);

        setRanking();

        clearFab = (FloatingActionButton) view.findViewById(R.id.clear_fab);
        clearFab.setOnClickListener(this);

        return view;
    }

    private void setRanking() {
        Cursor cursor = memoryHelper.getRankingByLevel("easy", "Scores");
        Player pos;
        if (cursor.moveToFirst()) {
            do {
                String username = cursor.getString(cursor.getColumnIndex("name"));
                int score = cursor.getInt(cursor.getColumnIndex("score"));
                pos = new Player(0, username, score);
                players.add(pos);
            } while (cursor.moveToNext());
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.clear_fab:
                setRanking();
                adapter.setPlayers(players);
                adapter.notifyDataSetChanged();
        }
    }
}
