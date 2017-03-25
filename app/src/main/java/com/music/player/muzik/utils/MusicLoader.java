package com.music.player.muzik.utils;

/**
 * Created by Ashu on 3/26/2017.
 */

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.music.player.muzik.model.Song;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MusicLoader extends BaseAsyncTaskLoader<Collection<Song>> {

    private final Uri albumArtUri = Uri.parse("content://media/external/audio/albumart");

    public MusicLoader(Context context) {
        super(context);
    }

    @Override
    public Collection<Song> loadInBackground() {
        String[] projection = new String[]{
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.ALBUM,
                MediaStore.Audio.Media.ALBUM_ID,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.DURATION,
                MediaStore.Audio.Media.DATA,
        };

        Cursor cursor = getContext().getContentResolver().query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                projection,
                MediaStore.Audio.Media.IS_MUSIC + "=1",
                null,"LOWER(" + MediaStore.Audio.Media.TITLE + ") ASC"
        );
        if (cursor == null) {
            return Collections.emptyList();
        }
        List<Song> items = new ArrayList<>();
        try {
            if (cursor.moveToFirst()) {
                int title = cursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
                int album = cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM);
                int artist = cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
                int duration = cursor.getColumnIndex(MediaStore.Audio.Media.DURATION);
                int albumId = cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID);
                int data = cursor.getColumnIndex(MediaStore.Audio.Media.DATA);
                do {
                    Song item = new Song();
                    item.setTitle(cursor.getString(title));
                    item.setAlbum(cursor.getString(album));
                    item.setArtist(cursor.getString(artist));
                    item.setDuration(cursor.getLong(duration));
                    item.setAlbumArtUri(ContentUris.withAppendedId(albumArtUri, cursor.getLong(albumId)));
                    item.setFileUri(Uri.parse(cursor.getString(data)));
                    items.add(item);
                } while (cursor.moveToNext());
            }
        } finally {
            cursor.close();
        }
        return items;
    }
}
