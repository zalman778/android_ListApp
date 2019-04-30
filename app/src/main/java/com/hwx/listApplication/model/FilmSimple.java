package com.hwx.listApplication.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Objects;

public class FilmSimple implements Serializable {
    @SerializedName("id") public Long id;

    @SerializedName("vote_average") public Double voteAverage;

    @SerializedName("title") public String title;

    @SerializedName("popularity") public Double popularity;

    @SerializedName("poster_path") public String posterPath;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilmSimple that = (FilmSimple) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(voteAverage, that.voteAverage) &&
                Objects.equals(title, that.title) &&
                Objects.equals(popularity, that.popularity) &&
                Objects.equals(posterPath, that.posterPath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, voteAverage, title, popularity, posterPath);
    }
}
