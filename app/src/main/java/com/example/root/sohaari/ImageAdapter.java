package com.example.root.sohaari;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;

/**
 * Created by root on 9/4/17.
 */

class ImageAdapter extends BaseAdapter {
    private Context mContext;
    public Integer[] pics={R.drawable.imgi,R.drawable.imgj,R.drawable.imgk,R.drawable.imgl,R.drawable.imgm,R.drawable.imgn};
    public ImageAdapter(Context c) {
        mContext=c;
    }

    @Override
    public int getCount() {
        return pics.length;
    }

    @Override
    public Object getItem(int position) {
        return pics[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView=new ImageView(mContext);
        imageView.setImageResource(pics[position]);
        imageView.setLayoutParams(new GridView.LayoutParams(350,350));
        return imageView;
    }
}
