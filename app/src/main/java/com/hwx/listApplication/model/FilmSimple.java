package com.hwx.listApplication.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Objects;

public class FilmSimple implements Serializable {
    @SerializedName("id") private Long id;

    @SerializedName("vote_average") private Double voteAverage;

    @SerializedName("title") private String title;

    @SerializedName("popularity") private Double popularity;

    @SerializedName("poster_path") private String posterPath;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

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
