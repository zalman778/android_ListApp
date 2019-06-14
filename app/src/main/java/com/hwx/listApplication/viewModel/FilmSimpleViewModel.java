package com.hwx.listApplication.viewModel;


import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hwx.listApplication.Configuration;
import com.hwx.listApplication.model.FilmDetail;
import com.hwx.listApplication.model.FilmSimple;
import com.hwx.listApplication.service.FilmService;
import com.livedata.SingleLiveEvent;

import java.util.Objects;

public class FilmSimpleViewModel extends BaseObservable {

    private FilmSimple filmSimple;

    private SingleLiveEvent uiEventLiveData;

    public FilmSimpleViewModel(FilmSimple filmSimple) {
        this.filmSimple = filmSimple;
        uiEventLiveData = new SingleLiveEvent<Long>();
    }



    public SingleLiveEvent getUiEventLiveData() {
        return uiEventLiveData;
    }

    public void setUiEventLiveData(SingleLiveEvent uiEventLiveData) {
        this.uiEventLiveData = uiEventLiveData;
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

//        //checking via Call:
//        filmService.fetchFilmDetail(Configuration.getMovieFullInfoUrl(filmSimple.id))
//                .enqueue(new Callback<FilmDetail>() {
//                    @Override
//                    public void onResponse(Call<FilmDetail> call, Response<FilmDetail> response) {
//                        uiEventLiveData.setValue(response.body());
//                    }
//
//                    @Override
//                    public void onFailure(Call<FilmDetail> call, Throwable t) {
//                        Log.e("AVX", "err", t);
//                    }
//                });

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
