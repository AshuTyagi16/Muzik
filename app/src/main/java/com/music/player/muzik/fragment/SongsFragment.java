package com.music.player.muzik.fragment;


import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.music.player.muzik.R;
import com.music.player.muzik.views.adapter.SongsAdapter;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by Ashu on 3/25/2017.
 */

public class SongsFragment extends MusicFragment {

    @BindView(R.id.rv_songs)
    RecyclerView mRvSongs;

    private SongsAdapter mSongsAdapter;
    private ArrayList<String> mSongsList;
    private Cursor mCursor;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_songs;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fetchSongs();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mSongsAdapter = new SongsAdapter();
        mRvSongs.setLayoutManager(new LinearLayoutManager(getContext()));
        mRvSongs.setAdapter(mSongsAdapter);
        if (mSongsList != null)
            mSongsAdapter.setSongList(mSongsList);
        else
            Toast.makeText(getContext(), "No Songs Found", Toast.LENGTH_SHORT).show();
    }

    public void fetchSongs() {
        String selection = MediaStore.Audio.Media.IS_MUSIC + " != 0";
        String sortOrder = MediaStore.Audio.Media.TITLE + " ASC";

        String[] projection = {
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.ARTIST
        };
//        String[] projection = {
//                MediaStore.Audio.Media._ID,
//                MediaStore.Audio.Media.ARTIST,
//                MediaStore.Audio.Media.TITLE,
//                MediaStore.Audio.Media.DATA,
//                MediaStore.Audio.Media.DISPLAY_NAME,
//                MediaStore.Audio.Media.DURATION
//        };

        mCursor = getActivity().managedQuery(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                projection,
                selection,
                null,
                sortOrder);

        mSongsList = new ArrayList<String>();
        while (mCursor.moveToNext()) {
            mSongsList.add(mCursor.getString(0) + "99999"
                    + mCursor.getString(1));
        }
//        while (mCursor.moveToNext()) {
//            mSongsList.add(mCursor.getString(0) + "||"
//                    + mCursor.getString(1) + "||"
//                    + mCursor.getString(2) + "||"
//                    + mCursor.getString(3) + "||"
//                    + mCursor.getString(4) + "||"
//                    + mCursor.getString(5));
//        }
    }
}
