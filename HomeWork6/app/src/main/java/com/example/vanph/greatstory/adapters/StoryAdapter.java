package com.example.vanph.greatstory.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vanph.greatstory.R;
import com.example.vanph.greatstory.databases.StoryModel;

import java.util.List;

/**
 * Created by vanph on 07/10/2017.
 */

public class StoryAdapter extends ArrayAdapter<StoryModel>{
    private Context context;
    private  int resource;
    private List<StoryModel> storyModelList;
    public StoryAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<StoryModel> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.storyModelList = objects;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        //setup UI
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        convertView = layoutInflater.inflate(resource,parent,false);
        TextView tvTitle = convertView.findViewById(R.id.tv_title);
        TextView tvAuthor = convertView.findViewById(R.id.tv_author);
        ImageView imView = convertView.findViewById(R.id.iv_story);

        //set data
        tvTitle.setText(storyModelList.get(position).getTitle());
        tvAuthor.setText(storyModelList.get(position).getAuthor());

        String[] base64 = storyModelList.get(position).getImage().split(",");
        Bitmap bitmap = BitmapFactory.decodeByteArray(
                Base64.decode(base64[1],Base64.DEFAULT),0,
                (Base64.decode(base64[1],Base64.DEFAULT)).length
        );
        imView.setImageBitmap(bitmap);
        View view = convertView.findViewById(R.id.view_bookmark);
        if(storyModelList.get(position).getBookmark()){
            view.setBackgroundColor(Color.rgb(255,204,0));
        }
        else{
            view.setBackgroundColor(Color.rgb(81,213,106));
        }
        return convertView;
    }
}
