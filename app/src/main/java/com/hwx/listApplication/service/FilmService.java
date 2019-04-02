package com.hwx.listApplication.service;

import com.hwx.listApplication.model.FilmDetail;
import com.hwx.listApplication.model.ObjectListResponse;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface FilmService {

    @GET
    Observable<ObjectListResponse> fetchFilmsList(@Url String url);

    @GET
    Call<FilmDetail> fetchFilmDetail(@Url String url);

}
