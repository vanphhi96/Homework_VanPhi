package android.trantan.freemusic11.fragments;


import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.trantan.freemusic11.adapter.TopSongAdapter;
import android.trantan.freemusic11.database.MusicTypeModel;
import android.trantan.freemusic11.database.TopSongModel;
import android.trantan.freemusic11.event.OnclickMusicTypeEvent;
import android.trantan.freemusic11.networks.MusicInterface;
import android.trantan.freemusic11.networks.RetrofitInstance;
import android.trantan.freemusic11.networks.TopSongResponseJSON;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.trantan.freemusic11.R;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.wang.avi.AVLoadingIndicatorView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.animators.FlipInTopXAnimator;
import jp.wasabeef.recyclerview.animators.SlideInRightAnimator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopSongFragment extends Fragment {

    private static final String TAG = TopSongFragment.class.toString();
    TextView tv_musict_type;
    ImageView iv_favorites;
    ImageView iv_music_type;
    Toolbar toolbar;
    FloatingActionButton fab;
    RecyclerView rvTopSong;
    MusicTypeModel musicTypeModel;
    AppBarLayout appBarLayout;
    TopSongAdapter topSongAdapter;
    private List<TopSongModel> topSongModelList = new ArrayList<>();
    AVLoadingIndicatorView avload;
    public TopSongFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_top_song,container,false);
        EventBus.getDefault().register(this);
       setupUI(view);
       LoadData();
       return view;
    }

    private void LoadData() {
        MusicInterface musicInterface = RetrofitInstance.getInstance().create(MusicInterface.class);
        musicInterface.getTopSong(musicTypeModel.id).enqueue(new Callback<TopSongResponseJSON>() {
            @Override
            public void onResponse(Call<TopSongResponseJSON> call, Response<TopSongResponseJSON> response) {
                avload.hide();
                List<TopSongResponseJSON.FeedJSON.EntryJSON> entryJSONList= response.body().feed.entry;
                for(int i=0; i<entryJSONList.size(); i++)
                {
                    TopSongModel topSongModel = new TopSongModel();
                    topSongModel.singer = entryJSONList.get(i).artist.label;
                    topSongModel.song = entryJSONList.get(i).name.label;
                    topSongModel.smallImage = entryJSONList.get(i).images.get(2).label;
                    topSongModelList.add(topSongModel);
                    topSongAdapter.notifyItemChanged(i);
                }
//                topSongAdapter.notifyDataSetChanged();
                Log.d(TAG,"onResponse"+response.body().feed.entry.size());
            }

            @Override
            public void onFailure(Call<TopSongResponseJSON> call, Throwable t) {
                Toast.makeText(getContext(),"No connection",Toast.LENGTH_LONG).show();
            }
        });
    }

    @Subscribe(sticky = true)
    public void OnReceicedOnMusicTypeClicked(OnclickMusicTypeEvent onclickMusicTypeEvent){
            musicTypeModel = onclickMusicTypeEvent.musicTypeModel;
    }
    private void setupUI(View view) {
        tv_musict_type = view.findViewById(R.id.tv_music_type);
        iv_favorites = view.findViewById(R.id.iv_favorite);
        iv_music_type = view.findViewById(R.id.iv_music_type);
        toolbar = view.findViewById(R.id.toolbar);
        fab = view.findViewById(R.id.fab);
        rvTopSong = view.findViewById(R.id.rv_top_songs);
        appBarLayout = view.findViewById(R.id.app_bar);
        avload= view.findViewById(R.id.avload);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
        tv_musict_type.setText(musicTypeModel.key);
        Picasso.with(getContext()).load(musicTypeModel.imageID).into(iv_music_type);

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                Log.d(TAG,"addOnOffsetChangedListener"+verticalOffset);
                if(verticalOffset==0)
                {
                    toolbar.setBackground(getResources().getDrawable(R.drawable.custom_gradient_text_bot_to_top));
                }
                else{
                    toolbar.setBackground(null);
                }
            }
        });
        topSongAdapter = new TopSongAdapter(getContext(),topSongModelList);
        rvTopSong.setAdapter(topSongAdapter);
        rvTopSong.setLayoutManager(new LinearLayoutManager(getContext()));
        rvTopSong.setItemAnimator(new SlideInRightAnimator());
        rvTopSong.getItemAnimator().setAddDuration(300);
        avload.show();
    }


}
