package com.hwx.listApplication.viewModel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hwx.listApplication.Configuration;
import com.hwx.listApplication.model.FilmDetail;

public class FilmDetailViewModel extends ViewModel {

    private MutableLiveData<String> backdropPath = new MutableLiveData<>();
    private MutableLiveData<String> imageUrl = new MutableLiveData<>();

    private MutableLiveData<String> title = new MutableLiveData<>();
    private MutableLiveData<String> originalTitle = new MutableLiveData<>();

    private MutableLiveData<String> overview = new MutableLiveData<>();
    private MutableLiveData<Double> popularity = new MutableLiveData<>();
    private MutableLiveData<Long> budget = new MutableLiveData<>();
    private MutableLiveData<String> releaseDate = new MutableLiveData<>();
    private MutableLiveData<Double> voteAverage = new MutableLiveData<>();
    private MutableLiveData<Integer> voteCount = new MutableLiveData<>();
    private MutableLiveData<String> homepage = new MutableLiveData<>();



    public FilmDetailViewModel(
            FilmDetail filmDetail
    ) {
        setFilmDetail(filmDetail);
    }

    public void setFilmDetail(FilmDetail filmDetail) {
        backdropPath.setValue(filmDetail.getBackdropPath());
        imageUrl.setValue(Configuration.getImageFullUrl(filmDetail.getPosterPath()));
        title.setValue(filmDetail.getTitle());
        originalTitle.setValue(filmDetail.getOriginalTitle());
        overview.setValue(filmDetail.getOverview());
        popularity.setValue(filmDetail.getPopularity());
        budget.setValue(filmDetail.getBudget());
        releaseDate.setValue(filmDetail.getReleaseDate());
        voteAverage.setValue(filmDetail.getVoteAverage());
        voteCount.setValue(filmDetail.getVoteCount());
        homepage.setValue(filmDetail.getHomepage());
    }

    public MutableLiveData<String> getTitle() {
        return title;
    }

    public MutableLiveData<String> getOriginalTitle() {
        return originalTitle;
    }

    public MutableLiveData<String> getBackdropPath() {
        return backdropPath;
    }

    public MutableLiveData<String> getImageUrl() {
        return imageUrl;
    }

    public MutableLiveData<String> getOverview() {
        return overview;
    }

    public MutableLiveData<Double> getPopularity() {
        return popularity;
    }

    public MutableLiveData<Long> getBudget() {
        return budget;
    }

    public MutableLiveData<String> getReleaseDate() {
        return releaseDate;
    }

    public MutableLiveData<Double> getVoteAverage() {
        return voteAverage;
    }

    public MutableLiveData<Integer> getVoteCount() {
        return voteCount;
    }

    public MutableLiveData<String> getHomepage() {
        return homepage;
    }

    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView view, String imageUrl){
        Glide.with(view.getContext()).load(imageUrl).into(view);
    }

}
