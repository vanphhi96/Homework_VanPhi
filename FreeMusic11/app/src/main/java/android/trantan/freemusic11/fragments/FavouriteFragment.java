package android.trantan.freemusic11.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.trantan.freemusic11.adapter.DatabaseHandler;
import android.trantan.freemusic11.adapter.MusicTypeAdapter;
import android.trantan.freemusic11.event.OnUpdateRvFav;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.trantan.freemusic11.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavouriteFragment extends Fragment {
    RecyclerView rv_facourite;

    public FavouriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favourite,container,false);
        rv_facourite = view.findViewById(R.id.rv_favourite);
        rv_facourite.setAdapter(new MusicTypeAdapter(DatabaseHandler.getFavourite(),getContext()));
        rv_facourite.setLayoutManager(new GridLayoutManager(getContext(),2));
        EventBus.getDefault().register(this);
        return view;
    }
    @Subscribe(sticky = true)
    public void update(OnUpdateRvFav onUpdateRvFav)
    {
        rv_facourite.setAdapter(new MusicTypeAdapter(DatabaseHandler.getFavourite(),getContext()));
    }

}
