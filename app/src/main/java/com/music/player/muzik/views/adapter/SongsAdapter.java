package com.music.player.muzik.views.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.music.player.muzik.R;
import com.music.player.muzik.views.SongsViewHolder;

/**
 * Created by Ashu on 3/25/2017.
 */

public class SongsAdapter extends RecyclerView.Adapter<SongsViewHolder> {

    @Override
    public SongsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.cell_song, null);
        return new SongsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SongsViewHolder holder, int position) {
        holder.setSong(position);
    }

    @Override
    public int getItemCount() {
        return 30;
    }
}
