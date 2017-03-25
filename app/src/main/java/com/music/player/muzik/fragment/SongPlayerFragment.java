package com.music.player.muzik.fragment;

import com.music.player.muzik.R;

/**
 * Created by Ashu on 3/25/2017.
 */

public class SongPlayerFragment extends MusicFragment {

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_song_player;
    }

    public static SongPlayerFragment newInstance() {
        return new SongPlayerFragment();
    }
}
