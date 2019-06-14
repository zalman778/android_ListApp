package com.hwx.listApplication.viewModel;


import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hwx.listApplication.Configuration;
import com.hwx.listApplication.model.FilmSimple;
import com.livedata.SingleLiveEvent;

public class FilmSimpleViewModel extends ViewModel {

    private SingleLiveEvent uiEventLiveData = new SingleLiveEvent<Long>();

    private MutableLiveData<String> caption = new MutableLiveData<>();
    private MutableLiveData<String> imageUrl = new MutableLiveData<>();
    private MutableLiveData<String> popularity = new MutableLiveData<>();

    private Long filmId;

    public FilmSimpleViewModel(FilmSimple filmSimple) {
        setFilmSimple(filmSimple);
    }

    public void setFilmSimple(FilmSimple filmSimple) {
        caption.setValue(filmSimple.getTitle());
        imageUrl.setValue(Configuration.getImageFullUrl(filmSimple.getPosterPath()));
        popularity.setValue("Рейтинг: "+filmSimple.getPopularity());
        filmId = filmSimple.getId();

    }

    public SingleLiveEvent getUiEventLiveData() {
        return uiEventLiveData;
    }

    public MutableLiveData<String> getCaption() {
        return caption;
    }

    public MutableLiveData<String> getImageUrl() {
        return imageUrl;
    }

    public MutableLiveData<String> getPopularity() {
        return popularity;
    }

    //вызывается при выборе карточки фильма из списка
    public void onItemClick(final View v){
        uiEventLiveData.setValue(filmId);
    }

    // Loading Image using Glide Library.
    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String url){
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }
}
