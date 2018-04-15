package com.example.martynaskairys.popularmoviesstageone;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;

import com.squareup.picasso.Picasso;

/**
 * Created by martynaskairys on 4/15/18.
 */

public class ImageAdapter extends BaseAdapter {

    private LayoutInflater layoutInflater;
    private String[] imgUrl;
    private MainActivity mContext;


    public ImageAdapter(MainActivity mainActivity, String[] imgUrl) {


        mContext = mainActivity;
        this.imgUrl = imgUrl;
        layoutInflater = (LayoutInflater) mainActivity.getSystemService(MainActivity.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return imgUrl.length;
    }

    @Override
    public Object getItem(int i) {
        return imgUrl[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ImageView imageViewPoster;

        if (view==null){
            imageViewPoster = new ImageView(mContext);
            imageViewPoster.setPadding(2,2,2,2);
            imageViewPoster.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageViewPoster.setAdjustViewBounds(true);
        }
        else {
            imageViewPoster = (ImageView) view;
        }

        Picasso.with(mContext).load(imgUrl[i]).into(imageViewPoster);


        return imageViewPoster;
    }
}
