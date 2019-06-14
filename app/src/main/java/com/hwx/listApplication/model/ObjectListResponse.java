package com.hwx.listApplication.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Objects;

public class ObjectListResponse {

    @SerializedName("results") private List<FilmSimple> filmSimpleList;

    public List<FilmSimple> getFilmSimpleList() { return filmSimpleList;}

    public void setFilmSimpleList(List<FilmSimple> filmSimpleList) { this.filmSimpleList = filmSimpleList ;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ObjectListResponse that = (ObjectListResponse) o;
        return Objects.equals(filmSimpleList, that.filmSimpleList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(filmSimpleList);
    }
}
