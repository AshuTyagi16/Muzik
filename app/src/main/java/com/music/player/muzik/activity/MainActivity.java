package com.music.player.muzik.activity;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.music.player.muzik.R;
import com.music.player.muzik.fragment.AlbumsFragment;
import com.music.player.muzik.fragment.ArtistsFragment;
import com.music.player.muzik.fragment.FolderFragment;
import com.music.player.muzik.fragment.PlayListFragment;
import com.music.player.muzik.fragment.SongsFragment;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tab_bar)
    SmartTabLayout mTabLayout;
    @BindView(R.id.viewpager)
    ViewPager mViewPager;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setupViewPager();
        mTabLayout.setViewPager(mViewPager);
        mToolbar.setTitle("Music Library");
        setSupportActionBar(mToolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void setupViewPager() {
        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(), FragmentPagerItems.with(this)
                .add(R.string.playlist, PlayListFragment.class)
                .add(R.string.songs, SongsFragment.class)
                .add(R.string.albums, AlbumsFragment.class)
                .add(R.string.folder, FolderFragment.class)
                .add(R.string.artists, ArtistsFragment.class)
                .create());
        mViewPager.setAdapter(adapter);
    }
}
