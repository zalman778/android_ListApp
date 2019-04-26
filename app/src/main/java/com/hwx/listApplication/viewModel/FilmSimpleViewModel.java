package com.hwx.listApplication.viewModel;


import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hwx.listApplication.Configuration;
import com.hwx.listApplication.model.FilmDetail;
import com.hwx.listApplication.model.FilmSimple;
import com.hwx.listApplication.service.ApiFactory;
import com.hwx.listApplication.service.FilmService;
import com.livedata.SingleLiveEvent;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FilmSimpleViewModel extends BaseObservable {

    private FilmSimple filmSimple;

    private SingleLiveEvent uiEventLiveData;

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
        //System.out.println("got click to open "+filmSimple.id);


        FilmService filmOldService = ApiFactory.create();


        //checking via Call:
        filmOldService.fetchFilmDetail(Configuration.getMovieFullInfoUrl(filmSimple.id))
                .enqueue(new Callback<FilmDetail>() {
                    @Override
                    public void onResponse(Call<FilmDetail> call, Response<FilmDetail> response) {
                        //context.startActivity(FilmDetailActivity.fillDetail(v.getContext(), response.body()));
                        uiEventLiveData.setValue(response.body());
                    }

                    @Override
                    public void onFailure(Call<FilmDetail> call, Throwable t) {
                        int j = 1;
                    }
                });

    }

    // Loading Image using Glide Library.
    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String url){
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }

    public FilmSimpleViewModel(FilmSimple filmSimple) {
        this.filmSimple = filmSimple;
        uiEventLiveData = new SingleLiveEvent<FilmDetail>();
    }

    public void setFilmSimple(FilmSimple filmSimple) {
        this.filmSimple = filmSimple;
        notifyChange();
    }
}
