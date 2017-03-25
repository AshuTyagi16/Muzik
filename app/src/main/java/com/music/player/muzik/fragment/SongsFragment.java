package com.music.player.muzik.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.music.player.muzik.R;
import com.music.player.muzik.views.adapter.SongsAdapter;

import butterknife.BindView;

/**
 * Created by Ashu on 3/25/2017.
 */

public class SongsFragment extends MusicFragment {

    @BindView(R.id.rv_songs)
    RecyclerView mRvSongs;

    private SongsAdapter mSongsAdapter;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_songs;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mSongsAdapter = new SongsAdapter();
        mRvSongs.setLayoutManager(new LinearLayoutManager(getContext()));
        mRvSongs.setAdapter(mSongsAdapter);
    }
}
