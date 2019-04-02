package com.hwx.listApplication.viewModel;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hwx.listApplication.Configuration;
import com.hwx.listApplication.model.FilmDetail;

public class FilmDetailViewModel {

    private FilmDetail filmDetail;


    public FilmDetailViewModel(FilmDetail filmDetail) {
        this.filmDetail = filmDetail;
    }


    public String getBackdropPath() {
        return Configuration.getImageFullUrl(filmDetail.backdropPath);
    }

    public String getImageUrl() {

        return Configuration.getImageFullUrl(filmDetail.posterPath);
    }

    public String getTitle() {
        return filmDetail.title;
    }

    public String getOriginalTitle() {
        return filmDetail.originalTitle;
    }

    public String getOverview() {
        return filmDetail.overview;
    }

    public Double getPopularity() {
        return filmDetail.popularity;
    }

    public Long getBudget() {
        return filmDetail.budget;
    }

    public String getReleaseDate() {
        return filmDetail.releaseDate;
    }

    public Double getVoteAverage() {
        return filmDetail.voteAverage;
    }

    public Integer getVoteCount() {
        return filmDetail.voteCount;
    }

    public String getHomepage() {
        return filmDetail.homepage;
    }

    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView view, String imageUrl){
        Glide.with(view.getContext()).load(imageUrl).into(view);
    }
}