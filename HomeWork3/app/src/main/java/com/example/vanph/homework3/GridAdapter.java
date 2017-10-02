package com.example.vanph.homework3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by vanph on 02/10/2017.
 */

public class GridAdapter extends BaseAdapter{
    private Context context;
    private int images[]=
    {
              R.drawable.fruit_1,
                R.drawable.fruit_2,
                R.drawable.fruit_3,
                R.drawable.fruit_4,
                R.drawable.fruit_5,
                R.drawable.fruit_6,
                R.drawable.fruit_7,
                R.drawable.fruit_8,
                R.drawable.fruit_9,
                R.drawable.fruit_10,
                R.drawable.fruit_11,
                R.drawable.fruit_12,
                R.drawable.fruit_13,
                R.drawable.fruit_14,
                R.drawable.fruit_15,
                R.drawable.fruit_16,

    };
    private String label[]={
            "cà chua",
            "cà pháo",
            "cà tím",
            "rau rền",
            "rau ngót",
            "rau đay",
            "rau muống",
            "táo",
            "bưởi",
            "chôm chôm",
            "mít",
            "cóc",
            "ổi",
            "xe đạp",
            "xe máy",
            "ô tô"


    };
    private LayoutInflater layoutInflater;

    public GridAdapter(Context context) {
        this.context = context;

    }

    @Override
    public int getCount() {
        return label.length;
    }

    @Override
    public Object getItem(int i) {
        return label[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View gridview = view;
        if(view==null)
        {
            layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            gridview = layoutInflater.inflate(R.layout.row,null);

        }
        ImageView imageView = gridview.findViewById(R.id.iv_image);
        TextView textView = gridview.findViewById(R.id.txt_textview);
        imageView.setImageResource(images[i]);
        textView.setText(label[i]);

        return gridview;
    }
}
