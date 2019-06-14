package com.hwx.listApplication.viewModel;


import android.arch.lifecycle.ViewModel;
import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hwx.listApplication.Configuration;
import com.hwx.listApplication.model.FilmSimple;
import com.livedata.SingleLiveEvent;

import java.util.Objects;

public class FilmSimpleViewModel extends ViewModel {

    private FilmSimple filmSimple;
    private SingleLiveEvent uiEventLiveData = new SingleLiveEvent<Long>();

    public FilmSimpleViewModel(FilmSimple filmSimple) {
        this.filmSimple = filmSimple;
    }

    public SingleLiveEvent getUiEventLiveData() {
        return uiEventLiveData;
    }

    public String getCaption() {
        return filmSimple.title;
    }

    public String getImageUrl() {
        return Configuration.getImageFullUrl(filmSimple.posterPath);
    }

    public String getPopularity() {
        return "Рейтинг: "+filmSimple.popularity;
    }

    //вызывается при выборе карточки фильма из списка
    public void onItemClick(final View v){
        uiEventLiveData.setValue(filmSimple.id);
    }

    // Loading Image using Glide Library.
    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String url){
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }

    public void setFilmSimple(FilmSimple filmSimple) {
        this.filmSimple = filmSimple;
        //notifyChange();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilmSimpleViewModel that = (FilmSimpleViewModel) o;
        return Objects.equals(filmSimple, that.filmSimple) &&
                Objects.equals(uiEventLiveData, that.uiEventLiveData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(filmSimple, uiEventLiveData);
    }
}
