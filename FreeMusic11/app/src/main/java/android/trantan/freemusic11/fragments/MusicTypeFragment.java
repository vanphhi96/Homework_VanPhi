package android.trantan.freemusic11.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.trantan.freemusic11.adapter.MusicTypeAdapter;
import android.trantan.freemusic11.database.MusicTypeModel;
import android.trantan.freemusic11.networks.MusicInterface;
import android.trantan.freemusic11.networks.MusicTypesResponseJSON;
import android.trantan.freemusic11.networks.RetrofitInstance;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.trantan.freemusic11.R;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.animators.FlipInTopXAnimator;
import jp.wasabeef.recyclerview.animators.ScaleInTopAnimator;
import jp.wasabeef.recyclerview.animators.SlideInRightAnimator;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class MusicTypeFragment extends Fragment {

    public RecyclerView rvMusicType;
    private List<MusicTypeModel> musicTypeModelList = new ArrayList<>();
    private Context context;
    private MusicTypeAdapter musicTypeAdapter;
    public MusicTypeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_music_type, container, false);
        rvMusicType= view.findViewById(R.id.rv_music_recycleview);
        context = getContext();
         musicTypeAdapter = new MusicTypeAdapter(musicTypeModelList,getContext());
        rvMusicType.setAdapter(musicTypeAdapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return (position%3==0?2:1);
            }
        });
        rvMusicType.setLayoutManager(gridLayoutManager);
        rvMusicType.setItemAnimator(new SlideInUpAnimator());
        rvMusicType.getItemAnimator().setAddDuration(300);
        loadData();

        return view;
    }
    private void loadData()
    {
        MusicInterface musicTypeInterface =
                RetrofitInstance.getInstance().create(MusicInterface.class);
        musicTypeInterface.getMusicType().enqueue(new Callback<MusicTypesResponseJSON>() {
            @Override
            public void onResponse(Call<MusicTypesResponseJSON> call, Response<MusicTypesResponseJSON> response) {
                List<MusicTypesResponseJSON.SubObjectJSON> list
                        = response.body().subgenres;
                for (MusicTypesResponseJSON.SubObjectJSON subObjectJSON : list){
                   MusicTypeModel musicTypeModel = new MusicTypeModel();
                   musicTypeModel.id = subObjectJSON.id;
                   musicTypeModel.key = subObjectJSON.translation_key;
                   musicTypeModel.imageID = context.getResources().getIdentifier("genre_x2_"+musicTypeModel.id,"raw",
                           context.getPackageName());
                    musicTypeModelList.add(musicTypeModel);
                }
                musicTypeAdapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<MusicTypesResponseJSON> call, Throwable t) {
                Toast.makeText(context,"No Connection",Toast.LENGTH_LONG).show();
            }
        });
    }

}
