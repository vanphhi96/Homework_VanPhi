package android.trantan.freemusic11.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.trantan.freemusic11.R;
import android.trantan.freemusic11.database.TopSongModel;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

/**
 * Created by vanph on 25/11/2017.
 */

public class TopSongAdapter extends RecyclerView.Adapter<TopSongAdapter.TopSongViewHolder>{
    Context context;
    private List<TopSongModel> topSongModelList;

    public TopSongAdapter(Context context, List<TopSongModel> topSongModelList) {
        this.context = context;
        this.topSongModelList = topSongModelList;
    }

    @Override
    public TopSongViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.item__list_box_song,parent,false);
        return new TopSongViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TopSongViewHolder holder, int position) {
        holder.setData(topSongModelList.get(position));
    }

    @Override
    public int getItemCount() {
      return topSongModelList.size();
    }

    public class TopSongViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView tv_song;
        TextView tv_singer;

        public TopSongViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_song);
            tv_song = itemView.findViewById(R.id.tv_song);
            tv_singer = itemView.findViewById(R.id.tv_singer);

        }
        public void setData(TopSongModel topSongModel)
        {
            Picasso.with(context).load(topSongModel.smallImage).transform(new CropCircleTransformation()).into(imageView);
            tv_song.setText(topSongModel.song);
            tv_singer.setText(topSongModel.singer);
        }
    }

}
