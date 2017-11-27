package android.trantan.freemusic11.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.trantan.freemusic11.R;
import android.trantan.freemusic11.activites.MainActivity;
import android.trantan.freemusic11.database.MusicTypeModel;
import android.trantan.freemusic11.event.OnclickMusicTypeEvent;
import android.trantan.freemusic11.fragments.TopSongFragment;
import android.trantan.freemusic11.utils.Utils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by vanph on 18/11/2017.
 */

public class MusicTypeAdapter extends RecyclerView.Adapter<MusicTypeAdapter.MusicTypeViewHolder> {
    List<MusicTypeModel> musicTypeModelList;
    Context context;

    public MusicTypeAdapter(List<MusicTypeModel> musicTypeModelList, Context context) {
        this.musicTypeModelList = musicTypeModelList;
        this.context = context;
    }

    //creat view
    @Override
    public MusicTypeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_list_music_type,parent,false);
        return new MusicTypeViewHolder(view);
    }
    //set data
    @Override
    public void onBindViewHolder(MusicTypeViewHolder holder, int position) {
        holder.setData(musicTypeModelList.get(position));
    }

    @Override
    public int getItemCount() {
        return musicTypeModelList.size();
    }

    public class MusicTypeViewHolder extends RecyclerView.ViewHolder{
    ImageView imageView;
    TextView textView;
    View view;
        public MusicTypeViewHolder(View itemView) {
            super(itemView);
            view = itemView;
           imageView = itemView.findViewById(R.id.iv_music_type);
           textView = itemView.findViewById(R.id.tv_music_type);
        }
        public void setData(final MusicTypeModel musicTypeModel)
        {
            Picasso.with(context).load(musicTypeModel.imageID).into(imageView);
            imageView.setImageResource(musicTypeModel.imageID);
            textView.setText(musicTypeModel.key);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Utils.openFragment(((MainActivity)context).getSupportFragmentManager(),
                            R.id.rl_main,new TopSongFragment());
                    EventBus.getDefault().postSticky(new OnclickMusicTypeEvent(musicTypeModel));
                }
            });
        }
    }

}
