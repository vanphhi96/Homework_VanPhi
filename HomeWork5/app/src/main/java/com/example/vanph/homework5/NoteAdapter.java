package com.example.vanph.homework5;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

import java.util.List;

/**
 * Created by vanph on 07/10/2017.
 */

public class NoteAdapter extends ArrayAdapter<NoteModel>{
    private Context context;
    private  int resource;
    private List<NoteModel> noteModelList;
    public NoteAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<NoteModel> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.noteModelList = objects;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        //setup UI
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        convertView = layoutInflater.inflate(resource,parent,false);
        TextView tvTitle = convertView.findViewById(R.id.tv_title);
        TextView tv_description = convertView.findViewById(R.id.tv_description);

        tvTitle.setText(noteModelList.get(position).getTitle());
        tv_description.setText(noteModelList.get(position).getDescription());
        return convertView;
    }
}
