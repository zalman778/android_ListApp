package com.hwx.listApplication.viewModel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hwx.listApplication.Configuration;
import com.hwx.listApplication.model.FilmDetail;

import java.util.Objects;

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
            FilmDetail filmDetail) {
        setFilmDetail(filmDetail);
    }

    public void setFilmDetail(FilmDetail filmDetail) {
        backdropPath.setValue(filmDetail.backdropPath);
        imageUrl.setValue(Configuration.getImageFullUrl(filmDetail.posterPath));
        title.setValue(filmDetail.title);
        originalTitle.setValue(filmDetail.originalTitle);
        overview.setValue(filmDetail.overview);
        popularity.setValue(filmDetail.popularity);
        budget.setValue(filmDetail.budget);
        releaseDate.setValue(filmDetail.releaseDate);
        voteAverage.setValue(filmDetail.voteAverage);
        voteCount.setValue(filmDetail.voteCount);
        homepage.setValue(filmDetail.homepage);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilmDetailViewModel that = (FilmDetailViewModel) o;
        return Objects.equals(backdropPath, that.backdropPath) &&
                Objects.equals(imageUrl, that.imageUrl) &&
                Objects.equals(title, that.title) &&
                Objects.equals(originalTitle, that.originalTitle) &&
                Objects.equals(overview, that.overview) &&
                Objects.equals(popularity, that.popularity) &&
                Objects.equals(budget, that.budget) &&
                Objects.equals(releaseDate, that.releaseDate) &&
                Objects.equals(voteAverage, that.voteAverage) &&
                Objects.equals(voteCount, that.voteCount) &&
                Objects.equals(homepage, that.homepage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(backdropPath, imageUrl, title, originalTitle, overview, popularity, budget, releaseDate, voteAverage, voteCount, homepage);
    }
}
