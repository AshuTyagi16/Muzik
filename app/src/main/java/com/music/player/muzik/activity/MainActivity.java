package com.music.player.muzik.activity;

import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

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
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tab_bar)
    SmartTabLayout mTabLayout;
    @BindView(R.id.viewpager)
    ViewPager mViewPager;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.iv_song_logo_universal)
    ImageView mIvLogo;
    @BindView(R.id.tv_song_name_universal)
    TextView mTvSongName;
    @BindView(R.id.iv_backward)
    ImageView mIvBackward;
    @BindView(R.id.iv_pause_play)
    ImageView mIvPausePlay;
    @BindView(R.id.iv_forward)
    ImageView mIvForward;
    @BindView(R.id.seekbar)
    SeekBar mSeekBar;

    private boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setupViewPager();
        mTabLayout.setViewPager(mViewPager);
        mToolbar.setTitle("Music Library");
        setSupportActionBar(mToolbar);
        mSeekBar.getProgressDrawable().setColorFilter(new PorterDuffColorFilter(0xffffffff, PorterDuff.Mode.MULTIPLY));
        mSeekBar.getThumb().setColorFilter(0xffffffff, PorterDuff.Mode.SRC_IN);
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

    @OnClick(R.id.iv_pause_play)
    public void toggle() {
        if (flag) {
            mIvPausePlay.setImageResource(R.drawable.ic_play);
            flag = false;
        } else {
            mIvPausePlay.setImageResource(R.drawable.ic_pause);
            flag = true;
        }
    }
}
