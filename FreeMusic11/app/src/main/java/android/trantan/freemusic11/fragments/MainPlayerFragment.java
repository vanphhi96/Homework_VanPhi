package android.trantan.freemusic11.fragments;


import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.trantan.freemusic11.database.DowloadSongModel;
import android.trantan.freemusic11.database.TopSongModel;
import android.trantan.freemusic11.event.OnClickFileDowloadEvent;
import android.trantan.freemusic11.event.OnClickTopSongEvent;
import android.trantan.freemusic11.utils.MusicHandler;
import android.trantan.freemusic11.utils.Utils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.trantan.freemusic11.R;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.thin.downloadmanager.DefaultRetryPolicy;
import com.thin.downloadmanager.DownloadRequest;
import com.thin.downloadmanager.DownloadStatusListener;
import com.thin.downloadmanager.RetryPolicy;
import com.thin.downloadmanager.ThinDownloadManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.File;
import java.util.List;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainPlayerFragment extends Fragment {

    ImageView iv_balck;
    ImageView iv_dowload;
    ImageView iv_song;
    TextView tv_song;
    TextView tv_singer;
    SeekBar seekBar;
    ImageView iv_next;
    ImageView iv_previous;
    FloatingActionButton fb_main;
    TextView tv_timecuren;
    TextView tv_timeduration;
    TopSongModel topSongModel;
    public static final String TAG = MainPlayerFragment.class.toString();
    public  boolean xetDK = true;
    DowloadSongModel dowloadSongModel;
    public MainPlayerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_player, container, false);
        iv_balck = view.findViewById(R.id.iv_back);
        iv_dowload = view.findViewById(R.id.iv_dowload);
        iv_song = view.findViewById(R.id.iv_image_song);
        tv_song = view.findViewById(R.id.tv_song);
        tv_singer = view.findViewById(R.id.tv_singer);
        seekBar = view.findViewById(R.id.sb_timeline);
        iv_next = view.findViewById(R.id.iv_next);
        iv_previous = view.findViewById(R.id.iv_previous);
        fb_main = view.findViewById(R.id.fb_main);
        tv_timecuren = view.findViewById(R.id.tv_curren_time);
        tv_timeduration = view.findViewById(R.id.tv_duration_time);
        EventBus.getDefault().register(this);
        fb_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MusicHandler.playPuase();
            }
        });
        iv_balck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
        iv_dowload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<DowloadSongModel> dowloadSongModelList = Utils.getPathFile();
               for (DowloadSongModel dowload:dowloadSongModelList)
               {
                   if(dowload.song.equals(topSongModel.song)&&dowload.singer.equals(topSongModel.singer))
                   {
                       Toast.makeText(getContext(), "Bài hát đã được tải về!", Toast.LENGTH_SHORT).show();
                       return;
                   }
               }
                Toast.makeText(getContext(), "Đang tải", Toast.LENGTH_SHORT).show();
                Uri downloadUri = Uri.parse(topSongModel.url);
                String root = Environment.getExternalStorageDirectory().toString();
                File myFolder = new File(root+"/MusicFree");
                myFolder.mkdirs();
                Uri destinationUri = Uri.parse(myFolder+"/"+topSongModel.song+"-"+topSongModel.singer+".mp3");
                DownloadRequest downloadRequest = new DownloadRequest(downloadUri)
                        .addCustomHeader("Auth-Token", "YourTokenApiKey")
                        .setRetryPolicy(new DefaultRetryPolicy())
                        .setDestinationURI(destinationUri).setPriority(DownloadRequest.Priority.HIGH)
                        .setDownloadContext(getContext())//Optional
                        .setDownloadListener(new DownloadStatusListener() {
                            @Override
                            public void onDownloadComplete(int id) {
                                Toast.makeText(getContext(), "Tải về thành công", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onDownloadFailed(int id, int errorCode, String errorMessage) {
                                Toast.makeText(getContext(), "Lỗi, không thể tải", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onProgress(int id, long totalBytes, long downlaodedBytes, int progress) {

                            }
                        });
                ThinDownloadManager thinDownloadManager = new ThinDownloadManager();
                thinDownloadManager.add(downloadRequest);
                iv_dowload.setClickable(false);

            }
        });

        iv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(xetDK)
                {
                    int count = TopSongFragment.topSongModelList.size();
                    for(int i=0; i<count; i++)
                    {
                        if(TopSongFragment.topSongModelList.get(i).equals(topSongModel)){
                            if(i<count-1)
                            {
                                EventBus.getDefault().postSticky(new OnClickTopSongEvent(TopSongFragment.topSongModelList.get(i+1)));
                                break;
                            }
                            else{
                                EventBus.getDefault().postSticky(new OnClickTopSongEvent(TopSongFragment.topSongModelList.get(0)));
                                break;
                            }
                        }
                    }
                }
                else{
                    int count = DowloadFragment.dowloadSongModelList.size();
                    for (int i=0; i<count; i++)
                    {
                        if(DowloadFragment.dowloadSongModelList.get(i).equals(dowloadSongModel))
                        {
                            if(i<count-1)
                            {
                                EventBus.getDefault().postSticky(new OnClickFileDowloadEvent(DowloadFragment.dowloadSongModelList.get(i+1)));
                                break;
                            }
                            else {
                                EventBus.getDefault().postSticky(new OnClickFileDowloadEvent(DowloadFragment.dowloadSongModelList.get(0)));
                                break;
                            }

                        }
                    }
                }


            }
        });

        iv_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(xetDK)
                {
                    int count = TopSongFragment.topSongModelList.size();
                    for(int i=0; i<count; i++)
                    {
                        if(TopSongFragment.topSongModelList.get(i).equals(topSongModel)){
                            if((MusicHandler.hybridMediaPlayer.getCurrentPosition()/1000)<5)
                            {
                               if(i==0)
                               {
                                   EventBus.getDefault().postSticky(new OnClickTopSongEvent(TopSongFragment.topSongModelList.get(count-1)));
                                   break;
                               }
                               else {
                                   EventBus.getDefault().postSticky(new OnClickTopSongEvent(TopSongFragment.topSongModelList.get(i-1)));
                                   break;
                               }
                            }
                            else{
                                EventBus.getDefault().postSticky(new OnClickTopSongEvent(topSongModel));
                                break;
                            }

                        }
                    }

                }
                else {
                    int count = DowloadFragment.dowloadSongModelList.size();
                    for (int i=0; i<count; i++)
                    {
                        if(DowloadFragment.dowloadSongModelList.get(i).equals(dowloadSongModel))
                        {
                            if((MusicHandler.hybridMediaPlayer.getCurrentPosition()/1000)<5)
                            {
                                if(i==0)
                                {
                                    EventBus.getDefault().postSticky(new OnClickFileDowloadEvent(DowloadFragment.dowloadSongModelList.get(count-1)));
                                    break;
                                }
                                else {
                                    EventBus.getDefault().postSticky(new OnClickFileDowloadEvent(DowloadFragment.dowloadSongModelList.get(i-1)));
                                    break;
                                }
                            }
                            else {
                                EventBus.getDefault().postSticky(new OnClickFileDowloadEvent(dowloadSongModel));
                            }


                        }
                    }
                }
            }
        });


        return view;
    }
    @Subscribe(sticky = true)
    public void onMiniPlayerClick(OnClickTopSongEvent onClickTopSongEvent)
    {
        xetDK = true;
        topSongModel = onClickTopSongEvent.topSongModel;
        tv_song.setText(topSongModel.song);
        tv_singer.setText(topSongModel.singer);
        Picasso.with(getContext()).load(topSongModel.largeImage).transform(new CropCircleTransformation()).into(iv_song);
        MusicHandler.updateUIRealtime(seekBar,fb_main,iv_song,tv_timecuren,tv_timeduration);

    }
    @Subscribe(sticky = true)
    public void onMiniPlayerClickDowLoad(OnClickFileDowloadEvent onClickFileDowloadEvent)
    {
        xetDK = false;
        dowloadSongModel = onClickFileDowloadEvent.dowloadSongModel;
        tv_song.setText(onClickFileDowloadEvent.dowloadSongModel.song);
        tv_singer.setText(onClickFileDowloadEvent.dowloadSongModel.singer);
        //Picasso.with(getContext()).load(R.raw.genre_x2_22).transform(new CropCircleTransformation()).into(iv_song);
        MusicHandler.updateUIRealtime(seekBar,fb_main,null,tv_timecuren,tv_timeduration);
    }
}
