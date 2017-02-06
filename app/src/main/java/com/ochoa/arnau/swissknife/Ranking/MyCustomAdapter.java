package com.ochoa.arnau.swissknife.Ranking;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ochoa.arnau.swissknife.R;

import java.util.ArrayList;

public class MyCustomAdapter extends RecyclerView.Adapter<MyCustomAdapter.AdapterViewHolder>{

    ArrayList<Player> players;

    public MyCustomAdapter(ArrayList<Player> players){
        this.players = players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    @Override
    public MyCustomAdapter.AdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.row_layout,viewGroup, false);
        return new AdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyCustomAdapter.AdapterViewHolder holder, int position) {
        int iconLayout = players.get(position).getImage();
        switch (iconLayout){
            case 0:
                holder.image.setImageDrawable(holder.view.getResources().getDrawable(R.drawable.ic_looks_one_black_24dp));
                break;
            case 1:
                holder.image.setImageDrawable(holder.view.getResources().getDrawable(R.drawable.ic_looks_two_black_24dp));
                break;
            case 2:
                holder.image.setImageDrawable(holder.view.getResources().getDrawable(R.drawable.ic_looks_3_black_24dp));
                break;
            default:
                holder.image.setImageDrawable(holder.view.getResources().getDrawable(R.drawable.ic_indeterminate_check_box_black_24dp));
                break;
        }
        holder.username.setText(players.get(position).getUsername());
        holder.score.setText("" + players.get(position).getScore());
    }

    @Override
    public int getItemCount() {
        return players.size();
    }

    public class AdapterViewHolder extends RecyclerView.ViewHolder{

        public View view;
        public ImageView image;
        public TextView username;
        public TextView score;

        public AdapterViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            this.image = (ImageView) itemView.findViewById(R.id.image);
            this.username = (TextView) itemView.findViewById(R.id.username);
            this.score = (TextView) itemView.findViewById(R.id.score);

        }
    }
}
