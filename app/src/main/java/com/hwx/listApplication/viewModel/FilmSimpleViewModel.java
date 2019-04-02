package com.hwx.listApplication.viewModel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hwx.listApplication.AppController;
import com.hwx.listApplication.Configuration;
import com.hwx.listApplication.model.FilmDetail;
import com.hwx.listApplication.model.FilmSimple;
import com.hwx.listApplication.service.FilmService;
import com.hwx.listApplication.view.activity.FilmDetailActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FilmSimpleViewModel extends BaseObservable {

    private FilmSimple filmSimple;
    private Context context;

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
        System.out.println("got click to open "+filmSimple.id);


        AppController appController = AppController.create(context);
        FilmService filmOldService = appController.getFilmService();


        //checking via Call:
        filmOldService.fetchFilmDetail(Configuration.getMovieFullInfoUrl(filmSimple.id))
                .enqueue(new Callback<FilmDetail>() {
                    @Override
                    public void onResponse(Call<FilmDetail> call, Response<FilmDetail> response) {
                        context.startActivity(FilmDetailActivity.fillDetail(v.getContext(), response.body()));
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

    public FilmSimpleViewModel(FilmSimple filmSimple, Context context) {
        this.filmSimple = filmSimple;
        this.context = context;
    }

    public void setFilmSimple(FilmSimple filmSimple) {
        this.filmSimple = filmSimple;
        notifyChange();
    }
}
