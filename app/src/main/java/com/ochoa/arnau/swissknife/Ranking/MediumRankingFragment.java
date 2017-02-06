package com.ochoa.arnau.swissknife.Ranking;


import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ochoa.arnau.swissknife.Data.DatabaseHelper;
import com.ochoa.arnau.swissknife.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MediumRankingFragment extends Fragment implements View.OnClickListener{

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;

    ArrayList<Player> players;

    FloatingActionButton clearFab;

    MyCustomAdapter adapter;

    DatabaseHelper databaseHelper;

    public MediumRankingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_medium_ranking, container, false);
        databaseHelper = new DatabaseHelper(getActivity().getApplicationContext());

        players = new ArrayList<>(0);

        setRanking();

        clearFab = (FloatingActionButton) view.findViewById(R.id.clear_fab);
        clearFab.setOnClickListener(this);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(getActivity());
        adapter = new MyCustomAdapter(players);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        return view;
    }

    private void setRanking() {
        Cursor cursor = databaseHelper.getRankingByLevel(String.valueOf(R.string.easy_level), String.valueOf(R.string.scores_table));
        Player player;
        int pos = 0;
        if (cursor.moveToFirst()) {
            do {
                String username = cursor.getString(cursor.getColumnIndex(String.valueOf(R.string.score_column)));
                int score = cursor.getInt(cursor.getColumnIndex(String.valueOf(R.string.score_column)));
                player = new Player(pos, username, score);
                players.add(player);
                pos ++;
            } while (cursor.moveToNext());
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.clear_fab:
                if (databaseHelper.clearRankingByLevel(String.valueOf(R.string.easy_level))) {
                    setRanking();
                    adapter.setPlayers(players);
                    adapter.notifyDataSetChanged();

                    final FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                    ft.detach(this);
                    ft.attach(this);
                    ft.commit();

                }else{
                    Toast.makeText(getActivity().getApplicationContext(),
                            getString(R.string.unable_clear), Toast.LENGTH_SHORT).show();
                }
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
