package com.music.player.muzik.views;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.music.player.muzik.R;
import com.music.player.muzik.activity.SongPlayerActivity;
import com.music.player.muzik.fragment.SongListFragment;
import com.music.player.muzik.model.Song;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Locale;

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
    @BindView(R.id.tv_song_duration)
    TextView mTvSongDuration;
    @BindView(R.id.rl_song_cell)
    RelativeLayout mRlSongCell;

    private ArrayList<Song> mList = new ArrayList<>();
    private int mPosition;

    public SongsViewHolder(final View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemView.getContext().startActivity(SongPlayerActivity.newIntent(itemView.getContext(), mList, mPosition));
            }
        });
    }

    public void setSong(ArrayList<Song> song, int position) {
        if (song != null) {
            mPosition = position;
            mList = song;
            if (song.get(position).getTitle() != null && song.get(position).getTitle().length() > 0)
                mTvSongName.setText(song.get(position).getTitle());
            if (song.get(position).getArtist() != null && song.get(position).getArtist().length() > 0)
                mTvArtistName.setText(song.get(position).getArtist());
            if ((String.valueOf(song.get(position).getDuration())).length() > 0)
                mTvSongDuration.setText(String.valueOf(convertDuration(song.get(position).getDuration())));
            if (song.get(position).getAlbumArtUri() != null)
                Picasso.with(itemView.getContext())
                        .load(song.get(position).getAlbumArtUri()).error(R.drawable.demo_music_logo)
                        .placeholder(R.drawable.demo_music_logo).into(mIvSongLogo);
        }
        if (position % 2 == 0) {
            mRlSongCell.setBackgroundResource(R.drawable.gradient_song_cell);
        }
    }

    private String convertDuration(long durationInMs) {
        long durationInSeconds = durationInMs / 1000;
        long seconds = durationInSeconds % 60;
        long minutes = (durationInSeconds % 3600) / 60;
        long hours = durationInSeconds / 3600;
        if (hours > 0) {
            return String.format(Locale.US, "%02d:%02d:%02d", hours, minutes, seconds);
        }
        return String.format(Locale.US, "%02d:%02d", minutes, seconds);
    }
}
