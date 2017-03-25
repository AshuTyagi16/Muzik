package com.music.player.muzik.views.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.futuremind.recyclerviewfastscroll.SectionTitleProvider;
import com.music.player.muzik.R;
import com.music.player.muzik.views.SongsViewHolder;

import java.util.ArrayList;

/**
 * Created by Ashu on 3/25/2017.
 */

public class SongsAdapter extends RecyclerView.Adapter<SongsViewHolder> implements SectionTitleProvider {

    private ArrayList<String> mSongsList;

    @Override
    public SongsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.cell_song, null);
        return new SongsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SongsViewHolder holder, int position) {
        holder.setSong(mSongsList.get(position), position);
    }

    @Override
    public int getItemCount() {
        if (mSongsList != null && mSongsList.size() != 0)
            return mSongsList.size();
        else
            return 0;
    }

    public void setSongList(ArrayList<String> songlist) {
        mSongsList = songlist;
        notifyDataSetChanged();
    }

    @Override
    public String getSectionTitle(int position) {
        String parts[] = mSongsList.get(position).split("99999");
        return parts[0].substring(0, 1);
    }
}
