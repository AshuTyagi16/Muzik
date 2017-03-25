package com.music.player.muzik.views;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.music.player.muzik.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ashu on 3/25/2017.
 */

public class SongsViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.iv_song_logo)
    ImageView mIvSongLogo;
    @BindView(R.id.tv_song_name)
    TextView mTvSongName;
    @BindView(R.id.tv_artist_name)
    TextView mTvArtistName;
    @BindView(R.id.rl_song_cell)
    RelativeLayout mRlSongCell;

    public SongsViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void setSong(String song, int position) {
        if (song.length() > 0) {
            String parts[] = song.split("99999");
            if (parts[0] != null && parts[0].length() > 0) {
                String song_name = parts[0];
                Log.d("setSong", "ZERO : "+parts[0]);
                mTvSongName.setText(song_name);
            }
            if (parts[1] != null && parts[1].length() > 0) {
                Log.d("setSong", "ONE : "+parts[1]);
                String artist_name = parts[1];
                mTvArtistName.setText(artist_name);
            }
        }
        if (position % 2 == 0) {
            mRlSongCell.setBackgroundResource(R.drawable.gradient_song_cell);
        }
    }
}
