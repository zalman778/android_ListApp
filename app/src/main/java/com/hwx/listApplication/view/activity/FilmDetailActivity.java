package com.hwx.listApplication.view.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.hwx.listApplication.Configuration;
import com.hwx.listApplication.R;
import com.hwx.listApplication.databinding.ActivityFilmDetailBinding;
import com.hwx.listApplication.model.FilmDetail;
import com.hwx.listApplication.viewModel.FilmDetailViewModel;

public class FilmDetailActivity extends AppCompatActivity {

    private ActivityFilmDetailBinding activityFilmDetailBinding;
    private Context context;

    private static final String EXTRA_FILM_DETAIL = "EXTRA_FILM_DETAIL";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        activityFilmDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_film_detail);


        FilmDetail filmDetail = (FilmDetail) getIntent().getSerializableExtra(EXTRA_FILM_DETAIL);
        FilmDetailViewModel filmDetailViewModel = new FilmDetailViewModel(filmDetail);
        activityFilmDetailBinding.setFilmDetailViewModel(filmDetailViewModel);
        setTitle(filmDetail.title);

        //loading background image into appbarlayout;
        //activityFilmDetailBinding.appBarLayout
        Glide.with(this)
                .load(Configuration.getImageFullUrl(filmDetail.backdropPath))
                .asBitmap()
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        Drawable drawable = new BitmapDrawable(context.getResources(), resource);
                        //yourRelativeLayout.setBackground(drawable);
                        activityFilmDetailBinding.appBarLayout.setBackground(drawable);
                    }
        });

    }

    public static Intent fillDetail(Context context, FilmDetail filmDetail) {
        Intent intent = new Intent(context, FilmDetailActivity.class);
        intent.putExtra(EXTRA_FILM_DETAIL, filmDetail);
        return intent;
    }
}
