package com.arturoguillen.socialpicture.view.search;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arturoguillen.socialpicture.R;
import com.arturoguillen.socialpicture.utils.PicassoView;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by arturo.guillen on 12/09/2017.
 */

class ImagesAdapter extends RecyclerView.Adapter {
    private Picasso picasso;
    private List<String> listImages;

    public ImagesAdapter(Picasso picasso, List<String> listImages) {
        this.listImages = listImages;
        this.picasso = picasso;
    }

    public void appendImages(List<String> listImages) {
        this.listImages.clear();
        this.listImages.addAll(listImages);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ImageCard(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_image, parent, false), picasso);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ImageCard) holder).fillImageCard(listImages.get(position));
    }

    @Override
    public int getItemCount() {
        return listImages.size();
    }
}

class ImageCard extends RecyclerView.ViewHolder {

    @BindView(R.id.feed_picasso)
    PicassoView feedPicasso;

    private Picasso picasso;

    public ImageCard(View itemView, Picasso picasso) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.picasso = picasso;
    }

    public void fillImageCard(String url) {
        feedPicasso.init(picasso, url);
    }
}



