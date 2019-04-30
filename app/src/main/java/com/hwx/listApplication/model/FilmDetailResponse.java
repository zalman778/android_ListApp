package com.hwx.listApplication.model;

import java.util.Objects;

public class FilmDetailResponse {

    private FilmDetail filmDetail;

    public FilmDetail getFilmDetail() {
        return filmDetail;
    }

    public void setFilmDetail(FilmDetail filmDetail) {
        this.filmDetail = filmDetail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilmDetailResponse that = (FilmDetailResponse) o;
        return Objects.equals(filmDetail, that.filmDetail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(filmDetail);
    }
}
