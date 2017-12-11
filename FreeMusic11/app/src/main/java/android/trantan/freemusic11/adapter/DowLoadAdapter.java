package android.trantan.freemusic11.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.trantan.freemusic11.R;
import android.trantan.freemusic11.database.DowloadSongModel;
import android.trantan.freemusic11.event.OnClickFileDowloadEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by vanph on 08/12/2017.
 */

public class DowLoadAdapter  extends RecyclerView.Adapter<DowLoadAdapter.DowloadHolder>{
    Context context;
    private List<DowloadSongModel> dowloadSongModelList;
    public DowLoadAdapter(Context context, List<DowloadSongModel> dowloadSongModelList)
    {
        this.context = context;
        this.dowloadSongModelList = dowloadSongModelList;
    }
    @Override
    public DowloadHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.item_list_dowload,parent,false);
        return new DowloadHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DowloadHolder holder, int position) {
        holder.setData(dowloadSongModelList.get(position));
    }

    @Override
    public int getItemCount() {
        return dowloadSongModelList.size();
    }

    public class DowloadHolder  extends RecyclerView.ViewHolder{

        TextView tv_song;
        TextView tv_singer;
        View view;
        public DowloadHolder (View itemView) {
            super(itemView);
            tv_song = itemView.findViewById(R.id.tv_songdow);
            tv_singer = itemView.findViewById(R.id.tv_singerdow);
            view = itemView;

        }
        public void setData(final DowloadSongModel dowloadSongModel)
        {
            tv_song.setText(dowloadSongModel.song);
            tv_singer.setText(dowloadSongModel.singer);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    EventBus.getDefault().postSticky(new OnClickFileDowloadEvent(dowloadSongModel));
                }
            });
        }
    }
}
