package android.trantan.freemusic11.activites;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.trantan.freemusic11.R;
import android.trantan.freemusic11.adapter.ViewPagerAdapter;
import android.trantan.freemusic11.database.DowloadSongModel;
import android.trantan.freemusic11.database.TopSongModel;
import android.trantan.freemusic11.event.OnClickFileDowloadEvent;
import android.trantan.freemusic11.event.OnClickTopSongEvent;
import android.trantan.freemusic11.fragments.MainPlayerFragment;
import android.trantan.freemusic11.networks.MusicInterface;
import android.trantan.freemusic11.networks.MusicTypesResponseJSON;
import android.trantan.freemusic11.networks.RetrofitInstance;
import android.trantan.freemusic11.notification.MusicNotification;
import android.trantan.freemusic11.utils.MusicHandler;
import android.trantan.freemusic11.utils.Utils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.toString();

    TabLayout tabLayout;
    ViewPager viewPager;
    RelativeLayout rlMini;
    SeekBar seekBar;
    ImageView imageView;
    TextView tvSong;
    TextView tvSinger;
    FloatingActionButton fbPlay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);
        setupUI();
    }
    @Subscribe(sticky = true)
    public void onReceivedTopSong(OnClickTopSongEvent onClickTopSongEvent)
    {
        TopSongModel topSongModel = onClickTopSongEvent.topSongModel;
        Log.d(TAG,"onReceivedTopSong: "+topSongModel.song);
        rlMini.setVisibility(View.VISIBLE);
        Picasso.with(this).load(topSongModel.smallImage).transform(new CropCircleTransformation()).into(imageView);
        tvSinger.setText(topSongModel.singer);
        tvSong.setText(topSongModel.song);
        MusicHandler.getSearchSong(topSongModel,this);
        MusicHandler.updateUIRealtime(seekBar,fbPlay,imageView,null,null);
    }
    @Subscribe(sticky = true)
    public void onReceivedDowload(OnClickFileDowloadEvent onClickFileDowloadEvent)
    {
        DowloadSongModel dowloadSongModel = onClickFileDowloadEvent.dowloadSongModel;
        Log.d(TAG,"onReceivedDowload: "+dowloadSongModel.song);
        rlMini.setVisibility(View.VISIBLE);
        tvSinger.setText(dowloadSongModel.singer);
        tvSong.setText(dowloadSongModel.song);
        TopSongModel topSongModelss = new TopSongModel();
        topSongModelss.singer=dowloadSongModel.singer;
        topSongModelss.song = dowloadSongModel.song;
        topSongModelss.url = dowloadSongModel.mp3Path;
        MusicHandler.playMusic(this,topSongModelss);
        MusicNotification.setupNotification(this,topSongModelss);
        MusicHandler.updateUIRealtime(seekBar,fbPlay,imageView,null,null);
    }
    private void setupUI() {
       tabLayout = findViewById(R.id.tab);
       viewPager = findViewById(R.id.view_pager);
       rlMini= findViewById(R.id.mini_player);
       seekBar = findViewById(R.id.sb_mini);
       imageView = findViewById(R.id.iv_song);
       fbPlay = findViewById(R.id.fb_mini);
       tvSong = findViewById(R.id.tv_song);
       tvSinger= findViewById(R.id.tv_singer);
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_dashboard_black_24dp));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_favorite_black_24dp));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_file_download_black_24dp));
        tabLayout.getTabAt(0).getIcon().setAlpha(255);
        tabLayout.getTabAt(1).getIcon().setAlpha(100);
        tabLayout.getTabAt(2).getIcon().setAlpha(100);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.getIcon().setAlpha(255);
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getIcon().setAlpha(100);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        seekBar.setPadding(0,0,0,0);

        fbPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MusicHandler.playPuase();
            }
        });
        rlMini.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.openFragment(getSupportFragmentManager(),R.id.rl_main_player,new MainPlayerFragment());
            }
        });

    }
    @Override
    public void onBackPressed() {
        if(getSupportFragmentManager().getBackStackEntryCount()!=0)
        {
            super.onBackPressed();
        }
        else{
            moveTaskToBack(true);
        }


    }
}
