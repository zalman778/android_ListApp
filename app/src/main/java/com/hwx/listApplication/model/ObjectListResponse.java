package com.hwx.listApplication.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ObjectListResponse {

    @SerializedName("results") private List<FilmSimple> filmSimpleList;

    public List<FilmSimple> getFilmSimpleList() { return filmSimpleList;}

    public void setFilmSimpleList(List<FilmSimple> filmSimpleList) { this.filmSimpleList = filmSimpleList ;}

}
