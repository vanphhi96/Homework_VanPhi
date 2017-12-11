package android.trantan.freemusic11.fragments;


import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.trantan.freemusic11.adapter.DowLoadAdapter;
import android.trantan.freemusic11.database.DowloadSongModel;
import android.trantan.freemusic11.utils.Utils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.trantan.freemusic11.R;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.animators.FlipInTopXAnimator;

/**
 * A simple {@link Fragment} subclass.
 */
public class DowloadFragment extends Fragment {
    private RecyclerView rv_dowload;
    public static List<DowloadSongModel> dowloadSongModelList = new ArrayList<>();
    private DowLoadAdapter dowLoadAdapter;
    public DowloadFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dowload,container,false);
        rv_dowload = view.findViewById(R.id.rv_dowload_recycleview);
       dowloadSongModelList= Utils.getPathFile();
        dowLoadAdapter = new DowLoadAdapter(getContext(),dowloadSongModelList);
        rv_dowload.setAdapter(dowLoadAdapter);
        rv_dowload.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_dowload.setItemAnimator(new FlipInTopXAnimator());
        rv_dowload.getItemAnimator().setAddDuration(300);
        return view;
    }



}
